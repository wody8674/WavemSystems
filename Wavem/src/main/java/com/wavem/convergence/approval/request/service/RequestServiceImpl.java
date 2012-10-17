package com.wavem.convergence.approval.request.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavem.convergence.approval.request.dao.RequestDao;

@Service("requestService")
public class RequestServiceImpl implements RequestService{
	
	@Autowired
	RequestDao requestDao;

	public String searchRequest(HttpServletRequest request,	Map<String, String> paraMap) {
		List<Map> ret = new ArrayList<Map>();
		ret = requestDao.searchRequest(paraMap);
		
		JSONArray json = JSONArray.fromObject(ret);
		
		return json.toString();
	}
	
	@Override
	public String searchInsertRequest(HttpServletRequest request,	Map<String, String> paraMap) {
		List<Map> ret = new ArrayList<Map>();
		ret = requestDao.searchInsertRequest(paraMap);
		
		System.out.println("service ret==" + ret);
		
		JSONArray json = JSONArray.fromObject(ret);
		
		return json.toString();
	}

	@Override
	public String saveRequest(HttpServletRequest request, String strJson) {
		
		Map<String, String> paramap = new HashMap<String, String>();
		JSONObject jsonObj = JSONObject.fromObject(strJson);
		String ret = "";
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		System.out.println("paramap ==" +paramap);
		paramap.put("MEM_CODE", (String) request.getSession().getAttribute("userMC"));
		ret = requestDao.saveRequest(paramap);
		
		
		return ret;
	}
	
	@Override
	public String semisaveRequest(HttpServletRequest request, String strJson) {
		
		Map<String, String> paramap = new HashMap<String, String>();
		JSONObject jsonObj = JSONObject.fromObject(strJson);
		String ret = "";
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		paramap.put("MEM_CODE", (String) request.getSession().getAttribute("userMC"));
		ret = requestDao.semisaveRequest(paramap);

		return ret;
	}

	@Override
	public String updateRequest(String jsonData) {
		Map<String, String> paramap = new HashMap<String, String>();
		JSONObject jsonObj = JSONObject.fromObject(jsonData);
		String ret = "";
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		ret = requestDao.updateRequest(paramap);
		return ret;
	}

}
