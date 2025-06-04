package com.example.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.dto.MemberDTO;
import com.example.rest.repository.MemberRepository;

@RestController
@RequestMapping("/api/v1/post-api")
public class PostController {
	
	@Autowired
	private MemberRepository memberRepository;

	
	// http://localhost:8090/api/v1/post-api/domain
	@PostMapping("/domain")
	public String postEx() {
		return "Hello Post API";
	}
	
	// http://localhost:8090/api/v1/post-api/member
	// json형태 또는 body르 넣어서 요청을 전달할 경우에는 @RequestBody를 사용해야 한다
	// body에 아래와 같이 입력
	/*
		{
		  "name" : "Ann",
		  "email" : "Ann@green.com",
		  "organization" : "Green"  
		}
	*/
	@PostMapping("/member")
	public MemberDTO postMember(@RequestBody MemberDTO memberDTO) {
		return memberDTO;
	}
	
	
	// http://localhost:8090/api/v1/post-api/member2
	@PostMapping("/member2")
	public String postMember2(@RequestBody Map<String, Object> postData) {
		StringBuilder sb = new StringBuilder();
		
		postData.entrySet().forEach(map -> {
			sb.append(map.getKey() + " : " + map.getValue() + "\n");
		});
		
		return sb.toString();
	}
	
	@PostMapping("/member3")
	public ResponseEntity<MemberDTO> postMember3(@RequestBody MemberDTO memberDTO) {
		// 만약 email이 null인 경우
		if (memberDTO.getEmail() == null || memberDTO.getEmail().trim().isEmpty()) {
			// 400 에러
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); 
            // return ResponseEntity.badRequest().build();
        }
		
		// email이 중복인 경우
		if (memberRepository.findByEmail(memberDTO.getEmail()).isPresent()) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).build();
	    }
		/*
		for (MemberDTO existing : list) {
            if (existing.getEmail().equalsIgnoreCase(memberDTO.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409
            }
        }
        */
		
		// 리스트에 추가
	    if(memberRepository.save(memberDTO)) {
	    	// 201
	    	return ResponseEntity.status(HttpStatus.CREATED).body(memberDTO); 		
	    }    
	    
	    // 추가가 제대로 안됨
	    return ResponseEntity.unprocessableEntity().build();
	}
	
}
