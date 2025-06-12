package br.edu.uepb.sistemarestaurante.controllers;

import br.edu.uepb.sistemarestaurante.models.*;
import br.edu.uepb.sistemarestaurante.utils.alertaUtils;
import br.edu.uepb.sistemarestaurante.utils.janelaUtils;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.List;

public class MesaController {

    @FXML
    private Button botaoAddPedido;

    @FXML
    private Button botaoFecharComanda;

    @FXML
    private Button botaoVerPedidos;

    @FXML
    private Button botaoVoltar;

    @FXML
    private Label numComanda;

    @FXML
    private Label numMesa;

    @FXML
    private Label totalComanda;

    @FXML
    private VBox vboxPedidos;

    private String janelaNovoPedido = "/br/edu/uepb/sistemarestaurante/views/NovoPedido.fxml";
    //private String janelaVerPedidos = "/br/edu/uepb/sistemarestaurante/views/.fxml";
    private Mesa mesa;

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
        carregarDadosMesa();
    }

    //  Metodo para configurar os dados da mesa
    public void carregarDadosMesa() {
        numMesa.setText(String.format("%02d", mesa.getNumero()));

        vboxPedidos.getChildren().clear();

        if (mesa.isOcupada()) {
            Comanda comanda = mesa.getComanda();
            List<Pedido> pedidos = comanda.getPedidos();

            numComanda.setText(String.valueOf(comanda.getID()));
            totalComanda.setText(String.format("R$ %.2f", comanda.caucularTotal()));

            for (Pedido pedido : pedidos) {
                // Cria a estrutura de uma linha de pedido
                HBox linha = new HBox();
                linha.setPrefWidth(270);
                linha.setAlignment(Pos.BASELINE_CENTER);
                linha.setStyle("-fx-padding: 5 15 5 15;");

                HBox hboxEsquerda = new HBox();
                hboxEsquerda.setAlignment(Pos.BOTTOM_LEFT);
                hboxEsquerda.setPrefWidth(200);

                Label lblPedido = new Label("Pedido " + pedido.getID());
                lblPedido.setStyle("-fx-font-size: 15px;");
                hboxEsquerda.getChildren().add(lblPedido);

                HBox hboxDireita = new HBox();
                hboxDireita.setAlignment(Pos.CENTER_RIGHT);
                hboxDireita.setPrefWidth(200);

                Label lblValor = new Label(String.format("R$ %.2f", pedido.calcularTotal()));
                lblValor.setStyle("-fx-font-size: 15px;");
                hboxDireita.getChildren().add(lblValor);

                linha.getChildren().addAll(hboxEsquerda, hboxDireita);
                vboxPedidos.getChildren().add(linha);
            }
        } else {
            numComanda.setText("0");
            totalComanda.setText("R$ 0.00");
        }
    }

    @FXML
    private void voltarTela(ActionEvent event) throws IOException {
        //TO DO
        //janelaUtils.mudarTela(event, CAMINHO, "Tela inicial");
    }

    @FXML
    private void chamarAddPedido(ActionEvent event) throws IOException {
        if(!this.mesa.isOcupada()){
            mesa.ocupar();
            mesa.setComanda(new Comanda());
        }

        janelaUtils.mudarTela(event, janelaNovoPedido, "Novo Pedido", (NovoPedidoController controller) -> {
            controller.setMesa(this.mesa);
        });
    }

    @FXML
    private void chamarVerPedidos(ActionEvent event) throws IOException {
        //TO DO
        /*
        janelaUtils.mudarTela(event, janelaVerPedidos, "Ver Pedidos", (NOME-DO-CONTROLLER controller) -> {
            controller.setMesa(this.mesa);
        });
        */
    }

    @FXML
    private void fecharComanda(ActionEvent event) throws IOException {
        if(this.mesa.isOcupada()){
            mesa.liberar();
            mesa.setComanda(null);
            carregarDadosMesa();
            alertaUtils.mostrarInformacao("Sucesso", "Comanda Fechada e Mesa Liberada!");
        } else {
            alertaUtils.mostrarAlerta("Erro", "A mesa ainda não possui comanda associada!");
        }
        //TALVEZ MUDAR PARA A TELA INICIAL
    }

    @FXML
    public void initialize() {
        if (mesa != null) {
            carregarDadosMesa();
        }
    }
}