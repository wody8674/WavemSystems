package com.wavem.convergence.dep_mem.enterprise.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavem.convergence.dep_mem.enterprise.dao.EnterpriseDao;

@Service("enterpriseService")
public class EnterpriseServiceImpl implements EnterpriseService{

	@Autowired
	EnterpriseDao enterpriseDao;
	
	@Override
	public String saveEnterprise(String strJson) {
		Map<String, String> paramap = new HashMap<String, String>();
		JSONObject jsonObj = JSONObject.fromObject(strJson);
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		String ret = enterpriseDao.saveEnterprise(paramap);
		
		return ret;
	}

	@Override
	public String searchEnterprise(HttpServletRequest request, Map<String, String> param) {
		List<Map> ret = new ArrayList<Map>();
		
		ret = enterpriseDao.searchEnterprise(param);
		
		JSONArray json = JSONArray.fromObject(ret);
		
		return json.toString();
	}

}
