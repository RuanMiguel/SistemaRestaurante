package br.edu.uepb.sistemarestaurante;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

/**
 * Classe principal da aplicação JavaFX que inicia a interface gráfica do sistema de gerenciamento de comandas e pedidos.
 * Carrega o arquivo FXML da tela de login e exibe a janela principal.
 *
 * @author Marcella Viana da Silva Lins
 */
public class App extends Application {
    /**
     * Método principal que inicia a aplicação JavaFX.
     * É o ponto de entrada da aplicação.
     *
     * @param args Argumentos de linha de comando (não utilizados neste caso).
     * @throws Exception Se ocorrer algum erro ao iniciar a aplicação.
     *
     * @author Marcella Viana da Silva Lins
     */
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    /**
     * Método que inicia a aplicação JavaFX.
     * Carrega o arquivo FXML da tela de login e exibe a janela principal.
     *
     * @param primaryStage A janela principal da aplicação.
     * @throws Exception Se ocorrer algum erro ao carregar o FXML.
     *
     * @author Marcella Viana da Silva Lins
     */

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/edu/uepb/sistemarestaurante/views/LoginView.fxml"));
        Parent root = fxmlLoader.load();
        Scene tela = new Scene(root, 520, 400);

        primaryStage.setTitle("Gerenciador de Comandas e Pedidos");
        primaryStage.setScene(tela);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
