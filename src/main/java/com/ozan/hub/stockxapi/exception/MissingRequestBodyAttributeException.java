package com.ozan.hub.stockxapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MissingRequestBodyAttributeException extends BaseException{
    public MissingRequestBodyAttributeException(String message) {
        super(message);
    }
}
