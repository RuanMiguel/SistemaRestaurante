package br.edu.uepb.sistemarestaurante.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PedidoItemController {

    @FXML
    private VBox pedidoBox; //VBox preenchido com dados do pedido

    //vbox cabeçalho início
    @FXML
    private Label numIDPedido;

    @FXML
    private Label horarioPedido;

    @FXML
    private Label statusPedido;
    //vbox cabeçalho fim

    @FXML
    private VBox containerItensPedido; // VBox itens modelo vazio

    @FXML
    private Button botaoStatus;

    public void setPedido(Pedido pedido) {
        numIDPedido.setText("Pedido #" + pedido.getID());
        horarioPedido.setText("Horário: " + pedido.getHORARIO());
        statusPedido.setText("Status: " + pedido.getStatus().name());

        containerItensPedido.getChildren().clear();
        for (ItemPedido item : pedido.getItens()) {
            Label lblItem = new Label("(" + item.getQuantidade() + "x) " + item.getNome());
            itensContainer.getChildren().add(lblItem);

            if (item.getObservacao() != null && !item.getObservacao().isEmpty()) {
                Label obsLabel = new Label("        obs: " + item.getObservacao());
                containerItensPedido.getChildren().add(obsLabel);
            }
        }

        botaoStatus.setText("Marcar como " + proximoStatus(pedido.getStatus()));
    }


}
