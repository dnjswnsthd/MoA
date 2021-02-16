package com.moa.model.service;

import java.util.List;
import java.util.Map;

import com.moa.model.CompleteProjectInfoDto;
import com.moa.model.MemberDto;
import com.moa.model.ProjectDto;

public interface ProjectService {

	public void create(ProjectDto param) throws Exception;
	public ProjectDto[] projectInfo(String id) throws Exception;
	public ProjectDto[] waitingProjectInfo(String id) throws Exception;
	public List<CompleteProjectInfoDto> completeProjectInfo(String id) throws Exception;
	public ProjectDto[] interestingProjectInfo(String id) throws Exception;
	
	public void end(int project_num) throws Exception;
	
	public int waiting(Map<String, Object> param) throws Exception;
	public void interesting(Map<String, Object> param) throws Exception;
	public void interestingDelete(Map<String, Object> param) throws Exception;
	
	public void permission(Map<String, Object> param) throws Exception;
	public void denial(Map<String, Object> param) throws Exception;
	public List<ProjectDto> getFundingList() throws Exception;
	public ProjectDto getFundingDetail(int project_num) throws Exception;
	public MemberDto[] memberchk(int project_num)throws Exception;
	public List<ProjectDto> getFundingListByCategory(String category) throws Exception;
	public MemberDto[] waitingList(int project_num) throws Exception;
	public void projectManager(Map<String, Object> param) throws Exception;
	public List<Map<String, Object>> getEvaluateList(int project_num) throws Exception;
	public ProjectDto[] search(String topic) throws Exception;

}
