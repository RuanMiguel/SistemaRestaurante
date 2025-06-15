package br.edu.uepb.sistemarestaurante.models;

abstract class Funcionario {
    private final String id;
    private final String senha;

    protected Funcionario(String id, String senha){
        this.id = id;
        this.senha = senha;
    }

    public abstract void atualizarStatus(Pedido pedido);

    public String getId() {
        return id;
    }

    public String getSenha() {
        return senha;
    }

}
