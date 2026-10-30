package com.study.commerceStock.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record OrderItemDTO(
        @NotNull(message = "ProductID is required")
        UUID productId,
        @Positive(message ="Quantity must be greater than zero" )
        Integer quantity
) {
}
