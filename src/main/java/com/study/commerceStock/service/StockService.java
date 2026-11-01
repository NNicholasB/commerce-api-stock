package com.study.commerceStock.service;

import com.study.commerceStock.dto.OrderCreatedDTO;
import com.study.commerceStock.dto.OrderItemDTO;
import com.study.commerceStock.dto.ProductCreatedEventDTO;
import com.study.commerceStock.entity.ProductStock;
import com.study.commerceStock.publisher.StockPublisher;
import com.study.commerceStock.repository.ProductStockRepository;
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
            List<ProductStock> stocks= new ArrayList<>();
            for (OrderItemDTO item : orderCreatedDTO.items()){
                ProductStock stock= stockRepository
                        .findByProductId(item.productId());
                if (stock==null){
                    throw new IllegalStateException("product not found"+item.productId());
                }
                if (stock.getQuantity()<item.quantity()){
                    stockPublisher.publishStockReject(orderCreatedDTO.orderId());
                    return;
                }
                stocks.add(stock);
            }
                for (int i=0;i<stocks.size();i++){
                    ProductStock stock=stocks.get(i);
                    Integer quantity=orderCreatedDTO.items().get(i).quantity();

                    stock.setQuantity(stock.getQuantity()-quantity);
                }
                stockRepository.saveAll(stocks);
                stockPublisher.publishStockReserved(orderCreatedDTO.orderId());
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
