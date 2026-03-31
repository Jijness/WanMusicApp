from __future__ import annotations

from typing import Any

import torch.nn as nn


# TODO: replace with your real model classes used in training.
def build_model(model_name: str, num_classes: int, **kwargs: Any) -> nn.Module:
    raise NotImplementedError(
        'Model definition not set. Edit app/models/model_definitions.py::build_model '
        'to return the exact nn.Module architecture for your checkpoint.'
    )