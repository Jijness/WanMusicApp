from __future__ import annotations

from fastapi import FastAPI, File, HTTPException, Query, UploadFile

from app.config import settings
from app.schemas import HealthResponse, PredictResponse
from app.service import MusicTaggerService

app = FastAPI(title=settings.app_name, version=settings.app_version)

service: MusicTaggerService | None = None
startup_error: str | None = None


@app.on_event('startup')
def startup() -> None:
    global service, startup_error
    try:
        service = MusicTaggerService()
        startup_error = None
    except Exception as exc:
        service = None
        startup_error = str(exc)


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

    content = await file.read()
    if not content:
        raise HTTPException(status_code=400, detail='Empty audio file.')

    try:
        result = service.predict_from_bytes(content, top_k=top_k)
        return PredictResponse(**result)
    except ValueError as exc:
        raise HTTPException(status_code=400, detail=str(exc)) from exc
    except Exception as exc:
        raise HTTPException(status_code=500, detail=f'Inference error: {exc}') from exc