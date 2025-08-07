package com.omupadev.comercioeletronico.exception;

import org.springframework.http.HttpStatus;

public class ApplicationException extends RuntimeException {

    private final String codigo;
    private final HttpStatus httpStatus;

    public ApplicationException(String mensagem, String codigo, HttpStatus httpStatus) {
        super(mensagem);
        this.codigo = codigo;
        this.httpStatus = httpStatus;
    }

    public String getCodigo() {
        return codigo;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
