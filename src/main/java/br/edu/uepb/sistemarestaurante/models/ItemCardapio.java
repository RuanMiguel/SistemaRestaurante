package br.edu.uepb.sistemarestaurante.models;

import java.util.ArrayList;
import java.util.List;

public abstract class ItemCardapio {
    private String nome;
    private double preco;

    protected static List<ItemCardapio> itensCardapio = new ArrayList<>();

    public ItemCardapio(String nome, double preco){
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome(){
        return nome;
    }

    public double getPreco(){
        return preco;
    }

    @Override
    public String toString(){
        return nome + " | Preço: R$ " + preco;
    }

}
