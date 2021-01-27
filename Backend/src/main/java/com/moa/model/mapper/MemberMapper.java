package com.moa.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import com.moa.model.MemberDto;

/**
 * 2021-01-26 로그인 맵퍼 구현 - member.xml과 연동
 * 2021-01-27 회원가입, 아이디 중복 체크 구현
 * 
 * @author Team Together
 */
public interface MemberMapper {
	public MemberDto login(MemberDto memberDto) throws SQLException;

	public int idChk(String id) throws SQLException;

	public void join(Map<String, Object> param) throws SQLException;

	public void joinMentor(Map<String, Object> param) throws SQLException;
	public void joinMentee(Map<String, Object> param) throws SQLException;
}
