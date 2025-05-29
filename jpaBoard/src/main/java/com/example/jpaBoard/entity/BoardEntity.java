package com.example.jpaBoard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "jpa_board")
@Getter
@Setter
public class BoardEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "board_id")
    private Long boardId;
	
	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;
	
	@Column(name = "writer")
	private String writer;
}
/*

@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_jpa_board_id")
@SequenceGenerator(
	name = "seq_jpa_board_id",         // JPA에서 사용할 시퀀스 이름 (논리명)
	sequenceName = "seq_jpa_board_id",  // 실제 DB의 시퀀스 이름
	allocationSize = 1              // 증가 단위 (1로 두는 것을 권장)
)

*/