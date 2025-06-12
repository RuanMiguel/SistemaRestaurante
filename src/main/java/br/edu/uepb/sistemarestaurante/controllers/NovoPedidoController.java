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
    private Mesa mesa;
    private Pedido novoPedido = new Pedido();
    private String cardapioSelecionado;
    private String subCardapioSelecionado;
    private ItemCardapio itemSelecionado;
    private Integer qtdSelecionada;
    private String obsEscrita;

    private List<String> categoriasCardapio = new ArrayList<>(Arrays.asList("Pratos", "Bebidas", "Sobremesas"));
    private ObservableList<String> obsCategoriasCardapio = FXCollections.observableArrayList(categoriasCardapio);

    private SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50);

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
        numMesa.setText(String.format("%02d", mesa.getNumero()));
    }

    public void carregarSeletores(){
        cardapio.setItems(obsCategoriasCardapio);
        carregarCBSubCardapio();
        carregarCBItem();
        valueFactory.setValue(1);
        quantidade.setValueFactory(valueFactory);
    }

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
                        for(ItemCardapio i : dao.listarItensCardapio(cardapioSelecionado, null)){
                            sobremesas.add(i.getNome());
                        }
                        item.setItems(FXCollections.observableArrayList(sobremesas));

                        break;
                }
            }
        });
    }

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


    @FXML
    private void voltarTela(ActionEvent event) throws IOException {
        janelaUtils.mudarTela(event, janelaMesa, "Mesa", (MesaController controller) -> {
            controller.setMesa(this.mesa);
        });
    }

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
    }

    @FXML
    private void adicionarPedidoAComanda(ActionEvent event) throws IOException {
        if(!novoPedido.getItens().isEmpty()) {
            mesa.getComanda().adicionarPedido(novoPedido);
            janelaUtils.mudarTela(event, janelaMesa, "Mesa", (MesaController controller) -> {
                controller.setMesa(this.mesa);
            });
        } else {
            alertaUtils.mostrarAlerta("Erro", "Adicione pelo menos um item ao pedido!");
        }
    }


    @FXML
    public void initialize() {
        carregarSeletores();
    }
}