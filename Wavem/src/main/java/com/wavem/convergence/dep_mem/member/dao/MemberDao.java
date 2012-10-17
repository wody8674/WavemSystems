package com.wavem.convergence.dep_mem.member.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("memberDao")
public class MemberDao extends SqlMapClientDaoSupport {
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);	//설정정보를 넘김
	}
	
	public String saveMember(Map<String, String> paraMap){
		
		String ret = getSqlMapClientTemplate().update("Member.saveMember", paraMap) + "";
		
		return ret;
	}
	
	public List<Map> searchMember(Map<String, String> paraMap) {
		
		List<Map> ret = new ArrayList<Map>();
		ret = getSqlMapClientTemplate().queryForList("Member.searchMember", paraMap);
		
		return ret;	
	}
	
	public String updateMember(Map<String, String> paraMap){
		
		String ret = getSqlMapClientTemplate().update("Member.updateMember", paraMap) + "";
		
		return ret;
	}	
	
	public List<Map> memComCode(Map paraMap) {
		
		List<Map> ret = new ArrayList<Map>();
		ret = getSqlMapClientTemplate().queryForList("Member."+paraMap.get("SEL_CD"), paraMap);
		return ret;
	}
	
}
