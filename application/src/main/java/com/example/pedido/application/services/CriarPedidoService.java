package com.example.pedido.application.services;

import com.example.pedido.application.ports.input.CriarPedidoCommand;
import com.example.pedido.application.ports.input.CriarPedidoUseCase;
import com.example.pedido.application.ports.output.PublicarEventoPedidoPort;
import com.example.pedido.application.ports.output.SalvarPedidoPort;
import com.example.pedido.domain.ItemPedido;
import com.example.pedido.domain.Pedido;

import java.util.List;

// Implementa o caso de uso orquestrando domínio e portas
public class CriarPedidoService implements CriarPedidoUseCase {

    private final SalvarPedidoPort salvarPedidoPort;
    private final PublicarEventoPedidoPort publicarEventoPort;

    public CriarPedidoService(SalvarPedidoPort salvarPedidoPort,
                              PublicarEventoPedidoPort publicarEventoPort) {
        this.salvarPedidoPort = salvarPedidoPort;
        this.publicarEventoPort = publicarEventoPort;
    }

    @Override
    public Pedido executar(CriarPedidoCommand command) {
        // 1. Converte command para domínio
        List<ItemPedido> itens = command.itens().stream()
                .map(item -> new ItemPedido(
                        item.produtoId(),
                        item.nomeProduto(),
                        item.quantidade(),
                        item.precoUnitario()
                ))
                .toList();

        // 2. Cria o pedido (regras de negócio aqui!)
        Pedido pedido = Pedido.criar(command.clienteId(), command.descricao(), itens);

        // 3. Salva (usa a porta de saída)
        salvarPedidoPort.salvar(pedido);

        // 4. Publica evento (usa outra porta de saída)
        publicarEventoPort.publicarPedidoCriado(pedido);

        return pedido;
    }
}