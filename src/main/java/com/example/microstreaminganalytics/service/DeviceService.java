package com.example.microstreaminganalytics.service;

import com.example.microstreaminganalytics.entity.Calculations;
import com.example.microstreaminganalytics.entity.Quartiles;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.List;

@Service
public interface DeviceService {

    void saveDevice(JsonNode deviceInfo) throws UnknownHostException;

    List<JsonNode> getDeviceInfo(String deviceId) throws UnknownHostException;

    Calculations getCalculations(String deviceId);

    String getDeviceMean(String deviceId);

    String getDeviceMedian(String deviceId);

    String getDeviceMode(String deviceId);

    String getDeviceStandardDeviation(String deviceId);

    Quartiles getDeviceQuartiles(String deviceId);

    String getDeviceMaximum(String deviceId);

    String getDeviceMinimum(String deviceId);
}
