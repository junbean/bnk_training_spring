package com.example.rest.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public abstract  class BaseEntity {
	// 컬럼명 : regdate
	// 게시글 수정이 안되게 설정
	@Column(name = "regdate", updatable = false)
	@CreatedDate
	private LocalDateTime regDate;
	
	@Column(name = "moddate")
	@LastModifiedDate
	private LocalDateTime modDate;
}
