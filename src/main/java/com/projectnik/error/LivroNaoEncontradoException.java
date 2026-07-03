package com.projectnik.error;

public class LivroNaoEncontrado extends Exception{
    public LivroNaoEncontrado(String mensagem){
        super(mensagem);
    }
}
