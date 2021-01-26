package com.moa.model.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moa.model.MemberDto;
import com.moa.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		// 아이디와 비밀번호 둘 중 하나라도 입력으로 주어지지 않았다면 null 반환
		if(memberDto.getId() == null || memberDto.getPw() == null)
			return null;
		return sqlSession.getMapper(MemberMapper.class).login(memberDto);
	}

}
