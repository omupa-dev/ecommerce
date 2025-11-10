package com.omupadev.comercioeletronico.repository;

import com.omupadev.comercioeletronico.entity.Produto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Integer> {

    @Modifying
    @Query("UPDATE Produto p " +
            "SET p.qtdEstoque = p.qtdEstoque - ?2 " +
            "WHERE p.id = ?1")
    int darBaixaQtdProdutosVendidos(Integer idProduto, Integer qtdVendida);
}
