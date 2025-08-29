package com.omupadev.comercioeletronico.repository;

import com.omupadev.comercioeletronico.entity.Identificadora;

import java.util.HashSet;
import java.util.Set;

public class ReutilizavelRepository<E extends Identificadora> {

    public final Set<E> dados = new HashSet<>();

    public void adicionar(E elemento) {
        dados.add(elemento);
    }

    public E consultarPorId(Long id) {
        for (E elemento : dados) {
            if (elemento.getId().equals(id)) {
                return elemento;
            }
        }

        return null;
    }

    public Set<E> listarTodos() {
        return dados;
    }

    public void remover(Long id) {
        dados.removeIf(elemento -> elemento.getId().equals(id));
    }

}
