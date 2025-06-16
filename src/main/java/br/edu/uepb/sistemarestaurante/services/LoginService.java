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
    private String garcomLogado;

    /**
     * Realiza a autenticação de um funcionário.
     *
     * @param idUsuario identificador do usuário (ID para garçons, "cozinha" para gerenciador de cozinha)
     * @param senha senha de acesso
     * @return {@link TipoFuncionario} correspondente ao tipo de perfil autenticado,
     *         ou {@code null} se as credenciais forem inválidas
     *
     * @implNote A credencial padrão para cozinha é idUsuario="cozinha" e senha="1234"
     */
    public TipoFuncionario autenticarFuncionario(String idUsuario, String senha) {
        if (idUsuario.equals("cozinha") && senha.equals("1234")) {
            return TipoFuncionario.COZINHA;
        }

        for (Garcom garcom : garcomLoginDAO.listarGarcons()) {
            if (garcom.getId().equals(idUsuario) && garcom.getSenha().equals(senha)) {
                garcomLogado = idUsuario;
                return TipoFuncionario.GARCOM;
            }
        }
        return null;
    }

    /**
     * Acessibilidade para identificador do garçom atualmente logado.
     *
     * @return ID do garçom logado, ou {@code null} se nenhum garçom estiver autenticado
     */
    public String getGarcomLogado() {
        return garcomLogado;
    }
}