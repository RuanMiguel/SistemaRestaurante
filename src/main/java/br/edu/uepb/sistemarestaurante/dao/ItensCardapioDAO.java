package br.edu.uepb.sistemarestaurante.dao;

import br.edu.uepb.sistemarestaurante.models.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ItensCardapioDAO {

    public List<ItemCardapio> listarItensCardapio(String cardapioSelecionado, String subCardapioSelecionado){
        switch (cardapioSelecionado) {
            case "Pratos":
                return lerArquivo("src/main/resources/br/edu/uepb/sistemarestaurante/pratos.txt",
                        linha -> criarPrato(linha, subCardapioSelecionado));
            case "Bebidas":
                return lerArquivo("src/main/resources/br/edu/uepb/sistemarestaurante/bebidas.txt",
                        linha -> criarBebida(linha, subCardapioSelecionado));
            case "Sobremesas":
                return lerArquivo("src/main/resources/br/edu/uepb/sistemarestaurante/sobremesas.txt",
                        this::criarSobremesa);
            default:
                return new ArrayList<>();
        }
    }

    private List<ItemCardapio> lerArquivo(String caminho, Function<String, ItemCardapio> mapper) {
        List<ItemCardapio> itens = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                ItemCardapio item = mapper.apply(linha);
                if (item != null) {
                    itens.add(item);
                }
            }
            System.out.println("Dados carregados com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return itens;
    }

    private Prato criarPrato(String linha, String subTipo) {
        String[] dados = linha.split(";");
        if (dados.length == 3 && subTipo.equals(dados[2].trim())) {
            String nome = dados[0].trim();
            double preco = Double.parseDouble(dados[1].trim());
            TipoPrato tipo = TipoPrato.fromTipo(dados[2].trim());
            return new Prato(nome, preco, tipo);
        }
        return null;
    }

    private Bebida criarBebida(String linha, String subTipo) {
        String[] dados = linha.split(";");
        if (dados.length == 4 && subTipo.equals(dados[3].trim())) {
            String nome = dados[0].trim();
            double preco = Double.parseDouble(dados[1].trim());
            VolumeBebida volume = VolumeBebida.fromVolume(dados[2].trim());
            TipoBebida tipo = TipoBebida.fromTipo(dados[3].trim());
            return new Bebida(nome, preco, tipo, volume);
        }
        return null;
    }

    private Sobremesa criarSobremesa(String linha) {
        String[] dados = linha.split(";");
        if (dados.length == 2) {
            String nome = dados[0].trim();
            double preco = Double.parseDouble(dados[1].trim());
            return new Sobremesa(nome, preco);
        }
        return null;
    }
}
