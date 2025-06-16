package br.edu.uepb.sistemarestaurante.models;

/**
 * Classe que representa um item de sobremesa no cardápio do restaurante.
 * Herda de ItemCardapio e representa itens sobremesas do restaurante.
 *
 * @author Letícia
 */
public class Sobremesa extends ItemCardapio {

    /**
     * Construtor para criar uma instância de Sobremesa.
     *
     * @param nome  Nome da sobremesa (não pode ser nulo ou vazio)
     * @param preco Preço da sobremesa (deve ser positivo)
     * @throws IllegalArgumentException Se nome for nulo/vazio ou preço for negativo
     */
    public Sobremesa(String nome, double preco) {
        super(nome, preco);
        itensCardapio.add(this);
    }

    /**
     * Retorna uma representação em string da sobremesa.
     *
     * @return String formatada com informações da sobremesa
     */
    @Override
    public String toString() {
        return String.format("Sobremesa: %s [Preço: R$%.2f]", getNome(), getPreco());
    }
}

