package org.example.consumers;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageListener {

    // Méthode qui sera appelée pour consommer les messages du topic "quickstart"
    @KafkaListener(topics = "quickstart", groupId = "monGroup")
    public void consume(String message) {
        System.out.println("Message reçu : " + message);
    }
}