package com.example.pedido.adapters.input.rest.dto;

import java.math.BigDecimal;
import java.util.List;

public record CriarPedidoRequest(
        String clienteId,
        List<ItemRequest> itens
) {
    public record ItemRequest(
            String produtoId,
            String nomeProduto,
            int quantidade,
            BigDecimal precoUnitario
    ) {
    }
}