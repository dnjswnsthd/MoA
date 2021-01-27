package com.moa.model.service;

import java.util.Map;

import com.moa.model.MemberDto;

public interface MemberService {
	public MemberDto login(MemberDto memberDto) throws Exception;
	public boolean checkId(String id);
}
