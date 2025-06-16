package br.edu.uepb.sistemarestaurante.models;

/**
 * Classe abstrata que representa um funcionário genérico do sistema de restaurante.
 * Serve como base para diferentes tipos de funcionários com responsabilidades específicas.
 */
public abstract class Funcionario {
	private final String id;     // Identificador único do funcionário
	private final String senha;  // Senha de acesso do funcionário

	/**
	 * Construtor protegido para a classe abstrata Funcionario.
	 *
	 * @param id    Identificador único do funcionário
	 * @param senha Senha de acesso do funcionário
	 */
	protected Funcionario(String id, String senha) {
		this.id = id;
		this.senha = senha;
	}

	/**
	 * Método abstrato para atualizar o status de um pedido.
	 * Deve ser implementado pelas classes concretas que herdam de Funcionario.
	 *
	 * @param pedido O pedido cujo status será atualizado
	 */
	public abstract void atualizarStatus(Pedido pedido);

	/**
	 * Retorna o ID do funcionário.
	 *
	 * @return String contendo o ID do funcionário
	 */
	public String getId() {
		return id;
	}

	/**
	 * Retorna a senha do funcionário.
	 *
	 * @return String contendo a senha do funcionário
	 */
	public String getSenha() {
		return senha;
	}
}