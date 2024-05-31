package com.ozan.hub.stockxapi.controller.api;

import com.ozan.hub.stockxapi.model.dto.ICreateStock;
import com.ozan.hub.stockxapi.model.dto.IUpdatePrice;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "stock-x-api", path = "/api/v1/stock")
public interface StockApi {

    @PostMapping(name = "Create Stock")
    ResponseEntity<ICreateStock.CreateStockResp> createStock(@Valid @RequestBody ICreateStock.CreateStockReq createStockReq);

    @PutMapping(name = "Update Stock Price")
    ResponseEntity<IUpdatePrice.UpdatePriceResp> updateStockPrice(@RequestBody IUpdatePrice.UpdatePriceReq updatePriceReq);

    @DeleteMapping(name = "Delete Stock")
    ResponseEntity<Boolean> deleteStock(@RequestParam("id") Long id);

}
