package com.ozan.hub.stockxapi.controller.api;

import com.ozan.hub.stockxapi.model.dto.IAddStock;
import com.ozan.hub.stockxapi.model.dto.IListStockExchange;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "stock-x-api", path = "/api/v1/stock-exchange")
public interface StockExchangeApi {

    @GetMapping(name = "List Stock Exchange with Stocks", path = "/{name}")
    ResponseEntity<IListStockExchange.ListStockExchangeResp> listStockExchange(@PathVariable("name") String name);

    @PutMapping(name = "Add Stock to Stock Exchange", path = "/{name}")
    ResponseEntity<Boolean> addStock(@PathVariable("name") String name, @RequestBody IAddStock.AddStockReq addStockReq);

    @DeleteMapping(name = "Remove Stock from Stock Exchange", path = "/{name}")
    ResponseEntity<Boolean> removeStock(@PathVariable("name") String name, @RequestParam("stockId") Long stockId);

}
