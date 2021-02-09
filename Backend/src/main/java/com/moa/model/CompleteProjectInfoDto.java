package com.moa.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "완료 된 프로젝트 정보", description = "종료된 프로젝트의 정보와 평가가 완료 되었는지에 대한 정보를 담아 전달")
public class CompleteProjectInfoDto {
	@ApiModelProperty(value = "프로젝트 번호")
	private int project_num;
	@ApiModelProperty(value = "프로젝트 명")
	private String project_name;
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
	@ApiModelProperty(value = "펀딩 마감")
	private String deadline;
	@ApiModelProperty(value = "평가 완료 여부")
	private int status;
	
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
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
