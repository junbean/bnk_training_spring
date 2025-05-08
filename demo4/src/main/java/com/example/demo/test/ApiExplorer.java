package com.example.demo.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.CulturalProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiExplorer {
	public static void main(String[] args) throws IOException {
		// API의 기본 URL 주소를 저장
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/6480000/gyeongnamcultural/gyeongnamculturallist"); /*URL*/
        // URL에 매개변수 추가
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=miNvylUX0WLqjVxJiBZPJ6ATsOEWyhkbSd%2FRtdlUZN1nUZ%2FJZHS50nHTwd6oeizYQQNx%2B6BxqvMzBBq0VemP6A%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("resultType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*JSON방식으로 호출 시 파라미터 resultType=json 입력*/
        // StringBuilder객체를 URL 객체로 변환
        URL url = new URL(urlBuilder.toString());
        // URL 객체로부터 HTTP 연결을 생성
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // HTTP 메서드를 GET으로 설정
        conn.setRequestMethod("GET");
        // HTTP 요청 헤더에 Content-type을 application/json으로 설정
        conn.setRequestProperty("Content-type", "application/json");
        
        // 서버로부터 받은 HTTP 응답 코드를 콘솔에 출력
        System.out.println("Response code: " + conn.getResponseCode());
        // HTTP 응답 데이터를 읽을 BufferedReader 객체를 선언
        BufferedReader rd;
        
        // 응답 코드가 200~300 사이(성공)면 정상 응답 스트림을, 
        // 그렇지 않으면 에러 스트림을 사용하여 BufferedReader를 초기화
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        
        // 응답 데이터를 저장할 StringBuilder 객체를 생성
        StringBuilder sb = new StringBuilder();
        String line;
        
        // BufferedReader로 응답 데이터를 한 줄씩 읽어 StringBuilder에 추가
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        
        // 리소스를 해제
        rd.close();
        // HTTP 연결을 종료
        conn.disconnect();
        // 응답 데이터 전체를 콘솔에 출력
        String jsonResponse = sb.toString();
        // System.out.println(jsonResponse);


        // ====================================================================================
        // JSON 응답 데이터 파싱 및 객체로 변환 (간단한 문자열 처리 방식)
        ObjectMapper mapper = new ObjectMapper();
        ApiResponse response = mapper.readValue(jsonResponse, ApiResponse.class);
        
        // List로 받아오기
        List<CulturalProperty> list = response.getGyeongnamculturallist().getBody().getItems().getItem();

        // 출력
        for (CulturalProperty property : list) {
            System.out.println("===문화재번호: " + property.getDojijungNo() + "===");
            System.out.println("문화재명: " + property.getMyongching());
            System.out.println("문화재명 한문: " + property.getMyongchingHanmun());
            System.out.println("면적: " + property.getMyonjuk());
            System.out.println("소유자명: " + property.getSoyoujaName());
            System.out.println("관리자명: " + property.getAdminName());
            System.out.println("시대: " + property.getSidae());
            System.out.println("지정날짜: " + property.getJijungDate());
            System.out.println("주소: " + property.getAddress1());
            System.out.println("X 좌표 : " + property.getUtmkX());
            System.out.println("Y 좌표 : " + property.getUtmkY());
            System.out.println("파일 URL 1: " + property.getFileurl1());
            System.out.println("파일 URL 2: " + property.getFileurl2());
            System.out.println("파일 URL 3: " + property.getFileurl3());
            System.out.println("내용: " + property.getContent());
            System.out.println("-------------------------------------------");
        }
        
        /*
        System.out.println("\n===== 문화재 목록 =====");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("\n[문화재 " + (i + 1) + "]");
            System.out.println(list.get(i).getDojijungNo());
            System.out.println(list.get(i).getMyongching());
            System.out.println(list.get(i).getMyongchingHanmun());
            System.out.println(list.get(i).getContent());
            System.out.println(list.get(i).getMyonjuk());
            System.out.println(list.get(i).getSoyoujaName());
            System.out.println(list.get(i).getAdminName());
            System.out.println(list.get(i).getSidae());
            System.out.println(list.get(i).getJijungDate());
            System.out.println(list.get(i).getAddress1());
            System.out.println(list.get(i).getUtmkX());
            System.out.println(list.get(i).getUtmkY());
            System.out.println(list.get(i).getFileurl1());
            System.out.println(list.get(i).getFileurl2());
            System.out.println(list.get(i).getFileurl3());
            System.out.print("------------------------");
        }
        */
    }
	

}
// 일반 인증키 - encoding
// miNvylUX0WLqjVxJiBZPJ6ATsOEWyhkbSd%2FRtdlUZN1nUZ%2FJZHS50nHTwd6oeizYQQNx%2B6BxqvMzBBq0VemP6A%3D%3D

// end point
// https://apis.data.go.kr/6480000/gyeongnamcultural

