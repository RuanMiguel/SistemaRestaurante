package br.edu.uepb.sistemarestaurante.models;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa um pedido feito no sistema de restaurante.
 * Contém uma lista de itens pedidos, horário de criação, status atual e um identificador único.
 *
 * @author Laryssa D. Ramos
 */
public class Pedido {
    private static int qtd_instancias;
    private final int ID;
    private StatusPedido status;
    private final LocalTime HORARIO;
    private List<ItemPedido> itens = new ArrayList<ItemPedido>();

    /**
     * Construtor padrão.
     * Inicializa o status como PENDENTE, define o horário atual de criação e atribui um ID único ao pedido.
     */
    public Pedido() {
        this.status = StatusPedido.PENDENTE;
        this.HORARIO = LocalTime.now(ZoneId.of("America/Sao_Paulo"));

        qtd_instancias++;
        this.ID = qtd_instancias;
    }


    //GETTERS
    /**
     * Retorna o ID do pedido.
     *
     * @return ID do pedido
     */
    public int getID() {
        return ID;
    }

    /**
     * Retorna o status atual do pedido.
     *
     * @return status do pedido
     */
    public StatusPedido getStatus() {
        return status;
    }

    /**
     * Retorna o horário de criação do pedido.
     *
     * @return horário do pedido
     */
    public LocalTime getHORARIO() {
        return HORARIO;
    }

    /**
     * Retorna a lista de itens do pedido.
     *
     * @return lista de itens do pedido
     */
    public List<ItemPedido> getItens() {
        return itens;
    }

    //MÉTODOS
    /**
     * Retorna uma representação em String do pedido.
     *
     * @return descrição textual do pedido
     */
    @Override
    public String toString() {
        return "Pedido #" + ID +
                " {status: " + status +
                ", horário: " + HORARIO +
                '}';
    }

    /**
     * Adiciona um novo item ao pedido com base em um item do cardápio.
     *
     * @param item Item do cardápio
     * @param qtd Quantidade do item
     * @param obs Observações sobre o item
     */
    public void adicionarItem(ItemCardapio item, int qtd, String obs){
        ItemPedido novoItem = new ItemPedido(item, qtd, obs);
        itens.add(novoItem);
        System.out.println("Item adicionado ao pedido");
    }

    /**
     * Remove um item da lista de itens do pedido.
     *
     * @param item item a ser removido
     * @throws IllegalArgumentException se o item não estiver presente no pedido
     */
    public void removerItem(ItemPedido item) throws IllegalArgumentException {
        if(itens.contains(item)){
            itens.remove(item);
        } else {
            throw new IllegalArgumentException("O pedido não contém este item.");
        }
    }

    /**
     * Retorna uma string com todos os itens do pedido, um por linha.
     *
     * @return string contendo a descrição dos itens
     */
    public String listarItens(){
        String retorno = "";
        for(ItemPedido i: itens){
            retorno = retorno + i.toString() + "\n";
        }
        return retorno;
    }

    /**
     * Altera o status do pedido para o próximo estado definido em {@link StatusPedido#novoStatus(StatusPedido)}.
     */
    public void alterarStatus(){
        this.status = StatusPedido.novoStatus(this.status);
    }

    /**
     * Calcula o valor total do pedido somando os subtotais de cada item.
     *
     * @return valor total do pedido
     */
    public double calcularTotal(){
        double total = 0;
        for(ItemPedido item : itens){
            total += item.CalcularSubtotal();
        }
        return total;
    }
}
