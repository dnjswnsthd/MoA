package com.moa.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.moa.model.EvaluateDto;
import com.moa.model.MemberDto;
import com.moa.model.MenteeDto;
import com.moa.model.MentorDto;
import com.moa.model.RankDto;
import com.moa.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	JavaMailSender javaMailSender;

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
	 * 회원 가입을 위한 method status를 확인하여 멘토인지 멘티인지 구분하여 회원가입 실행
	 * 
	 * @param param : 회원가입 정보가 포함된 Map 객체
	 */
	@Override
	public void join(Map<String, Object> param) throws Exception {
		sqlSession.getMapper(MemberMapper.class).join(param);
		if ((int) param.get("status") == 1) // 멘토 일 경우
			sqlSession.getMapper(MemberMapper.class).joinMentor(param);
		else if ((int) param.get("status") == 2) // 멘티 일 경우
			sqlSession.getMapper(MemberMapper.class).joinMentee(param);
	}

	public void memberUpdate(MemberDto memberDto) throws SQLException {
		// 회원정보 수정은 이미 로그인이 된 상태로 진행 가능
		sqlSession.getMapper(MemberMapper.class).memberUpdate(memberDto);
	}
	
	/**
	 * 비밀번호 변경을 위한 현재 pw 확인 후 변경.
	 * 
	 * @param memberDto : 비밀번호를 변경하려는 회원의 id와 password가 포함된 객체
	 */
	@Override
	public void changePassword(Map<String, Object> param) throws Exception {
		sqlSession.getMapper(MemberMapper.class).changePassword(param);
	}

	/**
	 * 임시 비밀번호를 생성하여 임시 비밀번호로 바꾸어 준다
	 * 
	 * @param email : 비밀번호 찾을 이메일(ID)
	 */
	@Override
	public void updateTempPassword(String email) throws Exception {
		String tempPassword = getRandomPassword(10); // 임시 비밀번호 생성
		// xml에서 활용하기 위하여 Map 객체에 담아 준다
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", email);
		map.put("pw", tempPassword);
		// 임시 비밀번호로 변경 실시
		sqlSession.getMapper(MemberMapper.class).updateTempPassword(map);
		// 임시 비밀번호를 메일로 발송
		send(email, tempPassword);
	}

	/**
	 * 임시 비밀번호 생성
	 * 
	 * @param len : 임시 비밀번호의 길이
	 * @return 생성한 임시 비밀번호 리턴
	 */
	public String getRandomPassword(int len) {
		char[] charSet = new char[] { // 이중 랜덤으로 10개를 선택하여 임시 비밀번호 생성
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
				'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
				'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

		int idx = 0;
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < len; i++) {
			idx = (int) (charSet.length * Math.random()); // 62 * 생선된 난수를 int로 추출(소숫점 제거)
			sb.append(charSet[idx]);
		}

		return sb.toString();
	}

	/**
	 * 이메일로 임시 비밀번호 발송
	 * 
	 * @param email        비밀번호 발송할 이메일 주소
	 * @param tempPassword 전송할 임시 비밀번호
	 */
	public void send(String email, String tempPassword) {
		sendMail(email, tempPassword);
	}

	/**
	 * 간단한 내용으로 임시 비밀번호 전송
	 * 
	 * @param email        비밀번호를 발송할 이메일 주소
	 * @param tempPassword 전송할 임시 비밀번호
	 */
	@Async
	public void sendMail(String email, String tempPassword) {
		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		simpleMessage.setTo(email);
		simpleMessage.setSubject("MoA 사이트 임시 비밀번호");
		simpleMessage.setText("임시 비밀번호: " + tempPassword);
		javaMailSender.send(simpleMessage);
	}

	/**
	 * status를 먼저 가져와 멘토인지 멘티인지 확인하여 정보를 가져온다
	 * 
	 * @param id 내 정보를 가져올 아이디
	 */
	@Override
	public Object memberInfo(String id) throws Exception {
		int status = sqlSession.getMapper(MemberMapper.class).getStatus(id);
		Object member = null;
		if (status == 1)
			member = sqlSession.getMapper(MemberMapper.class).mentorInfo(id);
		else
			member = sqlSession.getMapper(MemberMapper.class).menteeInfo(id);

		return member;
	}

	/**
	 * ID와 비밀번호가 맞아야만 회원탈퇴 가능
	 * 
	 * @param param 탈퇴활 아이디와 비밀번호를 포함한 정보
	 */
	@Override
	public void delete(String id) throws Exception {
		sqlSession.getMapper(MemberMapper.class).delete(id);
	}

	/**
	 * 입력한 아이디와 비밀번호를 확인하여 맞는지 알려준다
	 * 
	 * @param id, pw 비교할 pw와 정보 변경을 하려는 id
	 */
	@Override
	public boolean pwcheck(String id, String pw) throws Exception {
		if (sqlSession.getMapper(MemberMapper.class).pwcheck(id).equals(pw))
			return true;
		else return false;
	}
		
	@Override
	public void memberUpdatePoint(MemberDto memberDto) throws SQLException {
		// 회원정보 수정은 이미 로그인이 된 상태로 진행 가능
		sqlSession.getMapper(MemberMapper.class).memberUpdatePoint(memberDto);
	}

	/**
	 * 멘토의 각종 능력치에 대한 랭킹 정보 제공
	 * 
	 *  @return	경험치, 도덕성, 적극성, 신뢰성, 전문성, 리더쉽에 대한 랭킹 정보를 담은 Map
	 */
	@Override
	public List<List<RankDto>> getRanking() throws Exception {
		List<List<RankDto>> list = new ArrayList<List<RankDto>>();
		list.add(sqlSession.getMapper(MemberMapper.class).getRankingExp());
		list.add(sqlSession.getMapper(MemberMapper.class).getRankingMorality());
		list.add(sqlSession.getMapper(MemberMapper.class).getRankingPositiveness());
		list.add(sqlSession.getMapper(MemberMapper.class).getRankingReliability());
		list.add(sqlSession.getMapper(MemberMapper.class).getRankingProfessional());
		list.add(sqlSession.getMapper(MemberMapper.class).getRankingLeadership());
		
		return list;
	}

	@Override
	public void updateCompleteStatus(Map<String, Object> map) {
		sqlSession.getMapper(MemberMapper.class).updateCompleteStatus(map);
	}
	
	@Override
	public void updateEvaluate(EvaluateDto evaluateDto) throws Exception {
		MentorDto mentor = evaluateDto.getMentor();
		List<MenteeDto> mentees = evaluateDto.getMentees();
		
		if(mentor.getId() != null)
			sqlSession.getMapper(MemberMapper.class).updateEvaluateMentor(mentor);
		
		for(int i = 0; i < mentees.size(); i++)
			sqlSession.getMapper(MemberMapper.class).updateEvaluateMentee(mentees.get(i));
	}
}
