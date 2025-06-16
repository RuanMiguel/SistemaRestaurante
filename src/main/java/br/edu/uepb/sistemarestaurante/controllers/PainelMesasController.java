package br.edu.uepb.sistemarestaurante.controllers;

import br.edu.uepb.sistemarestaurante.models.*;
import br.edu.uepb.sistemarestaurante.services.LoginService;
import br.edu.uepb.sistemarestaurante.utils.janelaUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controlador responsável por gerenciar a tela do painel de mesas.
 * Permite que o {@link Garcom} visualize e acesse mesas, além de deslogar ou visualizar notificações.
 *
 * @author Marcella Viana da Silva Lins
 */

public class PainelMesasController {

    private static final String COR_DESOCUPADA = "#646363";
    private static final String COR_RESPONSAVEL = "#108635";
    private static final String COR_OUTRO_GARCOM = "#8BC34A";

    @FXML private Button deslogar;
    @FXML private MenuButton menu;
    @FXML private MenuItem notificacao;
//    @FXML private Label nameMesas;
    @FXML private Button mesa1, mesa2, mesa3, mesa4, mesa5, mesa6, mesa7, mesa8;

    private static Garcom garcomAtual;
    private final LoginService loginService = new LoginService();
    private String janelaMesa = "/br/edu/uepb/sistemarestaurante/views/Mesa.fxml";
    private String telaLogin = "/br/edu/uepb/sistemarestaurante/views/LoginView.fxml";
    private String telaNotificacoes = "/br/edu/uepb/sistemarestaurante/views/Notificacoes.fxml";

    /**
     * Método chamado automaticamente pelo JavaFX após a carga do FXML.
     * Configura os botões das mesas com base no estado atual de cada {@link Mesa}.
     */

    @FXML
    public void initialize() {
        configurarMesa(mesa1, 1);
        configurarMesa(mesa2, 2);
        configurarMesa(mesa3, 3);
        configurarMesa(mesa4, 4);
        configurarMesa(mesa5, 5);
        configurarMesa(mesa6, 6);
        configurarMesa(mesa7, 7);
        configurarMesa(mesa8, 8);
    }

    /**
     * Define o garçom atualmente logado e atualiza o painel com seu nome e permissões.
     *
     * @param garcom Garçom atualmente autenticado no sistema.
     */

    @FXML
    public void setGarcomAtual(Garcom garcom) {
        garcomAtual = garcom;
        if (garcom != null) {
//            nameMesas.setText("Garçom: " + garcom.getId());
            atualizarTodasAsCores();
        }
    }


    /**
     * Configura um botão de mesa com base no número da mesa e nas permissões do garçom.
     *
     * @param botao       Botão correspondente à mesa.
     * @param numeroMesa  Número identificador da mesa.
     */

    private void configurarMesa(Button botao, int numeroMesa) {
        Mesa mesa = Mesa.getMesas().get(numeroMesa);
        if (mesa == null) {
            System.err.println("Mesa " + numeroMesa + " não encontrada.");
            return;
        }

        atualizarCorMesa(botao, mesa);

        Comanda comanda = mesa.getComanda();

        boolean podeAcessar = (comanda == null || (comanda.getGarcom().equals(garcomAtual)));

        if (podeAcessar) {
            botao.setDisable(false);
            botao.setOnAction(e -> {
                try {
                    abrirMesa((ActionEvent) e, numeroMesa);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        } else {
            botao.setDisable(true);
        }
    }

    /**
     * Atualiza as cores de todos os botões de mesa conforme o status e o garçom responsável.
     */

    private void atualizarTodasAsCores() {
        configurarMesa(mesa1, 1);
        configurarMesa(mesa2, 2);
        configurarMesa(mesa3, 3);
        configurarMesa(mesa4, 4);
        configurarMesa(mesa5, 5);
        configurarMesa(mesa6, 6);
        configurarMesa(mesa7, 7);
        configurarMesa(mesa8, 8);
    }

    /**
     * Atualiza a cor de um botão de mesa com base na comanda associada.
     *
     * @param botao Botão da mesa.
     * @param mesa  Objeto Mesa associado.
     */

    private void atualizarCorMesa(Button botao, Mesa mesa) {
        Comanda comanda = mesa.getComanda();

        if (comanda == null || comanda.getGarcom() == null || comanda.getGarcom().getId() == null) {
            botao.setStyle("-fx-background-color: " + COR_DESOCUPADA + "; -fx-text-fill: white;");
        } else if (garcomAtual != null && garcomAtual.getId().equals(comanda.getGarcom().getId())) {
            botao.setStyle("-fx-background-color: " + COR_RESPONSAVEL + "; -fx-text-fill: white;");
        } else {
            botao.setStyle("-fx-background-color: " + COR_OUTRO_GARCOM + "; -fx-text-fill: white;");
        }
    }

    /**
     * Abre a tela de detalhes da {@link Mesa} selecionada.
     *
     * @param event        Evento de clique do botão.
     * @param numeroMesa   Número da mesa selecionada.
     * @throws IOException Caso ocorra erro ao carregar a nova janela.
     */

    private void abrirMesa(ActionEvent event, int numeroMesa) throws IOException {
        janelaUtils.mudarTela(event, janelaMesa, "Mesa", (MesaController controller) -> {
            controller.setMesaEGarcom(numeroMesa, garcomAtual);
        });
    }

    /**
     * Realiza o logout do {@link Garcom} e redireciona para a tela de login: {@link LoginController}.
     *
     * @throws IOException Caso ocorra erro ao carregar a tela de login.
     */


    @FXML
    private void deslogarFuncionario(ActionEvent event) throws IOException {
        janelaUtils.mudarTela(event, telaLogin, "Login");
    }

  //falta aplicar e documentar

    @FXML
    private void abrirNotificacoes(ActionEvent event) throws IOException {
        janelaUtils.mudarTela(event, telaNotificacoes, "Notificações");
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/uepb/sistemarestaurante/views/Notificacoes.fxml"));

    }
}
