package com.study.commerceStock.repository;

import com.study.commerceStock.entity.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductStockRepository extends JpaRepository<UUID, ProductStock> {
}
