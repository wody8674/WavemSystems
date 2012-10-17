package com.wavem.convergence.community.free.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

	@Repository("freeDao")
	public class FreeDao extends SqlMapClientDaoSupport{


		@Resource(name="sqlMapClient")
		protected void init(SqlMapClient sqlMapClient) {
			super.setSqlMapClient(sqlMapClient); 
		}
		
		public List<Map> free(Map<String, String> paraMap){
			
			List<Map> ret = new ArrayList<Map>();
	
			ret = getSqlMapClientTemplate().queryForList("FreeVo.selectFree", paraMap);
			
			return ret;
		}
		
		public String savefree(Map<String, String> paraMap) {
			
			String ret = getSqlMapClientTemplate().update("FreeVo.saveFree", paraMap) + "";
			
			return ret;
		}
		
		public List<Map> searchfree(Map<String, String> paraMap) {
			
			List<Map> ret = new ArrayList<Map>();
	
			ret = getSqlMapClientTemplate().queryForList("FreeVo.searchFree", paraMap);
			
			return ret;
		}
	}