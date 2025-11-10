package com.omupadev.comercioeletronico.service;

import com.omupadev.comercioeletronico.dto.CarrinhoDto;
import com.omupadev.comercioeletronico.exception.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class CheckoutService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CarrinhoService carrinhoService;
    private final PagamentoService pagamentoService;
    private final EnvioService envioService;
    private final ProdutoService produtoService;

    public CheckoutService(
            CarrinhoService carrinhoService,
            PagamentoService pagamentoService,
            EnvioService envioService, ProdutoService produtoService) {
        this.carrinhoService = carrinhoService;
        this.pagamentoService = pagamentoService;
        this.envioService = envioService;
        this.produtoService = produtoService;
    }

    @Transactional
    public void fecharCompra(Integer idCliente) {
        try {
            final CarrinhoDto carrinho = this.carrinhoService.consultarCarrinho(idCliente);
            this.validaExistenciaProdutosNoCarrinho(idCliente, carrinho);
            validarQuantidadeDeCadaProdutoDisponivel(idCliente, carrinho);

            final BigDecimal valorTotal = this.carrinhoService.valorTotalCarrinho(carrinho.idCarrinho());
            final String idDoPagamento = this.pagamentoService.solicitarPagamento(valorTotal, idCliente);

            if (!this.pagamentoService.verificarPagamento(idDoPagamento)) {
                throw new ApplicationException(
                        "Erro no pagamento de id=" + idDoPagamento,
                        "ERRO_PAGAMENTO",
                        HttpStatus.PRECONDITION_FAILED);
            }

            produtoService.darBaixaQtdProdutosVendidos(carrinho.produtos());
            this.envioService.enviarProdutos(carrinho.idCarrinho());
            carrinhoService.limparCarrinho(carrinho.idCarrinho());
        } catch (Exception e) {
            logger.error("Erro fecharCompra idCliente={} errMsg={}", idCliente, e.getMessage());
            throw e;
        }
    }

    private void validaExistenciaProdutosNoCarrinho(Integer idCliente, CarrinhoDto carrinho) {
        if (carrinho == null || carrinho.produtos().isEmpty()) {
            throw new ApplicationException(
                    "Erro no pagamento carrinho vazio idCliente=" + idCliente,
                    "ERRO_CARRINHO_VAZIO",
                    HttpStatus.PRECONDITION_FAILED);
        }
    }

    private void validarQuantidadeDeCadaProdutoDisponivel(Integer idCliente, CarrinhoDto carrinho) {
        // TODO: tarefa de casa
    }
}
