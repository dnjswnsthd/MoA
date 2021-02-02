package com.moa.model.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moa.model.mapper.SprintMapper;

@Service
public class SprintServiceImpl implements SprintService {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void add(Map<String, Object> param) throws Exception {
		sqlSession.getMapper(SprintMapper.class).add(param);
	}

}
