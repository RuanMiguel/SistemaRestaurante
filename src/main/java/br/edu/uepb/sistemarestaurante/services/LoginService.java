package br.edu.uepb.sistemarestaurante.services;

import br.edu.uepb.sistemarestaurante.dao.GarcomLoginDAO;
import br.edu.uepb.sistemarestaurante.models.Garcom;
import br.edu.uepb.sistemarestaurante.models.TipoFuncionario;

public class LoginService {
    private final GarcomLoginDAO garcomLoginDAO = new GarcomLoginDAO();
    private String garcomLogado;

    public TipoFuncionario autenticarFuncionario(String usuario, String senha){
        if (usuario.equals("cozinha") && senha.equals("1234")){
            return TipoFuncionario.COZINHA;
        }

        for (Garcom garcom : garcomLoginDAO.listarGarcons()) {
            if (garcom.getUsername().equals(usuario) && garcom.getSenha().equals(senha)) {
                garcomLogado = usuario;
                return TipoFuncionario.GARCOM;
            }
        }
        return null;
    }

    public String getGarcomLogado(){
        return garcomLogado;
    }



}