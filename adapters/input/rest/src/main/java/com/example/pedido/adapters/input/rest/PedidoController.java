package com.example.pedido.adapters.input.rest;

import com.example.pedido.adapters.input.rest.dto.CriarPedidoRequest;
import com.example.pedido.adapters.input.rest.dto.PedidoResponse;
import com.example.pedido.adapters.input.rest.mapper.PedidoRestMapper;
import com.example.pedido.application.ports.input.BuscarPedidoUseCase;
import com.example.pedido.application.ports.input.CriarPedidoCommand;
import com.example.pedido.application.ports.input.CriarPedidoUseCase;
import com.example.pedido.domain.Pedido;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final CriarPedidoUseCase criarPedidoUseCase;
    private final BuscarPedidoUseCase buscarPedidoUseCase;

    public PedidoController(CriarPedidoUseCase criarPedidoUseCase,
                            BuscarPedidoUseCase buscarPedidoUseCase) {
        this.criarPedidoUseCase = criarPedidoUseCase;
        this.buscarPedidoUseCase = buscarPedidoUseCase;
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> criar(@RequestBody CriarPedidoRequest request) {
        CriarPedidoCommand command = PedidoRestMapper.toCommand(request);
        Pedido pedido = criarPedidoUseCase.executar(command);
        PedidoResponse response = PedidoRestMapper.toResponse(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> buscar(@PathVariable String id) {
        return buscarPedidoUseCase.executar(id)
                .map(PedidoRestMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponse>> buscarTodos() {

        List<PedidoResponse> pedidos = buscarPedidoUseCase.buscarTodos().stream()
                .map(PedidoRestMapper::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(pedidos);
    }
}