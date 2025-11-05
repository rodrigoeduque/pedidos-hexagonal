package com.example.pedido.domain.exceptions;

public class PedidoInvalidoException extends RuntimeException {
    public PedidoInvalidoException(String mensagem) {
        super(mensagem);
    }
}