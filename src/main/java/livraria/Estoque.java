package livraria;

import java.util.ArrayList;
import java.util.List;

public class Estoque {

    private final List<Livro> todosLivros = new ArrayList<>();

    public Livro consultarEstoque(String titulo) {
        for (Livro livro : todosLivros) {
            if (livro.getTitulo().equals(titulo)) {
                return livro;
            }
        }
        return null;
    }

    public void inserirItem(Livro livro) {
        todosLivros.add(livro);
    }

    public Integer contarItens(String titulo) {
        Livro livroEncontrado = consultarEstoque(titulo);

        if (livroEncontrado == null) {
            return 0;
        }

        return livroEncontrado.getQuantidadeEmEstoque();
    }

    public Integer totalItens() {
        return todosLivros.size();
    }

    public void removerUmExemplar(Livro livro) {
        Livro livroEncontrado = consultarEstoque(livro.getTitulo());

        if (livroEncontrado == null)
            return;

        livroEncontrado.removerItens(1);
    }

    public void zerarEstoqueDeUmTitulo(Livro livro) {
        Livro livroEncontrado = consultarEstoque(livro.getTitulo());

        if (livroEncontrado == null)
            return;

        todosLivros.remove(livroEncontrado);
    }

}
