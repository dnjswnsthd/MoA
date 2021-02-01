package com.moa.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import com.moa.model.MemberDto;

public interface MemberMapper {
	public MemberDto login(MemberDto memberDto) throws SQLException;

	public int idChk(String id) throws SQLException;

	public void join(Map<String, Object> param) throws SQLException;
	public void joinMentor(Map<String, Object> param) throws SQLException;
	public void joinMentee(Map<String, Object> param) throws SQLException;
	
	public void delete(Map<String, Object> param) throws SQLException;
	
	public void memberUpdate(MemberDto memberDto) throws SQLException;
	public MemberDto memberSearch(MemberDto memberDto) throws SQLException;

	public void updateTempPassword(Map<String, String> map) throws SQLException;
}
