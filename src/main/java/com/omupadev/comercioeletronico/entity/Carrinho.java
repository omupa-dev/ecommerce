package com.omupadev.comercioeletronico.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "carrinhos")
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrinho")
    private Integer idCarrinho;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private Cliente cliente;

    @OneToMany
    @JoinColumn(name = "id_carrinho")
    private Set<ProdutoCarrinho> produtos;

    public Carrinho() {
    }

    public Carrinho(Cliente cliente) {
        this.cliente = cliente;
    }

    public Carrinho(Integer idCarrinho, Cliente cliente) {
        this.idCarrinho = idCarrinho;
        this.cliente = cliente;
    }

    public Boolean ehDoCliente(Integer idCliente) {
        return cliente.getIdCliente().equals(idCliente);
    }

    public Integer getIdCarrinho() {
        return idCarrinho;
    }

    public void setIdCarrinho(Integer idCarrinho) {
        this.idCarrinho = idCarrinho;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<ProdutoCarrinho> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<ProdutoCarrinho> produtos) {
        this.produtos = produtos;
    }
}