package com.wavem.convergence.community.etc.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("etcDao")
public class EtcDao extends SqlMapClientDaoSupport {
	
	@Resource(name = "sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}
	
	public String saveEtcDetail(Map<String, String> paraMap) {
		
		String ret = getSqlMapClientTemplate().update("Etc.saveEtcDetail", paraMap) + "";
		
		return ret;
	}
	
	public String updateEtcDetail(Map<String, String> paraMap) {
		
		String ret = getSqlMapClientTemplate().update("Etc.updateEtcDetail", paraMap) + "";
		
		return ret;
	}
	
	public List<Map> searchEtc(Map<String, String> paraMap) {
		
		List<Map> ret = new ArrayList<Map>();
		
		ret = getSqlMapClientTemplate().queryForList("Etc.searchEtc", paraMap);
		
		return ret;
	}
}