package br.edu.uepb.sistemarestaurante.controllers;

import br.edu.uepb.sistemarestaurante.models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Controlador para gerenciar a exibição e interação com os pedidos na cozinha.
 * Permite visualizar detalhes do pedido e atualizar seu status.
 *
 * @author Letícia B.M. da Cruz
 * @author Marcella Viana Lins
 */
public class PedidoCozinhaController {
    private Runnable onPedidoPronto;

    public void setOnPedidoPronto(Runnable callback) {
        this.onPedidoPronto = callback;
    }

    /* * Elementos da interface gráfica (FXML)
     */
    @FXML private Label numIDPedido;
    /* * Label que exibe o ID do pedido.
     */
    @FXML private Label horarioPedido;
    /* * Label que exibe o horário do pedido.
     */
    @FXML private Label statusPedido;
    /* * Label que exibe o status atual do pedido.
     */
    @FXML private VBox itensPedidoBox;
    /** VBox que contém os itens do pedido.
     */
    @FXML private Button botaoStatus;

    /** Botão para atualizar o status do pedido.
     * Quando clicado, chama o método para marcar o pedido como pronto ou entregue.
     */
    private Pedido pedido;
    /** Pedido atual que está sendo visualizado e gerenciado.
     */
    private GerenciadorCozinha gc;

    /**
     * Inicializa o controlador, configurando o botão de status.
     * Este método é chamado automaticamente pelo JavaFX após a injeção dos elementos FXML.
     */
    public void setGerenciadorCozinheiro(GerenciadorCozinha gc) {
        this.gc = gc;
    }

    /**
     * Define o pedido a ser exibido e atualizado.
     * Este método deve ser chamado antes de exibir a interface para garantir que o pedido esteja configurado.
     *
     * @param pedido O pedido a ser exibido. Não pode ser null.
     * @throws IllegalArgumentException Se o pedido for null.
     */
    public void setPedido(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não pode ser null!");
        }
        this.pedido = pedido;
        atualizarBox();
    }

    /**
     * Atualiza a exibição dos detalhes do pedido na interface gráfica.
     * Este método é chamado sempre que o pedido é alterado ou atualizado.
     */
    private void atualizarBox() {
        numIDPedido.setText("Pedido #" + pedido.getID());
        horarioPedido.setText("Horário: " + pedido.getHORARIO());
        statusPedido.setText("Status: " + pedido.getStatus().name());

        itensPedidoBox.getChildren().clear();
        for (ItemPedido item : pedido.getItens()) {
            Label lblItem = new Label("(" + item.getQtd() + "x) " + item.getItem());
            itensPedidoBox.getChildren().add(lblItem);

            if (item.getObs() != null && !item.getObs().isEmpty()) {
                Label obsLabel = new Label("        obs: " + item.getObs());
                itensPedidoBox.getChildren().add(obsLabel);
            }
        }

        StatusPedido  statusAtual = pedido.getStatus();
        StatusPedido proximoStatus = StatusPedido.novoStatus(statusAtual);
        if(proximoStatus !=null){
            botaoStatus.setText("Marcar como " + proximoStatus);
            botaoStatus.setDisable(false);
            botaoStatus.setOnAction(e -> marcarComo());
        }else{
            botaoStatus.setText("Entregue");
            botaoStatus.setDisable(true);
        }
    }

    /**
     * Método chamado quando o botão de status é clicado.
     * Atualiza o status do pedido e atualiza a exibição na interface gráfica.
     *
     */
    @FXML
    private void marcarComo() {
        try {
            gc.atualizarStatus(pedido);
            atualizarBox();

            if (pedido.getStatus() == StatusPedido.PRONTO && onPedidoPronto != null) {
                onPedidoPronto.run(); // Notifica o controller pai para remover da UI
            }

        } catch (IllegalStateException e) {
            statusPedido.setText("Erro: " + e.getMessage());
        }
    }
}
