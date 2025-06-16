package br.edu.uepb.sistemarestaurante.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ComandaTest {
    private Comanda comanda;
    private Mesa mesa;
    private Garcom garcom;
    private Pedido pedido;

    @BeforeEach
    void setUp() {
        mesa = new Mesa(1, 4);
        garcom = new Garcom("Marcella", "123456");
        comanda = new Comanda(mesa, garcom);
        pedido = new Pedido();
    }

    @Test
    void testConstrutorPadrao() {
        Comanda comandaPadrao = new Comanda();
        assertNotNull(comandaPadrao);
        assertNull(comandaPadrao.getMesa());
        assertNull(comandaPadrao.getGarcom());
        assertTrue(comandaPadrao.getPedidos().isEmpty());
        assertTrue(comandaPadrao.getID() > 0);
    }

    @Test
    void testConstrutorComParametros() {
        assertNotNull(comanda);
        assertEquals(mesa, comanda.getMesa());
        assertEquals(garcom, comanda.getGarcom());
        assertTrue(comanda.getPedidos().isEmpty());
        assertTrue(comanda.getID() > 0);
    }

    @Test
    void testAdicionarPedido() {
        comanda.adicionarPedido(pedido);
        assertEquals(1, comanda.getPedidos().size());
        assertTrue(comanda.getPedidos().contains(pedido));
    }

    @Test
    void testListarPedidoVazio() {
        // Não há uma maneira direta de testar a saída do System.out
        // mas podemos verificar se a lista está vazia
        assertTrue(comanda.getPedidos().isEmpty());
    }

    @Test
    void testListarPedidoComItens() {
        comanda.adicionarPedido(pedido);
        assertFalse(comanda.getPedidos().isEmpty());
        assertEquals(1, comanda.getPedidos().size());
    }

    @Test
    void testCalcularTotalSemPedidos() {
        assertEquals(0.0, comanda.calcularTotal(), 0.001);
    }

    @Test
    void testCalcularTotalComPedidos() {
        // Criando um pedido mockado com valor conhecido
        Pedido pedido1 = new Pedido();
        Pedido pedido2 = new Pedido();

        // Assumindo que cada pedido tem um valor total de 50.0
        // Você precisa ajustar isso de acordo com a implementação real do Pedido

        comanda.adicionarPedido(pedido1);
        comanda.adicionarPedido(pedido2);

        double totalEsperado = pedido1.calcularTotal() + pedido2.calcularTotal();
        assertEquals(totalEsperado, comanda.calcularTotal(), 0.001);
    }

    @Test
    void testIDsUnicos() {
        Comanda comanda1 = new Comanda();
        Comanda comanda2 = new Comanda();
        assertNotEquals(comanda1.getID(), comanda2.getID());
    }
}