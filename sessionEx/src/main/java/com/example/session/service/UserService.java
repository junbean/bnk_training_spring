package com.example.session.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.session.dao.IUserDao;
import com.example.session.dto.UserDto;

@Service
public class UserService {
	
	@Autowired
	private IUserDao userDao;
	
	public UserDto loginCheck(String id, String pw) {
		return userDao.login(id, pw);
	}
}
