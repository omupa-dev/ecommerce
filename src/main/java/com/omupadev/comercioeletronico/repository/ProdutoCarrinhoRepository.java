package com.omupadev.comercioeletronico.repository;

import com.omupadev.comercioeletronico.entity.ProdutoCarrinho;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProdutoCarrinhoRepository extends CrudRepository<ProdutoCarrinho, Integer> {

    @Modifying
    @Query("DELETE FROM ProdutoCarrinho pc " +
            "WHERE pc.produto.idProduto = :idProduto " +
            "AND pc.idCarrinho = :idCarrinho")
    void deleteByProdutoAndCarrinho(
            @Param("idProduto") Integer idProduto,
            @Param("idCarrinho") Integer idCarrinho);

    @NativeQuery(
            "SELECT SUM(p.preco * pc.qtd_produto_carrinho) AS total_do_produto " +
                    "FROM produtos p " +
                    "JOIN produto_carrinho pc ON pc.id_produto = p.id_produto " +
                    "WHERE pc.id_carrinho = 1 " +
                    "GROUP BY :idCarrinho")
    BigDecimal valorTotalDoCarrinho(@Param("idCarrinho") Integer idCarrinho);

    @Modifying
    @NativeQuery("DELETE FROM produto_carrinho WHERE id_carrinho = ?1")
    void deleteByIdCarrinho(Integer idCarrinho);
}
