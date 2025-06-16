package br.edu.uepb.sistemarestaurante.controllers;

import br.edu.uepb.sistemarestaurante.models.Pedido;
import br.edu.uepb.sistemarestaurante.utils.janelaUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NotificacaoController {
    private static final String CAMINHO_PAINEL_MESA_VIEW = "/br/edu/uepb/sistemarestaurante/views/PainelMesa.fxml";

    @FXML private Button botaoVoltar;

    @FXML
    public VBox containerPedidos;

    @FXML
    public void initialize() {
        carregarNotificacoes();
    }

    private void botaoVoltar(ActionEvent event) throws IOException {
        janelaUtils.mudarTela(event, "CAMINHO_PAINEL_MESA_VIEW", "Centro de Mesas");
    }

    private void carregarNotificacoes() {
        List<Pedido> pedidosProntos = ps.getPedidosNotificacao(null, null);
        containerPedidos.getChildren().clear();

        for (Pedido pedido : pedidosProntos) {
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/br/edu/uepb/sistemarestaurante/views/PedidoNotificacaoController.fxml")
                );
                HBox pedidoItem = loader.load();

                PedidoNotificacaoController controller = loader.getController();
                controller.setPedidoInfo(null, null, null);

                containerPedidos.getChildren().add(pedidoItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private List<Pedido> pegarPedidosPendentes() {
        // Implemente conforme sua lógica de negócios
        //TODO: Retornar lista de pedidos pendentes
        return new ArrayList<>();
    }

    private void entregar(Pedido pedido) {
        System.out.println("Pedido " + pedido.getID() + " marcado como entregue");
        // Implemente a lógica para atualizar no banco de dados/serviço
    }
}
