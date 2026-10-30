package com.study.commerceStock.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record ProductCreatedEventDTO(
        @NotNull(message = "ProductId is required")
        UUID productId,
        @Positive(message = "Stock must be greater than zero")
        Integer stock
) {
}
