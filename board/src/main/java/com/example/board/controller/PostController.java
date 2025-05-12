package com.example.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
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
	
	/*
	@GetMapping("/list")
	public String postList(Model model) {
		System.out.println("게시글 목록");
		
		List<PostDTO> list = postDao.selectToList();
		model.addAttribute("postList", list);
		
		return "post/postList";
	}
	*/
	

	@GetMapping("/list")
	public String postList(
		@RequestParam(value = "page", defaultValue = "1") int page,
		Model model
	) {
		// 페이지 관련 계산
		int pageSize = 10; 		// 한 페이지당 게시글 수
		int start = (page * pageSize) - (pageSize - 1);		// 현재 페에지에서 보여줄 첫번째 게시글의 인덱스 계산 -> 1페이지 : 1
		int end = (page * pageSize);						// 현재 페이지에서 보여줄 마지막 게시글의 인덱스 계산 -> 1페이지 : 10
		
		// 전체 게시글 수, 전체 페이지 수 계산
		int totalPosts = postDao.getTotalPostCount(); 		// 전체 게시글 수 -> 100 : 100
		int totalPages = (int) Math.ceil((double)totalPosts / pageSize); // 전체 페이지 수 계산 -> 95 : 10(9.5)
		
		//현재 페이지 블록의 시작과 끝 페이지 계산 (페이지 블록 : 화면에 표시할 페이지 번호 묶음)
		int pageBlockSize = 10;		// 페이지 블록 화면에 한번에 보여줄 페이지 번호의 개수(5로 설정된 첫번째 블럭 -> 1 2 3 4 5 )
		int startPage = ((page - 1) / pageBlockSize) * pageBlockSize + 1; // 현재 페이지 블록의 시작 페이지를 계산 (현재페이지 3 블록크기 5 -> 시작페이지 1, 1~5블록에 해당)
		int endPage = Math.min(startPage + pageBlockSize - 1, totalPages); // 현재 블록의 마지막 페이지 번호 (시작페이지 6, 블록크기 5, 전체페이지 20 -> 끝 페이지는 10)
		
		// 이전 블록과 다음 블록으로 이동하기 위한 페이지 번호
		// << 6 7 8 9 10 >>			<-- 이거
		boolean hasPrevBlock = startPage > 1;		// 이전페이지 블록의 존재여부 (startPage가 1보다 크면 이전 블록이 있다는 거)
		boolean hasNextBlock = endPage < totalPages;	// 다음페이지 블록의 존재여부 ( )
		int prevBlockPage = startPage - 1;
		int nextBlockPage = endPage + 1;
		
		
		// 게시글 데이터 조회
		List<PostDTO> list = postDao.selectToPage(start, end);
		
		// 모델에 정보 추가
		model.addAttribute("postList", list);		// 게시글 내용 = 100
		model.addAttribute("currentPage", page);	// 현재 페이지 = 1
		model.addAttribute("totalPages", totalPages);	// 전체 페이지 수 = 
		model.addAttribute("startPage", startPage);		// 시작 페이지
		model.addAttribute("endPage", endPage);			// 끝 페이지
		model.addAttribute("hasPrevBlock", hasPrevBlock);
		model.addAttribute("hasNextBlock", hasNextBlock);
		model.addAttribute("prevBlockPage", prevBlockPage);
		model.addAttribute("nextBlockPage", nextBlockPage);
		
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
		PostDTO post = postDao.selectToOne(postId);
		
		if(post != null) {
			model.addAttribute("postInfo", post);
			return "post/postDetail";
		} else {
			return "redirect:/post/list";
		}
	}
}
/*
boolean hasPrevBlock = startPage > 1;		// 이전페이지 블록의 존재여부 (startPage가 1보다 크면 이전 블록이 있다는 거)
boolean hasNextBlock = endPage < totalPages;	// 다음페이지 블록의 존재여부 ( )
int prevBlockPage = startPage - 1;
int nextBlockPage = endPage + 1;
*/




/*
// 이전 블록과 다음 블록으로 이동하기 위한 페이지 번호
// << 6 7 8 9 10 >>			<-- 이거
boolean hasPrevPage = page > 1;		// 이전페이지 블록의 존재여부 (startPage가 1보다 크면 이전 블록이 있다는 거)
boolean hasNextPage = page < totalPages;	// 다음페이지 블록의 존재여부 ( )
int prevPage = page - 1;
int nextPage = page + 1;


// 게시글 데이터 조회
List<PostDTO> list = postDao.selectToPage(start, end);

// 모델에 정보 추가
model.addAttribute("postList", list);		// 게시글 내용 = 100
model.addAttribute("currentPage", page);	// 현재 페이지 = 1
model.addAttribute("totalPages", totalPages);	// 전체 페이지 수 = 
model.addAttribute("startPage", startPage);		// 시작 페이지
model.addAttribute("endPage", endPage);			// 끝 페이지
model.addAttribute("hasPrevPage", hasPrevPage);
model.addAttribute("hasNextPage", hasNextPage);
model.addAttribute("prevPage", prevPage);
model.addAttribute("nextPage", nextPage);
*/