package br.edu.uepb.sistemarestaurante.models;

public class GerenciadorCozinha extends Funcionario {
	public GerenciadorCozinha(int id, String nome, String cpf, String senha){
        super(id, nome, cpf, senha);
    }

	public static void atualizarStatus(Pedido pedido){
		pedido.alterarStatus();
	}
}