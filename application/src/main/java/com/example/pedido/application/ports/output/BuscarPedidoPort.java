package com.example.pedido.application.ports.output;

import com.example.pedido.domain.Pedido;
import java.util.Optional;

public interface BuscarPedidoPort {
    Optional<Pedido> buscarPorId(String pedidoId);
}