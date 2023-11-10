package com.dfa.dfaserver.invest.controller.order.advice;

import com.dfa.dfaserver.invest.exception.BaseException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class OrderControllerAdvice {

    @ExceptionHandler(BaseException.class)
    public Map<String, String> handleBase(BaseException e, HttpServletResponse response) {
        response.setStatus(e.getStatus().value());
        log.warn("{} occurred with cause: {}", e.getClass().getSimpleName(), e.getMessage());
        // TODO: 10.11.2023 create error dto 
        return Collections.singletonMap("message", e.getMessage());
    }
}
