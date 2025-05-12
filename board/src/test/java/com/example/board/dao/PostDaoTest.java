package com.example.board.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.board.dto.PostDTO;

@SpringBootTest
class PostDaoTest {

	@Autowired
	IPostDao postDao;
	
	// test 케이스를 통해서 100개의 게시글을 삽입
	@Test
	void test() {
		// fail("Not yet implemented");
		for(int i=0; i<205; i++) {
			PostDTO post = new PostDTO();
			post.setTitle("Title..." + (1 + i));
			post.setContent("Content..." + (1 + i));
			post.setAuthorId("user1");
			postDao.insert(post);
		}
	}

	
}
