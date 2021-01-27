package com.moa.model.mapper;

import java.sql.SQLException;

import com.moa.model.MemberDto;

/**
 * 2021-01-26
 * 로그인 맵퍼 구현 - member.xml과 연동
 * 
 * @author Team Together
 */
public interface MemberMapper {
	public MemberDto login(MemberDto memberDto) throws SQLException;
	public String checkId(String id);
}
