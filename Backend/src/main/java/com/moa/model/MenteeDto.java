package com.moa.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 멘티 Dto 구현
 * MemberDto 상속 받아 구현
 * 
 * @author 조석준
 * 작성일 : 2021-01-26
 */
@ApiModel(value = "멘티 정보", description = "멘티의 보유 기술, 능력치 정보")
public class MenteeDto extends MemberDto {
	// 보유 기술
	@ApiModelProperty(value = "보유 기술")
	private String skill;
	// 능력치
	@ApiModelProperty(value = "소통력")
	private int communication;	// 소통력
	@ApiModelProperty(value = "책임감")
	private int responsibility;	// 책임감
	@ApiModelProperty(value = "구현 능력")
	private int performance;	// 구현 능력
	@ApiModelProperty(value = "적극성")
	private int positive;		//적극성
	@ApiModelProperty(value = "리더십")
	private int leadership;		// 리더쉽
	// getter, setter
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public int getCommunication() {
		return communication;
	}
	public void setCommunication(int communication) {
		this.communication = communication;
	}
	public int getResponsibility() {
		return responsibility;
	}
	public void setResponsibility(int responsibility) {
		this.responsibility = responsibility;
	}
	public int getPerformance() {
		return performance;
	}
	public void setPerformance(int performance) {
		this.performance = performance;
	}
	public int getPositive() {
		return positive;
	}
	public void setPositive(int positive) {
		this.positive = positive;
	}
	public int getLeadership() {
		return leadership;
	}
	public void setLeadership(int leadership) {
		this.leadership = leadership;
	}
}
