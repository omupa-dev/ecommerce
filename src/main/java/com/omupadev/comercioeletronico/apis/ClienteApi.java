package com.omupadev.comercioeletronico.apis;

import com.omupadev.comercioeletronico.entity.Cliente;
import com.omupadev.comercioeletronico.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteApi {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ClienteRepository clienteRepository;

    public ClienteApi(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void inserir(@RequestBody Cliente cliente) {
        logger.info("Inserindo cliente");
        logger.debug("Inserindo cliente={}", cliente);
        clienteRepository.save(cliente);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Cliente> listarTodos() {
        logger.info("Listando clientes");
        return clienteRepository.findAll();
    }

}
