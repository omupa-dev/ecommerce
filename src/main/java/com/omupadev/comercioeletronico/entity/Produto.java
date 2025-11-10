package com.omupadev.comercioeletronico.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Integer idProduto;

    private String titulo;
    private String descricao;
    private BigDecimal preco;

    @Column(name = "qtd_estoque")
    private Integer qtdEstoque;

    public Produto() {
    }

    public Produto(Integer idProduto, String titulo, String descricao, BigDecimal preco, Integer qtdEstoque) {
        this.idProduto = idProduto;
        this.titulo = titulo;
        this.descricao = descricao;
        this.preco = preco;
        this.qtdEstoque = qtdEstoque;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id='" + idProduto + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", qtdEstoque=" + qtdEstoque +
                '}';
    }
}
