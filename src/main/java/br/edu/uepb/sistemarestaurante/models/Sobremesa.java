package br.edu.uepb.sistemarestaurante.models;

public class Sobremesa extends ItemCardapio{

    public Sobremesa(String nome, double preco){
        super(nome, preco);
        itensCardapio.add(this);
    }
}

