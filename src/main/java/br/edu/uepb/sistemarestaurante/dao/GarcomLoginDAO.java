package br.edu.uepb.sistemarestaurante.dao;

import br.edu.uepb.sistemarestaurante.models.Garcom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) para manipulação de dados de login dos garçons.
 * Responsável por ler username(id) e senha dos garçons armazenadas em arquivo texto.
 *
 * @author Letícia B. M. da Cruz
 */
public class GarcomLoginDAO {
    /** Caminho do arquivo de armazenamento dos dados de login dos garçons.
     * Este caminho deve ser relativo ao diretório de recursos do projeto.
     * O arquivo será criado se não existir, com exemplos de uso.
     */
    private static final String CAMINHO_ARQUIVO = "src/main/resources/br/edu/uepb/sistemarestaurante/garcom-login.txt";

    /**
     * Construtor da classe GarcomLoginDAO.
     * Cria o arquivo de armazenamento caso não exista.
     */
    public GarcomLoginDAO() {
        criarArquivo();
    }

    /**
     * Verifica a existência do arquivo de armazenamento e cria um novo com exemplos
     * caso o arquivo não exista.
     *
     */
    private void criarArquivo() {
        File arquivo = new File(CAMINHO_ARQUIVO);
        if (!arquivo.exists()) {
            try {
                // Cria o diretório, se não existir
                arquivo.getParentFile().mkdirs();

                // Cria o arquivo e escreve o exemplo de uso
                FileWriter writer = new FileWriter(arquivo);
                writer.write("// Formato do arquivo: username;senha\n");
                writer.write("// Exemplo:\n");
                writer.write("garcom1;senha123\n");
                writer.write("garcom2;abc456\n");
                writer.close();

                System.out.println("Arquivo criado com sucesso em: " + CAMINHO_ARQUIVO);
            } catch (IOException e) {
                System.err.println("Erro ao criar o arquivo de garçons: " + e.getMessage());
            }
        }
    }

    /**
     * Lista todos os garçons cadastrados no sistema a partir do arquivo de armazenamento.
     * Ignora linhas de comentário (que começam com //) no arquivo.
     *
     * @return Lista de objetos Garcom contendo username e senha
     */
    public List<Garcom> listarGarcons() {
        List<Garcom> garcons = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                // Ignora linhas de comentário
                if (linha.trim().startsWith("//")) {
                    continue;
                }

                String[] dados = linha.split(";");
                if (dados.length == 2) {
                    String usernameGarcom = dados[0].trim();
                    String senhaGarcom = dados[1].trim();
                    garcons.add(new Garcom(usernameGarcom, senhaGarcom));
                }
            }
            System.out.println("Dados carregados com sucesso!");

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de garçons: " + e.getMessage());
        }
        return garcons;
    }
}
