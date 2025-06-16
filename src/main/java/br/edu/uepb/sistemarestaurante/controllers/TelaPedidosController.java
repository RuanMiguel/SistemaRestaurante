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

/**
 * Controlador para a tela de pedidos.
 * Responsável por exibir os pedidos de uma comanda específica.
 * Permite navegar de volta para a tela da mesa associada à comanda.
 *
 * @author Letícia B.M. da Cruz
 * @author Marcella Viana Lins
 */
public class TelaPedidosController {

    /** Label que exibe o número da mesa associada à comanda.
     * É atualizado quando a comanda é definida.
     */
    @FXML private Label numMesa;
    /** Botão para voltar à tela da mesa.
     * Aciona o método voltarTela quando clicado.
     */
    @FXML private Button botaoVoltar;
    /** VBox que contém a lista de pedidos.
     * A tela de pedidos será carregada dentro deste VBox.
     */
    @FXML private VBox contentVBox;  // VBox onde a outra tela será inserida

    /** Caminho para a tela da mesa.
     * Usado para retornar à tela da mesa associada à comanda.
     */
    private String janelaMesa = "/br/edu/uepb/sistemarestaurante/views/Mesa.fxml";
    /** Comanda associada a esta tela de pedidos.
     * Contém os pedidos que serão exibidos.
     */
    private Comanda comanda;

    /**
     * Método chamado ao inicializar a tela.
     * Pode ser usado para configurações iniciais, se necessário.
     */
    @FXML
    private void initialize() {
        // Aqui você pode fazer alguma inicialização padrão se quiser
    }

    /**
     * Define a comanda associada a esta tela de pedidos.
     * Atualiza o número da mesa e carrega a lista de pedidos.
     *
     * @param comanda A comanda que contém os pedidos a serem exibidos.
     */
    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
        numMesa.setText(String.format("%02d", comanda.getMesa().getNumero()));
        carregarTelaListaPedidos();
    }

    /**
     * Carrega a tela de lista de pedidos e exibe os pedidos da comanda.
     * Cada pedido é adicionado à interface gráfica.
     */
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

    /**
     * Método acionado ao clicar no botão de voltar.
     * Navega de volta para a tela da mesa associada à comanda.
     *
     * @param event O evento de ação do botão.
     */
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