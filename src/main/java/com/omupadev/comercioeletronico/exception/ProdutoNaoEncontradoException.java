package com.omupadev.comercioeletronico.exception;

public class ProdutoNaoEncontradoException extends RuntimeException {

    private final String codigo;

    public ProdutoNaoEncontradoException(String mensagem, String codigo) {
        super(mensagem);
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

}
