package com.moa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moa.model.EvaluateDto;
import com.moa.model.MemberDto;
import com.moa.model.RankDto;
import com.moa.model.service.JwtServiceImpl;
import com.moa.model.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("MemberController v0.1")
@RestController
@RequestMapping("/member")
public class MemberController {
	// Server side log를 위한 객체 생성
	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtServiceImpl jwtService; // 로그인 토큰 생성을 위한 service

	@Autowired
	private MemberService memberService; // 데이터베이스에 로그인 시도를 위한 service

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

			if (loginMember != null) { // 로그인 정보가 존재하는 경우
				// (key, data, subject)
				String token = jwtService.create("id", loginMember.getId(), "access-token");
				logger.debug("로그인 토큰 정보 : {}", token); // server side log
				resultMap.put("access-token", token); // access-token 전달
				resultMap.put("message", SUCCESS); // "성공" 메세지 전달
				status = HttpStatus.ACCEPTED;
			} else { // 로그인 정보가 존재하지 않는 경우
				resultMap.put("massage", FAIL); // "실패" 메세지 전달
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
	@GetMapping("/join/{id}")
	public ResponseEntity<Map<String, Object>> idChk(
			@PathVariable("id") @ApiParam(value = "중복 확인할 아이디", required = true) String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			if (memberService.idChk(id)) { // 아이디가 중복 되지 않을 경우
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("중복 확인 실패 : {}", e);
			resultMap.put("massage", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "PW 확인", notes = "내 정보 변경시 비밀번호 검사", response = Map.class)
	@PostMapping("/pwcheck")
	public ResponseEntity<Map<String, Object>> pwcheck(
			@RequestBody @ApiParam(value = "내 정보 변경시 비밀번호 검사를 위한 id, pw", required = true) MemberDto memberDto) {
		System.out.println(memberDto.getId());
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		String id = memberDto.getId();
		String pw = memberDto.getPw();
		
		try {
			if (memberService.pwcheck(id, pw)) {// pw가 맞으면
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}

		} catch (Exception e) {
			logger.error("비밀번호 확인 실패  : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원 가입(멘토, 멘티)", notes = "멘토의 회원 가입 결과를 반환한다.", response = Map.class)
	@PostMapping("/join")
	public ResponseEntity<Map<String, Object>> join(
			@RequestBody @ApiParam(value = "멘토 회원 가입에 필요한 회원 정보", required = true) Map<String, Object> param) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			memberService.join(param);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("massage", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원 정보 수정", notes = "회원 정보를 수정하고 성공 여부에 따라 Success Or Fail 문자열을 반환한다.", response = Map.class)
	@PutMapping("/update")
	public ResponseEntity<Map<String, Object>> memberUpdate(
			@RequestBody @ApiParam(value = "본인 확인용 pw", required = true) MemberDto memberDto) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			// 업데이트 데이터 전송시 updateMember 활용.
			memberService.memberUpdate(memberDto);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;

		} catch (Exception e) {
			logger.error("수정 실패 : {}", e);
			resultMap.put("massage", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "비밀번호 변경", notes = "비밀번호를 변경하고 성공 여부에 따라 Success Or Fail 문자열을 반환한다.", response = Map.class)
	@PutMapping("/changepw")
	public ResponseEntity<Map<String, Object>> changePassword(
			@RequestBody @ApiParam(value = "본인 확인용 pw", required = true) Map<String, Object> param) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			memberService.changePassword(param);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;

		} catch (Exception e) {
			logger.error("수정 실패 : {}", e);
			resultMap.put("massage", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "비밀번호 찾기", notes = "입력한 아이디를 확인하여 임시 비밀번호를 만들어 이메일로 전송하여준다.", response = Map.class)
	@PutMapping("/findpw/{email}")
	@Transactional // 트랜젝션 설정
	public ResponseEntity<Map<String, Object>> findPassword(
			@PathVariable @ApiParam(value = "비밀번호 찾을 이메일", required = true) String email) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			// 데이터베이스에 이메일이 존재해야 임시 비밀번호를 생성
			if (!memberService.idChk(email)) {
				memberService.updateTempPassword(email);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL); // 이메일이 존재하지 않는 경우
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("비밀번호 찾기 실패 : {}", e);
			resultMap.put("massage", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "내 정보 보기", notes = "로그인 후 마이 페이지에서 정보를 보기 위하여 멤버 관련 모든 데이터를 전송하여준다.", response = Map.class)
	@GetMapping("/mypage/{id}")
	public ResponseEntity<Map<String, Object>> memberInfo(
			@PathVariable("id") @ApiParam(value = "정보를 불러올 아이디", required = true) String id) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			Object memberInfo = memberService.memberInfo(id);
			resultMap.put("message", SUCCESS);
			resultMap.put("memberInfo", memberInfo);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("정보조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원 탈퇴", notes = "회원 탈퇴 결과 메세지를 반환한다.", response = Map.class)
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Object>> delete(
			@PathVariable("id") @ApiParam(value = "회원 탈퇴시 비밀번호 필요", required = true) String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			// Database에서 아이디 비밀번호 일치 여부 확인
			memberService.delete(id);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원 포인트 수정", notes = "회원 포인트를 갱신하고 성공 여부에 따라 Success Or Fail 문자열을 반환한다.", response = Map.class)
	@PutMapping("/updatepoint")
	public ResponseEntity<Map<String, Object>> memberUpdatePoint(
			@RequestBody @ApiParam(value = "멤머 Dto 값", required = true) MemberDto memberDto) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			// 업데이트 데이터 전송시 updateMember 활용.
			memberService.memberUpdatePoint(memberDto);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("수정 실패 : {}", e);
			resultMap.put("massage", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "멘토 랭킹 정보", notes = "멘토의 경험치, 도덕성, 신뢰성, 적극성, 전문성, 리더쉽 순으로 정렬된 리스트를 제공", response = Map.class)
	@GetMapping("/rank")
	public ResponseEntity<Map<String, Object>> getRanking() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		
		try {
			List<List<RankDto>> list = memberService.getRanking();
			resultMap.put("list", list);
			resultMap.put("massage", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "멘토, 멘티 평가 등록", notes = "", response = Map.class)
	@PutMapping("/evaluate/change")
	@Transactional
	public ResponseEntity<Map<String, Object>> updateCompleteStatus(
			@RequestBody @ApiParam(value = "평가를 완료한 사람의 id와 평가한 프로젝트 번호", required = true) Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		
		try {
			memberService.updateCompleteStatus(map);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "멘토, 멘티 평가 등록", notes = "", response = Map.class)
	@PostMapping("/evaluate")
	@Transactional // 트랜젝션 설정
	public ResponseEntity<Map<String, Object>> evaluateTeammate(
			@RequestBody @ApiParam(value = "평가 대상자의 id와 평가 값을 담은 객체", required = true) EvaluateDto evaluateDto) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		
		try {
			memberService.updateEvaluate(evaluateDto);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
