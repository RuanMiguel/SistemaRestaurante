package br.edu.uepb.sistemarestaurante.models;

public class GerenciadorCozinha extends Funcionario {
    private static final String ID_FIXO = "cozinha";
    private static final String SENHA_FIXA = "1234";

    public GerenciadorCozinha(){
        super(ID_FIXO, SENHA_FIXA);
    }

    @Override
    public void atualizarStatus(Pedido pedido) {
        if (pedido.getStatus() == StatusPedido.ENTREGUE) {
            throw new IllegalStateException("Pedido já entregue não pode ser alterado");
        }
        pedido.alterarStatus();
    }
}
