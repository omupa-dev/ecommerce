package com.omupadev.comercioeletronico.apis;

import com.omupadev.comercioeletronico.entity.Produto;
import com.omupadev.comercioeletronico.repository.EstoqueRepository;
import com.omupadev.comercioeletronico.repository.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueApi {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final EstoqueRepository estoqueRepository;
    private final ProdutoRepository produtoRepository;

    public EstoqueApi(EstoqueRepository estoqueRepository, ProdutoRepository produtoRepository) {
        this.estoqueRepository = estoqueRepository;
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public void inserirProduto(@RequestBody Produto produto) {
        logger.debug("Inserindo o produto={}", produto);
        produtoRepository.save(produto);
        estoqueRepository.inserirItem(produto);
    }

    @PutMapping
    public void atualizarProduto(@RequestBody Produto produto) {
        logger.info("Atualizando o produto de id={}", produto.getId());
        estoqueRepository.atualizar(produto);
    }

    @GetMapping
    public List<Produto> listarTodos() {
        logger.info("Listando todos os produtos");
        return estoqueRepository.listarProdutos();
    }

    @DeleteMapping("/{idProduto}")
    public void deletarProduto(@PathVariable String idProduto) {
        logger.info("Deletando o produto de id={}", idProduto);
        estoqueRepository.deletar(idProduto);
    }
}
