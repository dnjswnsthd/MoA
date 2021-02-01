package com.moa.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moa.model.ProjectDto;
import com.moa.model.service.ProjectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("ProjectController v0.1")
@RestController
@RequestMapping("/project")
public class ProjectController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private ProjectService projectService;
	
	@ApiOperation(value = "펀딩 생성", notes = "펀딩 생성 결과 메시지를 반환한다.", response = Map.class)
	@PostMapping("/create")
	//펀딩 오픈 컨트롤러
	public ResponseEntity<Map<String, Object>> create(
			@RequestBody @ApiParam(value = "펀딩 생성에 필요한 정보", required = true) ProjectDto project){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		
		try {
			projectService.create(project);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		}catch(Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	//펀딩 신청 컨트롤러
	@ApiOperation(value = "펀딩 신청 대기", notes = "펀딩 신청 후 수락까지 대기 중 DB에 상태 저장을 위함.", response = Map.class)
	@PostMapping("/waiting")
	public ResponseEntity<Map<String, Object>> waiting(
			@RequestBody @ApiParam(value = "펀딩 신청  후 대기중인 내역 DB에 추가 ") Map<String, Object> param){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			projectService.waiting(param);
			resultMap.put("messagae", SUCCESS);
			status = HttpStatus.ACCEPTED;
		}catch(Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity <Map<String,Object>>(resultMap, status);
	}
	
	//신청 허가 컨트롤러(허가 하면 각각 대기에선 삭제 프로젝트 목록에서는 추가 됩니다.)
	@ApiOperation(value = "신청 허가", notes = "신청 허가 후 waiting_project에선 삭제하고  project_member 테이블에 추가", response = Map.class)
	@PutMapping("/permission")
	public ResponseEntity<Map<String, Object>> permissiont(
			@RequestBody @ApiParam(value = "신청 허가 됬을 경우 DB 상태 처리") Map<String, Object> param){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			projectService.permission(param);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		}catch(Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity <Map<String,Object>>(resultMap, status);
	}
	
	
	
}
