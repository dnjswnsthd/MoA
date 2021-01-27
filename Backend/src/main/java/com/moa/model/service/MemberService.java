package com.moa.model.service;

import java.util.Map;

import com.moa.model.MemberDto;

public interface MemberService {
	public MemberDto login(MemberDto memberDto) throws Exception;
	public boolean idChk(String id) throws Exception;
	public void join(Map<String, Object> param) throws Exception;
}
