package br.edu.uepb.sistemarestaurante.controllers;

import br.edu.uepb.sistemarestaurante.models.TipoFuncionario;
import br.edu.uepb.sistemarestaurante.services.LoginService;
import br.edu.uepb.sistemarestaurante.utils.janelaUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController {

    @FXML
    private TextField campoUsuario;

    @FXML
    private PasswordField campoSenha;

    @FXML
    private Button botaoLogin;

    @FXML
    private Label mensagemErro;

    @FXML
    private ImageView imageViewLogo;

    @FXML
    public void initialize() {
        Image logo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/br/edu/uepb/sistemarestaurante/images/logo-restaurante.png")));
        imageViewLogo.setImage(logo);
    }

    private final LoginService ls = new LoginService();
    private Stage Stage;

    @FXML
    void fazerLogin(ActionEvent event) throws IOException {
        String usuario = campoUsuario.getText();
        String senha = campoSenha.getText();
        TipoFuncionario tipo = ls.autenticarFuncionario(usuario, senha);

        if(usuario.isEmpty()|| senha.isEmpty()){
            exibirMensagemErro("Todos os campos devem ser preenchidos!");
            return;
        }

        if (tipo == null){
            exibirMensagemErro("Usuário ou senha incorretos.");

        } else {

            if (tipo == TipoFuncionario.GARCOM) {
                System.out.println("Tela Garcom");
                System.out.println("O garçom logado é: " + ls.getGarcomLogado());
                String painelMesa = "/br/edu/uepb/sistemarestaurante/views/PainelMesa.fxml";//mudei
                janelaUtils.mudarTela(event, painelMesa, "Pedidos à cozinha");//mudei

            } else if (tipo == TipoFuncionario.COZINHA){
                String janelaCozinha = "/br/edu/uepb/sistemarestaurante/views/CozinhaView.fxml";
                janelaUtils.mudarTela(event, janelaCozinha, "Pedidos à cozinha");
                System.out.println("Tela Cozinha");
            }
        }
    }

    private void exibirMensagemErro(String mensagem){
        mensagemErro.setText(mensagem);
        mensagemErro.setVisible(true);
    }

}