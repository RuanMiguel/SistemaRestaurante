package br.edu.uepb.sistemarestaurante.models;

import java.util.ArrayList;
import java.util.List;
/**
 * Classe que representa uma comanda em um restaurante.
 * Cada comanda está associada a uma mesa e a um garçom, e pode conter vários pedidos.
 * Possui métodos para adicionar pedidos, listar pedidos e calcular o total da comanda.
 *
 * @author Marcella Viana da Silva Lins
 * @author Ruan Miguel
 */

public class Comanda {
    private static int qtd_instancias;
    private static List<Comanda> comandas = new ArrayList<>();
    private final int ID;
    private Mesa mesa;
    private Garcom garcom;
    private List<Pedido> pedidos;

    /**
     * Construtor padrão que inicializa uma comanda sem mesa e garçom.
     * A comanda recebe um ID único e é adicionada à lista de comandas.
     */

    public Comanda() {
        this.pedidos = new ArrayList<>();
        qtd_instancias++;
        this.ID = qtd_instancias;
        comandas.add(this);
    }
    /**
     * Construtor que inicializa uma comanda com uma mesa e um garçom específicos.
     * A comanda recebe um ID único e é adicionada à lista de comandas.
     *
     * @param mesa   A mesa associada à comanda
     * @param garcom O garçom associado à comanda
     */

    public Comanda(Mesa mesa, Garcom garcom) {
        this.mesa = mesa;
        this.garcom = garcom;

        this.pedidos = new ArrayList<>();
        qtd_instancias++;
        this.ID = qtd_instancias;
    }
    /**
     * Método estático para obter todas as comandas criadas.
     *
     * @return Lista de todas as comandas
     */
    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
        System.out.println("Pedido adicionado à comanda");
    }
    /**
     * Método para listar todos os pedidos associados à comanda.
     * Se não houver pedidos, exibe uma mensagem informando que nenhum pedido foi adicionado.
     */

    public void listarPedido() {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido adicionado");
            return;
        }

        for (Pedido pedido : pedidos) {
            System.out.println(pedido.toString() + "\n");
        }
    }
    /**
     * Método para calcular o total da comanda, somando os totais de todos os pedidos.
     *
     * @return O total da comanda
     */

    public double calcularTotal(){
        double total = 0;
        for(Pedido pedido : pedidos){
            total += pedido.calcularTotal();
        }
        return total;
    }



    /**
     * Método estático para obter todas as comandas criadas.
     *
     * @return Lista de todas as comandas
     */
    public int getID() {
        return ID;
    }

    /**
     * Método estático para obter todas as comandas criadas.
     *
     * @return Lista de todas as comandas
     */
    public Mesa getMesa() {
        return mesa;
    }

    /**
     * Método estático para obter todas as comandas criadas.
     *
     * @return Lista de todas as comandas
     */
    public Garcom getGarcom() {
        return garcom;
    }

    /**
     * Método estático para obter todas as comandas criadas.
     *
     * @return Lista de todas as comandas
     */
    public List<Pedido> getPedidos() {
        return pedidos;
    }


}
