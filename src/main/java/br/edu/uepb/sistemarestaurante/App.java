package br.edu.uepb.sistemarestaurante;

import br.edu.uepb.sistemarestaurante.controllers.MesaController;
import br.edu.uepb.sistemarestaurante.models.Mesa;
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/edu/uepb/sistemarestaurante/views/Mesa.fxml"));
        Parent root = fxmlLoader.load();
        Scene tela = new Scene(root);

        MesaController controller = fxmlLoader.getController();
        controller.setMesa(new Mesa(2,4));

        primaryStage.setTitle("Mesa de Teste");
        primaryStage.setScene(tela);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}