package br.edu.uepb.sistemarestaurante.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstrata que representa um item genérico do cardápio de um restaurante.
 * Serve como base para outros tipos específicos de itens do cardápio (Prato, Sobremesa, Bebida).
 * Mantém uma lista estática compartilhada de todos os itens do cardápio criados.
 *
 * @author Letícia B. M. da Cruz
 * @see Prato
 * @see Sobremesa
 * @see Bebida
 */
public abstract class ItemCardapio {
    private String nome;
    private double preco;

    /**
     * Lista estática compartilhada que armazena todos os itens do cardápio criados.
     * Acessível pelas subclasses (Prato, Sobremesa, Bebida).
     */
    protected static List<ItemCardapio> itensCardapio = new ArrayList<>();

    /**
     * Construtor para criar um novo item do cardápio.
     *
     * @param nome O nome do item do cardápio
     * @param preco O preço do item
     */
    public ItemCardapio(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    /**
     * Retorna o nome do item do cardápio.
     *
     * @return O nome do item
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o preço do item do cardápio.
     *
     * @return O preço do item
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Retorna uma representação em string do item do cardápio,
     * incluindo seu nome e preço formatado.
     *
     * @return String formatada no padrão: "Nome | Preço: R$ X.XX"
     */
    @Override
    public String toString() {
        return nome + " | Preço: R$ " + preco;
    }
}
