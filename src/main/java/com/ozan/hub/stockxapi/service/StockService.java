package com.ozan.hub.stockxapi.service;

import com.ozan.hub.stockxapi.model.dto.ICreateStock;
import com.ozan.hub.stockxapi.model.dto.IUpdatePrice;

public interface StockService {
    ICreateStock.CreateStockResp createStock(ICreateStock.CreateStockReq createStockReq);
    IUpdatePrice.UpdatePriceResp updatePrice(IUpdatePrice.UpdatePriceReq updatePriceReq);
    Boolean deleteStock(Long id);
}
