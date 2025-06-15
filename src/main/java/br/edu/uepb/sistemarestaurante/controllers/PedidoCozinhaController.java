package br.edu.uepb.sistemarestaurante.controllers;

import br.edu.uepb.sistemarestaurante.models.GerenciadorCozinha;
import br.edu.uepb.sistemarestaurante.models.ItemPedido;
import br.edu.uepb.sistemarestaurante.models.Pedido;
import br.edu.uepb.sistemarestaurante.models.StatusPedido;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PedidoCozinhaController {

    @FXML private Label numIDPedido;
    @FXML private Label horarioPedido;
    @FXML private Label statusPedido;
    @FXML private VBox itensPedidoBox;
    @FXML private Button botaoStatus;

    private Pedido pedido;
    private GerenciadorCozinha gc;

    // Metodo para injetar o gerenciador
    public void setGerenciadorCozinheiro(GerenciadorCozinha gc) {
        this.gc = gc;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
        atualizarBox();
    }

    private void atualizarBox() {
        numIDPedido.setText("Pedido #" + pedido.getID());
        horarioPedido.setText("Horário: " + pedido.getHORARIO());
        statusPedido.setText("Status: " + pedido.getStatus().name());

        itensPedidoBox.getChildren().clear();
        for (ItemPedido item : pedido.getItens()) {
            Label lblItem = new Label("(" + item.getQtd() + "x) " + item.getItem());
            itensPedidoBox.getChildren().add(lblItem);

            if (item.getObs() != null && !item.getObs().isEmpty()) {
                Label obsLabel = new Label("        obs: " + item.getObs());
                itensPedidoBox.getChildren().add(obsLabel);
            }
        }

        // Configura o botão conforme o status
        StatusPedido  statusAtual = pedido.getStatus();
        StatusPedido proximoStatus = StatusPedido.novoStatus(statusAtual);
        if(proximoStatus !=null){
                    botaoStatus.setText("Marcar como " + proximoStatus);
                    botaoStatus.setDisable(false);
                    botaoStatus.setOnAction(e -> atualizarStatusPedido());
        }else{
            botaoStatus.setText("Entregue");
            botaoStatus.setDisable(true);
        }
    }

    private void atualizarStatusPedido() {
        try {
            gc.atualizarStatus(pedido);
            atualizarBox(); // Atualiza a UI após mudar o status
        } catch (IllegalStateException e) {
            // Mostra mensagem de erro para o usuário
            statusPedido.setText("Erro: " + e.getMessage());
        }
    }
}
