package com.example.nexosapp.recursos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class OpenCageService {

    @Value("${opencage.api_key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Método para obtener la latitud y longitud de una dirección
     * @param address
     * @return
     */
    public Map<String, Double> getLatLon(String address) {
        String url = String.format("https://api.opencagedata.com/geocode/v1/json?q=%s&key=%s", address, apiKey);
        Map response = restTemplate.getForObject(url, Map.class);

        if (response != null && response.containsKey("results")) {
            Map geometry = (Map) ((Map) ((List) response.get("results")).get(0)).get("geometry");
            return Map.of("lat", (Double) geometry.get("lat"), "lon", (Double) geometry.get("lng"));
        }
        return Map.of();
    }
}