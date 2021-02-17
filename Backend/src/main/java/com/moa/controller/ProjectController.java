package com.moa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moa.model.CompleteProjectInfoDto;
import com.moa.model.MemberDto;
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
	 * @param param 프로젝트 정보와 member id
	 * @return	오픈 '성공' or '실패' 메시지
	 */
	@ApiOperation(value = "펀딩 생성", notes = "펀딩 생성 결과 메시지를 반환한다.", response = Map.class)
	@PostMapping("/create")
	public ResponseEntity<Map<String, Object>> create(
			@RequestBody @ApiParam(value = "펀딩 생성에 필요한 정보", required = true) ProjectDto param) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		
		System.out.println(param);
		
		try {
			projectService.create(param);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
	@ApiOperation(value = "펀딩 신청 대기", notes = "펀딩 신청 후 수락까지 대기 중 DB에 상태	 저장을 위함.", response = Map.class)
	@PostMapping("/waiting")
	public ResponseEntity<Map<String, Object>> waiting(
			@RequestBody @ApiParam(value = "펀딩 신청  후 대기중인 내역 DB에 추가 ") Map<String, Object> param) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			int cnt = projectService.waiting(param);
			if(cnt > 0) {
				resultMap.put("message", SUCCESS);
			}
			else{
				resultMap.put("message", FAIL);
			}
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
	 * 이 프로젝트에 포함된 팀원을 확인합니다.
	 * 
	 * @parma project_num 프로젝트에 포함된 인원을 보기 위해
	 * @return 포함되는 팀원들
	 */
	@ApiOperation(value = "프로젝트에 포함된 인원 확인", notes = "프로젝트 세부 페이지에서 팀원 확인하기", response = Map.class)
	@GetMapping("/memberchk/{project_num}")
	public ResponseEntity<Map<String, Object>> memberchk(
			@PathVariable("project_num") @ApiParam(value = "조회를 위한 프로젝트 넘버") int project_num){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			MemberDto [] member = projectService.memberchk(project_num);
			resultMap.put("member", member);
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
			ProjectDto[] projectInfo = projectService.projectInfo(id);
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
	 * 자신이 대기중인 펀딩의 정보를 모두 가지고 온다
	 * 
	 * @param id	자신의 아이디 정보
	 * @return	자신이 대기하고 있는 펀딩의 정보와 '성공' 메세지
	 */
	@ApiOperation(value = "대기 중인 펀딩 정보 보기", notes = "마이 페이지에서 유저의 대기중인 펀딩 정보 보기", response = Map.class)
	@GetMapping("/waiting/{id}")
	public ResponseEntity<Map<String, Object>> waitingProjectInfo(
			@PathVariable("id") @ApiParam(value = "대기 중인 펀딩 정보를 불러올 아이디", required = true) String id) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			// DB 에서 ID를 통해 자신이 참여 대기하고 있는 프로젝트의 정보를 가지고 온다
			ProjectDto[] waitingProjectInfo = projectService.waitingProjectInfo(id);
			resultMap.put("message", SUCCESS);
			resultMap.put("waitingProjectInfo", waitingProjectInfo);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	/**
	 * 자신의 종료된 펀딩의 정보를 모두 가지고 온다
	 * 
	 * @param id	자신의 아이디 정보
	 * @return	자신의 종료 된  펀딩의 정보와 '성공' 메세지
	 */
	@ApiOperation(value = "종료 된 펀딩 정보 보기", notes = "마이 페이지에서 유저의 종료 된 펀딩 정보 보기", response = Map.class)
	@GetMapping("/complete/{id}")
	@Transactional
	public ResponseEntity<Map<String, Object>> completeProjectInfo(
			@PathVariable("id") @ApiParam(value = "종료 된 펀딩 정보를 불러올 아이디", required = true) String id) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			// DB 에서 ID를 통해 자신의 종료 된 있는 프로젝트의 정보를 가지고 온다
			List<CompleteProjectInfoDto> list = projectService.completeProjectInfo(id);
			resultMap.put("message", SUCCESS);
			resultMap.put("list", list);
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
	@PutMapping("/denial")
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
	/**
	 * 프로젝트 종료 컨트롤러
	 * 
	 * @param project	종료 할 프로젝트의 정보가 담긴 Dto
	 * @return	성공 실패 메시지 전달
	 */
	@ApiOperation(value = "펀딩 종료", notes = "펀딩 종료 결과 메시지를 반환한다.", response = Map.class)
	@PostMapping("/end/{project_num}")
	public ResponseEntity<Map<String, Object>> end(
			@PathVariable("project_num") @ApiParam(value = "종료 된 펀딩 정보를 불러올 프로젝트 번호", required = true) int project_num) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			projectService.end(project_num);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	/**
	 * 관심 펀딩 등록 컨트롤러
	 * 
	 * @param param	등록한 펀딩의 프로젝트 번호와 사용자의 아이디 정보
	 * @return	등록 '성공' or '실패' 메시지 전달
	 */
	@ApiOperation(value = "관심 펀딩 등록", notes = "등록 후  DB에 상태 저장.", response = Map.class)
	@PostMapping("/interesting")
	public ResponseEntity<Map<String, Object>> interesting(
			@RequestBody @ApiParam(value = "등록  후  DB에 추가 ") Map<String, Object> param) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			projectService.interesting(param);
			resultMap.put("messagae", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	/**
	 * 자신의 관심 펀딩의 정보를 모두 가지고 온다
	 * 
	 * @param id	자신의 아이디 정보
	 * @return	자신의 종료 된  펀딩의 정보와 '성공' 메세지
	 */
	@ApiOperation(value = "관심 펀딩 정보 보기", notes = "마이 페이지에서 유저의 관심 펀딩 정보 보기", response = Map.class)
	@GetMapping("/interesting/{id}")
	public ResponseEntity<Map<String, Object>> interestingProjectInfo(
			@PathVariable("id") @ApiParam(value = "관심 펀딩 정보를 불러올 아이디", required = true) String id) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			// DB 에서 ID를 통해 자신의 관심 등록 된 프로젝트의 정보를 가지고 온다
			ProjectDto[] interestingProjectInfo = projectService.interestingProjectInfo(id);
			resultMap.put("message", SUCCESS);
			resultMap.put("interestingProjectInfo", interestingProjectInfo);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	/**
	 * 관심 펀딩 삭제 컨트롤러
	 * 
	 * @param param	삭제할 관심 펀딩의 프로젝트 번호와 사용자의 아이디 정보
	 * @return	삭제 '성공' or '실패' 메시지 전달
	 */
	@ApiOperation(value = "관심 펀딩 삭제", notes = "관심 펀딩 DB 데이터 삭제.", response = Map.class)
	@PostMapping("/interestingDelete")
	public ResponseEntity<Map<String, Object>> interestingDelete(
			@RequestBody @ApiParam(value = "DB에서 삭제 ") Map<String, Object> param) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			projectService.interestingDelete(param);
			resultMap.put("messagae", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "진행 중인 펀딩 목록", notes = "진행 중인 펀딩 최신 순으로 6개를 제공", response = Map.class)
	@GetMapping("/fundingList")
	public ResponseEntity<Map<String, Object>> getFundingList() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		
		try {
			List<ProjectDto> list = projectService.getFundingList();
			resultMap.put("list", list);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "선택한 카테고리에 대한 프로젝트 목록 제공", notes = "선택한 카테고리에 속하는 모든 프로젝트 정보를 제공", response = Map.class)
	@GetMapping("/fundingList/{category}")
	public ResponseEntity<Map<String, Object>> getFundingListByCategory(
			@PathVariable @ApiParam(value = "펀딩 정보를 가져올 카테고리", required = true) String category) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		
		try {
			List<ProjectDto> list = projectService.getFundingListByCategory(category);
			resultMap.put("list", list);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "펀딩 세부 정보 보기", notes = "펀딩 세부 정보를 가져옴", response = Map.class)
	@GetMapping("/fundingDetail/{project_num}")
	public ResponseEntity<Map<String, Object>> getFundingDetail(
			@PathVariable("project_num") @ApiParam(value = "펀딩 세부 정보를 보기 위한 project_num", required = true) int project_num){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			ProjectDto detail = projectService.getFundingDetail(project_num);
			resultMap.put("project", detail);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
			
		}catch(Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "허가 대기 중인 지원자 확인", notes = "프로젝트에 신청한 인원의 정보를 가져옴", response = Map.class)
	@GetMapping("/waitingList/{project_num}")
	public ResponseEntity<Map<String, Object>> waitingList(
			@PathVariable("project_num") @ApiParam(value = "신청 멤버를 보기 위한 project_num", required = true)int project_num){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			MemberDto [] member = projectService.waitingList(project_num);
			resultMap.put("member", member);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		}catch(Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "오늘 시간 기준으로 종료된 프로젝트로 보내버리기", notes = "프로젝트 관리", response = Map.class)
	@PutMapping("/projectmanage")
	public ResponseEntity<Map<String, Object>> projectManage(
			@RequestBody @ApiParam(value = "딱히 없음", required = true)  Map<String, Object> param){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			projectService.projectManager(param);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		}catch(Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "평가 대상자 목록 얻어오기", notes = "평가 대상자 목록을 제공", response = Map.class)
	@GetMapping("/evaluateList/{project_num}")
	public ResponseEntity<Map<String, Object>> getEvaluateList(
			@PathVariable("project_num") @ApiParam(value = "평가 대상자 목록을 얻어올 프로젝트 번호", required = true) int project_num) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			
			List<Map<String, Object>> list = projectService.getEvaluateList(project_num);
			System.out.println(list);
			resultMap.put("list", list);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "프로젝트 검색", notes = "프로젝트 검색하여 그 항목 띠우기", response = Map.class)
	@GetMapping("/search/{topic}")
	public ResponseEntity<Map<String, Object>> search(
			@PathVariable("topic") @ApiParam(value = "검색어", required = true) String topic){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			ProjectDto[] searchProject = projectService.search(topic);
			resultMap.put("list", searchProject);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		}catch(Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
}
