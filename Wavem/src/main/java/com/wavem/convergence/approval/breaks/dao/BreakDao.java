package com.wavem.convergence.approval.breaks.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.wavem.convergence.approval.breaks.vo.BreakVo;

@Repository("breakDao")
public class BreakDao extends SqlMapClientDaoSupport{
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient){
		super.setSqlMapClient(sqlMapClient);
	}
	public List<Map> searchBreak(Map<String, String> paraMap){
		List<Map> ret = new ArrayList<Map>();
		
		ret=getSqlMapClientTemplate().queryForList("Break.searchBreak",paraMap);
		
		return ret;
	}
	
	public String semisaveBreak(Map<String, String> paraMap){
		System.out.println("paraMap=========" +paraMap); 
		String retKey = (String) getSqlMapClientTemplate().queryForObject("Break.selectAppCodeKey");
		paraMap.put("app_code_key", retKey);
		String ret = getSqlMapClientTemplate().update("Break.semisaveBreak",paraMap)+"";
		
		return ret;
	}
	
	public String saveBreak(Map<String, String> paraMap){
		System.out.println("paraMap=========" +paraMap); 
		String retKey = (String) getSqlMapClientTemplate().queryForObject("Break.selectAppCodeKey");
		paraMap.put("app_code_key", retKey);
		String ret = getSqlMapClientTemplate().update("Break.saveBreak",paraMap)+"";
		
		return ret;
	}
	public List<Map> searchInsertBreak(Map<String, String> paraMap) {
		List<Map> ret = new ArrayList<Map>();
		ret = getSqlMapClientTemplate().queryForList("Break.searchInsertBreak",paraMap);
		
		System.out.println("Dao ret ===" + ret);
		return ret;
	}
	
	
	public String updateBreak(Map<String, String> paramap) {
		
		String ret = "";
		String ret1 ="";
		
		try {
			// iBatis의 자동커밋을 해제하고 다수 건 처리하기 위한 로직
			super.getSqlMapClient().startTransaction();
			super.getSqlMapClient().getCurrentConnection().setAutoCommit(false);
			super.getSqlMapClient().startBatch();
			
			ret = getSqlMapClient().update("Break.updateBreak", paramap) + "";
			
			ret1 = getSqlMapClient().update("Break.updateBreak2", paramap) + "";
			
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
	
	
	public String updateApproval(Map<String, String> paramap) {
		String ret = "";
		ret = getSqlMapClientTemplate().update("Break.updateApproval", paramap) + "";
		
		return ret;
	}
	public String updateRollback(Map<String, String> paramap) {
		String ret = "";
		ret = getSqlMapClientTemplate().update("Break.updateRollback", paramap) + "";
		
		return ret;
	}
	
	
	//임시저장이거나 반려일경우 날짜 수정및 상태변환
	public String updateSRSave(Map<String, String> paramap) {
		String ret = "";
		String ret1 ="";
		String ret2 ="";
		try {
			// iBatis의 자동커밋을 해제하고 다수 건 처리하기 위한 로직
			super.getSqlMapClient().startTransaction();
			super.getSqlMapClient().getCurrentConnection().setAutoCommit(false);
			super.getSqlMapClient().startBatch();
			
			ret = getSqlMapClient().update("Break.updateApproval", paramap) + "";
			
			ret1 = getSqlMapClient().update("Break.updateBreak", paramap) + "";
			ret2 = getSqlMapClient().update("Break.updateBreak2", paramap) + "";
			
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
