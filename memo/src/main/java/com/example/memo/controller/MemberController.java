package com.example.memo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.memo.entity.MemberEntity;
import com.example.memo.repository.MemberRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    
    // 등록 폼 이동
    @GetMapping("/registForm")
    public String registForm(Model model) {
        model.addAttribute("member", new MemberEntity()); // 빈 객체 전달
        return "/membersForm"; // templates/membersForm.html
    }
	
    // 등록 처리
    /*
    @PostMapping("/regist")
    public String regist(
        @Valid @ModelAttribute("member") MemberEntity memberEntity,
        BindingResult result,
        Model model
    ) {
        if (result.hasErrors()) {
            // 유효성 검사에 실패하면 입력 폼 다시 보여주기
            return "/membersForm";
        }

        memberRepository.save(memberEntity); // 유효한 경우 저장
        return "redirect:/members"; // 목록 페이지로 이동 (예: 리스트 페이지)
    }
	*/
    
    
	@PostMapping("/regist")
	public String regist(
		@Valid @ModelAttribute("member") MemberEntity member, 
		BindingResult result
	) {
		if(result.hasErrors()) {
			return "/membersForm";
		}
		
		return "redirect:/members";
	}
}
