package com.example.pedido.adapters.input.rest.mapper;

import com.example.pedido.adapters.input.rest.dto.CriarPedidoRequest;
import com.example.pedido.adapters.input.rest.dto.PedidoResponse;
import com.example.pedido.application.ports.input.CriarPedidoCommand;
import com.example.pedido.domain.Pedido;

public class PedidoRestMapper {

    public static CriarPedidoCommand toCommand(CriarPedidoRequest request) {
        var itens = request.itens().stream()
                .map(item -> new CriarPedidoCommand.ItemCommand(
                        item.produtoId(),
                        item.nomeProduto(),
                        item.quantidade(),
                        item.precoUnitario()
                ))
                .toList();

        return new CriarPedidoCommand(request.clienteId(),request.descricao(), itens);
    }

    public static PedidoResponse toResponse(Pedido pedido) {
        var itens = pedido.getItens().stream()
                .map(item -> new PedidoResponse.ItemResponse(
                        item.getProdutoId(),
                        item.getNomeProduto(),
                        item.getQuantidade(),
                        item.getPrecoUnitario(),
                        item.getSubtotal()
                ))
                .toList();

        return new PedidoResponse(
                pedido.getId(),
                pedido.getClienteId(),
                pedido.getDescricao(),
                itens,
                pedido.getValorTotal(),
                pedido.getStatus().name(),
                pedido.getDataCriacao()
        );
    }
}