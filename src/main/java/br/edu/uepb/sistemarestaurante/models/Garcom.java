package br.edu.uepb.sistemarestaurante.models;

public class Garcom extends Funcionario {
	private int totalGarcons;

    public Garcom(int id, String nome, String cpf, String senha){
        super(id, nome, cpf, senha);
    }
	
	public void setTotalGarcons(int totalGarcons) {
		this.totalGarcons = totalGarcons;
	}
	
	public static void anotarPedido(Mesa mesa, Pedido pedido){
		Comanda c = new Comanda();
        c.adicionarPedido(pedido);
		System.out.print("Pedido anotado!");
	}
}