package com.omupadev.comercioeletronico.repository;

import com.omupadev.comercioeletronico.entity.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
    Cliente findByEmail(String email);
}
