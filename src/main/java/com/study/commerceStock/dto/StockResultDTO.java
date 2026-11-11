package com.study.commerceStock.dto;


import java.util.UUID;

public record StockResultDTO(

        UUID eventId,

        UUID orderId,

        String status
) {
}
