package com.wavem.convergence.dep_mem_search.department.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("searchDepartmentDao")
public class SearchDepartmentDao extends SqlMapClientDaoSupport {

	@Resource(name = "sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}
	
	public List<Map> searchDepartment(Map<String, String> paraMap) {
		
		List<Map> ret = new ArrayList<Map>();
		ret = getSqlMapClientTemplate().queryForList("Department.searchDepartment", paraMap);
		
		return ret;
	}
}
