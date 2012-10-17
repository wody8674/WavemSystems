package com.wavem.convergence.dep_mem.enterprise.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("enterpriseDao")
public class EnterpriseDao extends SqlMapClientDaoSupport {

	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);	//설정정보를 넘김
	}
	
	public String saveEnterprise(Map<String, String> paraMap){
		
		String ret = getSqlMapClientTemplate().update("Enterprise.saveEnterprise", paraMap) + "";
		
		return ret;
	}
	
	public List<Map> searchEnterprise(Map<String, String> paraMap) {
		
		List<Map> ret = new ArrayList<Map>();
		ret = getSqlMapClientTemplate().queryForList("Enterprise.searchEnterprise", paraMap);
		
		return ret;	
		
	}
}
