package com.ozan.hub.stockxapi.repository;

import com.ozan.hub.stockxapi.model.data.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock findStockByNameAndDeleted(String name, Boolean deleted);
}
