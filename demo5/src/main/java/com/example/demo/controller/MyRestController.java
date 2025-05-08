package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {
	
	@GetMapping("/getList")
	public String getList() {
		System.out.println("리스트 목록");
		String result = null;
		
		try {
			result = fetchData();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String fetchData() throws IOException {
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
        while ((line = rd.readLine()) != null) {
        	sb.append(line);
        }
        rd.close(); 
        conn.disconnect();

        return sb.toString();
	}
}
