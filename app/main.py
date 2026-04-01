from __future__ import annotations

from pathlib import Path

from fastapi import FastAPI, File, HTTPException, Query, UploadFile
from fastapi.responses import FileResponse
from fastapi.staticfiles import StaticFiles

from app.config import settings
from app.schemas import HealthResponse, PredictResponse
from app.service import MusicTaggerService

app = FastAPI(title=settings.app_name, version=settings.app_version)
static_dir = Path(__file__).resolve().parent / 'static'
app.mount('/static', StaticFiles(directory=static_dir), name='static')

service: MusicTaggerService | None = None
startup_error: str | None = None
MAX_UPLOAD_BYTES = 30 * 1024 * 1024
ALLOWED_EXTENSIONS = {'.wav', '.mp3', '.flac', '.m4a', '.ogg'}


@app.on_event('startup')
def startup() -> None:
    global service, startup_error
    try:
        service = MusicTaggerService()
        startup_error = None
    except Exception as exc:
        service = None
        startup_error = str(exc)


@app.get('/')
def ui() -> FileResponse:
    return FileResponse(static_dir / 'index.html')


@app.get('/health', response_model=HealthResponse)
def health() -> HealthResponse:
    if startup_error:
        raise HTTPException(status_code=503, detail=f'Service not ready: {startup_error}')
    assert service is not None
    return HealthResponse(model_name=settings.model_name, num_labels=len(service.labels))


@app.get('/labels')
def labels() -> dict:
    if startup_error:
        raise HTTPException(status_code=503, detail=f'Service not ready: {startup_error}')
    assert service is not None
    return {'labels': service.labels}


@app.post('/predict', response_model=PredictResponse)
async def predict(
    file: UploadFile = File(...),
    top_k: int = Query(default=settings.top_k, ge=1, le=30),
) -> PredictResponse:
    if startup_error:
        raise HTTPException(status_code=503, detail=f'Service not ready: {startup_error}')
    assert service is not None

    filename = file.filename or ''
    ext = Path(filename).suffix.lower()
    content_type = (file.content_type or '').lower()
    if not content_type.startswith('audio/') and ext not in ALLOWED_EXTENSIONS:
        raise HTTPException(status_code=400, detail='Unsupported file type. Please upload an audio file.')

    content = await file.read(MAX_UPLOAD_BYTES + 1)
    if len(content) > MAX_UPLOAD_BYTES:
        raise HTTPException(status_code=413, detail='Audio file is too large.')
    if not content:
        raise HTTPException(status_code=400, detail='Empty audio file.')

    try:
        result = service.predict_from_bytes(content, top_k=top_k)
        return PredictResponse(**result)
    except ValueError as exc:
        raise HTTPException(status_code=400, detail=str(exc)) from exc
    except Exception as exc:
        raise HTTPException(status_code=500, detail=f'Inference error: {exc}') from exc
