package com.moa.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	
	@ApiOperation(value = "진행 중인 펀딩 정보 보기", notes ="마이 페이지에서 유저의 펀딩 정보 보기", response = Map.class)
	@GetMapping("/proceeding/{id}")
	public ResponseEntity<Map<String, Object>> projectInfo(@PathVariable("id") @ApiParam(value = "펀딩 정보를 불러올 아이디", required = true) String id){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			ProjectDto projectInfo = projectService.projectInfo(id);
			resultMap.put("message", SUCCESS);
			resultMap.put("projectInfo", projectInfo);
			status = HttpStatus.ACCEPTED;
		} catch(Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}
	
}
