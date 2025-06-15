package br.edu.uepb.sistemarestaurante.controllers;

import br.edu.uepb.sistemarestaurante.models.Comanda;
import br.edu.uepb.sistemarestaurante.models.Garcom;
import br.edu.uepb.sistemarestaurante.models.Pedido;
import br.edu.uepb.sistemarestaurante.models.StatusPedido;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class PedidoNotificacaoController {
    @FXML private Label labelPedido;
    @FXML private Label labelMesa;
    @FXML private Button botaoEntregar;

    private Pedido pedidoAtual;
    private Garcom garcom;  // Equivalente ao GerenciadorCozinha

    // Injeção do gerenciador (similar ao do cozinheiro)
    public void setGerenciadorGarcom(Garcom garcom) {
        this.garcom = garcom;
    }

    @FXML
    public void initialize() {
        botaoEntregar.setOnAction(event -> entregarPedido());
    }

    public void setPedidoInfo(String numeroPedido, String mesa, Pedido pedido) {
        labelPedido.setText("Pedido #"+numeroPedido);
        labelMesa.setText("- Mesa " + mesa);
        this.pedidoAtual = pedido;

    }

    private void entregarPedido() {
        try {

            garcom.atualizarStatus(pedidoAtual);
            atualizarBox();

        } catch (IllegalStateException e) {
            labelPedido.setText("Erro: " + e.getMessage());

        }
    }

    private void atualizarBox() {
        ((HBox) botaoEntregar.getParent()).setVisible(false);
    }
}
