package com.example.microstreaminganalytics.controller;

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
    public ResponseEntity<List<JsonNode>> getDeviceInfo(@RequestBody String deviceInfo) throws UnknownHostException {
        return ResponseEntity.ok(deviceService.getDeviceInfo(deviceInfo));
    }

}
