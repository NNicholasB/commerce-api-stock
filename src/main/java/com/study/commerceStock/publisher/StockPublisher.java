package com.study.commerceStock.publisher;


import com.study.commerceStock.dto.StockResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class StockPublisher {

    private final RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE="commerce.exchange";

    public void publishStockReserved(StockResultDTO result){
        StockResultDTO event=new StockResultDTO(result.orderId(),"RESERVED");

        rabbitTemplate.convertAndSend(EXCHANGE,"stock.reserved",event);
    }

    public void publishStockReject(StockResultDTO result){
        StockResultDTO event=new StockResultDTO(result.orderId(),"REJECT");

        rabbitTemplate.convertAndSend(EXCHANGE,"stock.reject",event);
    }
}
