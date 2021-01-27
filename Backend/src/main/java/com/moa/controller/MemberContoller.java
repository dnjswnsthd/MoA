package com.moa.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@ApiOperation(value = "ID 중복 검사", notes = "입력한 아이디가 존재하는지 검사하여 ", response = Map.class)
	@GetMapping("/join/idchk/{id}")
	public ResponseEntity<Map<String, Object>> idChk(
			@PathVariable("id") @ApiParam(value = "중복 확인할 아이디", required = true) String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		
		try {
			if(memberService.idChk(id)) { // 아이디가 중복 되지 않을 경우
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch(Exception e) {
			logger.error("중복 확인 실패 : {}", e);
			resultMap.put("massage", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "회원 가입(멘토, 멘티)", notes = "멘토의 회원 가입 결과를 반환한다.", response = Map.class)
	@PostMapping("/join")
	public ResponseEntity<Map<String, Object>> join(
			@RequestBody @ApiParam(value = "멘토 회원 가입에 필요한 회원 정보", required = true) Map<String, String> param) {
		System.out.println(param);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		
		try {
			
		} catch(Exception e) {
			
		}
		
		resultMap.put("message", SUCCESS);
		status = HttpStatus.ACCEPTED;
		
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "회원 정보 수정", notes = "회원 정보를 수정하고 성공 여부에 따라 Success Or Fail 문자열을 반환한다.", response = Map.class)
	@GetMapping("/update/")
	public ResponseEntity<Map<String, Object>> memberUpdate(
			@RequestBody @ApiParam(value = "본인 확인용 pw", required = true) MemberDto memberDto){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			//업데이트 데이터 전송시 updateMember 활용.
			MemberDto updateMember = memberService.memberUpdate(memberDto);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		
		} catch(Exception e) {
				logger.error("수정 실패 : {}", e);
				resultMap.put("massage", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}
}
