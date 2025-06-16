package br.edu.uepb.sistemarestaurante.services;

import br.edu.uepb.sistemarestaurante.dao.GarcomLoginDAO;
import br.edu.uepb.sistemarestaurante.models.Garcom;
import br.edu.uepb.sistemarestaurante.models.TipoFuncionario;

/**
 * Serviço de autenticação para funcionários do restaurante.
 *
 * <p>Gerencia o login de garçons e gerenciador da cozinha</p>
 *
 * <p>Utiliza {@link GarcomLoginDAO} para acesso aos dados
 * dos garçons registrados.</p>
 */
public class LoginService {
    private final GarcomLoginDAO garcomLoginDAO = new GarcomLoginDAO();
    private Garcom garcomLogado;

    /**
     * Autentica um funcionário com base no ID de usuário e senha.
     *
     * <p>Verifica se o ID e senha correspondem a um garçom ou ao gerenciador da cozinha.</p>
     *
     * @param idUsuario ID do usuário (garçom ou cozinha)
     * @param senha Senha do usuário
     * @return TipoFuncionario correspondente ao usuário autenticado, ou {@code null} se não for encontrado
     */
    public TipoFuncionario autenticarFuncionario(String idUsuario, String senha) {
        if (idUsuario.equals("cozinha") && senha.equals("1234")) {
            return TipoFuncionario.COZINHA;
        }

        for (Garcom garcom : garcomLoginDAO.listarGarcons()) {
            if (garcom.getId().equals(idUsuario) && garcom.getSenha().equals(senha)) {
                this.garcomLogado = garcom;
                return TipoFuncionario.GARCOM;
            }
        }
        return null;
    }

    /**
     * Retorna o garçom atualmente logado.
     *
     * <p>Este método é usado para acessar os dados do garçom autenticado
     * após um login bem-sucedido.</p>
     *
     * @return Garcom que está logado, ou {@code null} se nenhum garçom estiver logado
     */
    public Garcom getGarcomLogado() {
        return garcomLogado;
    }
}