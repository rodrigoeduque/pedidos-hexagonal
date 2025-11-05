package com.example.pedido.adapters.output.persistence.repository;

import com.example.pedido.adapters.output.persistence.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoJpaRepository extends JpaRepository<PedidoEntity, String> {
}