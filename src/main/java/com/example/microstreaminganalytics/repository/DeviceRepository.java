package com.example.microstreaminganalytics.repository;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository {

    void saveDevice(JsonNode device);

    List<JsonNode> getDeviceById(String deviceId);

}

