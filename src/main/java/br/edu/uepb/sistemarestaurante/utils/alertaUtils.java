package br.edu.uepb.sistemarestaurante.utils;

import javafx.scene.control.Alert;

/**
 * Classe utilitária para exibição de alertas e mensagens informativas na tela.
 *
 * <p>
 * Oferece métodos estáticos para mostrar uma janela de alerta do tipo <strong>Warning</strong> ou <strong>Information</strong>,
 * com título e mensagem personalizados.
 * </p>
 *
 * @author Laryssa D. Ramos
 */
public class alertaUtils {

    /**
     * Exibe um alerta do tipo {@link Alert.AlertType#WARNING} com o título e a mensagem especificados.
     *
     * @param titulo   o título da janela de alerta
     * @param mensagem o texto da mensagem a ser exibida no conteúdo do alerta
     */
    public static void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    /**
     * Exibe uma mensagem informativa do tipo {@link Alert.AlertType#INFORMATION} com o título e a mensagem especificados.
     *
     * @param titulo   o título da janela de informação
     * @param mensagem o texto da mensagem a ser exibida no conteúdo da informação
     */
    public static void mostrarInformacao(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
