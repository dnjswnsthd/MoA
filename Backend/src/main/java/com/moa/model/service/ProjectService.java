package com.moa.model.service;

import java.util.Map;

import com.moa.model.ProjectDto;

public interface ProjectService {

	public void create(ProjectDto project) throws Exception;
	public ProjectDto[] projectInfo(String id) throws Exception;
	public ProjectDto[] waitingProjectInfo(String id) throws Exception;
	public ProjectDto[] completeProjectInfo(String id) throws Exception;
	public ProjectDto[] interestingProjectInfo(String id) throws Exception;
	
	public void end(int project_num) throws Exception;
	
	public void waiting(Map<String, Object> param) throws Exception;
	public void interesting(Map<String, Object> param) throws Exception;

	public void permission(Map<String, Object> param) throws Exception;
	public void denial(Map<String, Object> param) throws Exception;

}
