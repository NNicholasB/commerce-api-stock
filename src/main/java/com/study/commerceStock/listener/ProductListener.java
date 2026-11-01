package com.study.commerceStock.listener;


import com.study.commerceStock.dto.ProductCreatedEventDTO;
import com.study.commerceStock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductListener {

    private final StockService service;

    @RabbitListener(queues = "commerce.stock.products")
    public void receiveProduct(ProductCreatedEventDTO productCreatedEventDTO){
        service.createStock(productCreatedEventDTO);

    }
}
