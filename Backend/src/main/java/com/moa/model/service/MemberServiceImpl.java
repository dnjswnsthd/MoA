package com.moa.model.service;

import java.util.Map;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moa.model.MemberDto;
import com.moa.model.mapper.MemberMapper;

/**
 * 2021-01-26 로그인 서비스 구현 - id, pw 필수 검사
 * 2021-01-27 멘토와 멘티 구분하여 회원가입 및 아이디 중복 검사 구현
 * 
 * @author Team Together
 */

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSession sqlSession;

	/**
	 * 로그인 method
	 * 
	 * @param memberDto : 로그인하려는 회원의 id와 password가 포함된 객체
	 * @return 사용자의 id, name이 포함된 memberDto 객체
	 */
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		// 아이디와 비밀번호 둘 중 하나라도 입력으로 주어지지 않았다면 null 반환
		if (memberDto.getId() == null || memberDto.getPw() == null)
			return null;
		return sqlSession.getMapper(MemberMapper.class).login(memberDto);
	}
	/**
	 * 아이디 중복 확인을 위한 method
	 * 
	 * @param id : 중복 확일할 아이디
	 * @return boolean 값으로 중복될 경우 false
	 */
	@Override
	public boolean idChk(String id) throws Exception {
		// 중복된 아이디가 존재할 경우
		if (sqlSession.getMapper(MemberMapper.class).idChk(id) > 0)
			return false;
		return true;
	}
	/**
	 * 회원 가입을 위한 method
	 * status를 확인하여 멘토인지 멘티인지 구분하여 회원가입 실행
	 * 
	 * @param param : 회원가입 정보가 포함된 Map 객체
	 */
	@Override
	public void join(Map<String, Object> param) throws Exception {
		sqlSession.getMapper(MemberMapper.class).join(param);
		if ((int) param.get("status") == 1)			// 멘토 일 경우
			sqlSession.getMapper(MemberMapper.class).joinMentor(param);
		else if ((int) param.get("status") == 2)	// 멘티 일 경우
			sqlSession.getMapper(MemberMapper.class).joinMentee(param);
	}
	
	public MemberDto memberUpdate(MemberDto memberDto) throws SQLException {
		//회원정보 수정은 이미 로그인이 된 상태로 진행 가능
		sqlSession.getMapper(MemberMapper.class).memberUpdate(memberDto);
		return sqlSession.getMapper(MemberMapper.class).memberSearch(memberDto);
	}
}
