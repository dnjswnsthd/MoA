package com.moa.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Member Dto 구현 멘토, 멘티가 가질 수 있는 공통 속성 정의
 * 
 * @author 조석준 작성일 : 2021-01-26
 */
@ApiModel(value = "회원정보", description = "관리자, 멘토, 멘티의 상세 정보")
public class MemberDto {
	@ApiModelProperty(value = "아이디(이메일 형식)")
	private String id;
	@ApiModelProperty(value = "비밀번호")
	private String pw;
	@ApiModelProperty(value = "이름")
	private String name;
	@ApiModelProperty(value = "나이")
	private int age;
	@ApiModelProperty(value = "전공분야")
	private String major;
	@ApiModelProperty(value = "상태(0: 관리자, 1: 멘토, 2:멘티")
	private int status;
	@ApiModelProperty(value = "폰 번호")
	private String phone;
	@ApiModelProperty(value = "관심분야 1")
	private String favorite_1;
	@ApiModelProperty(value = "관심분야 2")
	private String favorite_2;
	@ApiModelProperty(value = "관심분야 3")
	private String favorite_3;
	@ApiModelProperty(value = "가지고 있는 포인트(펀딩에 사용)")
	private int point;
	@ApiModelProperty(value = "자기소개")
	private String introduce;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFavorite_1() {
		return favorite_1;
	}

	public void setFavorite_1(String favorite_1) {
		this.favorite_1 = favorite_1;
	}

	public String getFavorite_2() {
		return favorite_2;
	}

	public void setFavorite_2(String favorite_2) {
		this.favorite_2 = favorite_2;
	}

	public String getFavorite_3() {
		return favorite_3;
	}

	public void setFavorite_3(String favorite_3) {
		this.favorite_3 = favorite_3;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
}
