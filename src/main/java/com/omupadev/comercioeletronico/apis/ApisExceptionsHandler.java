package com.omupadev.comercioeletronico.apis;

import com.omupadev.comercioeletronico.exception.ApiError;
import com.omupadev.comercioeletronico.exception.ApplicationException;
import com.omupadev.comercioeletronico.exception.ProdutoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApisExceptionsHandler {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ApiError handlerProdutoNotFoundException(ProdutoNaoEncontradoException ex) {
        return new ApiError(
                ex.getMessage(),
                ex.getCodigo()
        );
    }

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
