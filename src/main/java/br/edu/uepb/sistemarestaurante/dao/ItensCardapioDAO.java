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
 * Classe responsável por acessar os arquivos de dados dos itens do cardápio.
 * Realiza a leitura dos arquivos de texto com as informações de pratos, bebidas.txt e sobremesas,
 * e converte essas informações em objetos do tipo {@link ItemCardapio}.
 *
 * Os arquivos devem estar disponíveis no classpath:
 * <ul>
 * <li>/br/edu/uepb/sistemarestaurante/pratos.txt</li>
 * <li>/br/edu/uepb/sistemarestaurante/bebidas.txt.txt</li>
 * <li>/br/edu/uepb/sistemarestaurante/sobremesas.txt</li>
 * </ul>
 *
 * Cada linha dos arquivos deve estar no seguinte formato:
 * <ul>
 * <li>Pratos: nome;preço;tipo</li>
 * <li>Bebidas: nome;preço;volume;tipo</li>
 * <li>Sobremesas: nome;preço</li>
 * <ul>
 *
 * @author Laryssa D. Ramos
 */
public class ItensCardapioDAO {

    /**
     * Retorna uma lista de itens do cardápio com base na seleção do cardápio principal e do subcardápio.
     *
     * Lê o conteúdo dos arquivos de texto conforme o tipo de cardápio selecionado:
     * <ul>
     *     <li>Se {@code cardapioSelecionado} for "Pratos", o metodo busca no arquivo {@code pratos.txt}.</li>
     *     <li>Se for "Bebidas", busca em {@code bebidas.txt.txt}.</li>
     *     <li>Se for "Sobremesas", busca em {@code sobremesas.txt}, e {@code subCardapioSelecionado} será ignorado (pode ser {@code null}).</li>
     * </ul>
     *
     * @param cardapioSelecionado o tipo de cardápio selecionado (Pratos, Bebidas ou Sobremesas)
     * @param subCardapioSelecionado o subtipo de item, que pode ser o Tipo de prato (Entradas, Principais, Acompanhamentos ou Pratos Feitos), o Tipo de bebida (Àguas, Sucos, Refrigerantes ou Alcoólicas), ou {@code null} se o cardápio for Sobremesas
     * @return uma lista de itens do cardápio que correspondem à seleção
     * @throws NullPointerException se o arquivo correspondente não for encontrado no classpath
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
                InputStream input2 = getClass().getResourceAsStream("/br/edu/uepb/sistemarestaurante/bebidas.txt.txt");
                Objects.requireNonNull(input2, "Arquivo bebidas.txt.txt não encontrado no classpath");

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