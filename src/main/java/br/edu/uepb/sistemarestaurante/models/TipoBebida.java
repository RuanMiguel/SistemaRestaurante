package br.edu.uepb.sistemarestaurante.models;

public enum TipoBebida {
    AGUA("Àguas"),
    SUCO("Sucos"),
    REFRIGERANTE("Refrigerantes"),
    ALCOOLICA("Alcoólicas");

    private final String tipo;

    TipoBebida(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public static TipoBebida fromTipo(String tipo) {
        for (TipoBebida TipoB : TipoBebida.values()) {
            if (TipoB.getTipo().equals(tipo)) {
                return TipoB;
            }
        }
        throw new IllegalArgumentException("Nenhum tipo de bebida chamado " + tipo);
    }
}
