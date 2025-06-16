package br.edu.uepb.sistemarestaurante.models;

/**
 * Classe que representa uma bebida no cardápio do restaurante.
 * Herda de ItemCardapio e adiciona características específicas de bebidas.
 *
 * @author Letícia B.M. da Cruz
 */
public class Bebida extends ItemCardapio {
    private TipoBebida tipo;
    private VolumeBebida volume;

    /**
     * Construtor para criar uma instância de Bebida.
     *
     * @param nome   Nome da bebida
     * @param preco  Preço da bebida
     * @param tipo   Tipo da bebida (enum TipoBebida)
     * @param volume Volume da bebida (enum VolumeBebida)
     * @throws IllegalArgumentException Se algum parâmetro for inválido
     */
    public Bebida(String nome, double preco, TipoBebida tipo, VolumeBebida volume) {
        super(nome, preco);
        this.tipo = tipo;
        this.volume = volume;
        itensCardapio.add(this);
    }

    /**
     * Retorna o tipo da bebida.
     *
     * @return TipoBebida representando o tipo da bebida
     */
    public TipoBebida getTipo() {
        return tipo;
    }

    /**
     * Retorna o volume da bebida.
     *
     * @return VolumeBebida representando o volume da bebida
     */
    public VolumeBebida getVolume() {
        return volume;
    }

    /**
     * Retorna uma representação em string da bebida.
     *
     * @return String formatada com informações da bebida
     */
    @Override
    public String toString() {
        return String.format("%s [Tipo: %s, Volume: %s, Preço: R$%.2f]",
                getNome(), tipo, volume, getPreco());
    }
}