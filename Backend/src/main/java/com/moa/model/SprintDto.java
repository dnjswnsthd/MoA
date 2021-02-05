package com.moa.model;

public class SprintDto {
	int sprint_num;
	String sprint_name;
	int sprint_status;
	String sprint_subject;
	String description;
	String start_date;
	String end_date;
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
	public void setDescription(String description) {
		this.description = description;
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
	public int getProject_num() {
		return project_num;
	}
	public void setProject_num(int project_num) {
		this.project_num = project_num;
	}
	
}
