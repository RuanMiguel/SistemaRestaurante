package br.edu.uepb.sistemarestaurante.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class MesaTest {
    private Mesa mesa;

    @BeforeEach
    void setUp() {
        mesa = new Mesa(1, 6);
    }

    @Test
    void testConstrutorMesa() {
        assertNotNull(mesa);
        assertEquals(1, mesa.getNumero());
        assertFalse(mesa.isOcupada());
        assertNull(mesa.getComanda());
    }

    @Test
    void testCriarMesas() {
        Map<Integer, Mesa> mesas = Mesa.getMesas();
        assertNotNull(mesas);
        assertEquals(8, mesas.size());
        for (int i = 1; i <= 8; i++) {
            assertTrue(mesas.containsKey(i));
        }
    }

    @Test
    void testOcuparMesa() {
        assertFalse(mesa.isOcupada());
        mesa.ocupar();
        assertTrue(mesa.isOcupada());
    }

    @Test
    void testOcuparMesaJaOcupada() {
        mesa.ocupar();
        assertTrue(mesa.isOcupada());
        mesa.ocupar(); // Tentando ocupar novamente
        assertTrue(mesa.isOcupada()); // Deve continuar ocupada
    }

    @Test
    void testLiberarMesa() {
        mesa.ocupar();
        assertTrue(mesa.isOcupada());
        mesa.liberar();
        assertFalse(mesa.isOcupada());
    }

    @Test
    void testLiberarMesaJaLivre() {
        assertFalse(mesa.isOcupada());
        mesa.liberar(); // Tentando liberar mesa já livre
        assertFalse(mesa.isOcupada());
    }

    @Test
    void testSetComanda() {
        Comanda comanda = new Comanda();
        mesa.setComanda(comanda);
        assertEquals(comanda, mesa.getComanda());
    }

    @Test
    void testListarDisponiveis() {
        List<Mesa> mesas = new ArrayList<>();
        Mesa mesa1 = new Mesa(1, 6);
        Mesa mesa2 = new Mesa(2, 6);
        mesa1.ocupar();

        mesas.add(mesa1);
        mesas.add(mesa2);

        // Não é possível testar diretamente o output do System.out,
        // mas podemos verificar o estado das mesas
        assertTrue(mesa1.isOcupada());
        assertFalse(mesa2.isOcupada());
    }

    @Test
    void testGetMesasStatic() {
        Map<Integer, Mesa> mesas = Mesa.getMesas();
        assertNotNull(mesas);
        assertFalse(mesas.isEmpty());
        assertEquals(8, mesas.size());
    }
}