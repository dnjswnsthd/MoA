package com.moa.model;

public class ProjectDto {
	int project_num;
	String project_name;
	//프로젝트 시작 날짜
	String start_date;
	//프로젝트 종료 날짜
	String end_date;
	String category;
	int Participants;
	int funding_cost;
	String description;
	String mentor_chk;
	//펀딩 마감 날짜
	String deadline;
	
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
