package com.omupadev.comercioeletronico.service;

import com.omupadev.comercioeletronico.entity.Carrinho;
import com.omupadev.comercioeletronico.entity.Cliente;
import com.omupadev.comercioeletronico.entity.Produto;
import com.omupadev.comercioeletronico.exception.ApplicationException;
import com.omupadev.comercioeletronico.repository.CarrinhoRepository;
import com.omupadev.comercioeletronico.repository.ClienteRepository;
import com.omupadev.comercioeletronico.repository.EstoqueRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CarrinhoService {

    private final ClienteRepository clienteRepository;
    private final CarrinhoRepository carrinhoRepository;
    private final EstoqueRepository estoqueRepository;

    public CarrinhoService(
            ClienteRepository clienteRepository,
            CarrinhoRepository carrinhoRepository,
            EstoqueRepository estoqueRepository
    ) {
        this.clienteRepository = clienteRepository;
        this.carrinhoRepository = carrinhoRepository;
        this.estoqueRepository = estoqueRepository;
    }

    public Set<Carrinho> listarTodos() {
        return carrinhoRepository.listarTodos();
    }

    public Carrinho consultarCarrinho(Long idCliente) {
        return carrinhoRepository.consultarCarrinho(idCliente);
    }

    public void adicionarNoCarrinho(Long idCliente, Produto produto) {
        validarExistenciaDoProduto(produto);

        Carrinho carrinhoDoCliente = carrinhoRepository.consultarCarrinho(idCliente);
        if (carrinhoDoCliente == null) {
            carrinhoDoCliente = criarCarrinhoParaCliente(idCliente);
        }

        carrinhoDoCliente.adicionarProduto(produto);
        carrinhoRepository.adicionarNoCarrinho(carrinhoDoCliente);
    }

    private void validarExistenciaDoProduto(Produto produto) {
        Produto produtoEncontrado = estoqueRepository.consultarEstoque(produto.getId());

        if (produtoEncontrado == null) {
            throw new ApplicationException(
                    "Produto encontrado com o id=" + produto.getId(),
                    "PRODUTO_NAO_ENCONTRADO_PARA_ADD_CARRINHO",
                    HttpStatus.BAD_REQUEST);
        }
    }

    private Carrinho criarCarrinhoParaCliente(Long idCliente) {
        Cliente cliente = clienteRepository.consultarPorId(idCliente);

        if (cliente == null) {
            throw new ApplicationException(
                    "Cliente não encontrado para o id=" + idCliente,
                    "CLIENTE_NAO_ENCONTRADO",
                    HttpStatus.NOT_FOUND);
        }

        return new Carrinho(cliente);
    }

    public void removerDoCarrinho(Long idCliente, String idProduto) {
        carrinhoRepository.removerDoCarrinho(idCliente, idProduto);
    }
}
