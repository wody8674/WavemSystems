package com.wavem.convergence.main.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.wavem.convergence.main.vo.AjaxVo;

@Repository("ajaxDao")
public class AjaxDao extends SqlMapClientDaoSupport {
	
	@Resource(name="sqlMapClient")
	protected void init(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}
	
	public List selectParam(Map param) throws Exception{
		
		List ret = new ArrayList();
		
		ret = getSqlMapClientTemplate().queryForList("AjaxData.selectAjax", param);
		
		return ret;		
	}
	
	public List<AjaxVo> selectJson(Map<String, String> param) throws Exception{
		
		List<AjaxVo> ret = new ArrayList<AjaxVo>();
		
		ret = getSqlMapClientTemplate().queryForList("AjaxData.selectAjax", param);
		
		return ret;		
	}

}
