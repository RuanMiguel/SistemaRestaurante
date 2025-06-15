package br.edu.uepb.sistemarestaurante.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import br.edu.uepb.sistemarestaurante.models.*;

public class PedidoItemController {

    @FXML
    private VBox pedidoBox;

    @FXML
    private Label numIDPedido;

    @FXML
    private Label horarioPedido;

    @FXML
    private Label statusPedido;

    @FXML
    private VBox containerItensPedido;

    @FXML
    private Button botaoStatus;

    public void setPedido(Pedido pedido) {
        numIDPedido.setText("Pedido #" + pedido.getID());
        horarioPedido.setText("Horário: " + pedido.getHORARIO());
        statusPedido.setText("Status: " + pedido.getStatus().name());

        containerItensPedido.getChildren().clear();
        for (ItemPedido item : pedido.getItens()) {
            Label lblItem = new Label("(" + item.getQtd() + "x) " + item.getItem().getNome());
            containerItensPedido.getChildren().add(lblItem);

            if (item.getObs() != null && !item.getObs().isEmpty()) {
                Label obsLabel = new Label("        Obs: " + item.getObs());
                containerItensPedido.getChildren().add(obsLabel);
            }
        }

        try {
            StatusPedido proximoStatus = StatusPedido.novoStatus(pedido.getStatus());
            botaoStatus.setText("Marcar como " + proximoStatus.name());
        } catch (IllegalArgumentException e) {
            // Se não houver próximo status (como para ENTREGUE)
            botaoStatus.setText("Finalizado");
            botaoStatus.setDisable(true);
        }
    }
}