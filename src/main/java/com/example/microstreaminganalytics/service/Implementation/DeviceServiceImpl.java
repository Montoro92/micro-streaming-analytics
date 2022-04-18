package com.example.microstreaminganalytics.service.Implementation;

import com.example.microstreaminganalytics.repository.DeviceRepository;
import com.example.microstreaminganalytics.service.DeviceService;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    private DeviceRepository deviceRepository;

    private static final Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);

    @Autowired
    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public void saveDevice(JsonNode deviceInfo) throws UnknownHostException {

        deviceRepository.saveDevice(deviceInfo);

    }

    public List<JsonNode> getDeviceInfo(String deviceId) throws UnknownHostException {

        return deviceRepository.getDeviceById(deviceId);

    }
}
