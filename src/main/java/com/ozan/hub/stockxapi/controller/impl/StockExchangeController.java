package com.ozan.hub.stockxapi.controller.impl;

import com.ozan.hub.stockxapi.controller.api.StockExchangeApi;
import com.ozan.hub.stockxapi.model.dto.IAddStock;
import com.ozan.hub.stockxapi.model.dto.IListStockExchange;
import com.ozan.hub.stockxapi.service.StockExchangeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stock-exchange")
public class StockExchangeController implements StockExchangeApi {

    private final StockExchangeService stockExchangeService;

    public StockExchangeController(StockExchangeService stockExchangeService) {
        this.stockExchangeService = stockExchangeService;
    }

    @Override
    public ResponseEntity<IListStockExchange.ListStockExchangeResp> listStockExchange(String name) {
        return new ResponseEntity<>(stockExchangeService.getStockExchange(name), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> addStock(String name, IAddStock.AddStockReq addStockReq) {
        return new ResponseEntity<>(stockExchangeService.addStockToStockExchange(addStockReq, name), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> removeStock(String name, Long stockId) {
        return new ResponseEntity<>(stockExchangeService.removeStockFromStockExchange(stockId, name), HttpStatus.OK);
    }
}
