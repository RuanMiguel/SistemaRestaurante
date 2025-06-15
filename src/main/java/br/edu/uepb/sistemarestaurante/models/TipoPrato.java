package br.edu.uepb.sistemarestaurante.models;

public enum TipoPrato {
    ENTRADA("Entradas"),
    PRINCIPAL("Principais"),
    ACOMPANHAMENTO("Acompanhamentos"),
    PRATO_FEITO("Pratos Feitos");

    private final String tipo;

    TipoPrato(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public static TipoPrato fromTipo(String tipo) {
        for (TipoPrato tipoP : TipoPrato.values()) {
            if (tipoP.getTipo().equals(tipo)) {
                return tipoP;
            }
        }
        throw new IllegalArgumentException("Nenhum tipo de prato chamado " + tipo);
    }
}