package com.moa.model;

/**
 * 멘토 Dto 작성
 * MemberDto 상속 받아 구현
 * 
 * @author 조석준
 * 작성일 : 2021-01-26
 */
public class MentorDto extends MemberDto {
	private String career;
	private int exp;
	// 능력치
	private int leadership;		// 리더쉽
	private int morality;		// 도덕성
	private int positiveness;	// 적극성
	private int reliability;	// 신뢰성
	private int professional;	// 전문성
	// getter, setter
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getLeadership() {
		return leadership;
	}
	public void setLeadership(int leadership) {
		this.leadership = leadership;
	}
	public int getMorality() {
		return morality;
	}
	public void setMorality(int morality) {
		this.morality = morality;
	}
	public int getPositive() {
		return positiveness;
	}
	public void setPositive(int positiveness) {
		this.positiveness = positiveness;
	}
	public int getReliability() {
		return reliability;
	}
	public void setReliability(int reliability) {
		this.reliability = reliability;
	}
	public int getProfessional() {
		return professional;
	}
	public void setProfessional(int professional) {
		this.professional = professional;
	}
	
	
}
