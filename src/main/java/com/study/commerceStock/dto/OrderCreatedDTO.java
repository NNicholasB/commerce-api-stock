package com.study.commerceStock.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderCreatedDTO(
        @NotNull(message = "OrderId is required")
        UUID orderId ,

        @NotNull(message = "OrderId is required")
        UUID userId,

        @Positive(message ="total must be greater than zero" )
        BigDecimal total,

        String status,

        @NotEmpty(message = "Order must contain at least one item")
        List<OrderItemDTO> items
) {
}
