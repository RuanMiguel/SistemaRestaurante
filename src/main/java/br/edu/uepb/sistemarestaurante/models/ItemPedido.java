package br.edu.uepb.sistemarestaurante.models;

import br.edu.uepb.sistemarestaurante.models.ItemCardapio;

public class ItemPedido {
    private ItemCardapio item;
    private int qtd;
    private String obs;

    //CONSTRUCTOR
    public ItemPedido(ItemCardapio item, int qtd, String obs) { //usar na função adicionarItem da classe Pedido
        this.item = item;
        this.qtd = qtd;
        this.obs = obs;
    }

    //GETTERS E SETTER
    public ItemCardapio getItem() {
        return item;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    //METODOS
    @Override
    public String toString() {
        return qtd + "x " + item + " - Obs.: " + obs + ";";
    }

    //public double CalcularSubtotal(){}
}
