package com.ozan.hub.stockxapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class StockAlreadyInStockExchangeException extends BaseException {
    public StockAlreadyInStockExchangeException(String message) {
        super(message);
    }
}
