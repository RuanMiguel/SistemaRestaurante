package br.edu.uepb.sistemarestaurante.controllers;

import br.edu.uepb.sistemarestaurante.dao.ItensCardapioDAO;
import br.edu.uepb.sistemarestaurante.models.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

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


    private String cardapioSelecionado;
    private String subCardapioSelecionado;
    private ItemCardapio itemSelecionado;
    private Integer qtdSelecionada;
    private String obsEscrita;

    private List<String> categoriasCardapio = new ArrayList<>(Arrays.asList("Pratos", "Bebidas", "Sobremesas"));
    private ObservableList<String> obsCategoriasCardapio = FXCollections.observableArrayList(categoriasCardapio);

    private SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50);

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
                        break;
                }
            }
        });
    }

    public void carregarCBItem(){
        subCardapioSelecionado = subCardapio.getValue();
        subCardapio.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                subCardapioSelecionado = subCardapio.getValue();

                if (subCardapioSelecionado == null) return;

                ItensCardapioDAO itensCardapioDAO = new ItensCardapioDAO();
                List<String> nomeItens = new ArrayList<>();

                for(ItemCardapio i : itensCardapioDAO.listarItensCardapio(cardapioSelecionado, subCardapioSelecionado)){
                    nomeItens.add(i.getNome());
                }

                item.setItems((FXCollections.observableArrayList(nomeItens)));
            }
        });
    }

    @FXML
    public void initialize() {
        carregarSeletores();
    }
}