package com.wavem.convergence.main.service;

import java.util.List;

import com.wavem.convergence.main.vo.SpringProjectVo;

public interface SpringProjectService {
	public List<SpringProjectVo> selectTest() throws Exception;
	public int insertTest() throws Exception;
	public int updateTest() throws Exception;
	public int deleteTest() throws Exception;
	public List<SpringProjectVo> selectParam(SpringProjectVo param) throws Exception;
}
