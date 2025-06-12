package br.edu.uepb.sistemarestaurante.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Consumer;

public class janelaUtils {

    public static <T> void mudarTela(ActionEvent evento, String caminhoFXML, String titulo, Consumer<T> controllerHandler) throws IOException {
        FXMLLoader loader = new FXMLLoader(janelaUtils.class.getResource(caminhoFXML));
        Parent root = loader.load();

        // Pega o controller e aplica o handler
        T controller = loader.getController();
        if (controllerHandler != null) {
            controllerHandler.accept(controller);
        }

        Stage stage = (Stage) ((Node) evento.getSource()).getScene().getWindow();
        stage.setTitle(titulo);
        stage.setScene(new Scene(root));
        stage.setMaximized(true);
        stage.setWidth(Screen.getPrimary().getBounds().getWidth()); //garantindo que a tela vai abrir maximizada
        stage.setHeight(Screen.getPrimary().getBounds().getHeight()); //garantindo que a tela vai abrir maximizada
        stage.show();
    }

    // Versão sem handler (como antes)
    public static void mudarTela(ActionEvent evento, String caminhoFXML, String titulo) throws IOException {
        mudarTela(evento, caminhoFXML, titulo, null);
    }
}