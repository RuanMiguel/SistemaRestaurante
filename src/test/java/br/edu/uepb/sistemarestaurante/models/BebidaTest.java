package br.edu.uepb.sistemarestaurante.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BebidaTest {
    private Bebida bebida;

    @BeforeEach
    void setUp() {
        bebida = new Bebida("Coca-Cola", 5.00, TipoBebida.REFRIGERANTE, VolumeBebida.L2);
    }

    @Test
    void testCriacaoBebidaValida() {
        assertNotNull(bebida);
        assertEquals("Coca-Cola", bebida.getNome());
        assertEquals(5.00, bebida.getPreco());
        assertEquals(TipoBebida.REFRIGERANTE, bebida.getTipo());
        assertEquals(VolumeBebida.L2, bebida.getVolume());
    }

    @Test
    void testToString() {
        String esperado = "Coca-Cola [Tipo: REFRIGERANTE, Volume: L2, Preço: R$5,00]";
        assertEquals(esperado, bebida.toString());
    }

    @Test
    void testGetStringVolume() {
        Bebida bebidaPequena = new Bebida("Água", 3.00, TipoBebida.AGUA, VolumeBebida.L2);
        assertEquals("2L", bebidaPequena.getStringVolume());

        Bebida bebidaGrande = new Bebida("Suco", 8.00, TipoBebida.SUCO, VolumeBebida.L2);
        assertEquals("2L", bebidaGrande.getStringVolume());
    }

    @Test
    void testDiferentesTiposBebida() {
        Bebida agua = new Bebida("Água", 3.00, TipoBebida.AGUA, VolumeBebida.L2);
        assertEquals(TipoBebida.AGUA, agua.getTipo());

        Bebida suco = new Bebida("Suco", 7.00, TipoBebida.SUCO, VolumeBebida.L2);
        assertEquals(TipoBebida.SUCO, suco.getTipo());

        Bebida cerveja = new Bebida("Cerveja", 8.00, TipoBebida.AGUA, VolumeBebida.L2);
        assertEquals(TipoBebida.AGUA, cerveja.getTipo());
    }

    @Test
    void testDiferentesVolumesBebida() {
        Bebida bebida350 = new Bebida("Refrigerante", 5.00, TipoBebida.REFRIGERANTE, VolumeBebida.L2);
        assertEquals(VolumeBebida.L2, bebida350.getVolume());

        Bebida bebida500 = new Bebida("Refrigerante", 7.00, TipoBebida.REFRIGERANTE, VolumeBebida.L2);
        assertEquals(VolumeBebida.L2, bebida500.getVolume());

        Bebida bebida1000 = new Bebida("Refrigerante", 10.00, TipoBebida.REFRIGERANTE, VolumeBebida.L1);
        assertEquals(VolumeBebida.L1, bebida1000.getVolume());
    }
}