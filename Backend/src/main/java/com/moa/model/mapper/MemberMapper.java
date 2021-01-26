package com.moa.model.mapper;

import java.sql.SQLException;

import com.moa.model.MemberDto;

public interface MemberMapper {
	public MemberDto login(MemberDto memberDto) throws SQLException;
}
