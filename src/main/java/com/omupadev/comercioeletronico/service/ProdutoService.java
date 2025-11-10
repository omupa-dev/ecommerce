package com.omupadev.comercioeletronico.service;

import com.omupadev.comercioeletronico.entity.Produto;
import com.omupadev.comercioeletronico.entity.ProdutoCarrinho;
import com.omupadev.comercioeletronico.exception.ProdutoNaoEncontradoException;
import com.omupadev.comercioeletronico.repository.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service // @Component @Repository
public class ProdutoService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void atualizar(Produto produto) {
        logger.info("Atualizando o produto de id={}", produto.getIdProduto());
        logger.debug("Atualizando o produto={}", produto);

        Produto produtoEncontrado = this.produtoRepository.findById(produto.getIdProduto())
                .orElseThrow(() -> {
                    var errMsg = "Nao encontrado para atualizacao o produto=" + produto;
                    logger.error(errMsg);
                    return new ProdutoNaoEncontradoException(errMsg, "CODIGO_PERSONALIZADO");
                });

        produtoEncontrado.setDescricao(produto.getDescricao());
        produtoEncontrado.setPreco(produto.getPreco());
        produtoEncontrado.setTitulo(produto.getTitulo());
        produtoEncontrado.setQtdEstoque(produto.getQtdEstoque());
    }

    public void darBaixaQtdProdutosVendidos(Set<ProdutoCarrinho> produtos) {
        logger.info("Inciando darBaixaQtdProdutosVendidos");
        for (ProdutoCarrinho produto : produtos) {
            logger.info("Executando baixa idProduto={} quantidade={}",
                    produto.getProduto().getIdProduto(),
                    produto.getQtdDesseProdutoNoCarrinho());

            int resultado = produtoRepository.darBaixaQtdProdutosVendidos(
                    produto.getProduto().getIdProduto(),
                    produto.getQtdDesseProdutoNoCarrinho());

            logger.info("Executada baixa idProduto={} quantidade={} resultado={}",
                    produto.getProduto().getIdProduto(),
                    produto.getQtdDesseProdutoNoCarrinho(),
                    resultado);
        }
    }

}
