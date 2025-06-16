package br.edu.uepb.sistemarestaurante.models;

/**
 * Representa um prato principal do cardápio do restaurante.
 * Herda de {@link ItemCardapio} e adiciona a característica de tipo de prato.
 * Ao ser instanciado, o prato é automaticamente adicionado à lista compartilhada de itens do cardápio.
 *
 * @author Letícia B. M. da Cruz
 * @see ItemCardapio
 * @see TipoPrato
 */
public class Prato extends ItemCardapio {
    /**
     * O tipo do prato (entrada, principal, acompanhamento, etc.)
     * @see TipoPrato
     */
    private TipoPrato tipo;

    /**
     * Construtor para criar um novo prato no cardápio.
     *
     * @param nome O nome do prato
     * @param preco O preço do prato
     * @param tipo O tipo do prato {@link TipoPrato}
     * @throws IllegalArgumentException Se nome for nulo ou vazio, ou preço for negativo
     */
    public Prato(String nome, double preco, TipoPrato tipo) {
        super(nome, preco);
        this.tipo = tipo;
        itensCardapio.add(this);
    }

    /**
     * Retorna o tipo do prato.
     *
     * @return O {@link TipoPrato} do prato
     */
    public TipoPrato getTipo() {
        return tipo;
    }

    /**
     * Retorna uma representação em string do prato,
     * incluindo nome, preço e tipo formatados.
     *
     * @return String formatada no padrão: "Nome [Tipo] | Preço: R$ X.XX"
     */
    @Override
    public String toString() {
        return super.toString() + " [" + tipo + "]";
    }
}
