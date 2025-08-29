package com.omupadev.comercioeletronico.entity;

public class Identificadora {

    private Long id;

    public Identificadora(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Identificadora{" +
                "id=" + id +
                '}';
    }
}
