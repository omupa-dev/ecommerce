package com.omupadev.comercioeletronico.repository;

import com.omupadev.comercioeletronico.entity.Carrinho;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class CarrinhoRepository {

    private final Set<Carrinho> carrinhos = new HashSet<>();

    public void adicionarNoCarrinho(Carrinho carrinho) {
        carrinhos.add(carrinho);
    }

    public Carrinho consultarCarrinho(Long idCliente) {
        for (Carrinho carrinho : carrinhos) {
            if (carrinho.ehDoCliente(idCliente)) {
                return carrinho;
            }
        }

        return null;
    }

    public Set<Carrinho> listarTodos() {
        return carrinhos;
    }
}
