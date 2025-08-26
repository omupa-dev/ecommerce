package com.omupadev.comercioeletronico.entity;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Carrinho {

    private final Long id = new Random().nextLong();
    private Cliente cliente;
    private Set<Produto> produtos = new HashSet<>();

    public Carrinho(Cliente cliente) {
        this.cliente = cliente;
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public Boolean ehDoCliente(Long idCliente) {
        return cliente.getId().equals(idCliente);
    }

    public void limparCarrinho() {
        this.produtos = new HashSet<>();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<Produto> getProdutos() {
        return produtos;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Carrinho{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", produtos=" + produtos +
                '}';
    }
}
