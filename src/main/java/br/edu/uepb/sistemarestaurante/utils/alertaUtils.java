package br.edu.uepb.sistemarestaurante.utils;

import javafx.scene.control.Alert;

public class alertaUtils {
    public static void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}