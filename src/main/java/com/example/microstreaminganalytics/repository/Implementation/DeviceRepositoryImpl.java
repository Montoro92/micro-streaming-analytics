package com.example.microstreaminganalytics.repository.Implementation;

import com.example.microstreaminganalytics.repository.DeviceRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DeviceRepositoryImpl implements DeviceRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveDevice(JsonNode device) {

        if ((device.has("event") && device.get("event").has("device")) == true) {

            Document doc = Document.parse(device.toString());
            mongoTemplate.insert(doc, "Devices");

        }
    }

    public List<JsonNode> getDeviceById(String deviceId) {

        Query qry = new Query(Criteria.where("event.device.id").is(deviceId));

        List<Document> devices = mongoTemplate.find(qry, Document.class, "Devices");

        return documentToJsonNode(devices);

    }

    public List<JsonNode> documentToJsonNode(List<Document> documents) {
        ObjectMapper mapper = new ObjectMapper();

        return documents.stream().map(p -> {
            try {
                return mapper.readTree(p.toJson());
            } catch (JsonProcessingException e) {
                return mapper.createObjectNode();
            }
        }).collect(Collectors.toList());

    }

}

