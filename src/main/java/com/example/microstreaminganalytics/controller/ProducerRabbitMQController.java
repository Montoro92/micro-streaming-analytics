package com.example.microstreaminganalytics.controller;

import com.example.microstreaminganalytics.service.ProducerRabbitMQService;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbitMQProducer")
public class ProducerRabbitMQController {

    private ProducerRabbitMQService producerService;
    private static final Logger logger = LoggerFactory.getLogger(ProducerRabbitMQController.class);

    @Autowired
    public ProducerRabbitMQController(ProducerRabbitMQService producerRabbitMQService) {
        this.producerService = producerRabbitMQService;
    }

    @PostMapping(path = "/produce", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sendMessage(@RequestBody JsonNode analytics) {
        producerService.sendMessage(analytics);
        logger.info("Analysis sent: " + analytics);
        return ResponseEntity.ok("Message sent!");
    }
}
