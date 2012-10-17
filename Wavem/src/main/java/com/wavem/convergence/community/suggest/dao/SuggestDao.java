package com.wavem.convergence.community.suggest.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

	@Repository("suggestDao")
	public class SuggestDao extends SqlMapClientDaoSupport{


		@Resource(name="sqlMapClient")
		protected void init(SqlMapClient sqlMapClient) {
			super.setSqlMapClient(sqlMapClient); 
		}
		
		public List<Map> suggest(Map<String, String> paraMap){
			
			List<Map> ret = new ArrayList<Map>();
	
			ret = getSqlMapClientTemplate().queryForList("SuggestVo.selectSuggest", paraMap);
			
			return ret;
		}
		
		public String savesuggest(Map<String, String> paraMap) {
			
			String ret = getSqlMapClientTemplate().update("SuggestVo.saveSuggest", paraMap) + "";
			
			return ret;
		}
		
		public List<Map> searchsuggest(Map<String, String> paraMap) {
			
			List<Map> ret = new ArrayList<Map>();
	
			ret = getSqlMapClientTemplate().queryForList("SuggestVo.searchSuggest", paraMap);
			
			return ret;
		}
	}