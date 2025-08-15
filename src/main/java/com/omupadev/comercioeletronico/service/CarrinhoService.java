package com.omupadev.comercioeletronico.service;

import com.omupadev.comercioeletronico.entity.Carrinho;
import com.omupadev.comercioeletronico.entity.Cliente;
import com.omupadev.comercioeletronico.entity.Produto;
import com.omupadev.comercioeletronico.exception.ApplicationException;
import com.omupadev.comercioeletronico.repository.CarrinhoRepository;
import com.omupadev.comercioeletronico.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CarrinhoService {

    private final ClienteRepository clienteRepository;
    private final CarrinhoRepository carrinhoRepository;

    public CarrinhoService(ClienteRepository clienteRepository, CarrinhoRepository carrinhoRepository) {
        this.clienteRepository = clienteRepository;
        this.carrinhoRepository = carrinhoRepository;
    }

    public Set<Carrinho> listarTodos() {
        return carrinhoRepository.listarTodos();
    }

    public void adicionarNoCarrinho(Long idCliente, Produto produto) {
        Carrinho carrinhoDoCliente = carrinhoRepository.consultarCarrinho(idCliente);

        if (carrinhoDoCliente == null) {
            carrinhoDoCliente = criarCarrinhoParaCliente(idCliente);
        }

        carrinhoDoCliente.adicionarProduto(produto);
        carrinhoRepository.adicionarNoCarrinho(carrinhoDoCliente);
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
}
