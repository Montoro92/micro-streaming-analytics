package com.example.microstreaminganalytics.repository;

import com.example.microstreaminganalytics.entity.Calculations;
import com.example.microstreaminganalytics.entity.Quartiles;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository {

    void saveDevice(JsonNode device);

    List<JsonNode> getDeviceById(String deviceId);

    Calculations getCalculations(String deviceId);

    String getDeviceMean(String deviceId);

    String getDeviceMedian(String deviceId);

    String getDeviceMode(String deviceId);

    String getDeviceStandardDeviation(String deviceId);

    Quartiles getDeviceQuartiles(String deviceId);

    String getDeviceMaximum(String deviceId);

    String getDeviceMinimum(String deviceId);
}

