package com.wavem.convergence.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavem.convergence.main.dao.SpringProjectDao;
import com.wavem.convergence.main.vo.SpringProjectVo;

@Service("springProjectService")
public class SpringProjectServiceImpl implements SpringProjectService{

	@Autowired
	SpringProjectDao springProjectDao;
	
	@Override
	public List<SpringProjectVo> selectTest() throws Exception {
		
		List<SpringProjectVo> ret = new ArrayList<SpringProjectVo>();
		
		ret = springProjectDao.selectTest();
		
		return ret;
	}

	@Override
	public int insertTest() throws Exception {
		
		int ret = springProjectDao.insertTest();
		
		return ret;
	}

	@Override
	public int updateTest() throws Exception {
		
		int ret = springProjectDao.updateTest();
		
		return ret;
	}

	@Override
	public int deleteTest() throws Exception {

		int ret = springProjectDao.deleteTest();
		
		return ret;
	}

	@Override
	public List<SpringProjectVo> selectParam(SpringProjectVo param)
			throws Exception {

		List<SpringProjectVo> ret = new ArrayList<SpringProjectVo>();
		
		ret = springProjectDao.selectParam(param);
		
		return ret;
	}

}
