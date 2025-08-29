package com.omupadev.comercioeletronico.repository;

import com.omupadev.comercioeletronico.entity.Carrinho;
import org.springframework.stereotype.Repository;

@Repository
public class CarrinhoRepository extends ReutilizavelRepository<Carrinho> {

    public Carrinho consultarCarrinhoPorIdCliente(Long idCliente) {
        for (Carrinho carrinho : dados) {
            if (carrinho.ehDoCliente(idCliente)) {
                return carrinho;
            }
        }

        return null;
    }

    public void removerDoCarrinho(Long idCliente, String idProduto) {
        Carrinho carrinhoEncontrado = consultarCarrinhoPorIdCliente(idCliente);

        carrinhoEncontrado.getProdutos()
                .removeIf(produto -> produto.getId().equals(idProduto));

    }
}
