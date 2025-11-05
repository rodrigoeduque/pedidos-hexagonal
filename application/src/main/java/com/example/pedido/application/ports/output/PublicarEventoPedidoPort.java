package com.example.pedido.application.ports.output;

import com.example.pedido.domain.Pedido;

public interface PublicarEventoPedidoPort {
    void publicarPedidoCriado(Pedido pedido);
}