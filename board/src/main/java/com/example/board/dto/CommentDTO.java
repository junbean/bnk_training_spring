package com.example.board.dto;

import java.util.Date;
import lombok.Data;

@Data
public class CommentDTO {
	private Long commentId;
	private Long postId;
	private String content;
	private String authorId;
	private Date regDate;
}
