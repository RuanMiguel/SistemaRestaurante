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

    public static void anotarPedido(Mesa mesa, Pedido pedido){
        Comanda c = new Comanda();
        c.adicionarPedido(pedido);
        System.out.print("Pedido anotado!");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Garcom garcom)) return false;
        return getId().equals(garcom.getId());
    }
}
