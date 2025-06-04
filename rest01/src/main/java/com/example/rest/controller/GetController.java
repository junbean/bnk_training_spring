package com.example.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.dto.MemberDTO;

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {
	
	List<MemberDTO> list = new ArrayList<>();
	
	// http:localhost:8090/api/v1/get-api/hello
	// Hello World가 반환되도록 구현
	@GetMapping("/hello")
	public String hello() {
		System.out.println("hello");
		return "Hello World!";
	}
	
	// http:localhost:8090/api/v1/get-api/variable1/{variable}
	@GetMapping("/variable1/{variable}")
	public String variable(@PathVariable("variable") String variable) {
		return variable;
	}

	// http:localhost:8090/api/v1/get-api/request1?name=value&email=value&organization=value
	// http://localhost:8090/api/v1/get-api/request1?name=Ann&email=aa@naver.com&organization=GREEN
	@GetMapping("/request1")
	public String request(
		@RequestParam("name") String name, 
		@RequestParam("email") String email, 
		@RequestParam("organization") String organization
	) {
		return name + ", " + email + ", " + organization;
	}
	
	/*
	@GetMapping("/request2")
	public Map<String, String> request2(
		@RequestParam("key1") String key1, 
		@RequestParam("key2") String key2
	) {
		Map<String, String> result = new HashMap<>();
		result.put("key1", key1);
		result.put("key2", key2);
		return result;
	}
	*/
	
	//  http:localhost:8090/api/v1/get-api/request2?name=Ann&email=aa@naver.com&organization=GREEN
	@GetMapping("/request2")
	public String request2(@RequestParam Map<String, String> param) {
		Map<String, String> result = new HashMap<>();
		StringBuilder sb = new StringBuilder();
		param.entrySet().forEach(map -> {
			sb.append(map.getKey() + " : " + map.getValue() + "");
		});
		return sb.toString();
	}
	
	// http:localhost:8090/api/v1/get-api/request3?name=value&email=value&organization=value
	@GetMapping("/request3")
	public String request3(MemberDTO memberDTO) {
		return memberDTO.toString();
	} 
	
	// http:localhost:8090/api/v1/get-api/request4/{name}
	// 한명의 값을 반환
	@GetMapping("/request4/{name}")
	public MemberDTO request4(@PathVariable("name") String name) {
		MemberDTO member1 = new MemberDTO();
		member1.setName("Ann");
		member1.setEmail("ann@green.com");
		member1.setOrganization("GREEN");
		
		MemberDTO member2 = new MemberDTO();
		member2.setName("Ben");
		member2.setEmail("ben@green.com");
		member2.setOrganization("GREEN");
		
		MemberDTO member3 = new MemberDTO();
		member3.setName("Choi");
		member3.setEmail("choi@green.com");
		member3.setOrganization("GREEN");
		
		list.add(member1);
		list.add(member2);
		list.add(member3);
		
		for(MemberDTO m : list) {
			if(m.getName().equals(name)) {
				return m;
			}
		}
		
		return null;
	}

	@GetMapping("/status/{value}")
	public ResponseEntity<String> status(@PathVariable("value") String value) {
		if(value.equals("200")) {
			return ResponseEntity.ok("정상");
		} else {
			return ResponseEntity.notFound().build();			
		}
	}
	
	
	// http:localhost:8090/api/v1/get-api/request5/{name}
	@GetMapping("/request5/{name}")
	public Map<Integer, MemberDTO> request5(@PathVariable("name") String name) {
		list.clear();
		
		MemberDTO member1 = new MemberDTO();
		member1.setName("Ann");
		member1.setEmail("ann@green.com");
		member1.setOrganization("GREEN");
		
		MemberDTO member2 = new MemberDTO();
		member2.setName("Ben");
		member2.setEmail("ben@green.com");
		member2.setOrganization("GREEN");
		
		MemberDTO member3 = new MemberDTO();
		member3.setName("Choi");
		member3.setEmail("choi@green.com");
		member3.setOrganization("GREEN");
		
		list.add(member1);
		list.add(member2);
		list.add(member3);
		
		Map<Integer, MemberDTO> result = new HashMap<>();
		boolean flag = false;
		
		// 검색
		for(MemberDTO m : list) {
			if(m.getName().equals(name)) {
				result.put(200, m);
				flag = true;
				break;
			}
		}
		
		if(!flag) {
			result.put(404, null);
		}
		
		return result;
	}
	
	@GetMapping("/request6/{name}")
	public ResponseEntity<MemberDTO> request6(@PathVariable("name") String name) {
		list.clear();
		
		MemberDTO member1 = new MemberDTO();
		member1.setName("Ann");
		member1.setEmail("ann@green.com");
		member1.setOrganization("GREEN");
		
		MemberDTO member2 = new MemberDTO();
		member2.setName("Ben");
		member2.setEmail("ben@green.com");
		member2.setOrganization("GREEN");
		
		MemberDTO member3 = new MemberDTO();
		member3.setName("Choi");
		member3.setEmail("choi@green.com");
		member3.setOrganization("GREEN");
		
		list.add(member1);
		list.add(member2);
		list.add(member3);
		
		// 검색
		for(MemberDTO m : list) {
			if(m.getName().equals(name)) {
				// 아래 3개는 결과가 같음
				return ResponseEntity.status(HttpStatus.OK).body(m);
				// return ResponseEntity.status(200).body(m);
				// return ResponseEntity.ok(m);
			}
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
		// return ResponseEntity.notFound().build();;
	}
	
	
}
