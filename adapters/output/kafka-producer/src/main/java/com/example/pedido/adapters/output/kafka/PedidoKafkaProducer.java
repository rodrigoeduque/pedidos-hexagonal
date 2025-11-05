package com.example.pedido.adapters.output.kafka;

import com.example.pedido.adapters.output.kafka.dto.PedidoCriadoEvent;
import com.example.pedido.application.ports.output.PublicarEventoPedidoPort;
import com.example.pedido.domain.Pedido;
import io.micrometer.common.util.internal.logging.InternalLogger;
import io.micrometer.common.util.internal.logging.Slf4JLoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PedidoKafkaProducer implements PublicarEventoPedidoPort {

    private static final InternalLogger log = Slf4JLoggerFactory.getInstance(PedidoKafkaProducer.class);
    private static final String TOPIC = "pedido-criado";

    private final KafkaTemplate<String, PedidoCriadoEvent> kafkaTemplate;

    public PedidoKafkaProducer(KafkaTemplate<String, PedidoCriadoEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publicarPedidoCriado(Pedido pedido) {
        try {
            PedidoCriadoEvent event = new PedidoCriadoEvent(
                    pedido.getId(),
                    pedido.getClienteId(),
                    pedido.getValorTotal(),
                    pedido.getStatus().name(),
                    pedido.getDataCriacao()
            );

            kafkaTemplate.send(TOPIC, pedido.getId(), event);
            log.info("Evento publicado no Kafka: {}", event);

        } catch (Exception e) {
            log.error("Erro ao publicar evento no Kafka", e);
            // Aqui você pode implementar retry, fallback, etc
        }
    }
}