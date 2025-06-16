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

/**
 * Controlador para gerenciar notificações de pedidos prontos.
 * Carrega e exibe os pedidos prontos na interface do usuário.
 *
 * @author Marcella Viana Lins
 */
public class NotificacaoController {
    /* * Caminho para a view do painel de mesas.
     * Deve ser substituído pelo caminho correto no seu projeto.
     */
    private static final String CAMINHO_PAINEL_MESA_VIEW = "/br/edu/uepb/sistemarestaurante/views/PainelMesa.fxml";

    /* * Serviço de pedidos, responsável por gerenciar os pedidos.
     * Deve ser injetado ou inicializado conforme a lógica do seu projeto.
     */
    @FXML private Button botaoVoltar;

    /* * Container onde os pedidos prontos serão exibidos.
     * Deve ser definido no arquivo FXML correspondente.
     */
    @FXML
    public VBox containerPedidos;

    /* * Método chamado ao clicar no botão de voltar.
     * Redireciona para a tela do painel de mesas.
     */
    @FXML
    public void initialize() {
        carregarNotificacoes();
    }

    /**
     * Método chamado ao clicar no botão de voltar.
     * Redireciona para a tela do painel de mesas.
     *
     * @param event Evento de ação do botão
     * @throws IOException Se ocorrer um erro ao carregar a tela
     */
    private void botaoVoltar(ActionEvent event) throws IOException {
        janelaUtils.mudarTela(event, "CAMINHO_PAINEL_MESA_VIEW", "Centro de Mesas");
    }

    /**
     * Carrega os pedidos prontos e os exibe na interface do usuário.
     * Cada pedido é carregado em um item de notificação.
     */
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

    /**
     * Método para marcar um pedido como entregue.
     * Obtém os pedidos pendentes e atualiza o status de cada um.
     */
    private List<Pedido> pegarPedidosPendentes() {
        // Implemente conforme sua lógica de negócios
        //TODO: Retornar lista de pedidos pendentes
        return new ArrayList<>();
    }

    /**
     * Método para entregar um pedido.
     * Atualiza o status do pedido para entregue e exibe uma mensagem.
     *
     * @param pedido O pedido a ser entregue
     */
    private void entregar(Pedido pedido) {
        System.out.println("Pedido " + pedido.getID() + " marcado como entregue");
        // Implemente a lógica para atualizar no banco de dados/serviço
    }
}
