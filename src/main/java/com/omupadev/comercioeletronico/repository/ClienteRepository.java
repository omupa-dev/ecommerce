package com.omupadev.comercioeletronico.repository;

import com.omupadev.comercioeletronico.entity.Cliente;
import com.omupadev.comercioeletronico.exception.ClienteNaoEncontradoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Repository
public class ClienteRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Set<Cliente> clientes = new HashSet<>();

    public void inserir(Cliente cliente) {
        logger.info("Inserindo cliente");
        logger.debug("Inserindo cliente={}", cliente);

        try {
            consultarPorEmail(cliente.getEmail());
        } catch (ClienteNaoEncontradoException ignored) {
        }

        cliente.setId(new Random().nextLong());
        clientes.add(cliente);
    }

    private Cliente consultarPorEmail(String email) {
        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equals(email)) {
                return cliente;
            }
        }

        throw new ClienteNaoEncontradoException("Cliente não encontrado para o email=" + email);
    }

    public Cliente consultarPorId(Long id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(id)) {
                return cliente;
            }
        }

        throw new ClienteNaoEncontradoException("Cliente não encontrado para o id=" + id);
    }

    public Set<Cliente> listarTodos() {
        logger.info("Listando clientes");
        return clientes;
    }

}
