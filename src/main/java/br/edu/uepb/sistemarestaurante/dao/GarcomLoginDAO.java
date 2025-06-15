package br.edu.uepb.sistemarestaurante.dao;

import br.edu.uepb.sistemarestaurante.models.Garcom;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GarcomLoginDAO {
    private static final String CAMINHO_ARQUIVO = "br/edu/uepb/sistemarestaurante/garcom-login.txt";

    public List<Garcom> listarGarcons(){
        List<Garcom> garcons = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 2){
                    String usernameGarcom = dados[0].trim();
                    String senhaGarcom = dados[1].trim();
                    garcons.add(new Garcom(usernameGarcom, senhaGarcom));
                }

            }
            System.out.println("Dados carregados com sucesso!");

        } catch (IOException e){
            System.err.println("Erro ao ler o arquivo de garçons: " + e.getMessage());
        }
        return garcons;
    }
}