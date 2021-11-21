package com.gus.ponto.pontoServidor.model;

public class UsuarioModel {
    private int id;
    private String login;
    private String senha;
    private String nome;
    private String chavegerente;
    private boolean gerente;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getChavegerente() {
        return chavegerente;
    }

    public void setChavegerente(String chavegerente) {
        this.chavegerente = chavegerente;
    }

    public boolean isGerente() {
        return gerente;
    }

    public void setGerente(boolean gerente) {
        this.gerente = gerente;
    }
    
    
    
}
