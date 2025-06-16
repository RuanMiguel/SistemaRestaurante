package br.edu.uepb.sistemarestaurante.models;

/**
 * Enumeração que representa os tipos de funcionários do sistema de restaurante.
 * Utilizado para controle de acesso e definição de permissões no sistema.
 *
 * @author Letícia B. M Cruz
 */
public enum TipoFuncionario {
    /**
     * Representa um garçom, responsável pelo gerenciamento de pedidos nas mesas.
     */
    GARCOM,

    /**
     * Representa o gerenciador da cozinha, responsável pel
     * atualização do status dos pedidos.
     */
    COZINHA;

}