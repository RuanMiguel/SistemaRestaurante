package br.edu.uepb.sistemarestaurante.models;

public class Garcom extends Funcionario{

    public Garcom (String id, String senha){
        super(id, senha);
    }

    @Override
    public void atualizarStatus(Pedido pedido) {
        if (pedido.getStatus() == StatusPedido.PRONTO) {
            pedido.alterarStatus(); // Só avança se estiver PRONTO
        } else {
            throw new IllegalStateException("Garçom só pode alterar pedidos PRONTOS");
        }
    }

}
