package com.omupadev.comercioeletronico.service;

import com.omupadev.comercioeletronico.entity.Carrinho;
import com.omupadev.comercioeletronico.exception.ApplicationException;
import com.omupadev.comercioeletronico.repository.EstoqueRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CheckoutService {

    private final CarrinhoService carrinhoService;
    private final PagamentoService pagamentoService;
    private final EstoqueRepository estoqueRepository;
    private final EnvioService envioService;

    public CheckoutService(
            CarrinhoService carrinhoService,
            PagamentoService pagamentoService,
            EstoqueRepository estoqueRepository, EnvioService envioService) {
        this.carrinhoService = carrinhoService;
        this.pagamentoService = pagamentoService;
        this.estoqueRepository = estoqueRepository;
        this.envioService = envioService;
    }

    public void fecharCompra(Long idCliente) {
        final Carrinho carrinho = this.carrinhoService.consultarCarrinho(idCliente);
        this.validaExistenciaProdutosNoCarrinho(idCliente, carrinho);

        final BigDecimal valorTotal = this.carrinhoService.valorTotalCarrinho(idCliente);
        final String idDoPagamento = this.pagamentoService.solicitarPagamento(valorTotal, idCliente);

        if (!this.pagamentoService.verificarPagamento(idDoPagamento)) {
            throw new ApplicationException(
                    "Erro no pagamento de id=" + idDoPagamento,
                    "ERRO_PAGAMENTO",
                    HttpStatus.PRECONDITION_FAILED);
        }
        this.estoqueRepository.darBaixaNosProdutos(carrinho.getProdutos());
        this.envioService.enviarProdutos(carrinho.getId());
        carrinho.limparCarrinho();
    }

    private void validaExistenciaProdutosNoCarrinho(Long idCliente, Carrinho carrinho) {
        if (carrinho == null || carrinho.getProdutos().isEmpty()) {
            throw new ApplicationException(
                    "Erro no pagamento carrinho vazio idCliente=" + idCliente,
                    "ERRO_CARRINHO_VAZIO",
                    HttpStatus.PRECONDITION_FAILED);
        }
    }
}
