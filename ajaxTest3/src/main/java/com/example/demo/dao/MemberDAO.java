package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.MemberDTO;

@Repository
public class MemberDAO {

	@Autowired
	private JdbcTemplate jt;
	
	public MemberDTO viewMember(String id) {
		try {
	        String query = "SELECT * FROM tbl_member WHERE id = ?";
	        MemberDTO member = jt.queryForObject(
	                query, 
	                new BeanPropertyRowMapper<MemberDTO>(MemberDTO.class),
	                id
	                );
	        return member;
	    } catch (EmptyResultDataAccessException e) {
	        return null;
	    }
	}
	
	public MemberDTO loginMember(String id, String pw) {
		try {
			String query = "SELECT * FROM tbl_member WHERE id=? AND pw=?";
			MemberDTO member = jt.queryForObject(
					query, 
					new BeanPropertyRowMapper<MemberDTO>(MemberDTO.class),
					id,
					pw
					);
			return member;
		} catch (EmptyResultDataAccessException e) {
	        return null;
	    }
	} 
	
	// JDBC API를 활용한 SELECT 쿼리 실행
	public List<MemberDTO> list() {
		String query = "SELECT * FROM tbl_member";
		List<MemberDTO> list = jt.query(
				query, 
				new BeanPropertyRowMapper<MemberDTO>(MemberDTO.class)
				);
		return list;
	}
	
	public int writeMember(MemberDTO memberDTO) {
		String query = "INSERT INTO tbl_member VALUES(?,?,?,?,?)";
		int result = jt.update(
				query, 
				memberDTO.getId(), 
				memberDTO.getPw(), 
				memberDTO.getName(), 
				memberDTO.getPhone(), 
				memberDTO.getGrade()
				);
		return result;
	}
	
	public int deleteMember(String id) {
		String query = "DELETE tbl_member WHERE id=?";
		int result = jt.update(query, id);
		return result;
	}
	
	public int updateMember(MemberDTO memberDTO) {
		String query = "UPDATE tbl_member SET pw=?, name=?, phone=?, grade=? WHERE id=?";
		int result = jt.update(
				query, 
				memberDTO.getPw(), 
				memberDTO.getName(), 
				memberDTO.getPhone(), 
				memberDTO.getGrade(),
				memberDTO.getId()
				);
		return result;
	}
}
