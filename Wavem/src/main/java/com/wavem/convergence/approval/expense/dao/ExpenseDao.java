package com.wavem.convergence.approval.expense.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("expenseDao")
public class ExpenseDao extends SqlMapClientDaoSupport {
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient){
		super.setSqlMapClient(sqlMapClient);
	}
	public List<Map> searchExpense(Map<String, String> paraMap){
		List<Map> ret = new ArrayList<Map>();
		
		ret=getSqlMapClientTemplate().queryForList("Expense.searchExpense",paraMap);
		
		return ret;
	}
	
	public String semisaveExpense(Map<String, String> paraMap){
		System.out.println("paraMap=========" +paraMap); 
		String retKey = (String) getSqlMapClientTemplate().queryForObject("Expense.selectAppCodeKey");
		paraMap.put("app_code_key", retKey);
		String ret = getSqlMapClientTemplate().update("Expense.semisaveExpense",paraMap)+"";
		
		return ret;
	}
	
	public String saveExpense(Map<String, String> paraMap){
		System.out.println("paraMap=========" +paraMap); 
		String retKey = (String) getSqlMapClientTemplate().queryForObject("Break.selectAppCodeKey");
		paraMap.put("app_code_key", retKey);

		String ret = getSqlMapClientTemplate().update("Expense.saveExpense",paraMap)+"";
		
		return ret;
	}
	
	public List<Map> searchInsertExpense(Map<String, String> paraMap) {
		List<Map> ret = new ArrayList<Map>();
		ret = getSqlMapClientTemplate().queryForList("Expense.searchInsertExpense",paraMap);
		
		System.out.println("Dao ret ===" + ret);
		return ret;
	}
	public String updateExpense(Map<String, String> paramap) {
		
		String ret = "";
		String ret1 ="";
		
		try {
			// iBatis의 자동커밋을 해제하고 다수 건 처리하기 위한 로직
			super.getSqlMapClient().startTransaction();
			super.getSqlMapClient().getCurrentConnection().setAutoCommit(false);
			super.getSqlMapClient().startBatch();
			
			ret = getSqlMapClientTemplate().getSqlMapClient().update("Expense.updateExpense", paramap) + "";
			
			ret1 = getSqlMapClientTemplate().getSqlMapClient().update("Expense.updateExpense2", paramap) + "";
			
			ret="1";
			
			super.getSqlMapClient().commitTransaction();
			super.getSqlMapClient().executeBatch();
			super.getSqlMapClient().getCurrentConnection().commit();
			
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
