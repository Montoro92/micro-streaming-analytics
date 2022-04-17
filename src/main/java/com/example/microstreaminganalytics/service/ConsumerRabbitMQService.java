package com.example.microstreaminganalytics.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;

@Service
public class ConsumerRabbitMQService {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerRabbitMQService.class);

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessage(JsonNode device) throws UnknownHostException {
        logger.info("message received!");
    }

}
