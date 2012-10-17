package com.wavem.convergence.project.issues.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

	@Repository("issuesDao")
	public class IssuesDao extends SqlMapClientDaoSupport{


		@Resource(name="sqlMapClient")
		protected void init(SqlMapClient sqlMapClient) {
			super.setSqlMapClient(sqlMapClient); 
		}
		
		public String updateissues(Map<String, String> paraMap){
			
			String ret = getSqlMapClientTemplate().update("Issues.updateIssues", paraMap) + "";
			
			return ret;
		}
		
		public String saveissues(Map<String, String> paraMap) {
			
			String ret = getSqlMapClientTemplate().update("Issues.saveIssues", paraMap) + "";
			
			return ret;
		}
		
		public List<Map> searchissues(Map<String, String> paraMap) {
			
			List<Map> ret = new ArrayList<Map>();
	
			ret = getSqlMapClientTemplate().queryForList("Issues.searchIssues", paraMap);
			
			return ret;
		}
	}