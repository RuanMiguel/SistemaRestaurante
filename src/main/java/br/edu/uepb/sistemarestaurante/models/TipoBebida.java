package br.edu.uepb.sistemarestaurante.models;

/**
 * Enumeração que representa os tipos de bebidas disponíveis no sistema de restaurante.
 * <p>
 * Cada constante enum representa uma categoria específica de bebida, como água, suco, refrigerante ou bebida alcoólica.
 * Essa enumeração é usada para classificar e organizar as bebidas do cardápio.
 * </p>
 *
 * @author Laryssa D. Ramos
 * @author Leticia B. M. da Cruz
 */
public enum TipoBebida {
    /**
     * Tipos de bebidas disponíveis:
     * - AGUA: Representa bebidas à base de água.
     * - SUCO: Representa bebidas à base de suco de frutas.
     * - REFRIGERANTE: Representa bebidas gaseificadas e açucaradas.
     * - ALCOOLICA: Representa bebidas que contêm álcool.
     */
    AGUA("Àguas"),
    SUCO("Sucos"),
    REFRIGERANTE("Refrigerantes"),
    ALCOOLICA("Alcoólicas");

    private final String tipo;

    /**
     * Construtor privado da enumeração.
     *
     * @param tipo o nome da categoria de bebida
     */
    TipoBebida(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Retorna a representação do tipo de bebida em formato de string.
     *
     * @return o nome do tipo de bebida, como string
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Converte uma string representando o tipo de bebida em uma constante da enumeração {@code TipoBebida}.
     *
     * @param tipo o nome da categoria de bebida (ex: "Sucos")
     * @return a constante correspondente de {@code TipoBebida}
     * @throws IllegalArgumentException se o tipo informado não corresponder a nenhuma constante
     */
    public static TipoBebida fromTipo(String tipo) {
        for (TipoBebida TipoB : TipoBebida.values()) {
            if (TipoB.getTipo().equals(tipo)) {
                return TipoB;
            }
        }
        throw new IllegalArgumentException("Nenhum tipo de bebida chamado " + tipo);
    }
}
