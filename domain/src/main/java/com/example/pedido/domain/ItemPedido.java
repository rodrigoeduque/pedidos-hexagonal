package com.example.pedido.domain;

import java.math.BigDecimal;

public class ItemPedido {
    private String produtoId;
    private String nomeProduto;
    private int quantidade;
    private BigDecimal precoUnitario;


    public ItemPedido(String produtoId, String nomeProduto, int quantidade, BigDecimal precoUnitario) {
        validar(produtoId, nomeProduto, quantidade, precoUnitario);
        this.produtoId = produtoId;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    private void validar(String produtoId, String nomeProduto, int quantidade, BigDecimal precoUnitario) {
        if (produtoId == null || produtoId.isBlank()) {
            throw new PedidoInvalidoException("Produto ID é obrigatório");
        }
        if (nomeProduto == null || nomeProduto.isBlank()) {
            throw new PedidoInvalidoException("Nome do produto é obrigatório");
        }
        if (quantidade <= 0) {
            throw new PedidoInvalidoException("Quantidade deve ser maior que zero");
        }
        if (precoUnitario == null || precoUnitario.compareTo(BigDecimal.ZERO) <= 0) {
            throw new PedidoInvalidoException("Preço unitário deve ser maior que zero");
        }
    }


    public BigDecimal getSubtotal() {
        return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
    }

    // Getters
    public String getProdutoId() {
        return produtoId;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }
}

