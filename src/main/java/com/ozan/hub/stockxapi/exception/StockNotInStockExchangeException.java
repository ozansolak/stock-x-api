package com.ozan.hub.stockxapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class StockNotInStockExchangeException extends BaseException {
    public StockNotInStockExchangeException(String message) {
        super(message);
    }
}
