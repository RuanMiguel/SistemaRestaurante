package br.edu.uepb.sistemarestaurante.controllers;

import br.edu.uepb.sistemarestaurante.models.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class ControladorListaPedidos {

    @FXML
    private VBox pedidosContainer;

    @FXML
    private VBox contentVBox;

    public void adicionarPedido(String horario, String status, List<ItemPedido> itens, double total) {
        VBox pedidoBox = new VBox(5);
        pedidoBox.setStyle("-fx-padding: 10; -fx-border-color: black; -fx-border-radius: 5;");

        Label horarioLabel = new Label("Horário: " + horario);
        Label statusLabel = new Label("Status: " + status);
        Label totalLabel = new Label("Total: R$ " + String.format("%.2f", total));

        pedidoBox.getChildren().addAll(horarioLabel, statusLabel);

        for(ItemPedido item : itens) {
            if(item.getItem() instanceof Bebida) {
                Bebida bebida = (Bebida) item.getItem();
                Label itemLabel = new Label(bebida.getNome() + " - " + bebida.getStringVolume() + " x" + item.getQtd());
                pedidoBox.getChildren().add(itemLabel);
            } else {
                Label itemLabel = new Label(item.getItem().getNome() + " x" + item.getQtd());
                pedidoBox.getChildren().add(itemLabel);
            }
        }

        pedidoBox.getChildren().add(totalLabel);

        pedidosContainer.getChildren().add(pedidoBox);
    }

    public void carregarTelaListaPedidos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/uepb/sistemarestaurante/TelaListaPedidos.fxml"));
            Parent listaPedidos = loader.load();
            contentVBox.getChildren().setAll(listaPedidos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

