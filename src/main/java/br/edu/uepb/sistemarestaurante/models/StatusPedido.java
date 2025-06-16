package br.edu.uepb.sistemarestaurante.models;

/**
 * Enumeração que representa os diferentes status de um pedido no sistema de restaurante.
 * Os status possíveis são: PENDENTE, PREPARANDO, PRONTO e ENTREGUE.
 *
 * @author Laryssa D. Ramos
 * @author Marcella Viana
 * @author Ruan Miguel
 */
public enum StatusPedido {
    /* * Status do pedido:
     * - PENDENTE: Pedido foi criado, mas ainda não começou a ser preparado.
     * - PREPARANDO: Pedido está sendo preparado na cozinha.
     * - PRONTO: Pedido foi preparado e está pronto para entrega.
     * - ENTREGUE: Pedido foi entregue ao cliente.
     */
    PENDENTE, PREPARANDO, PRONTO, ENTREGUE;

    /**
     * Retorna o próximo status do pedido com base no status atual.
     *
     * @param atual O status atual do pedido
     * @return O próximo status do pedido
     * @throws IllegalArgumentException Se o status atual não for válido
     */
    public static StatusPedido novoStatus (StatusPedido atual) {
        switch (atual) {
            case PENDENTE:  return PREPARANDO;
            case PREPARANDO:     return PRONTO;
            case PRONTO:   return ENTREGUE;
            default:        throw new IllegalArgumentException("Status desconhecido");
        }
    }
}
