package com.study.commerceStock.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record StockResultDTO(

        @NotNull(message = "OrderId is required")
        UUID orderId,

        @NotNull(message = "status is required")
        String status
) {
}
