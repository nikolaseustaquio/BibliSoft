package com.projectnik;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Biblioteca biblioteca =  new Biblioteca();

    static void main() {

        while (true) {

            try {

                System.out.print("""
                        -------------------------------
                        Biblioteca online
                        
                        [1] Adicionar livro
                        [2] Remover livro
                        [3] Fazer emprestimo
                        [4] Listar os empréstimos
                        [5] Listar os livros
                        [6] Listar tudo
                        [0] Encerrar programa
                        -------------------------------
                        """);
                System.out.print("Digite -> ");
                int o = sc.nextInt();
                sc.nextLine();
                System.out.println("-------------------------------");

                switch (o) {

                    case 1 -> addLivro();
                    case 2 -> removeLivro();
                    case 3 -> fazerEmprestimo();
                    case 4 -> listarEmprestimos();
                    case 5 -> listarAcervos();
                    case 6 -> listarTudo();
                    case 0 -> encerrar();
                    default -> System.out.println("Opção Inválida");
                }
            }catch (InputMismatchException e) {
                System.out.println("\\\\\\\\\\ Erro ao digitar, insira um valor válido! /////");
                sc.nextLine();
            }
        }
    }

    private static void addLivro() {
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

        if (opcUsuario == 1) {
            System.out.print("Nome do autor: ");
            String nomeAutor = sc.nextLine();

            System.out.print("Nacionalidade do autor: ");
            String nacionalidadeAutor = sc.nextLine();

            Autor autor = new Autor(nomeAutor, nacionalidadeAutor);
            Livro livro = new Livro(titulo, autor);
            biblioteca.adicionarLivro(livro);

        }else {
            Livro livro = new Livro(titulo, null);
            biblioteca.adicionarLivro(livro);

        }
    }

    private static void removeLivro() {
        if (biblioteca.acervoVazio()){
            System.out.println("Não há livros para remover");
            return;
        }
        biblioteca.listarAcervo();
        System.out.print("Qual posição do livro que deseja remover?\n-> ");
        int p =  sc.nextInt();
        sc.nextLine();

        biblioteca.removerLivro(p-1);
        System.out.println("Livro removido com sucesso!");
    }

    private static void fazerEmprestimo() {
        String usuario = "";
        String tituloTentado = "";
        try {
            if (biblioteca.acervoVazio()) {
                throw new RuntimeException("Acervo vazio");
            }
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
        }catch (RuntimeException e){
            tituloTentado = "falha: " + e.getMessage();
        }finally {
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

        biblioteca.listarAcervo();
    }

    private static void listarAcervos() {
        if (biblioteca.acervoVazio()){
            System.out.println("Não livros para listar");
            return;
        }

        biblioteca.listarAcervo();
    }

    private static void listarTudo() {
        if (biblioteca.acervoVazio() && biblioteca.emprestimoVazio()){
            System.out.println("Não há livros e emprestimos para listar");
            return;
        }
        biblioteca.listarAcervo();
        biblioteca.listarEmprestimos();
    }

    private static void  encerrar() {
        System.out.println("Encerrando o programa!");
        System.exit(0);
    }
}
