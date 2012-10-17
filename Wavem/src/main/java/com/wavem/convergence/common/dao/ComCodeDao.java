package com.wavem.convergence.common.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("comCodeDao")
public class ComCodeDao extends SqlMapClientDaoSupport {
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}
	
	public List<Map> searchDepCom() {
		
		List<Map> ret = new ArrayList<Map>();
		ret = getSqlMapClientTemplate().queryForList("ComCode.searchDepCom");
		
		return ret;
	}
	
	public List<Map> searchProCom() {
		
		List<Map> ret = new ArrayList<Map>();
		ret = getSqlMapClientTemplate().queryForList("ComCode.searchProCom");
		
		return ret;
	}
	
	public List<Map> searchPostCom() {
		
		List<Map> ret = new ArrayList<Map>();
		ret = getSqlMapClientTemplate().queryForList("ComCode.searchPostCom");
		
		return ret;
	}
	
	public List<Map> searchComCode(Map paraMap) {
		
		List<Map> ret = new ArrayList<Map>();
		ret = getSqlMapClientTemplate().queryForList("ComCode."+paraMap.get("SEL_CD"), paraMap);
		return ret;
	}
	
	
}
