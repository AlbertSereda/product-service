package com.market.product.jpa.repository;

import com.market.product.jpa.entity.PriceHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceHistoryRepository extends JpaRepository<PriceHistoryEntity, Long> {

}
