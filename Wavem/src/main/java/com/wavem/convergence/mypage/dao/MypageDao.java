package com.wavem.convergence.mypage.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("mypageDao")
public class MypageDao extends SqlMapClientDaoSupport {

	@Resource(name = "sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}
	
public List<Map> searchMypage(Map<String, String> paraMap) {
		
		List<Map> ret = new ArrayList<Map>();
		ret = getSqlMapClientTemplate().queryForList("Mypage.searchMypage", paraMap);
		System.out.println("Dao ret ==="+ret);
		return ret;
	}
}
