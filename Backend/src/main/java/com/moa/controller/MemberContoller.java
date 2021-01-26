package com.moa.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moa.model.MemberDto;
import com.moa.model.service.JwtServiceImpl;
import com.moa.model.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 2021-01-26
 * 로그인 메소드 구현
 *  - jwt를 통해 세션 유지
 *  - 성공, 실패 여부를 message를 통해 front단에 전달
 * 
 * @author Team Together
 */

@Api("MemberController v0.1")
@RestController
@RequestMapping("/member")
public class MemberContoller {
	// Server side log를 위한 객체 생성
	public static final Logger logger = LoggerFactory.getLogger(MemberContoller.class);

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtServiceImpl jwtService;	// 로그인 토큰 생성을 위한 service
	
	@Autowired
	private MemberService memberService;	// 데이터베이스에 로그인 시도를 위한 service

	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(이메일, 비밀번호)", required = true) MemberDto memberDto) {
		// 결과 저장할 Map 객체
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		
		try {
			// Database에서 아이디와 비밀번호를 통해 로그인 정보 찾기
			MemberDto loginMember = memberService.login(memberDto);
			
			if(loginMember != null) { // 로그인 정보가 존재하는 경우
				// (key, data, subject)
				String token = jwtService.create("id", loginMember.getId(), "access-token");
				logger.debug("로그인 토큰 정보 : {}", token);	// server side log
				resultMap.put("access-token", token);	// access-token 전달
				resultMap.put("message", SUCCESS);		// "성공" 메세지 전달
				status = HttpStatus.ACCEPTED;
			} else {	// 로그인 정보가 존재하지 않는 경우
				resultMap.put("massage", FAIL);		// "실패" 메세지 전달
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("massage", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	
	
}
