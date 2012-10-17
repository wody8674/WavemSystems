package com.wavem.convergence.approval.contract.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("contractDao")
public class ContractDao extends SqlMapClientDaoSupport{
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient){
		super.setSqlMapClient(sqlMapClient);
	}
	public List<Map> searchContract(Map<String, String> paraMap){
		List<Map> ret = new ArrayList<Map>();
		
		ret=getSqlMapClientTemplate().queryForList("Contract.searchContract",paraMap);
		
		return ret;
	}
	
	public String saveContract(Map<String, String> paraMap){
		String ret = getSqlMapClientTemplate().update("Contract.saveContract",paraMap)+"";
		
		return ret;
	}


}
