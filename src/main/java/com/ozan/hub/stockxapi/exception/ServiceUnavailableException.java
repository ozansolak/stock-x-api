package com.ozan.hub.stockxapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE)
public class ServiceUnavailableException extends BaseException {
    public ServiceUnavailableException(String message) {
        super(message);
    }
}
