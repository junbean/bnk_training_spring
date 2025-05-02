package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.MemberDAO;
import com.example.demo.dto.MemberDTO;

@Controller
public class MemberController {
	
	@Autowired
    private MemberDAO memberDAO;
	
	@RequestMapping("/")
	public String root() {
		System.out.println("root============================");
		return "index";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		System.out.println("list============================");
		
		// ===========================================================================
		// 1. DB에서 member 리스트 가져오기 - 기존 DAO 사용 
		// member리스트 가져오기
		// MemberDAO dao = new MemberDAO();
		// List<MemberDTO> memberList = dao.selectAll();
		// request에 저장
		// model.addAttribute("list", memberList);
		// ===========================================================================

		

		// ===========================================================================
		// 2. DB에서 member 리스트 가져오기 - JDBC API 사용
		// member리스트 가져오기
        List<MemberDTO> memberList = memberDAO.list();
        System.out.println(memberList);
		// request에 저장
        model.addAttribute("list", memberList);
		// ===========================================================================
		
		// 화면 출력
		return "list";
	}

	@GetMapping("/member")
	public String member(
		@RequestParam("id") String id,
		Model model
	) {
		System.out.println("member============================");
		MemberDTO member = memberDAO.viewMember(id);
		model.addAttribute("member", member);
		return "member";
	}
	
	@GetMapping("/writePage")
	public String writePage() {
		System.out.println("writePage============================");
		return "write";
	}
	
	@PostMapping("/write")
	public String write(
		MemberDTO memberDTO,
		Model model
	) {
		System.out.println("write 작업 수행============================");
		
		int result = memberDAO.writeMember(memberDTO);
		if(result != 0) {
			System.out.println("등록됨");
		} else {
			System.out.println("등록안됨");
		}
		
		// 기존 화면 출력 방식
		// return "list";
		
		// 리다이렉트 방식
		return "redirect:/list";
	}
	
	@GetMapping("/detailPage")
	public String detailPage(
		@RequestParam("id") String id,
		Model model
	) {
		System.out.println("detailPage============================");
		MemberDTO member = memberDAO.viewMember(id);
		model.addAttribute("member", member);
		return "detail";
	}
	
	@GetMapping("/updatePage")
	public String updatePage(
		@RequestParam("id") String id,
		Model model
	) {
		System.out.println("updatePage============================");
		MemberDTO member = memberDAO.viewMember(id);
		model.addAttribute("member", member);
		return "update";
	}
	
	@PostMapping("/update")
	public String update(
		MemberDTO memberDTO,
		Model model
	) {
		System.out.println("update============================");
		
		int result = memberDAO.updateMember(memberDTO);
		if(result != 0) {
			System.out.println("수정됨");
		} else {
			System.out.println("수정안됨");
		}

	    return "redirect:/detailPage?id=" + memberDTO.getId();
	}
	
	
	
}

// IOC, DI
/*

IOC - 제어의 역전
	객체의 생성과 관리를 개발자가 직접 하지 않고 프레임워크에 위임하는 방식
	
	
	
	예제
		//====================================================================
		public class UserService {
		    private UserRepository userRepository;
		    
		    // 프레임워크가 의존성을 주입해줌 - new로 새로운 객체를 생성하지 않음
		    public UserService(UserRepository userRepository) {
		        this.userRepository = userRepository;
		    }
		}
		//====================================================================
	
	
DI
	의존성 주입
 
 
 	방식 
 		1. 생성자 주입 방식
 		2. setter 주입 방식
*/


/*

M : 모델 : 서버가 클라이언트에게 전달하는 데이터(를 처리하는 과정)
V : 뷰(jsp) - 클라이언트가 보는 화면 -> 서버가 제공한 데이터
C : 컨트롤러 - 요청/응답(Servlet)

웹서버(정적인 페이지 - 아파치 서버) + 동적데이터(WAS - 톰캣)
--------------------------------------------------
서비스 제공 	-> 	요청이 있으면
제공하다 		-> 	응답(페이지 + 추가 데이터 (처리))


model1 방식
	모든 파일 jsp로 구성

model2 방식
	Servlet + jsp + 클래스(DAO, Service)
	
----------------------------------------------------
컨트롤러(요청받기) --> 주소(Path-경로-URL)



*/
