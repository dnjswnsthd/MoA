package com.moa.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.moa.model.MemberDto;
import com.moa.model.MenteeDto;
import com.moa.model.MentorDto;
import com.moa.model.RankDto;

public interface MemberMapper {
	public MemberDto login(MemberDto memberDto) throws SQLException;

	public int idChk(String id) throws SQLException;

	public void join(Map<String, Object> param) throws SQLException;

	public void joinMentor(Map<String, Object> param) throws SQLException;

	public void joinMentee(Map<String, Object> param) throws SQLException;

	public void delete(String id) throws SQLException;

	public void memberUpdate(MemberDto memberDto) throws SQLException;
	
	public void changePassword(Map<String, Object> param) throws SQLException;

	public void memberUpdatePoint(MemberDto memberDto) throws SQLException;
	
	public MemberDto memberSearch(MemberDto memberDto) throws SQLException;

	public void updateTempPassword(Map<String, String> map) throws SQLException;

	public MemberDto memberInfo(String id) throws SQLException;

	public MentorDto mentorInfo(String id) throws SQLException;

	public MenteeDto menteeInfo(String id) throws SQLException;

	public int getStatus(String id) throws SQLException;

	public String pwcheck(String id) throws SQLException;
	// 랭킹 관련 Mapper
	public List<RankDto> getRankingExp() throws SQLException;
	public List<RankDto> getRankingMorality() throws SQLException;
	public List<RankDto> getRankingPositiveness() throws SQLException;
	public List<RankDto> getRankingReliability() throws SQLException;
	public List<RankDto> getRankingProfessional() throws SQLException;
	public List<RankDto> getRankingLeadership() throws SQLException;
	// 멘토 멘티 평가 업데이트 Mapper
	public void updateEvaluateMentor(MentorDto mentor) throws SQLException;
	public void updateEvaluateMentee(MenteeDto menteeDto) throws SQLException;

	public void updateCompleteStatus(Map<String, Object> map);

}
