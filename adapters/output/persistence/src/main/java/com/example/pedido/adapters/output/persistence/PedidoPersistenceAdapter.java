package com.example.pedido.adapters.output.persistence;

import com.example.pedido.adapters.output.persistence.entity.PedidoEntity;
import com.example.pedido.adapters.output.persistence.mapper.PedidoEntityMapper;
import com.example.pedido.adapters.output.persistence.repository.PedidoJpaRepository;
import com.example.pedido.application.ports.output.BuscarPedidoPort;
import com.example.pedido.application.ports.output.SalvarPedidoPort;
import com.example.pedido.domain.Pedido;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class PedidoPersistenceAdapter implements SalvarPedidoPort, BuscarPedidoPort {

    private final PedidoJpaRepository repository;

    public PedidoPersistenceAdapter(PedidoJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void salvar(Pedido pedido) {
        PedidoEntity entity = PedidoEntityMapper.toEntity(pedido);
        repository.save(entity);
    }

    @Override
    public Optional<Pedido> buscarPorId(String pedidoId) {
        return repository.findById(pedidoId)
                .map(PedidoEntityMapper::toDomain);
    }

    @Override
    public List<Pedido> buscarTodos() {
        return repository.findAll().stream()
                .map(PedidoEntityMapper::toDomain)
                .collect(Collectors.toList());
    }
}