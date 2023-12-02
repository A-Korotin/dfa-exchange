package com.dfa.dfaserver.invest.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {
    private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;
    public NotFoundException() {
        super(STATUS);
    }

    public NotFoundException(String format, Object... args) {
        super(format.formatted(args), STATUS);
    }


}
