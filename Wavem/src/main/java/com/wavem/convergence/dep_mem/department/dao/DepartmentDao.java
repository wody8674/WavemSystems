package com.wavem.convergence.dep_mem.department.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("departmentDao")
public class DepartmentDao extends SqlMapClientDaoSupport {
	
	@Resource(name = "sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}
	
	public List<Map> searchDepartment(Map<String, String> paraMap) {
		
		List<Map> ret = new ArrayList<Map>();
		ret = getSqlMapClientTemplate().queryForList("Department.searchDepartment", paraMap);
		
		return ret;
	}
	
	public String saveDepartment(Map<String, String> paraMap) {
		
		String ret = "";
		String ret1 = "";
		String code = "";
		try {
			// iBatis의 자동커밋을 해제하고 다수 건 처리하기 위한 로직
			super.getSqlMapClient().startTransaction();
			super.getSqlMapClient().getCurrentConnection().setAutoCommit(false);
			super.getSqlMapClient().startBatch();
			
			code = (String) getSqlMapClientTemplate().queryForObject("Department.insertDepCode");
			paraMap.put("depCode", code);
			
			ret = getSqlMapClient().update("Department.saveDepartment", paraMap) + "";
			ret1 = getSqlMapClient().update("Department.updateMember", paraMap) + "";
			
			super.getSqlMapClient().commitTransaction();
			super.getSqlMapClient().executeBatch();
			super.getSqlMapClient().getCurrentConnection().commit();
			
			ret = "1";
		}
		catch (Exception e) {
			try {
				super.getSqlMapClient().getCurrentConnection().rollback();
				ret = "0";
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally {
			try {
				super.getSqlMapClient().getCurrentConnection().setAutoCommit(true);
				super.getSqlMapClient().endTransaction();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
	
	public String updateDepartment(Map<String, String> paraMap) {
		
		String ret = "";
		String ret1 = "";
		String code = "";
		try {
			// iBatis의 자동커밋을 해제하고 다수 건 처리하기 위한 로직
			super.getSqlMapClient().startTransaction();
			super.getSqlMapClient().getCurrentConnection().setAutoCommit(false);
			super.getSqlMapClient().startBatch();
			
			ret1 = getSqlMapClient().update("Department.updateMemHead", paraMap) + "";
			ret = getSqlMapClient().update("Department.updateDepartment", paraMap) + "";
			
			super.getSqlMapClient().commitTransaction();
			super.getSqlMapClient().executeBatch();
			super.getSqlMapClient().getCurrentConnection().commit();
			
			ret = "1";
		}
		catch (Exception e) {
			try {
				super.getSqlMapClient().getCurrentConnection().rollback();
				ret = "0";
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally {
			try {
				super.getSqlMapClient().getCurrentConnection().setAutoCommit(true);
				super.getSqlMapClient().endTransaction();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
	
	public List<Map> depComCode(Map paraMap) {
		
		List<Map> ret = new ArrayList<Map>();
		ret = getSqlMapClientTemplate().queryForList("Department." + paraMap.get("SEL_CD"), paraMap);
		return ret;
	}
	
	public String updateHeadDep(Map<String, String> paraMap) {
		
		String ret = getSqlMapClientTemplate().update("Department.updateHeadDep", paraMap) + "";
		
		return ret;
	}
	
}
