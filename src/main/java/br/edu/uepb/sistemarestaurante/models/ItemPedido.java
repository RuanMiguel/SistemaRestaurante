package br.edu.uepb.sistemarestaurante.models;

/**
 * Representa um item dentro de um pedido.
 * Contém o item do cardápio, a quantidade e observações adicionais do cliente.
 *
 * @author Laryssa D. Ramos
 */
public class ItemPedido {
    /** Atributos da classe ItemPedido:
     * - item: O item do cardápio que foi pedido
     * - qtd: Quantidade do item pedida
     * - obs: Observações adicionais do cliente sobre o item
     */
    private ItemCardapio item;
   /**
     * Quantidade do item pedida.
     * Representa quantas unidades do item foram solicitadas pelo cliente.
     */
    private int qtd;
    /**
     * Observações adicionais do cliente sobre o item.
     * Podem incluir preferências, alergias ou instruções especiais.
     */
    private String obs;

    /**
     * Construtor da classe ItemPedido.
     *
     * @param item o item do cardápio
     * @param qtd quantidade pedida do item
     * @param obs observações adicionais do cliente
     */
    public ItemPedido(ItemCardapio item, int qtd, String obs) {
        this.item = item;
        this.qtd = qtd;
        this.obs = obs;
    }

    //GETTERS E SETTER
    /**
     * Retorna o item do cardápio.
     *
     * @return item do cardápio
     */
    public ItemCardapio getItem() {
        return item;
    }

    /**
     * Retorna a quantidade do item.
     *
     * @return quantidade
     */
    public int getQtd() {
        return qtd;
    }

    /**
     * Define a quantidade do item.
     *
     * @param qtd nova quantidade
     */
    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    /**
     * Retorna as observações sobre o item.
     *
     * @return observações
     */
    public String getObs() {
        return obs;
    }

    /**
     * Define as observações sobre o item.
     *
     * @param obs nova observação
     */
    public void setObs(String obs) {
        this.obs = obs;
    }

    //METODOS
    /**
     * Retorna uma representação textual do item no pedido.
     *
     * @return string formatada do item
     */
    @Override
    public String toString() {
        return qtd + "x " + item + " - Obs.: " + obs + ";";
    }

    /**
     * Calcula o subtotal do item, com base na quantidade e no preço unitário.
     *
     * @return subtotal (quantidade * preço unitário)
     */
    public double CalcularSubtotal(){
        return this.item.getPreco() * this.qtd;
    }
}
