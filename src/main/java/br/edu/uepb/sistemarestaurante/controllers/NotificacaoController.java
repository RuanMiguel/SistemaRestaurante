package br.edu.uepb.sistemarestaurante.controllers;

import br.edu.uepb.sistemarestaurante.models.Pedido;
import br.edu.uepb.sistemarestaurante.services.PedidosService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.List;

public class NotificacaoController {
    @FXML private Button botaoVoltar;

    @FXML
    public VBox containerPedidos;

    @FXML
    public void initialize() {
        configurarBotaoVoltar();
        carregarNotificacoes();
    }

    PedidosService ps = new PedidosService();
    private void configurarBotaoVoltar() {
        botaoVoltar.setOnAction(event -> {
            // Voltar para Login
        });
    }

    private void carregarNotificacoes() {
        // 1. Busca os pedidos prontos (exemplo: do banco de dados ou serviço)
        List<Pedido> pedidosProntos = ps.getPedidosNotificacao();

        containerPedidos.getChildren().clear();

        for (Pedido pedido : pedidosProntos) {
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/br/edu/uepb/sistemarestaurante/views/PedidoItemController.fxml")
                );
                HBox pedidoItem = loader.load();  // Carrega o layout do item

                // Obtém o controlador do item e define os dados
                PedidoNotificacaoController controller = loader.getController();
                controller.setPedidoInfo(
                        pedido.getID(),
                        pedido.getMesa().getNumero()
                );

                // Adiciona o item ao container
                containerPedidos.getChildren().add(pedidoItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private List<Pedido> pegarPedidosPendentes() {
        // Implemente conforme sua lógica de negócios

    }

    private void Entregar(Pedido pedido) {
        System.out.println("Pedido " + pedido.getID() + " marcado como entregue");
        // Implemente a lógica para atualizar no banco de dados/serviço
    }
}
