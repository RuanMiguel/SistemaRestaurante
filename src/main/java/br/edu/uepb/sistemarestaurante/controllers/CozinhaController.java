package br.edu.uepb.sistemarestaurante.controllers;

import br.edu.uepb.sistemarestaurante.models.Pedido;
import br.edu.uepb.sistemarestaurante.models.StatusPedido;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class CozinhaController {
    @FXML
    private Button botaoVoltar;

    @FXML
    private VBox pedidosBoxPai; //VBox pedido modelo vazio

    @FXML
    private void initialize(){

    }

    public void exibirPedidos(List<Pedido> pedidos) {
        pedidosBoxPai.getChildren().clear();

        for (Pedido pedido : pedidos) {
            if (pedido.getStatus() == StatusPedido.PENDENTE || pedido.getStatus() == StatusPedido.PREPARANDO) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/uepb/sistemarestaurante/views/PedidoItemView.fxml"));
                    VBox pedidoBox = loader.load();

                    PedidoCozinhaController controller = loader.getController();

                    controller.setPedido(pedido);

                    pedidosBoxPai.getChildren().add(pedidoBox);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}