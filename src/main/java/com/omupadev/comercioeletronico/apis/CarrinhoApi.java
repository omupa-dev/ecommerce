package com.omupadev.comercioeletronico.apis;

import com.omupadev.comercioeletronico.entity.Carrinho;
import com.omupadev.comercioeletronico.entity.Produto;
import com.omupadev.comercioeletronico.service.CarrinhoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/carrinhos")
public class CarrinhoApi {

    private final CarrinhoService carrinhoService;

    public CarrinhoApi(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<Carrinho> listarTodos() {
        return carrinhoService.listarTodos();
    }

    @PostMapping("/{idCliente}")
    public void adicionarNoCarrinho(
            @PathVariable Long idCliente,
            @RequestBody Produto produto
    ) {
        carrinhoService.adicionarNoCarrinho(idCliente, produto);
    }
}
