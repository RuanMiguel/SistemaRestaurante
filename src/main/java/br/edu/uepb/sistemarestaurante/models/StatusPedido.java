package br.edu.uepb.sistemarestaurante.models;

public enum StatusPedido {
    PENDENTE, PREPARANDO, PRONTO, ENTREGUE;

    public static StatusPedido novoStatus (StatusPedido atual) {
        switch (atual) {
            case PENDENTE:  return PREPARANDO;
            case PREPARANDO:     return PRONTO;
            case PRONTO:   return ENTREGUE;
            default:        throw new IllegalArgumentException("Status desconhecido");
        }
    }
}