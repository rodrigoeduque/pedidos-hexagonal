package com.example.pedido.application.services;

import com.example.pedido.application.ports.input.BuscarPedidoUseCase;
import com.example.pedido.application.ports.output.BuscarPedidoPort;
import com.example.pedido.domain.Pedido;

import java.util.Optional;

public class BuscarPedidoService implements BuscarPedidoUseCase {

    private final BuscarPedidoPort buscarPedidoPort;

    public BuscarPedidoService(BuscarPedidoPort buscarPedidoPort) {
        this.buscarPedidoPort = buscarPedidoPort;
    }

    @Override
    public Optional<Pedido> executar(String pedidoId) {
        return buscarPedidoPort.buscarPorId(pedidoId);
    }
}