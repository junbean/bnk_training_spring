package com.example.talend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.talend.dao.IMemberDao;
import com.example.talend.dto.MemberDTO;

// 허용할 출처 지정 - 특정 주소로 들어오는 요청은 허용해라
//@CrossOrigin(origins = "http://192.168.0.248:5501")
// 모든 경로에 대한 요청 허용
@CrossOrigin(origins = "*")
@RestController
public class MemberController {
	
	private IMemberDao memberDao;
	
	@Autowired
	public MemberController(IMemberDao dao) {
		memberDao = dao;
	}
	
	@GetMapping("/member")
	public MemberDTO member() {
		MemberDTO member = memberDao.getMember();
		return member;
	}
	
	@GetMapping("/members")
	public List<MemberDTO> members() {
		List<MemberDTO> list = memberDao.getMemberList();
		return list;
	}
	
	@GetMapping("/regist")
	public int memberRegist() {
		MemberDTO member = new MemberDTO();
		member.setId("qweasdzxc");
		member.setPw("qqq111www");
		member.setName("테스트실행");
		member.setPhone("11122333");
		member.setGrade("C");
		
		int result = memberDao.memberRegist(member);
		
		return result;
	}
	

	@PostMapping("/member1")
	public int addMember1(MemberDTO member) {
		System.out.println(member);
		
		if(member != null) {
			return 1;
		}
		return 0;
	}
	
	@PostMapping("/member2") 
	public int addMember2(@RequestBody MemberDTO member) {
		System.out.println(member);
		
		if(member != null) {
			return 1;
		}
		return 0;
	}
	
}
