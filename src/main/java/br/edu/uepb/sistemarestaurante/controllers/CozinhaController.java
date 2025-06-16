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

/**
 * Controlador para a tela de pedidos da cozinha.
 * Gerencia a exibição e atualização dos pedidos pendentes e em preparo.
 * Permite que o usuário deslogue e atualize manualmente a lista de pedidos.
 *
 * @author Laryssa D. Ramos
 * @author Marcella Viana da Silva Lins
 * @author Letícia B.M. da Cruz
 */
public class CozinhaController {
    /** Caminhos para as views FXML utilizadas neste controlador.
     * Esses caminhos são relativos ao diretório de recursos do projeto.
     */
    private static final String CAMINHO_LOGIN_VIEW = "/br/edu/uepb/sistemarestaurante/views/LoginView.fxml";
    /** Caminho para a view FXML que representa cada pedido na cozinha.
     * Este caminho deve apontar para o arquivo FXML que define a interface de um pedido na cozinha.
     */
    private static final String CAMINHO_PEDIDO_COZINHA_VIEW = "/br/edu/uepb/sistemarestaurante/views/PedidoCozinhaView.fxml";

    /**
     * Botão para deslogar da cozinha.
     * Aciona a mudança de tela para a tela de login.
     */
    @FXML
    private Button botaoVoltar;
    /**
     * Container onde os pedidos da cozinha serão exibidos.
     * Utiliza um VBox para organizar os pedidos verticalmente.
     */

    @FXML
    private VBox containerPedidos;
    /**
     * Lista observável que contém os pedidos pendentes e em preparo.
     * Utilizada para atualizar a interface automaticamente quando a lista muda.
     */

    private ObservableList<Pedido> pedidosObservable = FXCollections.observableArrayList();
    /**
     * Gerenciador de cozinha responsável por manipular os pedidos.
     * Este gerenciador pode ser usado para realizar operações adicionais nos pedidos.
     */
    private GerenciadorCozinha gc = new GerenciadorCozinha();

    /**
     * Método chamado ao inicializar o controlador.
     * Configura o listener para atualizações na lista de pedidos e carrega os pedidos iniciais.
     */

    @FXML
    private void initialize() {
        // Configura o listener para atualizações
        pedidosObservable.addListener((ListChangeListener<Pedido>) change -> {
            atualizarTelaPedidos();
        });

        // Carrega os pedidos iniciais
        carregarPedidos();
    }
    /**
     * Carrega os pedidos pendentes e em preparo da cozinha.
     * Obtém a lista de pedidos através do método estático Pedido.getPedidosCozinha().
     * Atualiza a lista observável com os pedidos carregados.
     */
    @FXML
    private void carregarPedidos() {
        List<Pedido> pedidosCozinha = Pedido.getPedidosCozinha(); // Usa o método estático
        pedidosObservable.setAll(pedidosCozinha);
    }
    /**
     * Atualiza a tela de pedidos, limpando o container e recarregando os pedidos.
     * Carrega cada pedido na interface usando o FXML correspondente.
     * Exibe um alerta em caso de falha ao carregar os pedidos.
     */

    @FXML
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
            alertaUtils.mostrarAlerta("Erro", "Falha ao carregar pedidos");
        }
    }
    /**
     * Método para deslogar da cozinha e retornar à tela de login.
     * Aciona a mudança de tela para a tela de login.
     *
     * @param event Evento de ação do botão de deslogar
     * @throws IOException Se ocorrer um erro ao carregar a tela de login
     */

    @FXML
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