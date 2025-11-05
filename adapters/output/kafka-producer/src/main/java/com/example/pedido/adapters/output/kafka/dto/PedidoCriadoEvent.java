package com.example.pedido.adapters.output.kafka.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PedidoCriadoEvent(
        String pedidoId,
        String clienteId,
        BigDecimal valorTotal,
        String status,
        LocalDateTime dataCriacao
) {
}