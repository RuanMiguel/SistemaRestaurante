package br.edu.uepb.sistemarestaurante.controllers;

import br.edu.uepb.sistemarestaurante.models.*;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

    //  Metodo para configurar os dados da mesa
    public void carregarDadosMesa(Mesa mesa) {
        numMesa.setText(String.format("%02d", mesa.getNumero()));

        vboxPedidos.getChildren().clear();

        Comanda comanda = mesa.getComanda();
        List<Pedido> pedidos = comanda.getPedidos();

        if (!pedidos.isEmpty()) {
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
    public void initialize() {
        // Simulação
        Mesa m = new Mesa(2,4);
        Comanda c = new Comanda();
        m.setComanda(c);

        Pedido p = new Pedido();
        ItemPedido i = new ItemPedido(new Sobremesa("pave", 12),1,"");
        p.adicionarItem(i);
        c.adicionarPedido(p);

        Pedido p2 = new Pedido();
        ItemPedido i2 = new ItemPedido(new Sobremesa("pudim", 9),2,"");
        p2.adicionarItem(i2);
        c.adicionarPedido(p2);
        c.adicionarPedido(p2);

        // Mostra na tela
        carregarDadosMesa(m);
    }

}