package br.edu.uepb.sistemarestaurante.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class janelaUtils {

    public static void mudarTela(ActionEvent evento, String caminhoFXML, String titulo) throws IOException {

        FXMLLoader loader = new FXMLLoader(janelaUtils.class.getResource(caminhoFXML));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) evento.getSource()).getScene().getWindow();
        stage.setTitle(titulo);
        stage.setScene(new Scene(root));
        stage.setMaximized(true);
        stage.show();
    }
}
