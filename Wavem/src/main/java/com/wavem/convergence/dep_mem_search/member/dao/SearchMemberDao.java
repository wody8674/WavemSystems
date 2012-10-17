package com.wavem.convergence.dep_mem_search.member.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("searchMemberDao")
public class SearchMemberDao extends SqlMapClientDaoSupport {

	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);	//설정정보를 넘김
	}
	
	public List<Map> searchMember(Map<String, String> paraMap) {
		
		List<Map> ret = new ArrayList<Map>();
		ret = getSqlMapClientTemplate().queryForList("Member.searchMember", paraMap);
		
		return ret;	
	}
}
