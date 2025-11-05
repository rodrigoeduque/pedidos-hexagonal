package com.example.pedido.adapters.input.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponse(
        String id,
        String clienteId,
        List<ItemResponse> itens,
        BigDecimal valorTotal,
        String status,
        LocalDateTime dataCriacao
) {
    public record ItemResponse(
            String produtoId,
            String nomeProduto,
            int quantidade,
            BigDecimal precoUnitario,
            BigDecimal subtotal
    ) {
    }
}