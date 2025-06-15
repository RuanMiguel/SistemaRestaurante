package br.edu.uepb.sistemarestaurante.models;

import java.util.ArrayList;
import java.util.List;

public class Comanda {
    private Mesa mesa;
    private Garcom garcom;
    private String cpfNotaFiscal;
    private double valorTotal;
    private List<Pedido> pedidos;
    private static int qtd_instancias;
    private final int ID;

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

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public int getID() {
        return ID;
    }

    public Garcom getGarcom() {
        return garcom;
    }




}