package com.moa.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "랭킹 정보를 담을 Dto", description = "랭킹 정보에 표시한 id와 각 랭킹에 해당하는 점수를 담아서 전송")
public class RankDto {
	@ApiModelProperty(value = "아이디(이메일 형식)")
	private String id;
	@ApiModelProperty(value = "랭킹에 해당하는 점수")
	private int score;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
