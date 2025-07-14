package livraria;

import java.math.BigDecimal;

public class App {

    public static void main(String[] args) {
        Cliente anderson = new Cliente(67, "Anderson", "04587965487", TipoNotificacao.EMAIL);

        Notificacao notificacao = new NotificarViaWhatsapp();
        notificacao.notificarCompra(anderson);

    }

    private static void exercicioEstoque() {
        // 1.
        Livro brancaDeNeve = new Livro(
                "Branca de neve",
                "Livro infantil",
                BigDecimal.valueOf(18.75),
                7,
                1714);

        Livro pinoquio = new Livro(
                "Pinoquio",
                "Livro infantil",
                BigDecimal.valueOf(18.75),
                1874,
                15);

        Livro alibaba = new Livro("Alibaba",
                "Livro infantil",
                BigDecimal.valueOf(18.75),
                1874,
                13);

        // 2.
        Estoque estoque = new Estoque();

        // 3.
        estoque.inserirItem(brancaDeNeve);
        estoque.inserirItem(pinoquio);
        estoque.inserirItem(alibaba);

        // 4.
        Integer qtdTitulosDisponiveis = estoque.totalItens();
        System.out.printf("\nA livraria tem %d titulos disponiveis", qtdTitulosDisponiveis);

        // 5.
        estoque.zerarEstoqueDeUmTitulo(alibaba);

        // 6.
        qtdTitulosDisponiveis = estoque.totalItens();
        System.out.printf("\nA livraria tem %d titulos disponiveis", qtdTitulosDisponiveis);

        // 7.
        Integer qtdBrancaDeNeve = estoque.contarItens("Branca de neve");
        System.out.printf("\nO livro %s tem %d exemplares", brancaDeNeve.getTitulo(), qtdBrancaDeNeve);

        // 8.
        estoque.removerUmExemplar(brancaDeNeve);

        // 9.
        qtdBrancaDeNeve = estoque.contarItens("Branca de neve");
        System.out.printf("\nO livro %s tem %d exemplares", brancaDeNeve.getTitulo(), qtdBrancaDeNeve);
    }
}
