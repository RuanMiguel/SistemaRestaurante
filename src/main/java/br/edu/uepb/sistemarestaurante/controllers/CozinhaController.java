package br.edu.uepb.sistemarestaurante.controllers;

import br.edu.uepb.sistemarestaurante.models.StatusPedido;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class CozinhaController {
    @FXML
    private Button botaoVoltar;

    @FXML
    private VBox containerPedido; //VBox pedido modelo vazio

    public void exibirPedidos(List<Pedido> pedidos) {
        containerPedido.getChildren().clear();

        for (Pedido pedido : pedidos) {
            if (pedido.getStatus() == StatusPedido.PENDENTE || pedido.getStatus() == StatusPedido.PREPARANDO) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/uepb/sistemarestaurante/views/PedidoItemView.fxml"));
                    VBox pedidoBox = loader.load();

                    PedidoItemController controller = loader.getController();
                    controller.setPedido(pedido);

                    containerPedido.getChildren().add(pedidoBox);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
