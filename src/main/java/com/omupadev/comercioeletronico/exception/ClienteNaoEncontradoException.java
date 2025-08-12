package com.omupadev.comercioeletronico.exception;

import org.springframework.http.HttpStatus;

public class ClienteNaoEncontradoException extends ApplicationException {

    public ClienteNaoEncontradoException(String message) {
        super(message, "CLIENTE_NAO_ENCONTRADO", HttpStatus.NOT_FOUND);
    }

}
