package com.wavem.convergence.login.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("loginDao")
public class LoginDao extends SqlMapClientDaoSupport{
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient){
		super.setSqlMapClient(sqlMapClient);
	}
	
	public List<Map> login(Map<String, String> paraMap){
		List<Map> ret = new ArrayList<Map>();
		ret = getSqlMapClientTemplate().queryForList("Login.selectUser",paraMap);
		return ret;
	}

}
