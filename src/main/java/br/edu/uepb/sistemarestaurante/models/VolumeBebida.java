package br.edu.uepb.sistemarestaurante.models;

public enum VolumeBebida {
    L0_250("0.250L"),
    L1("1L"),
    L1_5("1.5L"),
    L2("2L");

    private final String volume;

    VolumeBebida(String volume) {
        this.volume = volume;
    }

    public String getVolume() {
        return volume;
    }

    public static VolumeBebida fromVolume(String volume) {
        for (VolumeBebida volB : VolumeBebida.values()) {
            if (volB.getVolume().equals(volume)) {
                return volB;
            }
        }
        throw new IllegalArgumentException("Nenhum volume chamado " + volume);
    }
}
