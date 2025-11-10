package com.omupadev.comercioeletronico.repository;

import com.omupadev.comercioeletronico.entity.Carrinho;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoRepository extends CrudRepository<Carrinho, Integer> {

    Carrinho findByClienteIdCliente(Integer idCliente);

}
