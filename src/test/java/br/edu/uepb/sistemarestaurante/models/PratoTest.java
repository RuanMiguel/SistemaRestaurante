package br.edu.uepb.sistemarestaurante.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class PratoTest {
    private Prato prato;
    private static final String NOME_PRATO = "Filé à Parmegiana";
    private static final double PRECO_PRATO = 45.90;
    private static final TipoPrato TIPO_PRATO = TipoPrato.PRINCIPAL;

    @BeforeEach
    void setUp() {
        prato = new Prato(NOME_PRATO, PRECO_PRATO, TIPO_PRATO);
    }

    @Test
    void testConstrutorPrato() {
        assertNotNull(prato);
        assertEquals(NOME_PRATO, prato.getNome());
        assertEquals(PRECO_PRATO, prato.getPreco(), 0.001);
        assertEquals(TIPO_PRATO, prato.getTipo());
        assertEquals("Principais", TIPO_PRATO.getTipo());
    }


    @Test
    void testAdicionarPratoNoCardapio() {
        List<ItemCardapio> cardapio = ItemCardapio.itensCardapio;
        assertTrue(cardapio.contains(prato));

        // Verifica se o último item adicionado é o prato atual
        assertEquals(prato, cardapio.get(cardapio.size() - 1));
    }

    @Test
    void testTiposPrato() {
        // Testa todos os tipos disponíveis
        Prato entrada = new Prato("Salada", 25.0, TipoPrato.ENTRADA);
        Prato principal = new Prato("Bife", 35.0, TipoPrato.PRINCIPAL);
        Prato acompanhamento = new Prato("Arroz", 8.0, TipoPrato.ACOMPANHAMENTO);
        Prato pratoFeito = new Prato("PF do Dia", 20.0, TipoPrato.PRATO_FEITO);

        assertEquals("Entradas", entrada.getTipo().getTipo());
        assertEquals("Principais", principal.getTipo().getTipo());
        assertEquals("Acompanhamentos", acompanhamento.getTipo().getTipo());
        assertEquals("Pratos Feitos", pratoFeito.getTipo().getTipo());
    }

    @Test
    void testToString() {
        String esperado = NOME_PRATO + " | Preço: R$ " + PRECO_PRATO + " [" + TIPO_PRATO + "]";
        assertEquals(esperado, prato.toString());
    }

    @Test
    void testFromTipo() {
        assertEquals(TipoPrato.ENTRADA, TipoPrato.fromTipo("Entradas"));
        assertEquals(TipoPrato.PRINCIPAL, TipoPrato.fromTipo("Principais"));

        assertThrows(IllegalArgumentException.class, () ->
                TipoPrato.fromTipo("Tipo Inválido"));
    }

    @Test
    void testMultiplosPratosNoCardapio() {
        int tamanhoInicial = ItemCardapio.itensCardapio.size();

        Prato prato1 = new Prato("Prato 1", 30.0, TipoPrato.PRINCIPAL);
        Prato prato2 = new Prato("Prato 2", 25.0, TipoPrato.ENTRADA);

        assertEquals(tamanhoInicial + 2, ItemCardapio.itensCardapio.size());
        assertTrue(ItemCardapio.itensCardapio.contains(prato1));
        assertTrue(ItemCardapio.itensCardapio.contains(prato2));
    }
}