package com.study.commerceStock.listener;

import com.study.commerceStock.dto.OrderCreatedDTO;
import com.study.commerceStock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderListener {

    private final StockService service;

    @RabbitListener(queues = "commerce.stock")
    public void receive (OrderCreatedDTO orderCreatedDTO){
        service.reserveStock(orderCreatedDTO);
    }
}
