package com.omupadev.comercioeletronico.service;

import com.omupadev.comercioeletronico.repository.CarrinhoRepository;
import com.omupadev.comercioeletronico.repository.ClienteRepository;
import com.omupadev.comercioeletronico.repository.ProdutoCarrinhoRepository;
import com.omupadev.comercioeletronico.repository.ProdutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CarrinhoServiceTest {

    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private CarrinhoRepository carrinhoRepository;
    @Mock
    private ProdutoRepository produtoRepository;
    @Mock
    private ProdutoCarrinhoRepository produtoCarrinhoRepository;

    @InjectMocks
    private CarrinhoService carrinhoService;

    @Test
    public void limparCarrinhoComSucesso() {
        Integer idCarrinho = 5;
        carrinhoService.limparCarrinho(idCarrinho);
        Mockito.verify(produtoCarrinhoRepository, Mockito.atLeastOnce()).deleteByIdCarrinho(idCarrinho);
    }

    @Test
    public void limparCarrinhoComIdCarrinhoNulo() {
        Integer idCarrinho = null;

        Assertions.assertThrows(
                RuntimeException.class,
                () -> carrinhoService.limparCarrinho(idCarrinho));

        Mockito.verify(produtoCarrinhoRepository, Mockito.never()).deleteByIdCarrinho(idCarrinho);
    }

    @Test
    public void limparCarrinhoComErroNoDeleteByIdCarrinho() {
        // Arrange > Organiza
        Integer idCarrinho = 5;

        Mockito.doThrow(new RuntimeException())
                .when(produtoCarrinhoRepository).deleteByIdCarrinho(idCarrinho);

        // Act > Ação
        Assertions.assertThrows(
                RuntimeException.class,
                () -> carrinhoService.limparCarrinho(idCarrinho));

        // Assert > Verfica
        Mockito.verify(produtoCarrinhoRepository, Mockito.atLeastOnce()).deleteByIdCarrinho(idCarrinho);
    }
}
