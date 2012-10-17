package com.wavem.convergence.common.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.wavem.convergence.common.vo.CommonVo;

@Repository("commonDao")
public class CommonDao  extends SqlMapClientDaoSupport{
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}

	public List<CommonVo> selectMenu(Map paraMap){
		List<CommonVo> ret = new ArrayList<CommonVo>();
		ret = getSqlMapClientTemplate().queryForList("Common."+paraMap.get("SEL_MN"), paraMap);
		return ret;
	}
}
