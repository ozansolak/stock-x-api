package com.ozan.hub.stockxapi.controller.impl;

import com.ozan.hub.stockxapi.controller.api.StockApi;
import com.ozan.hub.stockxapi.model.dto.ICreateStock;
import com.ozan.hub.stockxapi.model.dto.IUpdatePrice;
import com.ozan.hub.stockxapi.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController implements StockApi {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @Override
    public ResponseEntity<ICreateStock.CreateStockResp> createStock(ICreateStock.CreateStockReq createStockReq) {
        return new ResponseEntity<>(stockService.createStock(createStockReq), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<IUpdatePrice.UpdatePriceResp> updateStockPrice(IUpdatePrice.UpdatePriceReq updatePriceReq) {
        return new ResponseEntity<>(stockService.updatePrice(updatePriceReq), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deleteStock(Long id) {
        return new ResponseEntity<>(stockService.deleteStock(id), HttpStatus.OK);
    }
}
