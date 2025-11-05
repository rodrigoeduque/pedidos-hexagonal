package com.example.pedido.application.ports.output;

import com.example.pedido.domain.Pedido;

// Interface que define O QUE o negócio precisa (mas não COMO)
public interface SalvarPedidoPort {
    void salvar(Pedido pedido);
}