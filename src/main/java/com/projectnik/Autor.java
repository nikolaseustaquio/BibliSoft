package com.projectnik;

public class Autor {
    private String nome;
    private String nacionalidade;

    public Autor(String nome, String nacionalidade) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
    }

    @Override
    public String toString() {

        return String.format("""
                Autor: %s
                Nacionalidade: %s
                """, nome, nacionalidade);
    }
}
