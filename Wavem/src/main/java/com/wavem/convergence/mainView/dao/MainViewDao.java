package com.wavem.convergence.mainView.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("mainViewDao")
public class MainViewDao extends SqlMapClientDaoSupport {
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient){
		super.setSqlMapClient(sqlMapClient);
	}
	
	public List<Map> selectMain(Map<String, String> paraMap) {
		List<Map> ret = new ArrayList<Map>();
		ret = getSqlMapClientTemplate().queryForList("mainView." + paraMap.get("sel_cd") , paraMap);
		return ret;
	}
}
