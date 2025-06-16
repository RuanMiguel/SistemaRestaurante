package br.edu.uepb.sistemarestaurante.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TipoFuncionarioTest {

    @Test
    void testValoresTipoFuncionario() {
        TipoFuncionario[] tipos = TipoFuncionario.values();
        assertEquals(2, tipos.length);

        assertTrue(containsTipo(tipos, TipoFuncionario.GARCOM));
        assertTrue(containsTipo(tipos, TipoFuncionario.COZINHA));
    }

    @Test
    void testValueOf() {
        assertEquals(TipoFuncionario.GARCOM, TipoFuncionario.valueOf("GARCOM"));
        assertEquals(TipoFuncionario.COZINHA, TipoFuncionario.valueOf("COZINHA"));
    }

    @Test
    void testValueOfInvalido() {
        assertThrows(IllegalArgumentException.class, () ->
                TipoFuncionario.valueOf("INVALIDO")
        );
    }

    @Test
    void testNomeEnums() {
        assertEquals("GARCOM", TipoFuncionario.GARCOM.name());
        assertEquals("COZINHA", TipoFuncionario.COZINHA.name());
    }

    @Test
    void testOrdinal() {
        assertEquals(0, TipoFuncionario.GARCOM.ordinal());
        assertEquals(1, TipoFuncionario.COZINHA.ordinal());
    }

    private boolean containsTipo(TipoFuncionario[] tipos, TipoFuncionario tipo) {
        for (TipoFuncionario t : tipos) {
            if (t == tipo) return true;
        }
        return false;
    }
}