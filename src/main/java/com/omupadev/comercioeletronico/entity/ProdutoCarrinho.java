package com.omupadev.comercioeletronico.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "produto_carrinho")
public class ProdutoCarrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto_carrinho")
    @JsonIgnore
    private Integer idProdutoNoCarrinho;

    @Column(name = "id_carrinho")
    private Integer idCarrinho;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @Column(name = "qtd_produto_carrinho")
    private Integer qtdDesseProdutoNoCarrinho;

    public ProdutoCarrinho() {
    }

    public ProdutoCarrinho(Integer idCarrinho, Produto produto, Integer qtdDesseProdutoNoCarrinho) {
        this.idCarrinho = idCarrinho;
        this.produto = produto;
        this.qtdDesseProdutoNoCarrinho = qtdDesseProdutoNoCarrinho;
    }

    public Integer getIdProdutoNoCarrinho() {
        return idProdutoNoCarrinho;
    }

    public void setIdProdutoNoCarrinho(Integer idProdutoNoCarrinho) {
        this.idProdutoNoCarrinho = idProdutoNoCarrinho;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQtdDesseProdutoNoCarrinho() {
        return qtdDesseProdutoNoCarrinho;
    }

    public void setQtdDesseProdutoNoCarrinho(Integer qtdDesseProdutoNoCarrinho) {
        this.qtdDesseProdutoNoCarrinho = qtdDesseProdutoNoCarrinho;
    }
}
