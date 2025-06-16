package br.edu.uepb.sistemarestaurante.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import java.util.List;

class PedidoTest {
    private Pedido pedido;
    private ItemCardapio itemPrato;

    // Classe auxiliar para testes que estende ItemCardapio
    private static class ItemCardapioTeste extends ItemCardapio {
        public ItemCardapioTeste(String nome, double preco) {
            super(nome, preco);
        }
    }

    @BeforeEach
    void setUp() {
        pedido = new Pedido();
        itemPrato = new ItemCardapioTeste("Arroz", 10.0);
    }

    @Test
    void testConstrutorPedido() {
        assertNotNull(pedido);
        assertEquals(StatusPedido.PENDENTE, pedido.getStatus());
        assertNotNull(pedido.getHORARIO());
        assertTrue(pedido.getID() > 0);
        assertTrue(pedido.getItens().isEmpty());
    }

    @Test
    void testAdicionarItem() {
        pedido.adicionarItem(itemPrato, 2, "Sem sal");
        assertEquals(1, pedido.getItens().size());

        ItemPedido itemAdicionado = pedido.getItens().get(0);
        assertEquals(itemPrato, itemAdicionado.getItem());
    }

    @Test
    void testRemoverItem() {
        pedido.adicionarItem(itemPrato, 1, "");
        ItemPedido itemParaRemover = pedido.getItens().get(0);

        pedido.removerItem(itemParaRemover);
        assertTrue(pedido.getItens().isEmpty());
    }

    @Test
    void testRemoverItemInexistente() {
        ItemPedido itemInexistente = new ItemPedido(itemPrato, 1, "");
        assertThrows(IllegalArgumentException.class, () ->
                pedido.removerItem(itemInexistente)
        );
    }

    @Test
    void testAlterarStatus() {
        assertEquals(StatusPedido.PENDENTE, pedido.getStatus());
        pedido.alterarStatus();
        assertEquals(StatusPedido.PREPARANDO, pedido.getStatus());
        pedido.alterarStatus();
        assertEquals(StatusPedido.PRONTO, pedido.getStatus());
        pedido.alterarStatus();
        assertEquals(StatusPedido.ENTREGUE, pedido.getStatus());
    }

    @Test
    void testCalcularTotalSemItens() {
        assertEquals(0.0, pedido.calcularTotal(), 0.001);
    }

    @Test
    void testCalcularTotalComItens() {
        pedido.adicionarItem(itemPrato, 2, ""); // 2 x R$10.0 = R$20.0
        ItemCardapio item2 = new ItemCardapioTeste("Feijão", 8.0);
        pedido.adicionarItem(item2, 1, ""); // 1 x R$8.0 = R$8.0

        assertEquals(28.0, pedido.calcularTotal(), 0.001);
    }

    @Test
    void testGetPedidosCozinha() {
        List<Pedido> pedidosCozinha = Pedido.getPedidosCozinha();
        assertNotNull(pedidosCozinha);

        for (Pedido p : pedidosCozinha) {
            StatusPedido status = p.getStatus();
            assertTrue(status == StatusPedido.PENDENTE ||
                    status == StatusPedido.PREPARANDO);
        }
    }

    @Test
    void testListarItensVazio() {
        String listaItens = pedido.listarItens();
        assertEquals("", listaItens);
    }

    @Test
    void testListarItensComItem() {
        pedido.adicionarItem(itemPrato, 2, "Sem sal");
        String listaItens = pedido.listarItens();
        assertNotNull(listaItens);
        assertTrue(listaItens.contains("Arroz"));
    }

    @Test
    void testToString() {
        String pedidoString = pedido.toString();
        assertTrue(pedidoString.contains("Pedido #"));
        assertTrue(pedidoString.contains("status: " + StatusPedido.PENDENTE));
        assertTrue(pedidoString.contains("horário:"));
    }

    @Test
    void testIDsUnicos() {
        Pedido outroPedido = new Pedido();
        assertNotEquals(pedido.getID(), outroPedido.getID());
    }
}