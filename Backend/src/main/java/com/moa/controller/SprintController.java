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

import com.moa.model.SprintDto;
import com.moa.model.service.SprintService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@Api("SprintController v0.1")
@RestController
@RequestMapping("/sprint")
public class SprintController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private SprintService sprintService;
	/**
	 * 스프린트 추가 
	 * 
	 * @param param 스프린트 추가에 필요한 데이터
	 * @return	추가 '성공' or '실패' 메시지
	 */
	@ApiOperation(value = "스프린트 추가", notes = "스프린트 추가 결과 메시지를 반환한다.", response = Map.class)
	@PostMapping("/add")
	public ResponseEntity<Map<String, Object>> add(
			@RequestBody @ApiParam(value = "스프린트 추가에 필요한 정보", required = true) Map<String, Object> param) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			sprintService.add(param);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	/**
	 * 스프린트 상태 변경
	 * 
	 * @param sprintDto 변경할 sprint의 num과 status
	 * @return 변경 '성공' or '실패' 메시지
	 */
	@ApiOperation(value = "스프린트 상태 변경", notes = "스프린트 상태 변경 결과 메시지를 반환한다.", response = Map.class)
	@PutMapping("/modify")
	public ResponseEntity<Map<String, Object>> modify(
			@RequestBody @ApiParam(value = "상태를 변경하고 싶은  스프린트의 기본키", required = true)SprintDto sprintDto){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			sprintService.modify(sprintDto);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	/**
	 * 
	 * 스프린트 삭제
	 * 
	 * @param sprint_num
	 * @return 삭제 '성공' or '실패' 메시지
	 */
	@ApiOperation(value = "스프린트 삭제", notes = "스프린트  삭제 변경 결과 메시지를 반환한다.", response = Map.class)
	@DeleteMapping("/delete/{sprint_num}")
	public ResponseEntity<Map<String, Object>> delete(
			@PathVariable("sprint_num") @ApiParam(value = "스프린트 삭제에 필요한 기본키", required = true)int sprint_num){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			sprintService.delete(sprint_num);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		}catch(Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	/**
	 * 
	 * 스프린트 조회
	 * @param project_num
	 * @return 프로젝트의 스프린트 정보와 '성공' 메시지 
	 */
	@ApiOperation(value = "프로젝트의 스프린트 정보 보기", notes= "project에 포함된 스프린트 정보 보기", response = Map.class)
	@GetMapping("/search/{project_num}")
	public ResponseEntity<Map<String, Object>> search(
			@PathVariable("project_num") @ApiParam(value = "스프린트 조회를 위한 프로젝트 기본키", required = true)int project_num){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			SprintDto [] sprintList = sprintService.search(project_num);
			resultMap.put("message", SUCCESS);
			resultMap.put("sprintList", sprintList);
			status = HttpStatus.ACCEPTED;
		}catch (Exception e) {
			resultMap.put("messgae", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	/**
	 * 스프린트 내용 변경
	 * 
	 * @param sprintDto 변경할 sprint의 내용
	 * @return 변경 '성공' or '실패' 메시지
	 */
	@ApiOperation(value = "스프린트 상태 변경", notes = "스프린트 상태 변경 결과 메시지를 반환한다.", response = Map.class)
	@PutMapping("/modifycontent")
	public ResponseEntity<Map<String, Object>> modifycontent(
			@RequestBody @ApiParam(value = "상태를 변경하고 싶은  스프린트의 기본키", required = true)SprintDto sprintDto){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			sprintService.modifycontent(sprintDto);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
}
