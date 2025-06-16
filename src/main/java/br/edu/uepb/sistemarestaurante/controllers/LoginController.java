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

/**
 * Controlador responsável pela tela de login do sistema de restaurante.
 * Permite que o usuário faça login com suas credenciais e redireciona para a tela apropriada.
 *
 * @author Laryssa D. Ramos
 * @author Marcella Viana da Silva Lins
 * @author Letícia B.M. da Cruz
 */
public class LoginController {

    /**
     * Campo de texto para o usuário inserir o nome de usuário.
     */
    @FXML
    private TextField campoUsuario;

    /**
     * Campo de senha para o usuário inserir sua senha.
     */
    @FXML
    private PasswordField campoSenha;

    /**
     * Botão para acionar o login.
     * Quando clicado, chama o método fazerLogin.
     */
    @FXML
    private Button botaoLogin;

    /**
     * Label para exibir mensagens de erro durante o processo de login.
     * É visível apenas quando há um erro.
     */
    @FXML
    private Label mensagemErro;

    /**
     * ImageView para exibir o logo do restaurante na tela de login.
     * A imagem é carregada a partir dos recursos do projeto.
     */
    @FXML
    private ImageView imageViewLogo;

    /**
     * Método chamado ao inicializar o controlador.
     * Carrega a imagem do logo do restaurante na ImageView.
     */
    @FXML
    public void initialize() {
        Image logo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/br/edu/uepb/sistemarestaurante/images/logo-restaurante.png")));
        imageViewLogo.setImage(logo);
    }

    /**
     * Serviço de login utilizado para autenticar o usuário.
     * É uma instância da classe LoginService, que contém a lógica de autenticação.
     */
    private final LoginService ls = new LoginService();
    /**
     * Referência para a Stage atual, usada para mudar de tela.
     * É injetada pelo JavaFX quando o controlador é carregado.
     */
    private Stage Stage;

    /**
     * Método chamado quando o botão de login é clicado.
     * Verifica as credenciais do usuário e redireciona para a tela apropriada.
     *
     * @param event O evento de ação do botão de login.
     * @throws IOException Se ocorrer um erro ao carregar a nova tela.
     */
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
                String painelMesa = "/br/edu/uepb/sistemarestaurante/views/PainelMesas.fxml";

                janelaUtils.mudarTela(event, painelMesa, "Pedidos da Mesa", (PainelMesasController controller) -> {
                    controller.setGarcomAtual(ls.getGarcomLogado());
                });

        } else if (tipo == TipoFuncionario.COZINHA){
                String janelaCozinha = "/br/edu/uepb/sistemarestaurante/views/CozinhaView.fxml";
                janelaUtils.mudarTela(event, janelaCozinha, "Pedidos à cozinha");
                System.out.println("Tela Cozinha");
            } else {
                System.out.println("Não encontrado um tipo de funcionário válido.");
            }
        }
    }

    /**
     * Exibe uma mensagem de erro na interface do usuário.
     * A mensagem é definida no Label mensagemErro e torna-se visível.
     *
     * @param mensagem A mensagem de erro a ser exibida.
     */
    private void exibirMensagemErro(String mensagem){
        mensagemErro.setText(mensagem);
        mensagemErro.setVisible(true);
    }

}