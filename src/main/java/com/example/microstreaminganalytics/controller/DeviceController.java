package com.example.microstreaminganalytics.controller;

import com.example.microstreaminganalytics.entity.Calculations;
import com.example.microstreaminganalytics.entity.Quartiles;
import com.example.microstreaminganalytics.service.DeviceService;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.util.List;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);

    private DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("/main")
    public String getMain() {
        return "Devices";
    }

    @PostMapping(path = "/saveDeviceInfo", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveDeviceInfo(@RequestBody JsonNode deviceInfo) throws UnknownHostException {
        deviceService.saveDevice(deviceInfo);
        return ResponseEntity.ok("Device saved!");
    }

    @GetMapping(path = "/getDeviceInfo", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<List<JsonNode>> getDeviceInfo(@RequestBody String deviceId) throws UnknownHostException {
        return ResponseEntity.ok(deviceService.getDeviceInfo(deviceId));
    }

    @GetMapping(path = "/getDeviceCalculations", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Calculations> getCalculationsOfDevice(@RequestBody String deviceId) throws UnknownHostException {
        return ResponseEntity.ok(deviceService.getCalculations(deviceId));
    }

    @GetMapping(path = "/getDeviceMean", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getDeviceMean(@RequestBody String deviceId) throws UnknownHostException {
        return ResponseEntity.ok(deviceService.getDeviceMean(deviceId));
    }

    @GetMapping(path = "/getDeviceMedian", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getDeviceMedian(@RequestBody String deviceId) throws UnknownHostException {
        return ResponseEntity.ok(deviceService.getDeviceMedian(deviceId));
    }

    @GetMapping(path = "/getDeviceMode", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getDeviceMode(@RequestBody String deviceId) throws UnknownHostException {
        return ResponseEntity.ok(deviceService.getDeviceMode(deviceId));
    }

    @GetMapping(path = "/getDeviceStandardDeviation", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getDeviceStandardDeviation(@RequestBody String deviceId) throws UnknownHostException {
        return ResponseEntity.ok(deviceService.getDeviceStandardDeviation(deviceId));
    }

    @GetMapping(path = "/getDeviceQuartiles", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Quartiles> getDeviceQuartiles(@RequestBody String deviceId) throws UnknownHostException {
        return ResponseEntity.ok(deviceService.getDeviceQuartiles(deviceId));
    }

    @GetMapping(path = "/getDeviceMaximum", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getDeviceMaximum(@RequestBody String deviceId) throws UnknownHostException {
        return ResponseEntity.ok(deviceService.getDeviceMaximum(deviceId));
    }

    @GetMapping(path = "/getDeviceMinimum", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getDeviceMinimum(@RequestBody String deviceId) throws UnknownHostException {
        return ResponseEntity.ok(deviceService.getDeviceMinimum(deviceId));
    }

}
