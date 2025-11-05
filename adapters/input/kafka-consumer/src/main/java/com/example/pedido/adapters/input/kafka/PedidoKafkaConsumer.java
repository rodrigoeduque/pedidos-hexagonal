package com.example.pedido.adapters.input.kafka;

import com.example.pedido.adapters.input.kafka.dto.ProcessarPedidoMessage;
import com.example.pedido.application.ports.input.BuscarPedidoUseCase;
import io.micrometer.common.util.internal.logging.InternalLogger;
import io.micrometer.common.util.internal.logging.Slf4JLoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PedidoKafkaConsumer {

    private static final InternalLogger log = Slf4JLoggerFactory.getInstance(PedidoKafkaConsumer.class);

    private final BuscarPedidoUseCase buscarPedidoUseCase;

    public PedidoKafkaConsumer(BuscarPedidoUseCase buscarPedidoUseCase) {
        this.buscarPedidoUseCase = buscarPedidoUseCase;
    }

    @KafkaListener(topics = "processar-pedido", groupId = "pedidos-group")
    public void consumir(ProcessarPedidoMessage message) {
        log.info("Mensagem recebida do Kafka: {}", message);

        try {
            // Busca o pedido e "processa" (aqui você pode adicionar lógica)
            buscarPedidoUseCase.executar(message.pedidoId())
                    .ifPresentOrElse(
                            pedido -> {
                                log.info("Pedido {} processado com sucesso!", pedido.getId());
                                // Aqui você poderia chamar um ConfirmarPedidoUseCase, por exemplo
                            },
                            () -> log.warn("Pedido {} não encontrado", message.pedidoId())
                    );

        } catch (Exception e) {
            log.error("Erro ao processar mensagem do Kafka", e);
            // Aqui você pode implementar retry, DLQ, etc
        }
    }
}