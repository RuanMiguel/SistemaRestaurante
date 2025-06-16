package br.edu.uepb.sistemarestaurante.models;

/**
 * Classe que representa um garçom no sistema de restaurante.
 * Responsável por gerenciar pedidos e comandas das mesas.
 *
 * @author Marcella Viana
 * @author Ruan Miguel
 * @author Letícia Cruz
 *
 */
public class Garcom extends Funcionario {

	/**
	 * Construtor para criar uma instância de Garcom.
	 *
	 * @param id    Identificador único do garçom
	 * @param senha Senha de acesso do garçom
	 */
	public Garcom(String id, String senha) {
		super(id, senha);
	}

	/**
	 * Atualiza o status de um pedido, somente se o pedido estiver com status PRONTO.
	 *
	 * @param pedido O pedido a ter seu status atualizado
	 * @throws IllegalStateException Se o pedido não estiver com status PRONTO
	 */
	@Override
	public void atualizarStatus(Pedido pedido) {
		if (pedido.getStatus() == StatusPedido.PRONTO) {
			pedido.alterarStatus();
		} else {
			throw new IllegalStateException("Garçom só pode alterar pedidos PRONTOS");
		}
	}

	/**
	 * Anota um novo pedido para uma mesa específica.
	 *
	 * @param mesa  A mesa que está realizando o pedido
	 * @param pedido O pedido a ser anotado
	 */
	public static void anotarPedido(Mesa mesa, Pedido pedido) {
		Comanda c = new Comanda();
		c.adicionarPedido(pedido);
		System.out.println("Pedido anotado para a mesa " + mesa.getNumero() + "!");
	}
}