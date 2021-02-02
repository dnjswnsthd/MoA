package com.moa.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import com.moa.model.ProjectDto;

public interface ProjectMapper {

	void create(ProjectDto project) throws SQLException;

	public ProjectDto projectInfo(String id) throws SQLException;
	
	public ProjectDto waitingProjectInfo(String id) throws SQLException;
	
	public ProjectDto completeProjectInfo(String id) throws SQLException;
	
	void memberToComplete(int project_num) throws SQLException;
	
	void deleteMemberProject(int project_num) throws SQLException;

	void waiting(Map<String, Object> param) throws SQLException;

	void watingDelete(Map<String, Object> param) throws SQLException;

	void projectAppend(Map<String, Object> param) throws SQLException;

	void denial(Map<String, Object> param) throws SQLException;
}
