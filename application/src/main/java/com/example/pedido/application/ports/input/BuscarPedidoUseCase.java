package com.example.pedido.application.ports.input;

import com.example.pedido.domain.Pedido;
import java.util.Optional;

public interface BuscarPedidoUseCase {
    Optional<Pedido> executar(String pedidoId);
}