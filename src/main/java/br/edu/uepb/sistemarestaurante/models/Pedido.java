package br.edu.uepb.sistemarestaurante.models;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int qtd_instancias;
    private final int ID;
    private StatusPedido status;
    private final LocalTime HORARIO;
    private List<ItemPedido> itens = new ArrayList<ItemPedido>();

    //CONSTRUCTOR
    public Pedido() {
        this.status = StatusPedido.PENDENTE;
        this.HORARIO = LocalTime.now(ZoneId.of("America/Sao_Paulo"));

        qtd_instancias++;
        this.ID = qtd_instancias;
    }


    //GETTERS
    public int getID() {
        return ID;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public LocalTime getHORARIO() {
        return HORARIO;
    }


    //MÉTODOS
    @Override
    public String toString() {
        return "Pedido #" + ID +
                " {status: " + status +
                ", horário: " + HORARIO +
                '}';
    }

    public void adicionarItem(ItemCardapio item, int qtd, String obs){
        ItemPedido novoItem = new ItemPedido(item, qtd, obs);
        itens.add(novoItem);
    }
    //ou:
    public void adicionarItem(ItemPedido novoItem){
        itens.add(novoItem);
    }

    public void removerItem(ItemPedido item) throws IllegalArgumentException {
        if(itens.contains(item)){
            itens.remove(item);
        } else {
            throw new IllegalArgumentException("O pedido não contém este item.");
        }
    }

    public String listarItens(){
        String retorno = "";
        for(ItemPedido i: itens){
            retorno = retorno + i.toString() + "\n";
        }
        return retorno;
    }

    public void alterarStatus(){
        this.status = StatusPedido.novoStatus(this.status);
    }

    public double calcularTotal(){
        double total = 0;
        for(ItemPedido item : itens){
            total += item.CalcularSubtotal();
        }
        return total;
    }
}
