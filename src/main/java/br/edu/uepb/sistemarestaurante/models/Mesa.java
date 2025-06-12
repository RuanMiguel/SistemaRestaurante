package br.edu.uepb.sistemarestaurante.models;

import java.util.List;

public class Mesa {
	private int numero;
	private int capacidade;
	private boolean ocupada;
	private Comanda comanda;
	
	public Mesa(int numero, int capacidade){
		this.numero = numero;
		this.capacidade = capacidade;
		this.ocupada = false;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public boolean isOcupada() {
		return ocupada;
	}

	public Comanda getComanda() {
		return comanda;
	}

	public void setComanda(Comanda comanda) {
		this.comanda = comanda;
	}

	public void ocupar() {
		if(!ocupada) {
			ocupada = true;
			System.out.println("A mesa " + numero + " está ocupada");
		}
		
	}
	
	public void liberar() {
		if(ocupada) {
			ocupada = false;
			System.out.println("A mesa " + numero + " está livre");
		}
	}
	
	public static void listarDisponiveis(List<Mesa> mesas) {
		for(Mesa m: mesas) {
			if(!m.isOcupada()) {
				System.out.println("Mesa " + m.getNumero());
			}
	    }
    }
}