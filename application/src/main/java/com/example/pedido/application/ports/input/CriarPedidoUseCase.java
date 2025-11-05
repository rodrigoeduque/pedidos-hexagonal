package com.example.pedido.application.ports.input;

import com.example.pedido.domain.Pedido;

// Interface que define O QUE o mundo externo pode fazer
public interface CriarPedidoUseCase {
    Pedido executar(CriarPedidoCommand command);
}