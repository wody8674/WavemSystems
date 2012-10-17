package com.wavem.convergence.project.data.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavem.convergence.project.data.dao.DataDao;


@Service("dataService")
public class DataServiceImpl implements DataService{

	@Autowired
	DataDao dataDao;
	
	@Override
	public String searchdata(HttpServletRequest request, Map<String, String> paraMap) {
	
		List<Map> ret = new ArrayList<Map>();
		
		ret = dataDao.searchdata(paraMap);
		
		JSONArray json = JSONArray.fromObject(ret);
		
		return json.toString();
	}

	@Override
	public String savedata(String strJson) {
		
		Map<String, String> paramap = new HashMap<String, String>();
		JSONObject jsonObj = JSONObject.fromObject(strJson);
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		dataDao.savedata(paramap);
		
		return null;
	}
	
	@Override
	public String updatedata(String strJson) {
		
		Map<String, String> paramap = new HashMap<String, String>();
		JSONObject jsonObj = JSONObject.fromObject(strJson);
		String ret = "";
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		ret = dataDao.updatedata(paramap);
		
		return ret;
	}

}