package com.moa.model.service;

import java.util.Map;

import com.moa.model.MemberDto;

public interface MemberService {
	public MemberDto login(MemberDto memberDto) throws Exception;

	public boolean idChk(String id) throws Exception;

	public void join(Map<String, Object> param) throws Exception;

	public void delete(Map<String, Object> param) throws Exception;

	public MemberDto memberUpdate(MemberDto memberDto) throws Exception;

	public void updateTempPassword(String email) throws Exception;

	public Object memberInfo(String id) throws Exception;

	public boolean pwcheck(String id, String pw);

}
