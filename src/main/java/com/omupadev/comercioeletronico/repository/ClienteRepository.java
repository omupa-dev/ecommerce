package com.omupadev.comercioeletronico.repository;

import com.omupadev.comercioeletronico.entity.Cliente;
import com.omupadev.comercioeletronico.exception.ClienteNaoEncontradoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Random;

@Repository
public class ClienteRepository extends ReutilizavelRepository<Cliente> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void inserir(Cliente cliente) {
        logger.info("Inserindo cliente");
        logger.debug("Inserindo cliente={}", cliente);

        try {
            consultarPorEmail(cliente.getEmail());
        } catch (ClienteNaoEncontradoException ignored) {
        }

        cliente.setId(new Random().nextLong());
        adicionar(cliente);
    }

    private Cliente consultarPorEmail(String email) {
        for (Cliente cliente : dados) {
            if (cliente.getEmail().equals(email)) {
                return cliente;
            }
        }

        throw new ClienteNaoEncontradoException("Cliente não encontrado para o email=" + email);
    }

}
