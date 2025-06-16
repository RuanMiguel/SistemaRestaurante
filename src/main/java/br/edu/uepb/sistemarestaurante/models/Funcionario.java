package br.edu.uepb.sistemarestaurante.models;

/**
 * Classe abstrata que representa um funcionário genérico do sistema de restaurante.
 * Serve como base para tipos de funcionários diferentes (Garçom e Gerenciador da Cozinha)
 * e cada um com uma responsabilidade específica.
 *
 * @author Marcella Viana
 * @author Ruan Miguel
 */
public abstract class Funcionario {
	/** Atributos da classe Funcionario:
	 * - id: Identificador único do funcionário
	 * - senha: Senha de acesso do funcionário
	 */
	private final String id;
	/** * Senha de acesso do funcionário.
	 * Utilizada para autenticação e autorização no sistema.
	 */
	private final String senha;
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