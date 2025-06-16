package br.edu.uepb.sistemarestaurante.controllers;

import br.edu.uepb.sistemarestaurante.models.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

/**
 * Controlador responsável por gerenciar a lista de pedidos na interface do usuário.
 * Permite adicionar pedidos à lista e carregar a tela de lista de pedidos.
 *
 * @author Letícia B.M. da Cruz
 * @author Marcella Viana Lins
 */
public class ControladorListaPedidos {

    /* * Container onde os pedidos serão exibidos.
     * Utiliza VBox para organizar os pedidos verticalmente.
     */
    @FXML
    private VBox pedidosContainer;

    /** Container onde a tela de lista de pedidos será carregada.
     * Utiliza VBox para permitir a substituição do conteúdo dinamicamente.
     */
    @FXML
    private VBox contentVBox;

    /**
     * Método chamado para inicializar o controlador após o carregamento do FXML.
     * Pode ser usado para configurar a interface ou carregar dados iniciais.
     */
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

    /**
     * Carrega a tela de lista de pedidos na interface do usuário.
     * Utiliza o FXMLLoader para carregar o FXML correspondente e substitui o conteúdo atual.
     */
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

