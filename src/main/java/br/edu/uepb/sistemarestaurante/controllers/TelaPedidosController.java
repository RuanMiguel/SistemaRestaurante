package br.edu.uepb.sistemarestaurante.controllers;

import br.edu.uepb.sistemarestaurante.models.Comanda;
import br.edu.uepb.sistemarestaurante.models.Pedido;
import br.edu.uepb.sistemarestaurante.utils.janelaUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TelaPedidosController {

    @FXML private Label numMesa;
    @FXML private Button botaoVoltar;
    @FXML private VBox contentVBox;  // VBox onde a outra tela será inserida

    private String janelaMesa = "/br/edu/uepb/sistemarestaurante/views/Mesa.fxml";
    private Comanda comanda;

    @FXML
    private void initialize() {
        // Aqui você pode fazer alguma inicialização padrão se quiser
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
        numMesa.setText(String.format("%02d", comanda.getMesa().getNumero()));
        carregarTelaListaPedidos();
    }

    private void carregarTelaListaPedidos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/uepb/sistemarestaurante/views/TelaListaPedidos.fxml"));
            Parent telaLista = loader.load();

            TelaListaPedidosController controller = loader.getController();

            for (Pedido pedido : comanda.getPedidos()) {
                controller.adicionarPedido(pedido);
            }

            contentVBox.getChildren().setAll(telaLista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void voltarTela(ActionEvent event) {
        try {
            janelaUtils.mudarTela(event, janelaMesa, "Mesa", (MesaController controller) -> {
                controller.setMesa(comanda.getMesa().getNumero());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}