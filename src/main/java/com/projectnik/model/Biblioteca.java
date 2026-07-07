package com.projectnik.model;

import com.projectnik.error.AcervoVazioException;
import com.projectnik.error.LivroDuplicadoException;
import com.projectnik.error.LivroNaoEncontradoException;

import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Emprestimo> emprestimos;
    private List<Livro> acervo;


    public Biblioteca(){
        this.emprestimos = new ArrayList<>();
        this.acervo = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) throws LivroDuplicadoException {
        if (livroExiste(livro.getTitulo())){
            throw new LivroDuplicadoException("O livro: '" + livro.getTitulo() + "' já existre!");
        }
        acervo.add(livro);
    }

    public void realizarEmprestimo(int escolha, String usuario, String data){
        Livro livroEscolhido = acervo.get(escolha);
        emprestimos.add(new Emprestimo(livroEscolhido, usuario, data));
    }

    public Livro buscarTitulo(String titulo) throws LivroNaoEncontradoException {
        for (Livro livro : acervo){
            if (livro.getTitulo().equalsIgnoreCase(titulo)){
                return livro;
            }
        }
        throw new LivroNaoEncontradoException("O livro: '" + titulo + "' não foi encontrado!");
    }

    private boolean livroExiste(String titulo){
        for (Livro livro : acervo){
            if (livro.getTitulo().equalsIgnoreCase(titulo)){
                return true;
            }
        }
        return false;
    }

    public void listarAcervo() throws AcervoVazioException{
        if (acervo.isEmpty()){
            throw new AcervoVazioException("Não livros para listar!");
        }

        for (int i = 0; i < this.acervo.size(); i++) {
            System.out.println("Livro [" + (i + 1) + "]:");
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

    public void removerPorIndice(int index) throws LivroNaoEncontradoException{
        if (index < 0 || index >= acervo.size()){
            throw new LivroNaoEncontradoException("Erro! Não existe esse livro para remover.");
        }
        acervo.remove(index);
    }

    public void salvarEmArquivo(){
        boolean sucesso = false;
        try(PrintWriter writer = new PrintWriter(new FileWriter("acervo.txt"))){
            for (Livro livro : acervo){

                String nomeAutor = (livro.getAutor() != null) ? livro.getAutor().getNome() : "Sem autor";
                String nacionalidade = (livro.getAutor() != null) ? livro.getAutor().getNacionalidade() : "N/A";

                writer.println(livro.getTitulo() + ";" + nomeAutor + ";" + nacionalidade);
            }
            sucesso = true;
        }catch (IOException e){
            System.out.println("Erro crítico ao salvar: " + e.getMessage());
        }finally {
            System.out.println("[LOG] Tentativa de salvamento finalizado. Status: " + (sucesso ? "Sucesso" : "Falha"));
        }
    }
}
