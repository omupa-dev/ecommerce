package com.omupadev.comercioeletronico.dto;

import com.omupadev.comercioeletronico.entity.ProdutoCarrinho;

import java.util.Set;

public record CarrinhoDto(
        Integer idCarrinho,
        Set<ProdutoCarrinho> produtos
) {
}
