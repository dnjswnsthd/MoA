package com.moa.model.service;

import java.util.Map;

import com.moa.model.ProjectDto;

public interface ProjectService {

	public void create(ProjectDto project) throws Exception;
	public ProjectDto projectInfo(String id) throws Exception;

	public void waiting(Map<String, Object> param) throws Exception;

}
