package com.moa.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "평가 등록을 위한 Dto", description = "멘토에 대한 평가와 멘티들에 대한 평가가 담겨 온다")
public class EvaluateDto {
	@ApiModelProperty(value = "평가를 위한 멘토의 정보")
	private MentorDto mentor;
	@ApiModelProperty(value = "평가를 위한 멘티들의 정보")
	private List<MenteeDto> mentees;
	
	public MentorDto getMentor() {
		return mentor;
	}
	public void setMentor(MentorDto mentor) {
		this.mentor = mentor;
	}
	public List<MenteeDto> getMentees() {
		return mentees;
	}
	public void setMentees(List<MenteeDto> mentees) {
		this.mentees = mentees;
	}
	
	
}
