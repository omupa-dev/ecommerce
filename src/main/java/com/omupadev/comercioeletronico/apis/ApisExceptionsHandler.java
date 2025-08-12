package com.omupadev.comercioeletronico.apis;

import com.omupadev.comercioeletronico.exception.ApiError;
import com.omupadev.comercioeletronico.exception.ApplicationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApisExceptionsHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApiError> handlerProdutoNotFoundException(ApplicationException ex) {
        ApiError apiError = new ApiError(
                ex.getMessage(),
                ex.getCodigo()
        );

        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handlerProdutoNotFoundException(Exception ex) {
        ApiError apiError = new ApiError(ex.getMessage(), "INDEFINIDO");

        return ResponseEntity
                .internalServerError()
                .body(apiError);
    }
}
