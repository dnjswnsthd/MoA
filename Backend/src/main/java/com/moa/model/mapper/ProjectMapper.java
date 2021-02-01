package com.moa.model.mapper;

import java.sql.SQLException;

import com.moa.model.ProjectDto;

public interface ProjectMapper {

	void create(ProjectDto project) throws SQLException;

}
