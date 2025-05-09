package com.example.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.board.dao.IPostDao;
import com.example.board.dto.PostDTO;
import com.example.board.dto.UserDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/post")
public class PostController {
	
	private IPostDao postDao;
	
	@Autowired
	public PostController(IPostDao dao) {
		postDao = dao;
	}
	
	@GetMapping("/list")
	public String postList(Model model) {
		System.out.println("게시글 목록");
		
		List<PostDTO> list = postDao.selectToList();
		model.addAttribute("postList", list);
		
		return "post/postList";
	}
	
	@GetMapping("/writeForm")
	public String writeForm() {
		System.out.println("게시글 작성폼");
		return "post/postWrite";
	}
	
	@PostMapping("/write")
	public String postWrite(
		@RequestParam("title") String title,
		@RequestParam("content") String content,
		HttpServletRequest request
	) {
		System.out.println("게시글 등록 수행");
		
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
		
		if(loginUser == null) {
			// 로그인 되지 않은 경우
			return "redirect/user/loginForm";
		}
		
		PostDTO post = new PostDTO();
		post.setTitle(title);
		post.setContent(content);
		post.setAuthorId(loginUser.getUserId());
		
		int result = postDao.insert(post);
		
		if(result > 0) {
			System.out.println("게시글 등록 성공");
		} else {
			System.out.println("게시글 등록 실패");
		}
		
		return "redirect:/post/list";
	}
	
	@GetMapping("/detail")
	public String postDetail(@RequestParam("postId") int postId, Model model) {
		System.out.println("상세 페이지");
		
		PostDTO post = postDao.selectToOne(postId);
		
		if(post != null) {
			model.addAttribute("postInfo", post);
			return "post/postDetail";
		} else {
			return "redirect:/post/list";
		}
	}
}
