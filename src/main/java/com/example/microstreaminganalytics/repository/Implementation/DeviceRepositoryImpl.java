package com.example.microstreaminganalytics.repository.Implementation;

import com.example.microstreaminganalytics.entity.Calculations;
import com.example.microstreaminganalytics.entity.Quartiles;
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

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Override
    public Calculations getCalculations(String deviceId) {
        String mean = getDeviceMean(deviceId);
        String median = getDeviceMedian(deviceId);
        String mode = getDeviceMode(deviceId);
        String standardDeviation = getDeviceStandardDeviation(deviceId);
        Quartiles quartiles = getDeviceQuartiles(deviceId);
        String maximum = getDeviceMaximum(deviceId);
        String minimum = getDeviceMinimum(deviceId);

        return new Calculations(mean, median, mode, standardDeviation, quartiles, maximum, minimum);
    }

    @Override
    public String getDeviceMean(String deviceId) {
        List<JsonNode> devicesById = getDeviceById(deviceId);

        List<String> collect = getCpuUsageOfDevice(devicesById);

        return String.valueOf(collect.stream()
                .mapToDouble(Double::parseDouble)
                .reduce(Double::sum).getAsDouble() / collect.size());
    }

    @Override
    public String getDeviceMedian(String deviceId) {

        List<JsonNode> devicesById = getDeviceById(deviceId);

        List<String> collect = getCpuUsageOfDevice(devicesById);

        double[] numeros = getDoubleList(collect);

        int numElements = collect.size();
        double mediana;

        if(numElements % 2 == 0){
            double sumaMedios = numeros[numElements/2] + numeros[numElements/2 - 1];
            mediana = sumaMedios / 2;
        } else {
            mediana = numeros[numElements/2];
        }
        return String.valueOf(mediana);
    }

    @Override
    public String getDeviceMode(String deviceId) {
        HashMap<Double,Integer> map = new HashMap();

        List<JsonNode> devicesById = getDeviceById(deviceId);

        List<String> collect = getCpuUsageOfDevice(devicesById);

        double[] numeros = getDoubleList(collect);

        int repetido = 0,repetidoCon = 0;
        double numMax = -1;

        for (double i : numeros) {
            if (map.containsKey(i)) {
                repetido =  map.get(i);
                map.put(i, ++repetido);
            } else{
                map.put(i, 1);
            }
        }

        for (Map.Entry<Double,Integer> e : map.entrySet()) {
            if (repetidoCon < e.getValue()) {
                repetidoCon = e.getValue();
                numMax = e.getKey();
            }
        }

        return String.valueOf(numMax);
    }

    @Override
    public String getDeviceStandardDeviation(String deviceId) {
        double standardDeviation = 0.0;

        List<JsonNode> devicesById = getDeviceById(deviceId);

        List<String> collect = getCpuUsageOfDevice(devicesById);

        double[] numeros = getDoubleList(collect);

        Double media = Double.valueOf(getDeviceMean(deviceId));

        for(double num: numeros) {

            standardDeviation += Math.pow(num - media, 2);

        }

        return String.valueOf(Math.sqrt(standardDeviation/collect.size()));
    }

    @Override
    public Quartiles getDeviceQuartiles(String deviceId) {
        List<JsonNode> devicesById = getDeviceById(deviceId);

        List<String> collect = getCpuUsageOfDevice(devicesById);

        List<Double> numeros = collect.stream().map(p -> {
            return Double.valueOf(p);
        }).sorted(Comparator.naturalOrder()).collect(Collectors.toList());

        int q1 = (int) ((numeros.size()+1)*0.25);
        int q2 = (int) ((numeros.size()+1)*0.50);
        int q3 = (int) ((numeros.size()+1)*0.75);

        return new Quartiles(String.valueOf(numeros.get(q1)), String.valueOf(numeros.get(q2)), String.valueOf(numeros.get(q3)));
    }

    @Override
    public String getDeviceMaximum(String deviceId) {
        List<JsonNode> devicesById = getDeviceById(deviceId);

        List<String> collect = getCpuUsageOfDevice(devicesById);

        return String.valueOf(collect.stream().mapToDouble(Double::parseDouble).max().getAsDouble());
    }

    @Override
    public String getDeviceMinimum(String deviceId) {
        List<JsonNode> devicesById = getDeviceById(deviceId);

        List<String> collect = getCpuUsageOfDevice(devicesById);

        return String.valueOf(collect.stream().mapToDouble(Double::parseDouble).min().getAsDouble());
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

    public List<String> getCpuUsageOfDevice(List<JsonNode> devicesById) {
        return devicesById.stream().map(p -> {
            return p.get("event").get("device").get("cpuUsage").get("current").asText();
        }).collect(Collectors.toList());
    }

    public double[] getDoubleList(List<String> numbers) {
        return numbers.stream().mapToDouble(Double::parseDouble).toArray();
    }

}

