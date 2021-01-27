package com.moa.model.service;

import com.moa.model.MemberDto;

public interface MemberService {
	public MemberDto login(MemberDto memberDto) throws Exception;
	public boolean idChk(String id);
	public MemberDto memberUpdate(MemberDto memberDto) throws Exception;
}
