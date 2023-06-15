package com.example.cooperative.impl.service;

import com.example.cooperative.impl.model.SessionClosedRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class MessageProducerService {

    private final KafkaProducer<String, Object> kafkaProducer;

    public void sendMessagetoQueue(SessionClosedRequest request) {
        ProducerRecord<String, Object> record = new ProducerRecord<>
                ("agenda.votes", "closed.session" , request);
        try{
            kafkaProducer.send(record);
            log.info("[MessageProducerService - sendMessagetoQueue] Mensagem enviada com sucesoo {}", request);
        }catch(Exception e) {
            log.info("[MessageProducerService - sendMessagetoQueue] Erro ao enviar mensagem para topico closed.session {}", request);
            log.error(e.getMessage());
        }
    }


}
