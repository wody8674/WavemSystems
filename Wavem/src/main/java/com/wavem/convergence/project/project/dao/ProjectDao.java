package com.wavem.convergence.project.project.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("projectDao")
public class ProjectDao extends SqlMapClientDaoSupport {
	
	@Resource(name = "sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}
	
	public List<Map> searchproject(Map<String, String> paraMap) {
		
		List<Map> ret = new ArrayList<Map>();
		System.out.println(paraMap);
		ret = getSqlMapClientTemplate().queryForList("Project.searchProject", paraMap);
		
		return ret;
	}
	
	public List<Map> searchProjectPart(Map<String, String> parMap) {
		
		// 1_ 리스트 생성
		List<Map> ret = new ArrayList<Map>();
		
		// 2_ DB조회 (위에서 받아온 맵을 파라미터로 보냄)
		ret = getSqlMapClientTemplate().queryForList("Project.searchProjectPart", parMap);
		
		return ret;
	}
	
	public String saveproject(Map<String, String> paraMap, List<Map<String, String>> paraList) {
		
		String pro_code_key = "";
		String mem_code = "";
		String ret = "0";
		String ret2 = "";
		
		try {
			// iBatis의 자동커밋을 해제하고 다수 건 처리하기 위한 로직
			super.getSqlMapClient().startTransaction();
			super.getSqlMapClient().getCurrentConnection().setAutoCommit(false);
			super.getSqlMapClient().startBatch();
			
			// 프로젝트 key 조회
			pro_code_key = (String) getSqlMapClientTemplate().queryForObject("Project.selectProjectCode");
			paraMap.put("pro_code_key", pro_code_key);
			
			// 프로젝트 등록
			ret = getSqlMapClient().insert("Project.saveProject", paraMap) + "";
			
			// 프로젝트 참여자 등록
			ret2 = "";
			mem_code = paraMap.get("mem_code");
			if (paraList.size() > 0) {
				
				for (Map<String, String> paraMap2 : paraList) {
					paraMap2.put("pro_code_key", pro_code_key);
					paraMap2.put("userMC", mem_code);
					// ret2 = getSqlMapClientTemplate().getSqlMapClient().insert("Project.saveProjectPart", paraMap2) + "";
					ret2 = getSqlMapClient().insert("Project.saveProjectPart", paraMap2) + "";
					
				}
			}
			
			super.getSqlMapClient().commitTransaction();
			super.getSqlMapClient().executeBatch();
			super.getSqlMapClient().getCurrentConnection().commit();
			ret = "1";
		}
		catch (SQLException e) {
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
	
	public String updateProject(Map<String, String> paraMap, List<Map<String, String>> paraList) {
		
		String pro_code_key = "";
		String mem_code = "";
		String ret = "";
		String ret2 = "";
		
		try {
			// iBatis의 자동커밋을 해제하고 다수 건 처리하기 위한 로직
			super.getSqlMapClient().startTransaction();
			super.getSqlMapClient().getCurrentConnection().setAutoCommit(false);
			super.getSqlMapClient().startBatch();
			
			// 프로젝트 등록
			ret = getSqlMapClient().update("Project.updateProject", paraMap) + "";
			
			pro_code_key = paraMap.get("pro_code_key");
			
			// 프로젝트 참여자 등록
			ret2 = "";
			mem_code = paraMap.get("mem_code");
			if (paraList.size() > 0) {
				
				for (Map paraMap2 : paraList) {
					
					paraMap2.put("pro_code_key", pro_code_key);
					paraMap2.put("userMC", mem_code);
					
					if (paraMap2.get("use_check").equals("1") && paraMap2.get("use_check2").equals("0")) {
						ret2 = getSqlMapClient().insert("Project.saveProjectPart", paraMap2) + "";
					}
					else {
						ret2 = getSqlMapClient().update("Project.updateProjectPart", paraMap2) + "";
					}
				}
			}
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
		
		System.out.println("ret =" + ret);
		return ret;
	}
	
}
