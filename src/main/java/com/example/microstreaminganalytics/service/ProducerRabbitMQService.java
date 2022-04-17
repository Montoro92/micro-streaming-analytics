package com.example.microstreaminganalytics.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProducerRabbitMQService {
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public ProducerRabbitMQService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;

    public void sendMessage(JsonNode analytics) {
        rabbitTemplate.convertAndSend(exchange,routingkey, analytics);
    }
}
