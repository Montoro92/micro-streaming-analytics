package com.example.microstreaminganalytics.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.List;

@Service
public interface DeviceService {

    void saveDevice(JsonNode deviceInfo) throws UnknownHostException;

    List<JsonNode> getDeviceInfo(String deviceId) throws UnknownHostException;

}
