package com.moa.model.service;

import java.util.Map;

import com.moa.model.SprintDto;

public interface SprintService {

	public void add(Map<String, Object> param) throws Exception;

	public void modify(SprintDto sprintDto) throws Exception;
	
	public void modifycontent(SprintDto sprintDto) throws Exception;

	public void delete(int sprint_num) throws Exception;

	public SprintDto[] search(int project_num);

}
