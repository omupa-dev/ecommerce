package com.omupadev.comercioeletronico.exception;

import org.springframework.http.HttpStatus;

public class ProdutoNaoEncontradoException extends ApplicationException {

    public ProdutoNaoEncontradoException(String mensagem, String codigo) {
        super(mensagem, codigo, HttpStatus.NOT_FOUND);
    }

}
