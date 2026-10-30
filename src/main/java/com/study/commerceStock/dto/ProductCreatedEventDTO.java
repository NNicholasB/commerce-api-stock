package com.study.commerceStock.dto;

import java.util.UUID;

public record ProductCreatedEventDTO(

        UUID productId,
        Integer stock
) {
}
