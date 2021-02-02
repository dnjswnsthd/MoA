package com.moa.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	/**
	 * 펀딩 오픈 컨트롤러
	 * 
	 * @param project	오픈할 프로젝트의 정보가 담긴 Dto
	 * @return	오픈 '성공' or '실패' 메시지
	 */
	@ApiOperation(value = "펀딩 생성", notes = "펀딩 생성 결과 메시지를 반환한다.", response = Map.class)
	@PostMapping("/create")
	public ResponseEntity<Map<String, Object>> create(
			@RequestBody @ApiParam(value = "펀딩 생성에 필요한 정보", required = true) ProjectDto project) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			projectService.create(project);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	/**
	 * 펀딩 신청 컨트롤러
	 * 
	 * @param param	신청한 펀딩의 프로젝트 번호와 신청자의 아이디 정보
	 * @return	신청 '성공' or '실패' 메시지 전달
	 */
	@ApiOperation(value = "펀딩 신청 대기", notes = "펀딩 신청 후 수락까지 대기 중 DB에 상태 저장을 위함.", response = Map.class)
	@PostMapping("/waiting")
	public ResponseEntity<Map<String, Object>> waiting(
			@RequestBody @ApiParam(value = "펀딩 신청  후 대기중인 내역 DB에 추가 ") Map<String, Object> param) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			projectService.waiting(param);
			resultMap.put("messagae", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	/**
	 * 신청 허가 컨트롤러(허가 하면 각각 대기에선 삭제 프로젝트 목록에서는 추가 됩니다.)
	 * 
	 * @param param	 허가할 프로젝트 번호와 신청자의 아이디 정보
	 * @return	성공 실패 메세지 전달
	 */
	@ApiOperation(value = "신청 허가", notes = "신청 허가 후 waiting_project에선 삭제하고  project_member 테이블에 추가", response = Map.class)
	@PutMapping("/permission")
	public ResponseEntity<Map<String, Object>> permission(
			@RequestBody @ApiParam(value = "신청 허가 됬을 경우 DB 상태 처리") Map<String, Object> param) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			projectService.permission(param);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	/**
	 * 자신이 참여하고 있는 펀딩의 정보를 모두 가지고 온다
	 * 
	 * @param id	자신의 아이디 정보
	 * @return	자신이 참여하고 있는 펀딩의 정보와 '성공' 메세지
	 */
	@ApiOperation(value = "진행 중인 펀딩 정보 보기", notes = "마이 페이지에서 유저의 펀딩 정보 보기", response = Map.class)
	@GetMapping("/proceeding/{id}")
	public ResponseEntity<Map<String, Object>> projectInfo(
			@PathVariable("id") @ApiParam(value = "펀딩 정보를 불러올 아이디", required = true) String id) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			// DB 에서 ID를 통해 자신이 참여하고 있는 프로젝트의 정보를 가지고 온다
			ProjectDto projectInfo = projectService.projectInfo(id);
			resultMap.put("message", SUCCESS);
			resultMap.put("projectInfo", projectInfo);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	/**
	 * 신청 거부 컨트롤러(거부 하면  대기 Table에서 삭제 합니다.)
	 * 
	 * @param param 거부할 프로젝트 번호와 신청자의 아이디 정보
	 * @return 성공 실패 메시지 전달
	 */
	@ApiOperation(value = "신청 거절", notes = "신청 거절 됬을 경우 waiting_project db에서 삭제함.", response = Map.class)
	@DeleteMapping("/denial")
	public ResponseEntity<Map<String, Object>> denial(
			@RequestBody @ApiParam(value = "신청 거절 됬을 경우 DB 상태 처리") Map<String, Object> param) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			projectService.denial(param);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
