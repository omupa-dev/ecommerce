package com.omupadev.comercioeletronico.repository;

import com.omupadev.comercioeletronico.entity.Produto;
import com.omupadev.comercioeletronico.exception.ProdutoNaoEncontradoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EstoqueRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final List<Produto> estoque = new ArrayList<>();

    public List<Produto> listarProdutos() {
        return estoque;
    }

    public Produto consultarEstoque(String idProduto) {
        for (Produto produto : estoque) {
            if (produto.getId().equals(idProduto)) {
                return produto;
            }
        }
        return null;
    }

    public void inserirItem(Produto produto) {
        estoque.add(produto);
    }

    public void deletar(String idProduto) {
        estoque.removeIf(produto -> produto.getId().equals(idProduto));
    }

    public void atualizar(Produto produto) {
        logger.info("Atualizando o produto de id={}", produto.getId());
        logger.debug("Atualizando o produto={}", produto);

        Produto produtoEncontrado = consultarEstoque(produto.getId());

        if (produtoEncontrado == null) {
            var errMsg = "Nao encontrado para atualizacao o produto=" + produto;
            logger.error(errMsg);
            throw new ProdutoNaoEncontradoException(errMsg, "CODIGO_PERSONALIZADO");
        }

        produtoEncontrado.setDescricao(produto.getDescricao());
        produtoEncontrado.setPreco(produto.getPreco());
        produtoEncontrado.setTitulo(produto.getTitulo());
        produtoEncontrado.setQtdEstoque(produto.getQtdEstoque());
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

    public void zerarEstoqueDeUmTitulo(Produto produto) {
        Produto produtoEncontrado = consultarEstoque(produto.getTitulo());

        if (produtoEncontrado == null)
            return;

        estoque.remove(produtoEncontrado);
    }

}
