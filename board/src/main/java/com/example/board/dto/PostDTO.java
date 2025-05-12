package com.example.board.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PostDTO {
	private Long postId;           // 게시글 번호(시퀀스)
    private String title;          // 제목
    private String content;        // 내용
    private String authorId;       // 작성자 id
    private Date regDate;          // 등록날짜
    private Date updateDate;       // 수정날짜
    private int viewCount;     // 조회수
    private int commentCount;  // 댓글수
}
