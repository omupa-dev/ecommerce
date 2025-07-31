package com.omupadev.comercioeletronico.apis;

import com.omupadev.comercioeletronico.entity.Produto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueApi {

    /*
    private final EstoqueRepository estoqueRepository;

    public EstoqueApi(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }
    */

    @PostMapping
    public void inserirProduto(@RequestBody Produto produto) {
        System.out.println("Inserindo o produto=" + produto);
    }

    @PutMapping("/{idProduto}")
    public void atualizarProduto(@PathVariable String idProduto) {
        System.out.println("Atualizando o produto de id=" + idProduto);
    }

    @GetMapping
    public List<Produto> listarTodos() {
        System.out.println("Listando todos os produtos");
        return null;
    }

    @DeleteMapping("/{idProduto}")
    public void deletarProduto(@PathVariable String idProduto) {
        System.out.println("Deletando o produto de id=" + idProduto);
    }
}
