package com.moa.model.service;

import java.util.Map;

import com.moa.model.SprintDto;

public interface SprintService {

	public void add(Map<String, Object> param) throws Exception;

	public void modify(SprintDto sprintDto) throws Exception;

}
