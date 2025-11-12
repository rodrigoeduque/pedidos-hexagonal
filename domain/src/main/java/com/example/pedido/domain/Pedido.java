package com.example.pedido.domain;

import com.example.pedido.domain.exceptions.PedidoInvalidoException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Pedido {

    private String id;
    private String clienteId;
    private String descricao;
    private List<ItemPedido> itens;
    private BigDecimal valorTotal;
    private StatusPedido status;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    // Construtor privado - use o builder ou factory method
    private Pedido() {
        this.itens = new ArrayList<>();
    }

    // Factory method para criar novo pedido
    public static Pedido criar(String clienteId, String descricao,
                               List<ItemPedido> itens) {
        var pedido = new Pedido();
        pedido.id = UUID.randomUUID().toString();
        pedido.clienteId = clienteId;
        pedido.descricao = descricao;
        pedido.itens = new ArrayList<>(itens);
        pedido.status = StatusPedido.PENDENTE;
        pedido.dataCriacao = LocalDateTime.now();
        pedido.dataAtualizacao = LocalDateTime.now();
        pedido.calcularValorTotal();
        pedido.validar();
        return pedido;
    }

    // Factory method para reconstruir pedido existente (ex: do banco)
    public static Pedido reconstruir(String id,
                                     String clienteId,
                                     String descricao,
                                     List<ItemPedido> itens,
                                     BigDecimal valorTotal,
                                     StatusPedido status,
                                     LocalDateTime dataCriacao,
                                     LocalDateTime dataAtualizacao) {
        var pedido = new Pedido();
        pedido.id = id;
        pedido.clienteId = clienteId;
        pedido.descricao = descricao;
        pedido.itens = new ArrayList<>(itens);
        pedido.valorTotal = valorTotal;
        pedido.status = status;
        pedido.dataCriacao = dataCriacao;
        pedido.dataAtualizacao = dataAtualizacao;
        return pedido;
    }

    // Regra de negócio: calcular valor total
    private void calcularValorTotal() {
        this.valorTotal = itens.stream().map(ItemPedido::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    // Regra de negócio: validações

    private void validar() {
        if (clienteId == null || clienteId.isBlank()) {
            throw new PedidoInvalidoException("Cliente ID é obrigatório");
        }
        if (itens == null || itens.isEmpty()) {
            throw new PedidoInvalidoException("Pedido deve ter pelo menos um item");
        }
        if (valorTotal.compareTo(BigDecimal.ZERO) <= 0) {
            throw new PedidoInvalidoException("Valor total deve ser maior que zero");
        }
    }    // Regra de negócio: confirmar pedido

    public void confirmar() {
        if (status != StatusPedido.PENDENTE) {
            throw new PedidoInvalidoException("Apenas pedidos pendentes podem ser confirmados");
        }
        this.status = StatusPedido.CONFIRMADO;
        this.dataAtualizacao = LocalDateTime.now();
    }

    //Regra de negócio: cancelar pedido
    public void cancelar() {
        if (status == StatusPedido.ENTREGUE) {
            throw new PedidoInvalidoException("Pedidos entregues não podem ser cancelados");
        }
        this.status = StatusPedido.CANCELADO;
        this.dataAtualizacao = LocalDateTime.now();
    }

    //Getters

    public String getId() {
        return id;
    }

    public String getClienteId() {
        return clienteId;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<ItemPedido> getItens() {
        return new ArrayList<>(itens);
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }
}
