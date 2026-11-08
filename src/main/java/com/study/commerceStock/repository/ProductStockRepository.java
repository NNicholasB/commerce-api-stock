package com.study.commerceStock.repository;

import com.study.commerceStock.entity.ProductStock;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ProductStockRepository extends JpaRepository<ProductStock,UUID> {
    ProductStock findByProductId(UUID productId);

    boolean existsByProductId(UUID uuid);


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM Stock s WHERE s.productId = :productId")
    Optional<ProductStock> findByProductIdForUpdate(@Param("productId") UUID productId);
}
