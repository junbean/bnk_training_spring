package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.MemberDTO;

@Repository
public class MemberDAO {
	
	@Autowired
	private JdbcTemplate jt;
	
	public MemberDTO viewMember(String id) {
		String query = "SELECT * FROM tbl_member WHERE id = ?";
		MemberDTO member = jt.queryForObject(
				query, 
				new BeanPropertyRowMapper<MemberDTO>(MemberDTO.class),
				id
				);
		return member;
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
	
	
	
	
	
	
	
	
	//=========================================================================
	//=========================================================================
	//=========================================================================
	//=========================================================================
	//=========================================================================
	//=========================================================================
	//=========================================================================
	
	
	
	
	
	
	
	
	
	
	
	
	// 기존 DAO 방식
	public List<MemberDTO> getList() {
		String query = "SELECT * FROM tbl_member";
		List<MemberDTO> list = new ArrayList<>();
		
		try(
			Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery()
		) {
			while(rs.next()) {
				MemberDTO member = new MemberDTO();
				member.setId(rs.getString("id"));
				member.setPw(rs.getString("pw"));
				member.setName(rs.getString("name"));
				member.setPhone(rs.getString("phone"));
				member.setGrade(rs.getString("grade"));
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 기존 DAO 방식 - insert
	public int insert(MemberDTO member) {
		String query = "INSERT INTO tbl_member VALUES(?, ?, ?, ?, ?)";
		int result = 0;
		
		try(
			Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
		) {
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPw());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getGrade());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return result;
	}
	
	
}
