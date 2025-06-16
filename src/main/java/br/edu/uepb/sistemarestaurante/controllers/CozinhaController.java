package br.edu.uepb.sistemarestaurante.controllers;

import br.edu.uepb.sistemarestaurante.models.GerenciadorCozinha;
import br.edu.uepb.sistemarestaurante.models.Pedido;
import br.edu.uepb.sistemarestaurante.utils.alertaUtils;
import br.edu.uepb.sistemarestaurante.utils.janelaUtils;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class CozinhaController {
    private static final String CAMINHO_LOGIN_VIEW = "/br/edu/uepb/sistemarestaurante/views/LoginView.fxml";
    private static final String CAMINHO_PEDIDO_COZINHA_VIEW = "/br/edu/uepb/sistemarestaurante/views/PedidoCozinhaView.fxml";

    @FXML
    private Button botaoVoltar;
    @FXML
    private VBox containerPedidos;

    private ObservableList<Pedido> pedidosObservable = FXCollections.observableArrayList();
    private GerenciadorCozinha gc = new GerenciadorCozinha();

    @FXML
    private void initialize() {
        // Configura o listener para atualizações
        pedidosObservable.addListener((ListChangeListener<Pedido>) change -> {
            atualizarTelaPedidos();
        });

        // Carrega os pedidos iniciais
        carregarPedidos();
    }

    private void carregarPedidos() {
        List<Pedido> pedidosCozinha = Pedido.getPedidosCozinha(); // Usa o método estático
        pedidosObservable.setAll(pedidosCozinha);
    }

    private void atualizarTelaPedidos() {
        containerPedidos.getChildren().clear();

        try {
            URL fxmlUrl = getClass().getResource(CAMINHO_PEDIDO_COZINHA_VIEW);
            if (fxmlUrl == null) {
                throw new IOException("Arquivo FXML não encontrado: " + CAMINHO_PEDIDO_COZINHA_VIEW);
            }

            for (Pedido pedido : pedidosObservable) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(CAMINHO_PEDIDO_COZINHA_VIEW));
                VBox pedidoBox = loader.load();
                PedidoCozinhaController controller = loader.getController();

                controller.setPedido(pedido);
                controller.setGerenciadorCozinheiro(new GerenciadorCozinha()); // ou um GC já existente

                containerPedidos.getChildren().add(pedidoBox);
            }
        } catch (IOException e) {
            alertaUtils.mostrarAlerta("Erro", "Falha ao carregar pedidos", e.getMessage());
        }
    }

    public void deslogarCozinha(ActionEvent event) throws IOException {
        janelaUtils.mudarTela(event, CAMINHO_LOGIN_VIEW, "Login");
    }

    /**
     * Método para atualizar manualmente a lista de pedidos
     */
    public void atualizarPedidos() {
        List<Pedido> novosPedidos = Pedido.getPedidosCozinha();
        pedidosObservable.setAll(novosPedidos);
    }
}