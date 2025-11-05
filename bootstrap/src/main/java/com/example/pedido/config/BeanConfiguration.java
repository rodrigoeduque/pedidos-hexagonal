package com.example.pedido.config;

import com.example.pedido.application.ports.input.BuscarPedidoUseCase;
import com.example.pedido.application.ports.input.CriarPedidoUseCase;
import com.example.pedido.application.ports.output.BuscarPedidoPort;
import com.example.pedido.application.ports.output.PublicarEventoPedidoPort;
import com.example.pedido.application.ports.output.SalvarPedidoPort;
import com.example.pedido.application.services.BuscarPedidoService;
import com.example.pedido.application.services.CriarPedidoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CriarPedidoUseCase criarPedidoUseCase(
            SalvarPedidoPort salvarPedidoPort,
            PublicarEventoPedidoPort publicarEventoPort) {
        return new CriarPedidoService(salvarPedidoPort, publicarEventoPort);
    }

    @Bean
    public BuscarPedidoUseCase buscarPedidoUseCase(BuscarPedidoPort buscarPedidoPort) {
        return new BuscarPedidoService(buscarPedidoPort);
    }
}