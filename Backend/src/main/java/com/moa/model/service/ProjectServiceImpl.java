package com.moa.model.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moa.model.ProjectDto;
import com.moa.model.mapper.ProjectMapper;

@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void create(ProjectDto project) throws Exception{
		sqlSession.getMapper(ProjectMapper.class).create(project);	
	}

	public ProjectDto projectInfo(String id) throws Exception {
		return sqlSession.getMapper(ProjectMapper.class).projectInfo(id);
	}
}
