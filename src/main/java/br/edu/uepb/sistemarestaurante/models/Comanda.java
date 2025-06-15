package br.edu.uepb.sistemarestaurante.models;

import java.util.ArrayList;
import java.util.List;

public class Comanda {
    private static int qtd_instancias;
    private final int ID;
    private Mesa mesa;
    private Garcom garcom;
    private List<Pedido> pedidos;

    public Comanda() {
        this.pedidos = new ArrayList<>();
        qtd_instancias++;
        this.ID = qtd_instancias;
    }

    public Comanda(Mesa mesa, Garcom garcom) {
        this.mesa = mesa;
        this.garcom = garcom;

        this.pedidos = new ArrayList<>();
        qtd_instancias++;
        this.ID = qtd_instancias;
    }

    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
        System.out.println("Pedido adicionado à comanda");
    }

    public void listarPedido() {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido adicionado");
            return;
        }

        for (Pedido pedido : pedidos) {
            System.out.println(pedido.toString() + "\n");
        }
    }

    public double caucularTotal(){
        double total = 0;
        for(Pedido pedido : pedidos){
            total += pedido.calcularTotal();
        }
        return total;
    }

    //GETTERS

    public int getID() {
        return ID;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public Garcom getGarcom() {
        return garcom;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    /*MESMA COISA DE listarPedido():

    @Override
    public String toString() {
        return "Comanda{" +
                "pedidos=" + pedidos +
                '}';
    }*/
}