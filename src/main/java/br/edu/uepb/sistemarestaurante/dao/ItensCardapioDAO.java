package br.edu.uepb.sistemarestaurante.dao;

import br.edu.uepb.sistemarestaurante.models.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe responsável por acessar os dados dos itens do cardápio do restaurante.
 * Fornece métodos para listar itens de acordo com o tipo de cardápio e subcardápio selecionados.
 *
 * @author Letícia B.M. da Cruz
 * @author Marcella Viana Lins
 */
public class ItensCardapioDAO {

    /**
     * Lista os itens do cardápio de acordo com o tipo de cardápio e subcardápio selecionados.
     * Lê os dados dos arquivos de texto correspondentes e cria objetos dos tipos específicos de itens.
     *
     * @param cardapioSelecionado O tipo de cardápio selecionado (Pratos, Bebidas, Sobremesas)
     * @param subCardapioSelecionado O subcardápio selecionado (ex: tipo de prato, volume da bebida)
     * @return Uma lista de itens do cardápio correspondentes ao filtro aplicado
     */
    public List<ItemCardapio> listarItensCardapio(String cardapioSelecionado, String subCardapioSelecionado){
        List<ItemCardapio> itens = new ArrayList<>();

        switch (cardapioSelecionado) {
            case "Pratos":
                InputStream input = getClass().getResourceAsStream("/br/edu/uepb/sistemarestaurante/pratos.txt");
                Objects.requireNonNull(input, "Arquivo pratos.txt não encontrado no classpath");

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
                    String linha;
                    while ((linha = reader.readLine()) != null) {
                        String[] dados = linha.split(";");
                        if (dados.length == 3 && subCardapioSelecionado.equals(dados[2])){
                            String nome = dados[0].trim();
                            double preco = Double.parseDouble(dados[1].trim());
                            TipoPrato tipo = TipoPrato.fromTipo(dados[2].trim());
                            itens.add(new Prato(nome, preco, tipo));
                        }
                    }
                    System.out.println("Dados carregados com sucesso!");

                } catch (IOException e){
                    System.err.println("Erro ao ler o arquivo de Pratos: " + e.getMessage());
                }
                break;
            case "Bebidas":
                InputStream input2 = getClass().getResourceAsStream("/br/edu/uepb/sistemarestaurante/bebidas.txt");
                Objects.requireNonNull(input2, "Arquivo bebidas.txt não encontrado no classpath");

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(input2))) {
                    String linha;
                    while ((linha = reader.readLine()) != null) {
                        String[] dados = linha.split(";");
                        if (dados.length == 4 && subCardapioSelecionado.equals(dados[3])){
                            String nome = dados[0].trim();
                            double preco = Double.parseDouble(dados[1].trim());
                            VolumeBebida volume = VolumeBebida.fromVolume(dados[2].trim());
                            TipoBebida tipo = TipoBebida.fromTipo(dados[3].trim());
                            itens.add(new Bebida(nome, preco, tipo, volume));
                        }
                    }
                    System.out.println("Dados carregados com sucesso!");

                } catch (IOException e){
                    System.err.println("Erro ao ler o arquivo de Bebidas: " + e.getMessage());
                }
                break;
            case "Sobremesas":
                InputStream input3 = getClass().getResourceAsStream("/br/edu/uepb/sistemarestaurante/sobremesas.txt");
                Objects.requireNonNull(input3, "Arquivo sobremesas.txt não encontrado no classpath");

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(input3))) {
                    String linha;
                    while ((linha = reader.readLine()) != null) {
                        String[] dados = linha.split(";");
                        if (dados.length == 2){
                            String nome = dados[0].trim();
                            double preco = Double.parseDouble(dados[1].trim());
                            itens.add(new Sobremesa(nome, preco));
                        }
                    }
                    System.out.println("Dados carregados com sucesso!");

                } catch (IOException e){
                    System.err.println("Erro ao ler o arquivo de Sobremesas: " + e.getMessage());
                }
                break;
        }

        return itens;
    }
}
