package com.wavem.convergence.community.notice.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("noticeDao")
public class NoticeDao extends SqlMapClientDaoSupport {
	
	@Resource(name = "sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}
	
	public List<Map> searchGubun_Dep() {
		
		List<Map> ret = new ArrayList<Map>();
		
		ret = getSqlMapClientTemplate().queryForList("Notice.searchGubun_Dep");
		
		return ret;
	}
	
	public List<Map> searchGubun_Pro() {
		
		List<Map> ret = new ArrayList<Map>();
		
		ret = getSqlMapClientTemplate().queryForList("Notice.searchGubun_Pro");
		
		return ret;
	}
	
	public String saveNoticeDetail(Map<String, String> paraMap) {
		
		String ret = getSqlMapClientTemplate().update("Notice.saveNoticeDetail", paraMap) + "";
		
		return ret;
	}
	
	public String updateNoticeDetail(Map<String, String> paraMap) {
		
		String ret = getSqlMapClientTemplate().update("Notice.updateNoticeDetail", paraMap) + "";
		
		return ret;
	}
	
	public List<Map> searchNotice(Map<String, String> paraMap) {
		
		List<Map> ret = new ArrayList<Map>();
		
		ret = getSqlMapClientTemplate().queryForList("Notice.searchNotice", paraMap);
		
		return ret;
	}
}