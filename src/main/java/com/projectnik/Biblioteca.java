package com.projectnik;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> emprestimos;
    private List<Livro> acervo;

    public Biblioteca(){
        this.emprestimos = new ArrayList<Livro>();
        this.acervo = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro){
        this.acervo.add(livro);
    }

    public void removerLivro(int opcao){
        this.acervo.remove(opcao);
    }

    public void realizarEmprestimo(int escolha, String usuario, String data){
        Livro livroEscolhido = acervo.get(escolha);
        emprestimos.add(livroEscolhido);
    }

    public boolean acervoVazio(){
        return acervo.isEmpty();
    }

    public void listarAcervo(){
        if(acervoVazio()){
            System.out.println("Nenhum livro encontrado");
            return;
        }

        for (int i = 0; i < this.acervo.size(); i++) {
            System.out.println("Livro [" + (i+1) + "]:");
            System.out.println(this.acervo.get(i));
        }
    }

    public boolean emprestimoVazio(){
        return emprestimos.isEmpty();
    }

    public void listarEmprestimos(){
        if (emprestimoVazio()){
            System.out.println("Nenhum empréstimo encontrado!");
            return;
        }

        this.emprestimos.forEach(System.out::println);
    }
}
