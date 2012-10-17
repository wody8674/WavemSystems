package com.wavem.convergence.project.data.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;


	@Repository("dataDao")
	public class DataDao extends SqlMapClientDaoSupport{
		
		@Resource(name="sqlMapClient")
		protected void init(SqlMapClient sqlMapClient) {
			super.setSqlMapClient(sqlMapClient); 
		}
		
		public String updatedata(Map<String, String> paraMap){
			
			String ret = getSqlMapClientTemplate().update("Data.updateData", paraMap) + "";
			
			return ret;
		}
		
		public String savedata(Map<String, String> paraMap) {
			
			String ret = getSqlMapClientTemplate().update("Data.saveData", paraMap) + "";
			
			return ret;
		}
		
		public List<Map> searchdata(Map<String, String> paraMap) {
			
			List<Map> ret = new ArrayList<Map>();

			ret = getSqlMapClientTemplate().queryForList("Data.searchData", paraMap);
			
			return ret;
		}
	}
