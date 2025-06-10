package com.example.memo.repository;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.memo.entity.MemberEntity;

import jakarta.transaction.Transactional;

@SpringBootTest
class TestMemberRepository {

	@Autowired
	MemberRepository memberRepository;
	
	/*
	@Test
	void test() {
		fail("Not yet implemented");
	}
	*/
	
	
	@Test
	public void testDelete() {
		Long member_id = 2L;
		
		memberRepository.deleteById(member_id);
		
	}
	
	
	// @Transactional
	@Test
	public void testUpdate() {
		/*
		MemberEntity member = MemberEntity.builder()
				.id(1L)
				.username("user1")
				.password("4444")
				.build();
		*/
       MemberEntity member = memberRepository.findById(1L).orElseThrow();
       member.setPassword("5678");
       member.setName("user01");
       memberRepository.save(member);
	}
	
	
	@Test
	void testSelect3() {
		Long member_id = 30L;
		try {
			MemberEntity member = memberRepository.findById(member_id).orElseThrow(() -> new NoSuchElementException("회원"));
			System.out.println(member);
		} catch(NoSuchElementException e) {
			System.out.println(e.toString());
		}
	}
	
	
	@Test
	void testSelect2() {
		Long member_id = 5L;
		
		memberRepository.findById(member_id).ifPresentOrElse(
			member -> System.out.println(member),
			() -> System.out.println("해당 회원을 찾을 수 없습니다")
		);
	}
	
	@Test
	void testSelect1() {
		Long member_id = 2L;
		
		Optional<MemberEntity> result = memberRepository.findById(member_id);
		
		System.out.println("=============================================");
		if(result.isPresent()) {
			MemberEntity member = result.get();
			System.out.println(member);
		} else {
			System.out.println("해당 회원을 찾을 수 없습니다");
		}
		
	}
	
	@Test
	void testInsertDummies() {
		IntStream.rangeClosed(0, 3).forEach(i -> {
			MemberEntity member = MemberEntity.builder()
					.username("user" + i)
					.password("1234" + i)
					.name("user" + i)
					.phone("010-1111-222" + i)
					.build();
			memberRepository.save(member);
		});
	}
}
