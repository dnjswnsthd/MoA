package com.moa.model.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moa.model.SprintDto;
import com.moa.model.mapper.SprintMapper;

@Service
public class SprintServiceImpl implements SprintService {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void add(Map<String, Object> param) throws Exception {
		sqlSession.getMapper(SprintMapper.class).add(param);
	}

	@Override
	public void modify(SprintDto sprintDto) throws Exception {
		sqlSession.getMapper(SprintMapper.class).modify(sprintDto);	
	}
	
	@Override
	public void modifycontent(SprintDto sprintDto) throws Exception {
		sqlSession.getMapper(SprintMapper.class).modifycontent(sprintDto);	
	}

	@Override
	public void delete(int sprint_num) throws Exception {
		sqlSession.getMapper(SprintMapper.class).delete(sprint_num);
	}

	@Override
	public SprintDto[] search(int project_num) {
		return sqlSession.getMapper(SprintMapper.class).search(project_num);
	}
	
}
