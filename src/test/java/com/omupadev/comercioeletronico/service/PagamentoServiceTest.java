package com.omupadev.comercioeletronico.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class PagamentoServiceTest {

    @InjectMocks
    private PagamentoService pagamentoService;

    @Test
    public void solicitarPagamentoComSucesso() {
        String retorno = pagamentoService.solicitarPagamento(BigDecimal.valueOf(543.05), 10);
        Assertions.assertNotNull(retorno);
    }
}