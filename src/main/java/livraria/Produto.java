package livraria;

import java.math.BigDecimal;

public class Produto {

    private String titulo;
    private String descricao;
    private BigDecimal preco;
    private Integer qtdEstoque;

    public Produto(String titulo, String descricao, BigDecimal preco, Integer qtdEstoque) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.preco = preco;
        this.qtdEstoque = qtdEstoque;
    }

    public void removerItens(Integer quantidade) {
        this.qtdEstoque -= quantidade;
    }
}
