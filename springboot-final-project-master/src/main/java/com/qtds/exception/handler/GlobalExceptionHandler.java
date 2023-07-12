package com.qtds.exception.handler;

import com.qtds.exception.UniqueException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> globalException(Exception e) {
        log.error(e.getMessage());
        return buildResponseEntity(ApiError.error(e.getMessage()));
    }

    @ExceptionHandler(UniqueException.class)
    public ResponseEntity<ApiError> globalException(UniqueException e) {
        return buildResponseEntity(ApiError.error(BAD_REQUEST.value(), e.getMsg()));
    }

    /**
     * 统一返回
     */
    private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getStatus()));
    }
}
