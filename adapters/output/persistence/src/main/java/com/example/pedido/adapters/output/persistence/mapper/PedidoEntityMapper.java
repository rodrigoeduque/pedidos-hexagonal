package com.example.pedido.adapters.output.persistence.mapper;

import com.example.pedido.adapters.output.persistence.entity.ItemPedidoEntity;
import com.example.pedido.adapters.output.persistence.entity.PedidoEntity;
import com.example.pedido.domain.ItemPedido;
import com.example.pedido.domain.Pedido;
import com.example.pedido.domain.StatusPedido;

import java.util.stream.Collectors;

public class PedidoEntityMapper {

    public static PedidoEntity toEntity(Pedido pedido) {
        PedidoEntity entity = new PedidoEntity();
        entity.setId(pedido.getId());
        entity.setClienteId(pedido.getClienteId());
        entity.setDescricao(pedido.getDescricao());
        entity.setValorTotal(pedido.getValorTotal());
        entity.setStatus(pedido.getStatus().name());
        entity.setDataCriacao(pedido.getDataCriacao());
        entity.setDataAtualizacao(pedido.getDataAtualizacao());

        var itens = pedido.getItens().stream()
                .map(PedidoEntityMapper::toItemEntity)
                .collect(Collectors.toList());
        entity.setItens(itens);

        return entity;
    }

    public static Pedido toDomain(PedidoEntity entity) {
        var itens = entity.getItens().stream()
                .map(PedidoEntityMapper::toItemDomain)
                .collect(Collectors.toList());

        return Pedido.reconstruir(
                entity.getId(),
                entity.getClienteId(),
                entity.getDescricao(),
                itens,
                entity.getValorTotal(),
                StatusPedido.valueOf(entity.getStatus()),
                entity.getDataCriacao(),
                entity.getDataAtualizacao()
        );
    }

    private static ItemPedidoEntity toItemEntity(ItemPedido item) {
        ItemPedidoEntity entity = new ItemPedidoEntity();
        entity.setProdutoId(item.getProdutoId());
        entity.setNomeProduto(item.getNomeProduto());
        entity.setQuantidade(item.getQuantidade());
        entity.setPrecoUnitario(item.getPrecoUnitario());
        return entity;
    }

    private static ItemPedido toItemDomain(ItemPedidoEntity entity) {
        return new ItemPedido(
                entity.getProdutoId(),
                entity.getNomeProduto(),
                entity.getQuantidade(),
                entity.getPrecoUnitario()
        );
    }
}