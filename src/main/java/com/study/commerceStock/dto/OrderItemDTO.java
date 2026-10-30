package com.study.commerceStock.dto;

import java.util.UUID;

public record OrderItemDTO(
        UUID product,
        Integer quantity
) {
}
