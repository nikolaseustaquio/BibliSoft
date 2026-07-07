package com.projectnik.model;

public class Emprestimo {
    private Livro livro;
    private String nomeUsuario;
    private String dataEmprestimo;

    public Emprestimo(Livro livro, String nomeUsuario, String dataEmprestimo) {
        this.livro = livro;
        this.nomeUsuario = nomeUsuario;
        this.dataEmprestimo = dataEmprestimo;
    }

    @Override
    public String toString() {
        return String.format("""
                %s
                Usuário: %s
                Data de Emprestimo: %s
                """,  livro, nomeUsuario, dataEmprestimo);
    }
}
