package br.edu.uepb.sistemarestaurante.services;

import br.edu.uepb.sistemarestaurante.models.Comanda;
import br.edu.uepb.sistemarestaurante.models.Garcom;
import br.edu.uepb.sistemarestaurante.models.Pedido;
import br.edu.uepb.sistemarestaurante.models.StatusPedido;

import java.util.ArrayList;
import java.util.List;

public class PedidosService {
    public List<Pedido> getPedidosNotificacao(Garcom garcomLogado, List<Comanda> todasComandas) {
        List<Pedido> pedidosProntos = new ArrayList<>();

        for (Comanda comanda : todasComandas) {
            if (comanda.getGarcom().equals(garcomLogado)) { // Verifica se é do garçom logado
                for (Pedido pedido : comanda.getPedidos()) {
                    if (pedido.getStatus() == StatusPedido.PRONTO) { // Verifica se está pronto
                        pedidosProntos.add(pedido);
                    }
                }
            }
        }

        return pedidosProntos;
    }

    public List<Pedido> getPedidosCozinha(List<Pedido> todosPedidos) {
        List<Pedido> resultado = new ArrayList<>();

        for (Pedido pedido : todosPedidos) {
            if (pedido.getStatus() == StatusPedido.PENDENTE ||
                    pedido.getStatus() == StatusPedido.PREPARANDO) {
                resultado.add(pedido);
            }
        }

        return resultado;
    }
}
