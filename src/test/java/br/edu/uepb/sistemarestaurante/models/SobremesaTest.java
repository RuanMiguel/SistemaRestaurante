package br.edu.uepb.sistemarestaurante.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SobremesaTest {
    private Sobremesa sobremesa;
    private static final String NOME_SOBREMESA = "Pudim";
    private static final double PRECO_SOBREMESA = 12.90;

    @BeforeEach
    void setUp() {
        sobremesa = new Sobremesa(NOME_SOBREMESA, PRECO_SOBREMESA);
    }

    @Test
    void testConstrutorSobremesa() {
        assertNotNull(sobremesa);
        assertEquals(NOME_SOBREMESA, sobremesa.getNome());
        assertEquals(PRECO_SOBREMESA, sobremesa.getPreco(), 0.001);
    }


    @Test
    void testAdicionarSobremesaNoCardapio() {
        assertTrue(ItemCardapio.itensCardapio.contains(sobremesa));
        assertEquals(sobremesa, ItemCardapio.itensCardapio.get(ItemCardapio.itensCardapio.size() - 1));
    }

    @Test
    void testToString() {
        String esperado = String.format("Sobremesa: %s [Preço: R$%.2f]", NOME_SOBREMESA, PRECO_SOBREMESA);
        assertEquals(esperado, sobremesa.toString());
    }

    @Test
    void testMultiplasSobremesasNoCardapio() {
        int tamanhoInicial = ItemCardapio.itensCardapio.size();

        Sobremesa sobremesa1 = new Sobremesa("Sorvete", 8.0);
        Sobremesa sobremesa2 = new Sobremesa("Mousse", 10.0);

        assertEquals(tamanhoInicial + 2, ItemCardapio.itensCardapio.size());
        assertTrue(ItemCardapio.itensCardapio.contains(sobremesa1));
        assertTrue(ItemCardapio.itensCardapio.contains(sobremesa2));
    }

    @Test
    void testSobremesasDistintas() {
        Sobremesa outraSobremesa = new Sobremesa("Torta", PRECO_SOBREMESA);
        assertNotEquals(sobremesa, outraSobremesa);
    }
}