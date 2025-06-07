package br.edu.uepb.sistemarestaurante.models;

public class Garcom {
    private String username;
    private String senha;

    public Garcom (String username, String senha){
        this.username = username;
        this.senha = senha;
    }

    public String getUsername(){
        return username;
    }

    public String getSenha(){
        return senha;
    }


}
