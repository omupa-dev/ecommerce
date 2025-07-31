package com.omupadev.comercioeletronico.repository;

import com.omupadev.comercioeletronico.entity.Produto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

// DAO: Data Access Object

@Repository
public class EstoqueRepository {

    private final List<Produto> estoque = new ArrayList<>();

    public List<Produto> listarProdutos() {
        return estoque;
    }

    public Produto consultarEstoque(String titulo) {
        for (Produto produto : estoque) {
            if (produto.getTitulo().equals(titulo)) {
                return produto;
            }
        }
        return null;
    }

    public void inserirItem(Produto produto) {
        estoque.add(produto);
    }

    public Integer contarItens(String titulo) {
        Produto produtoEncontrado = consultarEstoque(titulo);

        if (produtoEncontrado == null) {
            return 0;
        }

        return produtoEncontrado.getQtdEstoque();
    }

    public Integer totalItens() {
        return estoque.size();
    }

    public void removerUmExemplar(Produto produto) {
        Produto produtoEncontrado = consultarEstoque(produto.getTitulo());

        if (produtoEncontrado == null)
            return;

        produtoEncontrado.removerItens(1);
    }

    public void zerarEstoqueDeUmTitulo(Produto produto) {
        Produto produtoEncontrado = consultarEstoque(produto.getTitulo());

        if (produtoEncontrado == null)
            return;

        estoque.remove(produtoEncontrado);
    }

}
