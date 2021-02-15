package com.moa.model.service;

import java.util.List;
import java.util.Map;

import com.moa.model.EvaluateDto;
import com.moa.model.MemberDto;
import com.moa.model.RankDto;

public interface MemberService {
	public MemberDto login(MemberDto memberDto) throws Exception;

	public boolean idChk(String id) throws Exception;

	public void join(Map<String, Object> param) throws Exception;

	public void delete(String id) throws Exception;

	public void memberUpdate(MemberDto memberDto) throws Exception;

	public void memberUpdatePoint(MemberDto memberDto) throws Exception;
	
	public void changePassword(Map<String, Object> param) throws Exception;

	public void updateTempPassword(String email) throws Exception;

	public Object memberInfo(String id) throws Exception;

	public boolean pwcheck(String id, String pw) throws Exception;

	public List<List<RankDto>> getRanking() throws Exception;

	public void updateCompleteStatus(Map<String, Object> map);
	public void updateEvaluate(EvaluateDto evaluateDto) throws Exception;

}
