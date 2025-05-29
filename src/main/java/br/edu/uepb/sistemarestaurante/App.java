package br.edu.uepb.sistemarestaurante;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


import javafx.stage.Stage;


public class App extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

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
