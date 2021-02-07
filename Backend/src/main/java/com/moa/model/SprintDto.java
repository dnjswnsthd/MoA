package com.moa.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "스프린트 정보", description = "스프린트의 상세 정보")
public class SprintDto {
	@ApiModelProperty(value = "스프린트 번호")
	int sprint_num;
	@ApiModelProperty(value = "스프린트 명")
	String sprint_name;
	@ApiModelProperty(value = "상태(1,2,3)")
	int sprint_status;
	@ApiModelProperty(value = "스프린트 주제?")
	String sprint_subject;
	@ApiModelProperty(value = "설명")
	String description;
	@ApiModelProperty(value = "스프린트 시작 날짜")
	String sprint_start_date;
	@ApiModelProperty(value = "스프린트 종료 날짜")
	String sprint_end_date;
	@ApiModelProperty(value = "프로젝트 번호")
	int project_num;
	public int getSprint_num() {
		return sprint_num;
	}
	public void setSprint_num(int sprint_num) {
		this.sprint_num = sprint_num;
	}
	public String getSprint_name() {
		return sprint_name;
	}
	public void setSprint_name(String sprint_name) {
		this.sprint_name = sprint_name;
	}
	public int getSprint_status() {
		return sprint_status;
	}
	public void setSprint_status(int sprint_status) {
		this.sprint_status = sprint_status;
	}
	public String getSprint_subject() {
		return sprint_subject;
	}
	public void setSprint_subject(String sprint_subject) {
		this.sprint_subject = sprint_subject;
	}
	public String getDescription() {
		return description;
	}
	public String getSprint_start_date() {
		return sprint_start_date;
	}
	public void setSprint_start_date(String sprint_start_date) {
		this.sprint_start_date = sprint_start_date;
	}
	public String getSprint_end_date() {
		return sprint_end_date;
	}
	public void setSprint_end_date(String sprint_end_date) {
		this.sprint_end_date = sprint_end_date;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getProject_num() {
		return project_num;
	}
	public void setProject_num(int project_num) {
		this.project_num = project_num;
	}
	
}
