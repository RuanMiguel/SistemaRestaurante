package br.edu.uepb.sistemarestaurante.models;

/**
 * Enumeração que representa os tipos de pratos disponíveis no sistema de restaurante.
 * <p>
 * Cada constante enum representa uma categoria específica de prato.
 * Essa enumeração é usada para classificar e organizar as pratos do cardápio.
 * </p>
 *
 * @author Laryssa D. Ramos
 * @author Leticia B. M. da Cruz
 */
public enum TipoPrato {
    ENTRADA("Entradas"),
    PRINCIPAL("Principais"),
    ACOMPANHAMENTO("Acompanhamentos"),
    PRATO_FEITO("Pratos Feitos");

    private final String tipo;

    /**
     * Construtor privado da enumeração.
     *
     * @param tipo o nome da categoria de prato
     */
    TipoPrato(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Retorna a representação do tipo de prato em formato de string.
     *
     * @return o nome do tipo de prato, como string
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Converte uma string representando o tipo de prato em uma constante da enumeração {@code TipoPrato}.
     *
     * @param tipo o nome da categoria de prato
     * @return a constante correspondente de {@code TipoPrato}
     * @throws IllegalArgumentException se o tipo informado não corresponder a nenhuma constante
     */
    public static TipoPrato fromTipo(String tipo) {
        for (TipoPrato tipoP : TipoPrato.values()) {
            if (tipoP.getTipo().equals(tipo)) {
                return tipoP;
            }
        }
        throw new IllegalArgumentException("Nenhum tipo de prato chamado " + tipo);
    }
}
