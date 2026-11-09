package com.study.commerceStock.dto;


import java.util.UUID;

public record StockResultDTO(


        UUID orderId,


        String status
) {
}
