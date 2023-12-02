package com.dfa.dfaserver.invest.controller.advice;


import com.dfa.dfaserver.invest.dto.error.ErrorDto;
import com.dfa.dfaserver.invest.exception.BaseException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class WalletControllerAdvice {

    @ExceptionHandler(BaseException.class)
    public ErrorDto handleBase(BaseException e, HttpServletResponse response) {
        response.setStatus(e.getStatus().value());
        log.warn("{} occurred with cause: {}", e.getClass().getSimpleName(), e.getMessage());
        return new ErrorDto(e.getMessage());
    }
}
