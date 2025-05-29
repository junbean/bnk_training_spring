package com.example.jpa01.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_member2")
@Getter
@Setter
public class MemberEntity {
	@Id
	private String username;
	private String password;
	private String name;
	private String role;
}


/*

만약 tbl_member2의 테이블이 없다면 jpa가 알아서 테이블을 생성해준다
	메세지
		Hibernate: create table tbl_member2 (name varchar2(255 char), password varchar2(255 char), role varchar2(255 char), username varchar2(255 char) not null, primary key (username))

	@Id
		헤당 컬럼을 Priamry key로 지정한다는 의미
	

*/