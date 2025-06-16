package br.edu.uepb.sistemarestaurante.models;

/**
 * Classe que representa o gerenciador da cozinha no sistema de restaurante.
 * Responsável por atualizar o status dos pedidos em preparação.
 * Utiliza credenciais fixas para autenticação no sistema.
 *
 * @author Marcella Viana
 * @author Ruan Miguel
 * @author Letícia Cruz
 */

public class GerenciadorCozinha extends Funcionario {
	/** Identificador fixo do gerenciador da cozinha. */
	private static final String ID_FIXO = "cozinha";
	/** Senha fixa do gerenciador da cozinha. */
	private static final String SENHA_FIXA = "1234";

	/**
	 * Constrói uma instância do GerenciadorCozinha com credenciais pré-definidas.
	 * As credenciais são fixas para simplificar o acesso do setor de cozinha.
	 */
	public GerenciadorCozinha() {
		super(ID_FIXO, SENHA_FIXA);
	}

	/**
	 * Atualiza o status de um pedido, exceto se já estiver marcado como ENTREGUE.
	 *
	 * @param pedido O pedido a ter seu status atualizado
	 * @throws IllegalStateException Se o pedido já estiver com status ENTREGUE
	 */
	@Override
	public void atualizarStatus(Pedido pedido) {
		if (pedido.getStatus() == StatusPedido.ENTREGUE) {
			throw new IllegalStateException("Pedido já entregue não pode ser alterado");
		}
		pedido.alterarStatus();
	}
}