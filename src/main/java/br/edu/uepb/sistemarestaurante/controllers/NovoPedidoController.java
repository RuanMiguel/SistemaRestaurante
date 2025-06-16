package br.edu.uepb.sistemarestaurante.controllers;

import br.edu.uepb.sistemarestaurante.dao.ItensCardapioDAO;
import br.edu.uepb.sistemarestaurante.models.*;
import br.edu.uepb.sistemarestaurante.utils.alertaUtils;
import br.edu.uepb.sistemarestaurante.utils.janelaUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Controller responsável por gerenciar a criação de novos pedidos para a coamnda de uma mesa no sistema de restaurante.
 * Permite selecionar itens do cardápio, adicionar observações, quantidades, e confirmar o pedido.
 * Interage com as entidades {@link Mesa}, {@link Comanda}, {@link Pedido}, {@link ItemPedido}, {@link ItemCardapio}, entre outras.
 * Utiliza DAOs e utilitários para manipular dados e trocar de telas.
 *
 * @author Laryssa D. Ramos
 */
public class NovoPedidoController {

    @FXML
    private Button addItem;

    @FXML
    private Button addPedido;

    @FXML
    private Button botaoVoltar;

    @FXML
    private ComboBox<String> cardapio;

    @FXML
    private ComboBox<String> item;

    @FXML
    private Label numMesa;

    @FXML
    private TextField observacao;

    @FXML
    private Spinner<Integer> quantidade;

    @FXML
    private ComboBox<String> subCardapio;

    @FXML
    private VBox subCardapioContainer;

    private String janelaMesa = "/br/edu/uepb/sistemarestaurante/views/Mesa.fxml";
    private int numeroMesa;
    private Garcom garcom;
    private Pedido novoPedido = new Pedido();
    private String cardapioSelecionado;
    private String subCardapioSelecionado;
    private ItemCardapio itemSelecionado;
    private Integer qtdSelecionada;
    private String obsEscrita;

    private List<String> categoriasCardapio = new ArrayList<>(Arrays.asList("Pratos", "Bebidas", "Sobremesas"));
    private ObservableList<String> obsCategoriasCardapio = FXCollections.observableArrayList(categoriasCardapio);

