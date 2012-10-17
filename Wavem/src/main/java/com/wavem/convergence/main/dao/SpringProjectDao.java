package com.wavem.convergence.main.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.wavem.convergence.main.vo.SpringProjectVo;

@Repository("springProjectDao")
public class SpringProjectDao extends SqlMapClientDaoSupport {
			
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}
	
	public List<SpringProjectVo> selectTest() throws Exception{
		
		List<SpringProjectVo> ret = new ArrayList<SpringProjectVo>();
		
		ret = getSqlMapClientTemplate().queryForList("SpringProject.selectSpringProject");
		
		return ret;		
	}
	
	public int insertTest() throws Exception {
		
		int ret = getSqlMapClientTemplate().update("SpringProject.insertSpringProject");
		
		return ret;
	}
	
	public int updateTest() throws Exception {
		
		int ret = getSqlMapClientTemplate().update("SpringProject.updateSpringProject");
		
		return ret;
	}
	
	public int deleteTest() throws Exception {
		
		int ret = getSqlMapClientTemplate().delete("SpringProject.deleteSpringProject");
		
		return ret;
	}
	
	public List<SpringProjectVo> selectParam(SpringProjectVo param) throws Exception{
		
		List<SpringProjectVo> ret = new ArrayList<SpringProjectVo>();
		
		ret = getSqlMapClientTemplate().queryForList("SpringProject.selectParam", param);
		
		return ret;		
	}
}
