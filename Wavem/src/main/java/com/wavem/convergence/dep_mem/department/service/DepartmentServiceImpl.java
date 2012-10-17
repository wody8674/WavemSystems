package com.wavem.convergence.dep_mem.department.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavem.convergence.dep_mem.department.dao.DepartmentDao;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	DepartmentDao departmentDao;
	
	@Override
	public String searchDepartment(HttpServletRequest request, Map<String, String> param) {
		List<Map> ret = new ArrayList<Map>();
		
		ret = departmentDao.searchDepartment(param);
		
		JSONArray json = JSONArray.fromObject(ret);
		
		return json.toString();
	}
	
	@Override
	public String saveDepartment(HttpServletRequest request, String strJson) {
		
		Map<String, String> paramap = new HashMap<String, String>();
		JSONObject jsonObj = JSONObject.fromObject(strJson);
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		paramap.put("user_mc", (String) request.getSession().getAttribute("userMC"));
		
		String ret = departmentDao.saveDepartment(paramap);
		
		return ret;
	}
	
	@Override
	public String updateDepartment(HttpServletRequest request, String strJson) {
		Map<String, String> paramap = new HashMap<String, String>();
		JSONObject jsonObj = JSONObject.fromObject(strJson);
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		paramap.put("user_mc", (String) request.getSession().getAttribute("userMC"));
		
		String ret = departmentDao.updateDepartment(paramap);
		
		return ret;
	}
	
	@Override
	public String updateHeadDep(HttpServletRequest request, String strJson) {
		Map<String, String> paramap = new HashMap<String, String>();
		JSONObject jsonObj = JSONObject.fromObject(strJson);
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		paramap.put("user_mc", (String) request.getSession().getAttribute("userMC"));
		
		String ret = departmentDao.updateHeadDep(paramap);
		
		return ret;
	}
	
	@Override
	public String depComCode(Map paraMap) {
		List<Map> ret = new ArrayList<Map>();
		
		ret = departmentDao.depComCode(paraMap);
		
		JSONArray json = JSONArray.fromObject(ret);
		
		return json.toString();
	}
}
