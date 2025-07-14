package ecommerce;

import java.util.ArrayList;
import java.util.List;

public class Estoque {

    private final List<Produto> todosLivros = new ArrayList<>();

    public Produto consultarEstoque(String titulo) {
        for (Produto produto : todosLivros) {
            if (produto.getTitulo().equals(titulo)) {
                return produto;
            }
        }
        return null;
    }

    public void inserirItem(Produto produto) {
        todosLivros.add(produto);
    }

    public Integer contarItens(String titulo) {
        Produto produtoEncontrado = consultarEstoque(titulo);

        if (produtoEncontrado == null) {
            return 0;
        }

        return produtoEncontrado.getQtdEstoque();
    }

    public Integer totalItens() {
        return todosLivros.size();
    }

    public void removerUmExemplar(Produto produto) {
        Produto produtoEncontrado = consultarEstoque(produto.getTitulo());

        if (produtoEncontrado == null)
            return;

        produtoEncontrado.removerItens(1);
    }

    public void zerarEstoqueDeUmTitulo(Produto produto) {
        Produto produtoEncontrado = consultarEstoque(produto.getTitulo());

        if (produtoEncontrado == null)
            return;

        todosLivros.remove(produtoEncontrado);
    }

}
