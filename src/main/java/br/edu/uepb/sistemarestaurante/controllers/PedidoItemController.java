package br.edu.uepb.sistemarestaurante.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import br.edu.uepb.sistemarestaurante.models.*;

/**
 * Controlador para exibir os detalhes de um pedido específico, incluindo
 * informações do pedido e seus itens.
 *
 * @author Letícia B.M. da Cruz
 * @author Marcella Viana Lins
 */
public class PedidoItemController {

    /* * Elementos da interface gráfica (FXML)
     * Estes elementos são injetados pelo JavaFX quando o arquivo FXML é carregado.
     */
    @FXML
    private VBox pedidoBox;

    /** Elementos que exibem informações do pedido
     * Estes elementos são usados para mostrar o ID do pedido, horário, status e itens.
     */
    @FXML
    private Label numIDPedido;

    /** Elementos que exibem informações do pedido
     * Estes elementos são usados para mostrar o horário do pedido e seu status.
     */
    @FXML
    private Label horarioPedido;

    /** Elementos que exibem informações do pedido
     * Estes elementos são usados para mostrar o status do pedido e os itens incluídos.
     */
    @FXML
    private Label statusPedido;

    /** Elementos que exibem informações do pedido
     * Este elemento é usado para exibir os itens do pedido em uma lista vertical.
     */
    @FXML
    private VBox containerItensPedido;
    /** Botão para alterar o status do pedido
     * Este botão permite ao usuário marcar o pedido como concluído ou avançar para o próximo status.
     */
    @FXML
    private Button botaoStatus;

    /**
     * Método chamado quando o botão de status é clicado.
     * Ele altera o status do pedido para o próximo status definido na enumeração StatusPedido.
     */
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