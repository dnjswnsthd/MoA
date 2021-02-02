package com.moa.model.mapper;

import java.util.Map;

import com.moa.model.SprintDto;

public interface SprintMapper {
	void add(Map<String, Object> param);

	void modify(SprintDto sprintDto);
}
