package com.moa.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 멘토 Dto 작성
 * MemberDto 상속 받아 구현
 * 
 * @author 조석준
 * 작성일 : 2021-01-26
 */
@ApiModel(value = "멘토 정보", description = "멘토의 경력, 경험치, 능력치 정보")
public class MentorDto extends MemberDto {
	@ApiModelProperty(value = "경력")
	private String career;
	@ApiModelProperty(value = "경험치")
	private int exp;
	@ApiModelProperty(value = "평가 받은 횟수")
	private int number_of_evaluation;
	// 능력치
	@ApiModelProperty(value = "리더십")
	private int leadership;		// 리더쉽
	@ApiModelProperty(value = "도덕성")
	private int morality;		// 도덕성
	@ApiModelProperty(value = "적극성")
	private int positiveness;	// 적극성
	@ApiModelProperty(value = "신뢰성")
	private int reliability;	// 신뢰성
	@ApiModelProperty(value = "전문성")
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
	public int getNumber_of_evaluation() {
		return number_of_evaluation;
	}
	public void setNumber_of_evaluation(int number_of_evaluation) {
		this.number_of_evaluation = number_of_evaluation;
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
	public int getPositiveness() {
		return positiveness;
	}
	public void setPositiveness(int positiveness) {
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
