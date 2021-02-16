package com.moa.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moa.model.CompleteProjectInfoDto;
import com.moa.model.MemberDto;
import com.moa.model.ProjectDto;
import com.moa.model.mapper.ProjectMapper;

@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void create(ProjectDto param) throws Exception {
		sqlSession.getMapper(ProjectMapper.class).createProject(param);
		sqlSession.getMapper(ProjectMapper.class).appendProject(param);
	}

	@Override
	public ProjectDto[] projectInfo(String id) throws Exception {
		return sqlSession.getMapper(ProjectMapper.class).projectInfo(id);
	}
	
	@Override
	public ProjectDto[] waitingProjectInfo(String id) throws Exception {
		return sqlSession.getMapper(ProjectMapper.class).waitingProjectInfo(id);
	}
	
	@Override
	public List<CompleteProjectInfoDto> completeProjectInfo(String id) throws Exception {
		return sqlSession.getMapper(ProjectMapper.class).completeProjectInfo(id);
	}
	
	@Override
	public ProjectDto[] interestingProjectInfo(String id) throws Exception {
		return sqlSession.getMapper(ProjectMapper.class).interestingProjectInfo(id);
	}
	
	@Override
	public int waiting(Map<String, Object> param) throws Exception {
		sqlSession.getMapper(ProjectMapper.class).waiting(param);
		return sqlSession.getMapper(ProjectMapper.class).getCnt(param);
	}

	@Override
	public void permission(Map<String, Object> param) throws Exception {
		sqlSession.getMapper(ProjectMapper.class).watingDelete(param);
		sqlSession.getMapper(ProjectMapper.class).projectAppend(param);
		sqlSession.getMapper(ProjectMapper.class).updatePoint(param);
	}

	@Override
	public void denial(Map<String, Object> param) throws Exception {
		sqlSession.getMapper(ProjectMapper.class).denial(param);
	}
	
	@Override
	public void end(int project_num) throws Exception {
		sqlSession.getMapper(ProjectMapper.class).memberToComplete(project_num);
		sqlSession.getMapper(ProjectMapper.class).deleteMemberProject(project_num);
	}
	
	@Override
	public void interesting(Map<String, Object> param) throws Exception {
		sqlSession.getMapper(ProjectMapper.class).interesting(param);
	}
	
	@Override
	public void interestingDelete(Map<String, Object> param) throws Exception {
		sqlSession.getMapper(ProjectMapper.class).interestingDelete(param);
	}

	@Override
	public List<ProjectDto> getFundingList() throws Exception {
		return sqlSession.getMapper(ProjectMapper.class).getFundingList();
	}

	@Override
	public ProjectDto getFundingDetail(int project_num) throws Exception {
		return sqlSession.getMapper(ProjectMapper.class).getFundingDetail(project_num);
	}

	@Override
	public MemberDto[] memberchk(int project_num) throws Exception{
		return sqlSession.getMapper(ProjectMapper.class).memberchk(project_num);
	}
	@Override
	public List<ProjectDto> getFundingListByCategory(String category) throws Exception {
		return sqlSession.getMapper(ProjectMapper.class).getFundingListByCategory(category);
	}
	
	@Override
	public MemberDto[] waitingList(int project_num) throws Exception {
		return sqlSession.getMapper(ProjectMapper.class).waitingList(project_num);
	}

	@Override
	public void projectManager(Map<String, Object> param) throws Exception {
		sqlSession.getMapper(ProjectMapper.class).completeAppend(param);
		sqlSession.getMapper(ProjectMapper.class).memberDelete(param);
	}

	@Override
	public List<Map<String, Object>> getEvaluateList(int project_num) throws Exception {
		return sqlSession.getMapper(ProjectMapper.class).getEvaluateList(project_num);
	}

	@Override
	public ProjectDto[] search(String topic) throws Exception {
		return sqlSession.getMapper(ProjectMapper.class).search(topic);
	}

}
