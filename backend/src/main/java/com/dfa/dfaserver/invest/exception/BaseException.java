package com.dfa.dfaserver.invest.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends RuntimeException {
    private final HttpStatus status;

    public BaseException(HttpStatus status) {
        super();
        this.status = status;
    }

    public BaseException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
