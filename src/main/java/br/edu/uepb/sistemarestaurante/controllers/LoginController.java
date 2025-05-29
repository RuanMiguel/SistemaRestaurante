package br.edu.uepb.sistemarestaurante.controllers;

import br.edu.uepb.sistemarestaurante.models.TipoFuncionario;
import br.edu.uepb.sistemarestaurante.services.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField campoUsuario;

    @FXML
    private PasswordField campoSenha;

    @FXML
    private Button botaoLogin;

    @FXML
    private Label mensagemErro;

    private final LoginService ls = new LoginService();
    private Stage Stage;

    @FXML
    void fazerLogin(ActionEvent event) {
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
            mensagemErro.setVisible(false);

            if (tipo == TipoFuncionario.GARCOM) {
                //REDIRECIONAR TELA GARCOM
                System.out.println("Tela Garcom");
            } else if (tipo == TipoFuncionario.COZINHA){
                //REDIRECIONAR TELA COZINHA
                System.out.println("Tela Garcom");
            }
        }
    }

    private void exibirMensagemErro(String mensagem){
        mensagemErro.setText(mensagem);
        mensagemErro.setVisible(true);
    }

    private void redirecionarTelaInicialGarcom(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/uepb/sistemarestaurante/views/CozinhaView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Tela do Gaçom"); //tela mesas
            stage.setScene(new Scene(root));
            stage.show();

            fecharTelaAtual();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void redirecionarTelaInicialCozinha(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/uepb/sistemarestaurante/views/CozinhaView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Tela da Cozinha");
            stage.setScene(new Scene(root));
            stage.show();
            
            fecharTelaAtual();
            
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    private void fecharTelaAtual(){
        //fecharTela:
        Stage stage = (Stage);
        campoUsuario.getScene().getWindow();
        stage.close();
    }
    



}