    private SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50);

    /**
     * Define a mesa atual para a qual o pedido será feito e o garçom responsável.
     *
     * @param numeroMesa o número da mesa
     * @param garcom o garçom responsável pela mesa
     */
    public void setMesaEGarcom(int numeroMesa, Garcom garcom) {
        this.numeroMesa = numeroMesa;
        this.garcom = garcom;
        numMesa.setText(String.format("%02d", Mesa.getMesas().get(numeroMesa).getNumero()));
    }

    /**
     * Inicializa os seletores de cardápio, subcardápio, item e quantidade.
     */
    public void carregarSeletores(){
        cardapio.setItems(obsCategoriasCardapio);
        carregarCBSubCardapio();
        carregarCBItem();
        valueFactory.setValue(1);
        quantidade.setValueFactory(valueFactory);
    }

    /**
     * Carrega os tipos de subcardápio no ComboBox, caso a categoria selecionada no cardápio principal seja "Pratos" ou "Bebidas".
     * Caso a categoria selecionada no cardápio principal seja "Sobremesas", atualiza a lista de itens para esta categoria e torna o ComboBox de subcardápio invisível.
     */
    public void carregarCBSubCardapio() {
        cardapioSelecionado = cardapio.getValue();
        cardapio.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                cardapioSelecionado = cardapio.getValue();

                if (cardapioSelecionado == null) return;

                switch (cardapioSelecionado) {
                    case "Pratos":
                        subCardapio.setItems(FXCollections.observableArrayList(
                                Arrays.stream(TipoPrato.values())
                                        .map(TipoPrato::getTipo)
                                        .toList()
                        ));
                        subCardapioContainer.setVisible(true);
                        subCardapioContainer.setManaged(true);
                        break;

                    case "Bebidas":
                        subCardapio.setItems(FXCollections.observableArrayList(
                                Arrays.stream(TipoBebida.values())
                                        .map(TipoBebida::getTipo)
                                        .toList()
                        ));
                        subCardapioContainer.setVisible(true);
                        subCardapioContainer.setManaged(true);
                        break;

                    case "Sobremesas":
                        subCardapioContainer.setVisible(false);
                        subCardapioContainer.setManaged(false);

                        ItensCardapioDAO dao = new ItensCardapioDAO();
                        List<String> sobremesas = new ArrayList<>();
                        for(ItemCardapio i : dao.listarItensCardapio(cardapioSelecionado, cardapioSelecionado.equals("Sobremesas") ? null : subCardapioSelecionado)){
                            sobremesas.add(i.getNome());
                        }
                        item.setItems(FXCollections.observableArrayList(sobremesas));

                        break;
                }
            }
        });
    }

    /**
     * Carrega no ComboBox os itens disponíveis, conforme o subcardápio selecionado.
     */
    public void carregarCBItem(){
        cardapioSelecionado = cardapio.getValue();
        subCardapioSelecionado = subCardapio.getValue();
        subCardapio.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                subCardapioSelecionado = subCardapio.getValue();

                if (subCardapioSelecionado == null) return;

                ItensCardapioDAO dao = new ItensCardapioDAO();
                List<String> nomeItens = new ArrayList<>();

                for(ItemCardapio i : dao.listarItensCardapio(cardapioSelecionado, subCardapioSelecionado)){
                    if(i instanceof Bebida) {
                        Bebida bebida = (Bebida) i;
                        nomeItens.add(i.getNome()+" - "+bebida.getStringVolume());
                    } else {
                        nomeItens.add(i.getNome());
                    }
                }

                item.setItems(FXCollections.observableArrayList(nomeItens));
            }
        });
    }


    /**
     * Manipula o clique no botão "Voltar", redirecionando para a tela da mesa atual.
     *
     * @param event o evento de clique no botão "Voltar"
     * @throws IOException se a tela não puder ser carregada
     */
    @FXML
    private void voltarTela(ActionEvent event) throws IOException {
        janelaUtils.mudarTela(event, janelaMesa, "Mesa", (MesaController controller) -> {
            controller.setMesa(numeroMesa);
        });
    }

    /**
     * Adiciona o item selecionado ao pedido atual com base nas seleções do usuário, ao clicar no botão "Adicionar item ao pedido".
     * Realiza validações e exibe alertas em caso de erro.
     */
    @FXML
    private void adicionarItemAoPedido() {
        String nomeItem = item.getValue();
        qtdSelecionada = quantidade.getValue();
        obsEscrita = observacao.getText();

        // Verificações
        if (cardapioSelecionado == null || cardapioSelecionado.isEmpty()) {
            alertaUtils.mostrarAlerta("Erro", "Selecione uma categoria de cardápio.");
            return;
        }

        if ((cardapioSelecionado.equals("Pratos") || cardapioSelecionado.equals("Bebidas"))
                && (subCardapioSelecionado == null || subCardapioSelecionado.isEmpty())) {
            alertaUtils.mostrarAlerta("Erro", "Selecione um tipo dentro do cardápio.");
            return;
        }

        if (nomeItem == null || nomeItem.isEmpty()) {
            alertaUtils.mostrarAlerta("Erro", "Selecione um item para adicionar.");
            return;
        }

        if (qtdSelecionada == null || qtdSelecionada <= 0) {
            alertaUtils.mostrarAlerta("Erro", "Quantidade inválida.");
            return;
        }

        // Obter o itemSelecionado completo a partir do nome (buscando no DAO)
        ItensCardapioDAO dao = new ItensCardapioDAO();
        itemSelecionado = null;

        for (ItemCardapio i : dao.listarItensCardapio(cardapioSelecionado, subCardapioSelecionado)) {
            String nomeFormatado = i.getNome();
            if (i instanceof Bebida) {
                nomeFormatado += " - " + ((Bebida) i).getStringVolume();
            }
            if (nomeFormatado.equals(nomeItem)) {
                itemSelecionado = i;
                break;
            }
        }

        if (itemSelecionado == null) {
            alertaUtils.mostrarAlerta("Erro", "Item não encontrado. Verifique suas seleções.");
            return;
        }

        try {
            // Adiciona ao pedido
            novoPedido.adicionarItem(itemSelecionado, qtdSelecionada, obsEscrita);

            // Limpar campos e resetar seleções
            cardapio.getSelectionModel().clearSelection();
            subCardapio.getSelectionModel().clearSelection();
            item.getSelectionModel().clearSelection();
            observacao.clear();
            quantidade.getValueFactory().setValue(1);

            // Reset variáveis
            cardapioSelecionado = null;
            subCardapioSelecionado = null;
            itemSelecionado = null;
            qtdSelecionada = null;
            obsEscrita = null;

            // Atualiza os seletores (lista de categorias)
            carregarSeletores();
        } catch (Exception e) {
            alertaUtils.mostrarAlerta("Erro", "Erro ao adicionar item ao pedido: " + e.getMessage());
            e.printStackTrace(); // Para log ou debug
        }
    }

    /**
     * Verifica se a mesa està desocupada, ocupando-a e criando uma nova comanda em caso afirmativo.
     * Adiciona o pedido atual à comanda da mesa e à lista estatica de pedidos da classe {@link Pedido} e retorna à tela da mesa atual.
     *
     * @param event o evento do botão
     * @throws IOException se houver erro ao mudar de tela
     */
    @FXML
    private void adicionarPedidoAComanda(ActionEvent event) throws IOException {
        if(!novoPedido.getItens().isEmpty()) {
            if(!Mesa.getMesas().get(numeroMesa).isOcupada()){
                Mesa.getMesas().get(numeroMesa).ocupar();
                Mesa.getMesas().get(numeroMesa).setComanda(new Comanda(Mesa.getMesas().get(numeroMesa), this.garcom));
            }

            Mesa.getMesas().get(numeroMesa).getComanda().adicionarPedido(novoPedido);

            janelaUtils.mudarTela(event, janelaMesa, "Mesa", (MesaController controller) -> {
                controller.setMesa(numeroMesa);
            });
        } else {
            alertaUtils.mostrarAlerta("Erro", "Adicione pelo menos um item ao pedido!");
        }
    }

    /**
     * Metodo chamado automaticamente pelo JavaFX após o carregamento do FXML.
     * Inicializa os seletores de cardápio e configura listeners.
     */
    @FXML
    public void initialize() {
        carregarSeletores();
    }
}
