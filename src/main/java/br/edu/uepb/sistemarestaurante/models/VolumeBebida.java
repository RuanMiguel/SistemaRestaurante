package br.edu.uepb.sistemarestaurante.models;

/**
 * Enumeração que representa os volumes (em Litros) disponíveis para bebidas no sistema de restaurante.
 * <p>
 * Cada constante enum representa um volume específico de bebida, como 0.250L, 1L, 1.5L ou 2L.
 * Este enum é utilizado para facilitar o controle e a padronização dos volumes no cardápio.
 * </p>
 *
 * @author Laryssa D. Ramos
 * @author Leticia B. M. da Cruz
 */
public enum VolumeBebida {
    L0_250("0.250L"),
    L1("1L"),
    L1_5("1.5L"),
    L2("2L");

    private final String volume;

    /**
     * Construtor privado da enumeração.
     *
     * @param volume o valor de volume como string formatada (ex: "1.5L")
     */
    VolumeBebida(String volume) {
        this.volume = volume;
    }

    /**
     * Retorna a representação do volume em formato de string.
     *
     * @return o volume como uma string (ex: "1L")
     */
    public String getVolume() {
        return volume;
    }

    /**
     * Converte uma string representando um volume em uma constante da enumeração {@code VolumeBebida}.
     *
     * @param volume o volume no formato de string (ex: "2L")
     * @return a constante correspondente de {@code VolumeBebida}
     * @throws IllegalArgumentException se o volume informado não corresponder a nenhuma constante
     */
    public static VolumeBebida fromVolume(String volume) {
        for (VolumeBebida volB : VolumeBebida.values()) {
            if (volB.getVolume().equals(volume)) {
                return volB;
            }
        }
        throw new IllegalArgumentException("Nenhum volume chamado " + volume);
    }
}