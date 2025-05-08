package com.example.demo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.CulturalProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CulturalPropertyService {
	public List<CulturalProperty> fetchCulturalProperties() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/6480000/gyeongnamcultural/gyeongnamculturallist");
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=miNvylUX0WLqjVxJiBZPJ6ATsOEWyhkbSd%2FRtdlUZN1nUZ%2FJZHS50nHTwd6oeizYQQNx%2B6BxqvMzBBq0VemP6A%3D%3D");
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=1");
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=10");
        urlBuilder.append("&" + URLEncoder.encode("resultType","UTF-8") + "=json");

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        BufferedReader rd = new BufferedReader(new InputStreamReader(
                conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300
                        ? conn.getInputStream()
                        : conn.getErrorStream()));

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) sb.append(line);
        rd.close(); conn.disconnect();

        ObjectMapper mapper = new ObjectMapper();
        ApiResponse response = mapper.readValue(sb.toString(), ApiResponse.class);
        return response.getGyeongnamculturallist().getBody().getItems().getItem();
    }
}
