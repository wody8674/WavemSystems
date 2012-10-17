package com.wavem.convergence.project.issues.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavem.convergence.project.issues.dao.IssuesDao;


@Service("issuesService")
public class IssuesServiceImpl  implements IssuesService{

	@Autowired
	IssuesDao issuesDao;
	
	@Override
	public String searchissues(HttpServletRequest request, Map<String, String> paraMap) {
	
		List<Map> ret = new ArrayList<Map>();
		
		ret = issuesDao.searchissues(paraMap);
		
		JSONArray json = JSONArray.fromObject(ret);
		
		return json.toString();
	}

	@Override
	public String saveissues(String strJson) {
		
		Map<String, String> paramap = new HashMap<String, String>();
		JSONObject jsonObj = JSONObject.fromObject(strJson);
		String ret = "";
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		ret = issuesDao.saveissues(paramap);
		
		return ret;
	}
	
	@Override
	public String updateissues(String strJson) {
		
		Map<String, String> paramap = new HashMap<String, String>();
		JSONObject jsonObj = JSONObject.fromObject(strJson);
		String ret = "";
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		ret = issuesDao.updateissues(paramap);
		
		return ret;
	}

}
