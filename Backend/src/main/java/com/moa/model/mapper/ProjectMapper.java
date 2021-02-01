package com.moa.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import com.moa.model.ProjectDto;

public interface ProjectMapper {

	void create(ProjectDto project) throws SQLException;

	void waiting(Map<String, Object> param) throws SQLException;

}
