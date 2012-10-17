package com.wavem.convergence.approval.overtime.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("overtimeDao")
public class OvertimeDao extends SqlMapClientDaoSupport{
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient){
		super.setSqlMapClient(sqlMapClient);
	}
	public List<Map> searchOvertime(Map<String, String> paraMap){
		List<Map> ret = new ArrayList<Map>();
		
		ret=getSqlMapClientTemplate().queryForList("Overtime.searchOvertime",paraMap);
		
		return ret;
	}
	
	public String semisaveOvertime(Map<String, String> paraMap){
		System.out.println("paraMap=========" +paraMap); 
		String retKey = (String) getSqlMapClientTemplate().queryForObject("Overtime.selectAppCodeKey");
		paraMap.put("app_code_key", retKey);
		String ret = getSqlMapClientTemplate().update("Overtime.semisaveOvertime",paraMap)+"";
		
		return ret;
	}
	
	public String saveOvertime(Map<String, String> paraMap){
		System.out.println("paraMap=========" +paraMap); 
		String retKey = (String) getSqlMapClientTemplate().queryForObject("Overtime.selectAppCodeKey");
		paraMap.put("app_code_key", retKey);
		String ret = getSqlMapClientTemplate().update("Overtime.saveOvertime",paraMap)+"";
		
		return ret;
	}
	public List<Map> searchInsertOvertime(Map<String, String> paraMap) {
		List<Map> ret = new ArrayList<Map>();
		ret = getSqlMapClientTemplate().queryForList("Overtime.searchInsertOvertime",paraMap);
		
		System.out.println("Dao ret ===" + ret);
		return ret;
	}
	
	public String updateOvertime(Map<String, String> paramap) {
		
		String ret = "";
		String ret2 = "";
		
		try {
			// iBatis의 자동커밋을 해제하고 다수 건 처리하기 위한 로직
			super.getSqlMapClient().startTransaction();
			super.getSqlMapClient().getCurrentConnection().setAutoCommit(false);
			super.getSqlMapClient().startBatch();
			
			ret = getSqlMapClient().update("Overtime.updateOvertime", paramap) + "";
			
			ret2 = getSqlMapClient().update("Overtime.updateOvertime2", paramap) + "";
			
			ret="1";
		
			
			super.getSqlMapClient().commitTransaction();
			super.getSqlMapClient().executeBatch();
			super.getSqlMapClient().getCurrentConnection().commit();
			
			
			
		} catch (Exception e) {
			try {
				super.getSqlMapClient().getCurrentConnection().rollback();
				ret = "0";
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				super.getSqlMapClient().getCurrentConnection().setAutoCommit(true);
				super.getSqlMapClient().endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return ret;
	}
}
