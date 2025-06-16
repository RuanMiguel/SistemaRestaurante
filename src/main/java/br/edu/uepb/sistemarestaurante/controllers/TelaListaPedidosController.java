package br.edu.uepb.sistemarestaurante.controllers;

import br.edu.uepb.sistemarestaurante.models.ItemPedido;
import br.edu.uepb.sistemarestaurante.models.Pedido;
import br.edu.uepb.sistemarestaurante.models.StatusPedido;
import br.edu.uepb.sistemarestaurante.models.Bebida;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.time.format.DateTimeFormatter;


/**
 * Controlador para a tela de lista de pedidos.
 * Responsável por exibir os pedidos realizados no restaurante.
 * Permite adicionar novos pedidos à lista exibida na interface gráfica.
 *
 * @author Marcella Viana Lins
 * @author Ruan M ds S. Costa
 */
public class TelaListaPedidosController {

    /* * VBox que contém a lista de pedidos.
     * Cada pedido será adicionado como um card dentro deste VBox.
     */
    @FXML private VBox vboxListaPedidos;


    /*public void adicionarPedido(Pedido pedido) {
        VBox pedidoBox = new VBox(5);
        pedidoBox.setStyle("-fx-padding: 10; -fx-border-color: black; -fx-border-radius: 5;");

        Label idLabel = new Label("Pedido #" + pedido.getID());
        Label horarioLabel = new Label("Horário: " + pedido.getHORARIO());
        Label statusLabel = new Label("Status: " + pedido.getStatus());
        Label totalLabel = new Label("Total: R$ " + String.format("%.2f", pedido.calcularTotal()));

        pedidoBox.getChildren().addAll(idLabel, horarioLabel, statusLabel);

        for (ItemPedido item : pedido.getItens()) {
            String itemDescricao;
            if (item.getItem() instanceof Bebida bebida) {
                itemDescricao = bebida.getNome() + " - " + bebida.getStringVolume() + " x" + item.getQtd();
            } else {
                itemDescricao = item.getItem().getNome() + " x" + item.getQtd();
            }
            Label itemLabel = new Label(itemDescricao);
            pedidoBox.getChildren().add(itemLabel);
        }

        pedidoBox.getChildren().add(totalLabel);
        vboxListaPedidos.getChildren().add(pedidoBox);
    }*/

    /**
     * Adiciona um pedido à lista de pedidos exibida na interface gráfica.
     * Cria um card visual para o pedido, contendo informações como ID, horário,
     * status e itens do pedido.
     *
     * @param pedido O objeto Pedido a ser adicionado à lista.
     */
    public void adicionarPedido (Pedido pedido) {
        VBox cardPedido = new VBox();
        cardPedido.setSpacing(5);
        cardPedido.setPadding(new Insets(15));
        cardPedido.setStyle("-fx-border-color: black; -fx-border-width: 1;");
        cardPedido.setMaxWidth(400);
        cardPedido.setAlignment(Pos.CENTER_LEFT);

        Label titulo = new Label("Pedido #" + pedido.getID());
        titulo.setFont(Font.font("System", FontWeight.BOLD, 20));
        titulo.setAlignment(Pos.CENTER);
        titulo.setMaxWidth(Double.MAX_VALUE);

        Label horario = new Label(pedido.getHORARIO().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        horario.setFont(Font.font(14));
        horario.setAlignment(Pos.CENTER);
        horario.setMaxWidth(Double.MAX_VALUE);

        Label status = new Label("Status: " + pedido.getStatus());
        status.setFont(Font.font(14));
        status.setAlignment(Pos.CENTER);
        status.setMaxWidth(Double.MAX_VALUE);

        Separator sep = new Separator();

        VBox itensBox = new VBox();
        itensBox.setSpacing(3);
        for (ItemPedido item : pedido.getItens()) {
            String nome;
            if (item.getItem() instanceof Bebida bebida) {
                nome = bebida.getNome() + " - " + bebida.getStringVolume();
            }else{
                nome = item.getItem().getNome();
            }
            String textoItem = String.format("(%dx) %s%s%.2f",
                    item.getQtd(),
                    nome,
                    "  - R$ ",
                    item.CalcularSubtotal());
            Label itemLabel = new Label(textoItem);
            itemLabel.setFont(Font.font("System", 14));
            itensBox.getChildren().add(itemLabel);
        }

        Label total = new Label("Total  - " + String.format("R$ %.2f", pedido.calcularTotal()));
        total.setFont(Font.font("System", FontWeight.BOLD, 14));

        cardPedido.getChildren().addAll(titulo, horario, status, sep, itensBox, total);
        vboxListaPedidos.getChildren().add(cardPedido);
    }
}
