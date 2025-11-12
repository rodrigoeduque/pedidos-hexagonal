package com.example.pedido.application.ports.input;

import java.math.BigDecimal;
import java.util.List;

// DTO que representa a intenção de criar um pedido
public record CriarPedidoCommand(
        String clienteId,
        String descricao,
        List<ItemCommand> itens
) {
    public record ItemCommand(String produtoId, String nomeProduto, int quantidade, BigDecimal precoUnitario
    ) {
    }
}