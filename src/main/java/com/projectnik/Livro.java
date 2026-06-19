package com.projectnik;

import java.util.UUID;

public class Livro {
    private String titulo;
    private String isbn;
    private Autor autor;

    public Livro(String titulo, Autor autor) {
        this.titulo = titulo;
        this.isbn = UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0, 13);
        this.autor = autor;
    }

    @Override
    public String toString() {
        String  autorDesconhecido = autor != null ? autor.toString() : "Autor desconhecido";
        return String.format("""
                Titulo: %s
                isbn: %s
                %s""",  titulo, isbn, autorDesconhecido);
    }
}
