package br.edu.uepb.sistemarestaurante.models;

public class Prato extends ItemCardapio{
    private TipoPrato tipo;

    public Prato(String nome, double preco, TipoPrato tipo){
        super(nome, preco);
        this.tipo = tipo;
        itensCardapio.add(this);
    }
    public TipoPrato getTipo(){
        return tipo;
    }

}