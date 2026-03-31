from __future__ import annotations

import io

import librosa
import numpy as np


class AudioPreprocessor:
    def __init__(
        self,
        sample_rate: int,
        n_mels: int,
        n_fft: int,
        hop_length: int,
        segment_seconds: float,
    ) -> None:
        self.sample_rate = sample_rate
        self.n_mels = n_mels
        self.n_fft = n_fft
        self.hop_length = hop_length
        self.segment_samples = int(sample_rate * segment_seconds)

    def to_log_mel(self, audio_bytes: bytes) -> np.ndarray:
        with io.BytesIO(audio_bytes) as buf:
            y, _ = librosa.load(buf, sr=self.sample_rate, mono=True)

        if y.size == 0:
            raise ValueError('Empty or invalid audio input.')

        y = self._fit_length(y)
        mel = librosa.feature.melspectrogram(
            y=y,
            sr=self.sample_rate,
            n_mels=self.n_mels,
            n_fft=self.n_fft,
            hop_length=self.hop_length,
            power=2.0,
        )
        log_mel = librosa.power_to_db(mel, ref=np.max)
        return log_mel.astype(np.float32)

    def _fit_length(self, y: np.ndarray) -> np.ndarray:
        if y.shape[0] > self.segment_samples:
            return y[: self.segment_samples]
        if y.shape[0] < self.segment_samples:
            pad = self.segment_samples - y.shape[0]
            return np.pad(y, (0, pad), mode='constant')
        return y