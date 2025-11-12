package com.example.pedido.application.ports.input;

import com.example.pedido.domain.Pedido;

import java.util.List;
import java.util.Optional;

public interface BuscarPedidoUseCase {
    Optional<Pedido> executar(String pedidoId);
    List<Pedido> buscarTodos();
}