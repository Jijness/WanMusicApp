from pydantic import Field
from pydantic_settings import BaseSettings, SettingsConfigDict


class Settings(BaseSettings):
    model_config = SettingsConfigDict(env_file='.env', env_file_encoding='utf-8', extra='ignore')

    app_name: str = 'Wan Music Classifier API'
    app_version: str = '1.0.0'

    model_name: str = Field(default='efficientnet')
    model_path: str = Field(default='trained_models/best_efficientnet.pth')
    training_results_path: str = Field(default='trained_models/training_results.json')

    sample_rate: int = Field(default=22050)
    n_mels: int = Field(default=128)
    n_fft: int = Field(default=2048)
    hop_length: int = Field(default=512)
    segment_seconds: float = Field(default=15.0)

    top_k: int = Field(default=10)

    # If true, try loading 3 models and run weighted ensemble.
    enable_ensemble: bool = Field(default=False)


settings = Settings()
