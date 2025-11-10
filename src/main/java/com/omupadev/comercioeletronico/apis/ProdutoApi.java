package com.omupadev.comercioeletronico.apis;

import com.omupadev.comercioeletronico.entity.Produto;
import com.omupadev.comercioeletronico.repository.ProdutoRepository;
import com.omupadev.comercioeletronico.service.ProdutoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoApi {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ProdutoRepository produtoRepository;
    private final ProdutoService produtoService;

    public ProdutoApi(ProdutoRepository produtoRepository,
                      ProdutoService produtoService) {
        this.produtoRepository = produtoRepository;
        this.produtoService = produtoService;
    }

    @PostMapping
    public void inserirProduto(@RequestBody Produto produto) {
        logger.debug("Inserindo o produto={}", produto);
        produtoRepository.save(produto);
    }

    @PutMapping
    public void atualizarProduto(@RequestBody Produto produto) {
        logger.info("Atualizando o produto de id={}", produto.getIdProduto());
        produtoService.atualizar(produto);
    }

    @GetMapping
    public List<Produto> listarTodos() {
        logger.info("Listando todos os produtos");
        List<Produto> produtos = new ArrayList<>();
        produtoRepository.findAll().forEach(produtos::add);
        return produtos;
    }

    @DeleteMapping("/{idProduto}")
    public void deletarProduto(@PathVariable Integer idProduto) {
        logger.info("Deletando o produto de id={}", idProduto);
        produtoRepository.deleteById(idProduto);
    }
}
