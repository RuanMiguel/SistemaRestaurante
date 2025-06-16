package br.edu.uepb.sistemarestaurante.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GerenciadorCozinhaTest {
    private GerenciadorCozinha gerenciador;
    private Pedido pedido;

    @BeforeEach
    void setUp() {
        gerenciador = new GerenciadorCozinha();
        pedido = new Pedido();
    }


    @Test
    void testAtualizarStatusPedidoPendente() {
        assertEquals(StatusPedido.PENDENTE, pedido.getStatus());

        gerenciador.atualizarStatus(pedido);
        assertEquals(StatusPedido.PREPARANDO, pedido.getStatus());
    }

    @Test
    void testAtualizarStatusPedidoPreparando() {
        pedido.alterarStatus(); // PENDENTE -> PREPARANDO
        assertEquals(StatusPedido.PREPARANDO, pedido.getStatus());

        gerenciador.atualizarStatus(pedido);
        assertEquals(StatusPedido.PRONTO, pedido.getStatus());
    }

    @Test
    void testAtualizarStatusPedidoPronto() {
        pedido.alterarStatus(); // PENDENTE -> PREPARANDO
        pedido.alterarStatus(); // PREPARANDO -> PRONTO
        assertEquals(StatusPedido.PRONTO, pedido.getStatus());

        gerenciador.atualizarStatus(pedido);
        assertEquals(StatusPedido.ENTREGUE, pedido.getStatus());
    }

    @Test
    void testAtualizarStatusPedidoEntregue() {
        // Configurando pedido como ENTREGUE
        pedido.alterarStatus(); // PENDENTE -> PREPARANDO
        pedido.alterarStatus(); // PREPARANDO -> PRONTO
        pedido.alterarStatus(); // PRONTO -> ENTREGUE
        assertEquals(StatusPedido.ENTREGUE, pedido.getStatus());

        assertThrows(IllegalStateException.class, () -> {
            gerenciador.atualizarStatus(pedido);
        });
    }

    @Test
    void testAtualizarStatusPedidoNull() {
        assertThrows(NullPointerException.class, () -> {
            gerenciador.atualizarStatus(null);
        });
    }


}