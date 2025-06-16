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

/**
 * Classe utilitária para gerenciar mudanças de telas usando FXML.
 *
 * <p>
 * Essa classe oferece métodos estáticos para carregar uma nova cena a partir de um arquivo FXML,
 * definindo o título da janela e ajustando seu tamanho para a tela inteira.
 * Também permite aplicar um handler no controller da nova tela, caso necessário.
 * </p>
 *
 * @author Leticia B. M. da Cruz
 * @author Laryssa D. Ramos
 */
public class janelaUtils {

    /**
     * Altera a tela atual para a tela especificada, com a possibilidade de aplicar uma ação no controller carregado.
     *
     * @param evento o evento de ação que disparou a troca
     * @param caminhoFXML o caminho relativo para o arquivo FXML da nova cena
     * @param titulo o título da janela a ser exibido
     * @param controllerHandler função que recebe o controller da nova tela
     * @param <T> o tipo do controller da nova tela
     * @throws IOException se houver erro ao carregar o arquivo FXML
     */
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

    /**
     * Altera a tela atual para a tela especificada, sem aplicar nenhuma ação no controller.
     *
     * @param evento       o evento de ação que disparou a troca
     * @param caminhoFXML  o caminho relativo para o arquivo FXML da nova cena
     * @param titulo       o título da janela a ser exibido
     * @throws IOException se houver erro ao carregar o arquivo FXML
     */
    public static void mudarTela(ActionEvent evento, String caminhoFXML, String titulo) throws IOException {
        mudarTela(evento, caminhoFXML, titulo, null);
    }
}
