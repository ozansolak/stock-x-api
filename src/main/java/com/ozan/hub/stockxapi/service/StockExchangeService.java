package com.ozan.hub.stockxapi.service;

import com.ozan.hub.stockxapi.model.dto.IAddStock;
import com.ozan.hub.stockxapi.model.dto.IListStockExchange;

public interface StockExchangeService {
    IListStockExchange.ListStockExchangeResp getStockExchange(String name);
    Boolean addStockToStockExchange(IAddStock.AddStockReq addStockReq, String name);
    Boolean removeStockFromStockExchange(Long stockId, String name);
}
