package com.omupadev.comercioeletronico.apis;

import com.omupadev.comercioeletronico.dto.CarrinhoDto;
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
    public Set<CarrinhoDto> listarTodos() {
        return carrinhoService.listarTodos();
    }

    @GetMapping("cliente/{idCliente}")
    @ResponseStatus(HttpStatus.OK)
    public CarrinhoDto consultarCarrinho(@PathVariable Integer idCliente) {
        return carrinhoService.consultarCarrinho(idCliente);
    }

    @PostMapping("cliente/{idCliente}/produto/{idProduto}/quantidade/{qtdProduto}")
    public void adicionarNoCarrinho(
            @PathVariable Integer idCliente,
            @PathVariable Integer idProduto,
            @PathVariable Integer qtdProduto
    ) {
        carrinhoService.adicionarNoCarrinho(idCliente, idProduto, qtdProduto);
    }

    @DeleteMapping("{idCarrinho}/produtos/{idProduto}")
    @ResponseStatus(HttpStatus.OK)
    public void removerDoCarrinho(
            @PathVariable Integer idCarrinho,
            @PathVariable Integer idProduto
    ) {
        carrinhoService.removerDoCarrinho(idCarrinho, idProduto);
    }
}
