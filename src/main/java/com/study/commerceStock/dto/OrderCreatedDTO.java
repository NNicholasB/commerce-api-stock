package com.study.commerceStock.dto;

import java.util.List;
import java.util.UUID;

public record OrderCreatedDTO(
        UUID order ,
        List<OrderItemDTO> items
) {
}
