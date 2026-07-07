package com.projectnik;

import com.projectnik.error.AcervoVazioException;
import com.projectnik.error.LivroDuplicadoException;
import com.projectnik.error.LivroNaoEncontradoException;
import com.projectnik.model.Autor;
import com.projectnik.model.Biblioteca;
import com.projectnik.model.Livro;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Biblioteca biblioteca =  new Biblioteca();

    static void main() {

        while (true) {

            try {
                exibirMenu();
                int o = sc.nextInt();
                sc.nextLine();
                System.out.println("-------------------------------");

                switch (o) {

                    case 1 -> addLivro();
                    case 2 -> removeLivro();
                    case 3 -> fazerEmprestimo();
                    case 4 -> listarEmprestimos();
                    case 5 -> listarAcervos();
                    case 6 -> biblioteca.salvarEmArquivo();
                    case 0 -> encerrar();
                    default -> System.out.println("Opção Inválida");
                }
            }catch (InputMismatchException e) {
                System.out.println("\\\\\\\\\\ Erro ao digitar, insira um valor válido! /////");
                sc.nextLine();
            }catch (Exception e){
                System.out.println("Erro critíco: " + e.getMessage());
            }
        }
    }

    private static void exibirMenu(){
        System.out.print("""
                        -------------------------------
                        Biblioteca online
                        
                        [1] Adicionar livro
                        [2] Remover livro
                        [3] Fazer emprestimo
                        [4] Listar os empréstimos
                        [5] Listar os livros
                        [6] Salvar Arquivo
                        [0] Encerrar programa
                        -------------------------------
                        """);
        System.out.print("Digite -> ");
    }

    static void addLivro() {
        boolean validacao = false;
        int opcUsuario = 0;
        System.out.print("Digite o nome do livro: ");
        String titulo = sc.nextLine();

        while (!validacao) {
            try {
                System.out.print("Existe um autor? [1] Sim / [2] Não\n -> ");
                opcUsuario = sc.nextInt();
                sc.nextLine();
                if (opcUsuario == 1 || opcUsuario == 2){
                    validacao = true;
                }else{
                    throw new IllegalArgumentException("Opção inválida! Digite as opções: (1) ou (2).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido. Tente novamente");
                sc.nextLine();
            } catch (IllegalArgumentException e){
                System.out.println("Erro: " + e.getMessage());
            } catch (Exception e){
                System.out.println("Erro inesperado no sistema. Tente novamente!");
            }
        }
        try {
            if (opcUsuario == 1) {
                System.out.print("Nome do autor: ");
                String nomeAutor = sc.nextLine();

                System.out.print("Nacionalidade do autor: ");
                String nacionalidadeAutor = sc.nextLine();

                Autor autor = new Autor(nomeAutor, nacionalidadeAutor);
                Livro livro = new Livro(titulo, autor);

                biblioteca.adicionarLivro(livro);

            } else {
                Livro livro = new Livro(titulo, null);
                biblioteca.adicionarLivro(livro);

            }
        }catch (LivroDuplicadoException e){
            System.out.println(e.getMessage());
        }
    }

    private static void removeLivro() {
        try {
            biblioteca.listarAcervo();

            System.out.print("Qual posição do livro que deseja remover?\n-> ");
            int p = sc.nextInt();
            sc.nextLine();

            biblioteca.removerPorIndice(p - 1);
            System.out.println("Livro removido com sucesso!");

        } catch (AcervoVazioException | LivroNaoEncontradoException exception) {
            System.out.println("Aviso: " + exception.getMessage());
        }catch (InputMismatchException e){
            System.out.println("Erro: Por favor, digite um número válido!");
            sc.nextLine();
        }
    }

    private static void fazerEmprestimo() {
        String usuario = "";
        String tituloTentado = "";
        try {
            biblioteca.listarAcervo();
            System.out.print("Qual livro deseja fazer o emprestimo?\n-> ");
            int e = sc.nextInt();
            sc.nextLine();

            System.out.print("Usuário: ");
            usuario = sc.nextLine();

            System.out.print("Data de Emprestimo: ");
            String dataEmprestimo = sc.nextLine();

            biblioteca.realizarEmprestimo(e - 1, usuario, dataEmprestimo);
            tituloTentado = "sucesso";
        }catch (AcervoVazioException acervoVazio){
            System.out.println("Aviso: " + acervoVazio.getMessage());
        }catch (RuntimeException e){
            tituloTentado = "falha: " + e.getMessage();
        } finally {
            System.out.println("[LOG] Tentativa de empréstimo - usuário: " +
                    (usuario.isEmpty() ? "não informado" : usuario)
                    + "| status: " + tituloTentado);
        }
    }

    private static  void listarEmprestimos() {
        if (biblioteca.emprestimoVazio()){
            System.out.println("Não há emprestimos para listar");
            return;
        }

        biblioteca.listarEmprestimos();
    }

    private static void listarAcervos() {
        try{
            biblioteca.listarAcervo();
        } catch (AcervoVazioException acervoVazio) {
            System.out.println("Aviso: " + acervoVazio.getMessage());
        }
    }

    private static void  encerrar() {
        System.out.println("Encerrando o programa!");
        System.exit(0);
    }
}
