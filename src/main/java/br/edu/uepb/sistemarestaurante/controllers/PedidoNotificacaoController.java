package br.edu.uepb.sistemarestaurante.controllers;

import br.edu.uepb.sistemarestaurante.models.Comanda;
import br.edu.uepb.sistemarestaurante.models.Garcom;
import br.edu.uepb.sistemarestaurante.models.Pedido;
import br.edu.uepb.sistemarestaurante.models.StatusPedido;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Controlador para exibir notificações de pedidos para o garçom.
 * Permite que o garçom entregue pedidos e atualize seu status.
 *
 * @author Letícia B.M. da Cruz
 * @author Marcella Viana Lins
 */
public class PedidoNotificacaoController {
    /* * Elementos da interface gráfica (FXML)
     * Estes elementos são injetados pelo JavaFX quando o arquivo FXML é carregado.
     */
    @FXML private Label labelPedido;
    /* * Elementos que exibem informações do pedido
     * Este elemento é usado para exibir o número do pedido.
     */
    @FXML private Label labelMesa;
    /** Elementos que exibem informações do pedido
     * Este elemento é usado para exibir a mesa associada ao pedido.
     */
    @FXML private Button botaoEntregar;

    /** Elementos que exibem informações do pedido
     * Este elemento é usado para exibir o botão de entrega do pedido.
     */
    private Pedido pedidoAtual;
    /* * Pedido atual que está sendo exibido.
     * Este objeto contém informações sobre o pedido, como número, mesa e status.
     */
    private Garcom garcom;  // Equivalente ao GerenciadorCozinha

    /**
     * Construtor que inicializa o controlador com o garçom responsável.
     *
     */
    @FXML
    public void initialize() {
        botaoEntregar.setOnAction(event -> entregarPedido());
    }

    /**
     * Método para definir as informações do pedido e a mesa associada.
     *
     * @param numeroPedido Número do pedido
     * @param mesa Mesa associada ao pedido
     * @param pedido Objeto Pedido contendo detalhes do pedido
     */
    public void setPedidoInfo(String numeroPedido, String mesa, Pedido pedido) {
        labelPedido.setText("Pedido #"+numeroPedido);
        labelMesa.setText("- Mesa " + mesa);
        this.pedidoAtual = pedido;

    }

    /**
     * Método para definir o garçom responsável por este pedido.
     *
     */
    private void entregarPedido() {
        try {

            garcom.atualizarStatus(pedidoAtual);
            atualizarBox();

        } catch (IllegalStateException e) {
            labelPedido.setText("Erro: " + e.getMessage());

        }
    }

    /**
     * Método para definir o garçom responsável por este pedido.
     *
     */
    private void atualizarBox() {
        ((HBox) botaoEntregar.getParent()).setVisible(false);
    }
}
