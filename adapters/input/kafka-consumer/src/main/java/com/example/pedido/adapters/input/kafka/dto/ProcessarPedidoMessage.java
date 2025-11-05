package com.example.pedido.adapters.input.kafka.dto;

public record ProcessarPedidoMessage(
        String pedidoId,
        String acao
) {
}