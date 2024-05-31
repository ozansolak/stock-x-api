package com.ozan.hub.stockxapi.repository;

import com.ozan.hub.stockxapi.model.data.StockExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StockExchangeRepository extends JpaRepository<StockExchange, Long> {

    @Query("SELECT se FROM StockExchange se LEFT JOIN FETCH se.stocks WHERE se.name=:name AND se.deleted=FALSE")
    StockExchange findByName(@Param("name") String name);

}
