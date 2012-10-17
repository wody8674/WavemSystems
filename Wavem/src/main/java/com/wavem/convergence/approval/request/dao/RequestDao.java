package com.wavem.convergence.approval.request.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("requestDao")
public class RequestDao extends SqlMapClientDaoSupport {
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient){
		super.setSqlMapClient(sqlMapClient);
	}
	public List<Map> searchRequest(Map<String, String> paraMap){
		List<Map> ret = new ArrayList<Map>();
		System.out.println(paraMap);
		ret=getSqlMapClientTemplate().queryForList("Request.searchRequest",  paraMap);
		
		return ret;
	}
	
	public String semisaveRequest(Map<String, String> paraMap){
		System.out.println("paraMap=========" +paraMap); 
		String retKey = (String) getSqlMapClientTemplate().queryForObject("Request.selectAppCodeKey");
		paraMap.put("app_code_key", retKey);
		String ret = getSqlMapClientTemplate().update("Request.semisaveRequest",paraMap)+"";
		
		return ret;
	}
	
	public String saveRequest(Map<String, String> paraMap){
		System.out.println("paraMap=========" +paraMap); 
		String retKey = (String) getSqlMapClientTemplate().queryForObject("Request.selectAppCodeKey");
		paraMap.put("app_code_key", retKey);
		String ret = getSqlMapClientTemplate().update("Request.saveRequest",paraMap)+"";
		
		return ret;
	}
	
	public List<Map> searchInsertRequest(Map<String, String> paraMap) {
		List<Map> ret = new ArrayList<Map>();
		ret = getSqlMapClientTemplate().queryForList("Request.searchInsertRequest",paraMap);
		
		System.out.println("Dao ret ===" + ret);
		return ret;
	}
	
	public String updateRequest(Map<String, String> paramap) {
		
		String up1 ="";
		String up2 ="";
		String ret = "";
		
		try {
			// iBatis의 자동커밋을 해제하고 다수 건 처리하기 위한 로직
			super.getSqlMapClientTemplate().getSqlMapClient().startTransaction();
			super.getSqlMapClientTemplate().getSqlMapClient().getCurrentConnection().setAutoCommit(false);
			super.getSqlMapClientTemplate().getSqlMapClient().startBatch();
			
			up1 = getSqlMapClientTemplate().getSqlMapClient().update("Request.updateRequest", paramap) + "";
			
			up2 = getSqlMapClientTemplate().getSqlMapClient().update("Request.updateRequest2", paramap) + "";
			
			if(up1=="1" && up2=="1"){
				ret="1";
			} else {
				ret="0";
			}
			
			super.getSqlMapClientTemplate().getSqlMapClient().commitTransaction();
			super.getSqlMapClientTemplate().getSqlMapClient().executeBatch();
			super.getSqlMapClientTemplate().getSqlMapClient().getCurrentConnection().commit();
			
			
			
		} catch (SQLException e) {
			try {
				super.getSqlMapClientTemplate().getSqlMapClient().getCurrentConnection().rollback();
				ret = "0";
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} 	 {
			try {
				super.getSqlMapClientTemplate().getSqlMapClient().getCurrentConnection().setAutoCommit(true);
				super.getSqlMapClient().endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return ret;
	}

}
