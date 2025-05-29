package com.example.jpa01.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.jpa01.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {
	MemberEntity findByUsername(String username);
	
	// 메서드 이름으로 자동 쿼리 생성
	long countByUsernameAndPassword(String username, String password);
	
	// 커스텀 @Query 사용 - JPQL
	// *는 사용이 안된다 -> 엔티티 변수(MemberEntity)를 사용해야 한다
	@Query("SELECT COUNT(m) FROM MemberEntity m WHERE m.username = :username AND m.password = :password")
	long getLoginCount(@Param("username") String username, @Param("password") String password);

	// 네이티브 SQL 사용
	@Query(value = "SELECT COUNT(*) FROM tbl_member2 WHERE username = :username AND password = :password", nativeQuery = true)
	long countByRawSQL(@Param("username") String username, @Param("password") String password);

}

/*

객체지햐 방식으로 DBMS를 다루는 방식 - jpa 방식

메서드 이름 규칙

커스텀 쿼리

네이티브 SQL


JPA와 MyBatis는 병행이 가능하다


*/