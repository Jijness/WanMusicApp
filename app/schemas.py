from typing import List

from pydantic import BaseModel, Field


class PredictResponseItem(BaseModel):
    label: str
    score: float


class PredictResponse(BaseModel):
    predicted_label: str
    confidence: float
    top_k: List[PredictResponseItem]


class ErrorResponse(BaseModel):
    detail: str


class HealthResponse(BaseModel):
    status: str = Field(default='ok')
    model_name: str
    num_labels: int