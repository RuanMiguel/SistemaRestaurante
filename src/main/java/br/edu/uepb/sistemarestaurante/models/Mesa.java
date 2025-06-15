package br.edu.uepb.sistemarestaurante.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mesa {
    private static final Map<Integer, Mesa> mesas = criarMesas();
    private int numero;
    private int capacidade;
    private boolean ocupada;
    private Comanda comanda;

    public Mesa(int numero, int capacidade){
        this.numero = numero;
        this.capacidade = capacidade;
        this.ocupada = false;
    }

    private static Map<Integer, Mesa> criarMesas() { //*
        Map<Integer, Mesa> mapa = new HashMap<>();
        for (int i = 1; i <= 8; i++) {
            mapa.put(i, new Mesa(i, 6));
        }
        return mapa;
    }

    public static Map<Integer, Mesa> getMesas() {
        return mesas;
    }

    public int getNumero() {
        return numero;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public void ocupar() {
        if(!ocupada) {
            ocupada = true;
            System.out.println("A mesa " + numero + " está ocupada");
        }

    }

    public void liberar() {
        if(ocupada) {
            ocupada = false;
            System.out.println("A mesa " + numero + " está livre");
        }
    }

    public static void listarDisponiveis(List<Mesa> mesas) {
        for(Mesa m: mesas) {
            if(!m.isOcupada()) {
                System.out.println("Mesa " + m.getNumero());
            }
        }
    }
}
