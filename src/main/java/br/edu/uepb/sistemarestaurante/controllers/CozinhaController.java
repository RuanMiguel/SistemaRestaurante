package br.edu.uepb.sistemarestaurante.controllers;

import br.edu.uepb.sistemarestaurante.models.ItemCardapio;
import br.edu.uepb.sistemarestaurante.models.ItemPedido;
import br.edu.uepb.sistemarestaurante.models.Pedido;
import br.edu.uepb.sistemarestaurante.models.StatusPedido;
import br.edu.uepb.sistemarestaurante.utils.alertaUtils;
import br.edu.uepb.sistemarestaurante.utils.janelaUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Controller responsável pela interface da cozinha do sistema de restaurante.
 * Gerencia a exibição e manipulação de pedidos em status PENDENTE ou PREPARANDO.
 *
 * @author Letícia B. M. da Cruz
 * @see Pedido
 * @see StatusPedido
 * @see PedidoCozinhaController
 */
public class CozinhaController {
    @FXML
    private Button botaoVoltar;

    @FXML
    private VBox containerPedidos;

    /**
     * Inicializa o controller. Método chamado automaticamente após o carregamento do FXML.
     */
    @FXML
    private void initialize() {
        // Pode ser usado para configurações iniciais
    }

    /**
     * Realiza o logout do usuário da tela da cozinha, retornando à tela de login.
     *
     * @param event O evento de ação que disparou o método
     * @throws RuntimeException Se ocorrer um erro durante a mudança de tela
     */
    public void deslogarCozinha(ActionEvent event) throws IOException {
        String janelaLogin = "/br/edu/uepb/sistemarestaurante/views/LoginView.fxml";
        janelaUtils.mudarTela(event, janelaLogin, "Login");
    }

    /**
     * Exibe os pedidos na interface da cozinha.
     * Mostra apenas pedidos com status PENDENTE ou PREPARANDO.
     *
     * @param pedidos Lista de pedidos a serem exibidos
     * @throws IllegalArgumentException Se a lista de pedidos for nula
     */
    public void exibirPedidos(List<Pedido> pedidos) {
        if (pedidos == null) {
            throw new IllegalArgumentException("Lista de pedidos não pode ser nula");
        }

        containerPedidos.getChildren().clear();

        try {
            URL fxmlUrl = getClass().getResource(
                    "/br/edu/uepb/sistemarestaurante/views/PedidoCozinhaView.fxml");

            if (fxmlUrl == null) {
                throw new IOException("Arquivo FXML não encontrado");
            }

            for (Pedido pedido : pedidos) {
                if (pedido.getStatus() == StatusPedido.PENDENTE ||
                        pedido.getStatus() == StatusPedido.PREPARANDO) {

                    FXMLLoader loader = new FXMLLoader(fxmlUrl);
                    VBox pedidoBox = loader.load();
                    PedidoCozinhaController controller = loader.getController();
                    controller.setPedido(pedido);
                    containerPedidos.getChildren().add(pedidoBox);
                }
            }
        } catch (IOException e) {
            alertaUtils.mostrarAlerta("Erro", "Não foi possível carregar os pedidos: " + e.getMessage());
        }
    }
}
