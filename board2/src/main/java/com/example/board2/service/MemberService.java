package com.example.board2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.board2.dao.IMemberDao;
import com.example.board2.dto.MemberDTO;

@Service
public class MemberService {
	
	@Autowired
	private IMemberDao memberDao;
	
	public List<MemberDTO> getMemberList() {
		return memberDao.getMemberList();
	}
	
	public MemberDTO checkMember() {
		return memberDao.checkMember();
	}
	
	public int insertMember() {
		return memberDao.insertMember();
	}
	
}
