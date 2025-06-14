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

    private String painelMesas = "/br/edu/uepb/sistemarestaurante/views/PainelMesas.fxml";
    private String janelaNovoPedido = "/br/edu/uepb/sistemarestaurante/views/NovoPedido.fxml";
    //private String janelaVerPedidos = "/br/edu/uepb/sistemarestaurante/views/.fxml";
    private int numeroMesa;
    private Garcom garcom;

    public void setMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
        carregarDadosMesa();
    }

    public void setMesaEGarcom(int numeroMesa, Garcom garcom) {
        this.numeroMesa = numeroMesa;
        this.garcom = garcom;
        carregarDadosMesa();
    }

    //  Metodo para configurar os dados da mesa
    public void carregarDadosMesa() {
        if (Mesa.getMesas().get(numeroMesa) == null) {
            System.err.println("Mesa com ID " + numeroMesa + " não encontrada no mapa.");
            return;
        }

        numMesa.setText(String.format("%02d", Mesa.getMesas().get(numeroMesa).getNumero()));

        vboxPedidos.getChildren().clear();

        if (Mesa.getMesas().get(numeroMesa).isOcupada()) {
            Comanda comanda = Mesa.getMesas().get(numeroMesa).getComanda();
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
        janelaUtils.mudarTela(event, painelMesas, "Tela inicial");
    }

    @FXML
    private void chamarAddPedido(ActionEvent event) throws IOException {
        if(!Mesa.getMesas().get(numeroMesa).isOcupada()){
            Mesa.getMesas().get(numeroMesa).ocupar();
            Mesa.getMesas().get(numeroMesa).setComanda(new Comanda(Mesa.getMesas().get(numeroMesa), this.garcom));
        }

        janelaUtils.mudarTela(event, janelaNovoPedido, "Novo Pedido", (NovoPedidoController controller) -> {
            controller.setMesa(numeroMesa);
        });
    }

    @FXML
    private void chamarVerPedidos(ActionEvent event) throws IOException {
        /*
        if(Mesa.getMesas().get(numeroMesa).isOcupada()){
            janelaUtils.mudarTela(event, janelaVerPedidos, "Ver Pedidos", (NOME-DO-CONTROLLER controller) -> {
                controller.setComanda(Mesa.getMesas().get(numeroMesa).getComanda());
            });
        } else {
            alertaUtils.mostrarAlerta("Erro", "A mesa ainda não possui comanda associada!");
        }
        */
    }

    @FXML
    private void fecharComanda(ActionEvent event) throws IOException {
        if(Mesa.getMesas().get(numeroMesa).isOcupada()){
            Mesa.getMesas().get(numeroMesa).liberar();
            Mesa.getMesas().get(numeroMesa).setComanda(null);
            carregarDadosMesa();
            alertaUtils.mostrarInformacao("Sucesso", "Comanda Fechada e Mesa Liberada!");
            // voltarTela(event); // TALVEZ MUDAR PARA A TELA INICIAL
        } else {
            alertaUtils.mostrarAlerta("Erro", "A mesa ainda não possui comanda associada!");
        }
    }

    /*@FXML
    public void initialize() {}*/
}