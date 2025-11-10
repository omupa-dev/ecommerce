package com.omupadev.comercioeletronico.service;

import com.omupadev.comercioeletronico.dto.CarrinhoDto;
import com.omupadev.comercioeletronico.entity.Carrinho;
import com.omupadev.comercioeletronico.entity.Cliente;
import com.omupadev.comercioeletronico.entity.Produto;
import com.omupadev.comercioeletronico.entity.ProdutoCarrinho;
import com.omupadev.comercioeletronico.exception.ApplicationException;
import com.omupadev.comercioeletronico.exception.ProdutoNaoEncontradoException;
import com.omupadev.comercioeletronico.repository.CarrinhoRepository;
import com.omupadev.comercioeletronico.repository.ClienteRepository;
import com.omupadev.comercioeletronico.repository.ProdutoCarrinhoRepository;
import com.omupadev.comercioeletronico.repository.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Service
public class CarrinhoService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ClienteRepository clienteRepository;
    private final CarrinhoRepository carrinhoRepository;
    private final ProdutoRepository produtoRepository;
    private final ProdutoCarrinhoRepository produtoCarrinhoRepository;

    public CarrinhoService(
            ClienteRepository clienteRepository,
            CarrinhoRepository carrinhoRepository,
            ProdutoRepository produtoRepository,
            ProdutoCarrinhoRepository produtoCarrinhoRepository
    ) {
        this.clienteRepository = clienteRepository;
        this.carrinhoRepository = carrinhoRepository;
        this.produtoRepository = produtoRepository;
        this.produtoCarrinhoRepository = produtoCarrinhoRepository;
    }

    public Set<CarrinhoDto> listarTodos() {
        Set<CarrinhoDto> carrinhos = new HashSet<>();

        carrinhoRepository.findAll()
                .forEach(encontrados ->
                        carrinhos.add(new CarrinhoDto(
                                encontrados.getIdCarrinho(),
                                encontrados.getProdutos())
                        ));

        return carrinhos;
    }

    public CarrinhoDto consultarCarrinho(Integer idCliente) {
        Carrinho carrinhoEncontrado = carrinhoRepository.findByClienteIdCliente(idCliente);

        return new CarrinhoDto(
                carrinhoEncontrado.getIdCarrinho(),
                carrinhoEncontrado.getProdutos());
    }

    public void adicionarNoCarrinho(Integer idCliente, Integer idProduto, Integer qtdProduto) {
        Produto produtoEncontrado = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(
                        "Produto não encontrado com id=" + idProduto,
                        "PRODUTO_NAO_ENCONTRADO")
                );

        Carrinho carrinhoDoCliente = carrinhoRepository.findByClienteIdCliente(idCliente);
        if (carrinhoDoCliente == null) {
            carrinhoDoCliente = criarCarrinhoParaCliente(idCliente);
        }

        var produtoCarrinho = new ProdutoCarrinho(
                carrinhoDoCliente.getIdCarrinho(),
                produtoEncontrado,
                qtdProduto
        );

        produtoCarrinhoRepository.save(produtoCarrinho);
    }

    private Carrinho criarCarrinhoParaCliente(Integer idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ApplicationException(
                        "Cliente não encontrado para o id=" + idCliente,
                        "CLIENTE_NAO_ENCONTRADO",
                        HttpStatus.NOT_FOUND));

        var carrinhoCriado = new Carrinho(cliente);
        return carrinhoRepository.save(carrinhoCriado);
    }

    @Transactional
    public void removerDoCarrinho(Integer idCarrinho, Integer idProduto) {
        try {
            logger.info("Removendo do carrinho {} o produto {}", idCarrinho, idProduto);
            produtoCarrinhoRepository.deleteByProdutoAndCarrinho(idProduto, idCarrinho);
        } catch (Exception e) {
            logger.error("Erro ao remover do carrinho {} o produto {} errMsg={}", idCarrinho, idProduto, e.getMessage());
            throw e;
        }
    }

    public BigDecimal valorTotalCarrinho(Integer idCarrinho) {
        try {
            logger.info("Consultando valor total do idCarrinho={}", idCarrinho);
            BigDecimal valorTotal = produtoCarrinhoRepository.valorTotalDoCarrinho(idCarrinho);
            logger.info("Valor total do idCarrinho={} total={}", idCarrinho, valorTotal);
            return valorTotal;
        } catch (Exception e) {
            logger.error("Erro ao consultar valor total do idCarrinho={} errMsg={}", idCarrinho, e.getMessage());
            throw e;
        }
    }

    public void limparCarrinho(Integer idCarrinho) {
        try {
            logger.info("Limpando o idCarrinho={}", idCarrinho);
            produtoCarrinhoRepository.deleteByIdCarrinho(idCarrinho);
        } catch (Exception e) {
            logger.error("Erro ao limpar o idCarrinho={}", idCarrinho);
            throw e;
        }
    }
}
