package com.study.commerceStock.publisher;


import com.study.commerceStock.dto.StockResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class StockPublisher {

    private final RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE="commerce.exchange";

    public void publishStockReserved(UUID orderId){
        StockResultDTO event=new StockResultDTO(orderId,"RESERVED");

        rabbitTemplate.convertAndSend(EXCHANGE,"stock.reserved",event);
    }

    public void publishStockReject(UUID orderId){
        StockResultDTO event=new StockResultDTO(orderId,"REJECT");

        rabbitTemplate.convertAndSend(EXCHANGE,"stock.reject",event);
    }
}
