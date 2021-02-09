package com.moa.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "프로젝트 정보", description = "프로젝트의 상세 정보")
public class ProjectDto {
	@ApiModelProperty(value = "프로젝트 번호")
	private int project_num;
	@ApiModelProperty(value = "프로젝트 명")
	private String project_name;
	@ApiModelProperty(value = "프로젝트 시작 날짜")
	private String start_date;
	@ApiModelProperty(value = "프로젝트 마감 날짜")
	private String end_date;
	@ApiModelProperty(value = "프로젝트 분류")
	private String category;
	@ApiModelProperty(value = "참가자 수")
	private int Participants;
	@ApiModelProperty(value = "펀딩 금액")
	private int funding_cost;
	@ApiModelProperty(value = "설명")
	private String description;
	@ApiModelProperty(value = "멘토 여부")
	private String mentor_chk;
	@ApiModelProperty(value = "펀딩 마감")
	private String deadline;
	@ApiModelProperty(value = "팀장")
	private String leader;
	
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public int getProject_num() {
		return project_num;
	}
	public void setProject_num(int project_num) {
		this.project_num = project_num;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getParticipants() {
		return Participants;
	}
	public void setParticipants(int participants) {
		Participants = participants;
	}
	public int getFunding_cost() {
		return funding_cost;
	}
	public void setFunding_cost(int funding_cost) {
		this.funding_cost = funding_cost;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMentor_chk() {
		return mentor_chk;
	}
	public void setMentor_chk(String mentor_chk) {
		this.mentor_chk = mentor_chk;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
}
