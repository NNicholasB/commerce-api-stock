package com.study.commerceStock.service;

import com.study.commerceStock.dto.OrderCreatedDTO;
import com.study.commerceStock.dto.OrderItemDTO;
import com.study.commerceStock.dto.ProductCreatedEventDTO;
import com.study.commerceStock.entity.ProductStock;
import com.study.commerceStock.publisher.StockPublisher;
import com.study.commerceStock.repository.ProductStockRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

        private final StockPublisher stockPublisher;
        private final ProductStockRepository stockRepository;

    @Transactional
    public void reserveStock(OrderCreatedDTO orderCreatedDTO){
               for (OrderItemDTO item : orderCreatedDTO.items()){
                ProductStock stock= stockRepository
                        .findByProductIdForUpdate(item.productId()).orElseThrow(()-> new EntityNotFoundException("Stock not found for product: "+item.productId()));
                if (stock.getAvailableQuantity()<item.quantity()){
                    throw new IllegalStateException("Insufficient stock for product: "+item.productId());
                }
                stock.setReservedQuantity(stock.getReservedQuantity()-item.quantity());

            }

    }


    public void createStock(ProductCreatedEventDTO productCreatedEventDTO) {
              if (stockRepository.existsByProductId(productCreatedEventDTO.productId())){
                    return;
              }

                ProductStock stock = new ProductStock();
                stock.setProductId(productCreatedEventDTO.productId());
                stock.setQuantity(productCreatedEventDTO.stock());

                stockRepository.save(stock);
    }
}
