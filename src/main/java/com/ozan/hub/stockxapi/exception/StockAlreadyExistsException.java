package com.ozan.hub.stockxapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.ALREADY_REPORTED)
public class StockAlreadyExistsException extends BaseException {
    public StockAlreadyExistsException(String message) {
        super(message);
    }
}
