package com.omupadev.comercioeletronico.apis;

import com.omupadev.comercioeletronico.service.CheckoutService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkouts")
public class CheckoutApi {

    private final CheckoutService checkoutService;

    public CheckoutApi(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/clientes/{idCliente}")
    public void fecharCompra(@PathVariable Integer idCliente) {
        this.checkoutService.fecharCompra(idCliente);
    }
}
