package com.omupadev.comercioeletronico.repository;

import com.omupadev.comercioeletronico.entity.Produto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Integer> {
}
