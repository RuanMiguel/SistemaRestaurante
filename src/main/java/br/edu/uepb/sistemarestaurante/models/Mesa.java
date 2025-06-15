package br.edu.uepb.sistemarestaurante.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Representa uma mesa do restaurante.
 * Cada mesa possui número, capacidade, estado de ocupação e uma comanda associada.
 * A classe mantém um mapa estático com todas as mesas criadas (de 1 a 8).
 *
 * @author Marcella Viana, Ruan Miguel
 *
 */
public class Mesa {
    /**
     * Mapa estático que armazena todas as mesas do restaurante,
     * indexadas pelo número da mesa.
     */

    private static final Map<Integer, Mesa> mesas = criarMesas();

    /** Número identificador da mesa. */
    private int numero;

    /** Capacidade máxima de pessoas na mesa. */
    private int capacidade;

    /** Indica se a mesa está ocupada ou não. */
    private boolean ocupada;

    /** Comanda associada à mesa, pode ser nula caso não haja pedido. */
    private Comanda comanda;

    /**
     * Construtor que cria uma mesa com número e capacidade definidos.
     * Inicialmente, a mesa não está ocupada.
     *
     * @param numero Número da mesa.
     * @param capacidade Capacidade máxima de pessoas.
     */
    public Mesa(int numero, int capacidade){
        this.numero = numero;
        this.capacidade = capacidade;
        this.ocupada = false;
    }

    /**
     * Cria e inicializa o mapa estático com as mesas do restaurante.
     * São criadas 8 mesas numeradas de 1 a 8, cada uma com capacidade 6.
     *
     * @return Mapa contendo as mesas criadas.
     */
    private static Map<Integer, Mesa> criarMesas() {
        Map<Integer, Mesa> mapa = new HashMap<>();
        for (int i = 1; i <= 8; i++) {
            mapa.put(i, new Mesa(i, 6));
        }
        return mapa;
    }

    /**
     * Retorna o mapa estático com todas as mesas.
     *
     * @return Mapa das mesas indexado por número.
     */
    public static Map<Integer, Mesa> getMesas() {
        return mesas;
    }

    /**
     * Retorna o número da mesa.
     *
     * @return Número da mesa.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Verifica se a mesa está atualmente ocupada.
     *
     * @return true se a mesa estiver ocupada, false caso contrário.
     */
    public boolean isOcupada() {
        return ocupada;
    }

    /**
     * Retorna a comanda associada à mesa.
     *
     * @return Comanda da mesa, ou null se não houver comanda.
     */
    public Comanda getComanda() {
        return comanda;
    }

    /**
     * Define a comanda associada à mesa.
     *
     * @param comanda Comanda a ser associada.
     */
    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    /**
     * Marca a mesa como ocupada e exibe uma mensagem no console.
     * Caso a mesa já esteja ocupada, não realiza alteração.
     */
    public void ocupar() {
        if(!ocupada) {
            ocupada = true;
            System.out.println("A mesa " + numero + " está ocupada");
        }
    }

    /**
     * Marca a mesa como livre (não ocupada) e exibe uma mensagem no console.
     * Caso a mesa já esteja livre, não realiza alteração.
     */
    public void liberar() {
        if(ocupada) {
            ocupada = false;
            System.out.println("A mesa " + numero + " está livre");
        }
    }

    /**
     * Exibe no console a lista das mesas disponíveis (não ocupadas)
     * a partir de uma lista fornecida.
     *
     * @param mesas Lista de mesas para verificar disponibilidade.
     */

    public static void listarDisponiveis(List<Mesa> mesas) {
        for(Mesa m: mesas) {
            if(!m.isOcupada()) {
                System.out.println("Mesa " + m.getNumero());
            }
        }
    }
}
