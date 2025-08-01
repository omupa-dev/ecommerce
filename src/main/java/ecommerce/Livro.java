package ecommerce;

import java.math.BigDecimal;

public class Livro extends Produto {

    private Integer anoLancamento;

    public Livro(String titulo, String descricao, BigDecimal preco, Integer qtdEstoque, Integer anoLancamento) {
        super(titulo, descricao, preco, qtdEstoque);
        this.anoLancamento = anoLancamento;
    }

    public boolean ehAntigo() {
        return anoLancamento < 2000;
    }

    public void exibirInformacoes() {
        System.out.println(getTitulo() + " " + anoLancamento);
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "anoLancamento=" + anoLancamento +
                '}';
    }
}
