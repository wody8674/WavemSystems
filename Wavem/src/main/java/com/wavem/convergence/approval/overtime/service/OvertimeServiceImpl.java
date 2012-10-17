package com.wavem.convergence.approval.overtime.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavem.convergence.approval.overtime.dao.OvertimeDao;
import com.wavem.convergence.common.ComProc;

@Service("overtimeService")
public class OvertimeServiceImpl implements OvertimeService {
	
	@Autowired
	OvertimeDao overtimeDao;

	@Override
	public String searchOvertime(HttpServletRequest request, Map<String, String> paraMap) {
		List<Map> ret = new ArrayList<Map>();
		ret = overtimeDao.searchOvertime(paraMap);
		
		JSONArray json = JSONArray.fromObject(ret);
		
		return json.toString();
	}

	@Override
	public String saveOvertime(HttpServletRequest request, String strJson) {
		
		Map<String, String> paramap = new HashMap<String, String>();
		JSONObject jsonObj = JSONObject.fromObject(strJson);
		String ret = "";
		
		String btnFlg = ComProc.urlDecoder(request.getParameter("btn_flag"));
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		paramap.put("MEM_CODE", (String) request.getSession().getAttribute("userMC"));
		paramap.put("btn_flag", btnFlg);
		System.out.println("paramap ==" +paramap);
		
		ret = overtimeDao.saveOvertime(paramap);
		return ret;
	}

	@Override
	public String searchInsertOvertime(HttpServletRequest request, Map<String, String> paraMap) {
		List<Map> ret = new ArrayList<Map>();
		ret = overtimeDao.searchInsertOvertime(paraMap);
		
		System.out.println("service ret==" + ret);
		
		JSONArray json = JSONArray.fromObject(ret);
		
		return json.toString();
	}
	
	
	@Override
	public String semisaveOvertime(HttpServletRequest request, String strJson) {
		
		Map<String, String> paramap = new HashMap<String, String>();
		JSONObject jsonObj = JSONObject.fromObject(strJson);
		String ret = "";
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		paramap.put("MEM_CODE", (String) request.getSession().getAttribute("userMC"));
		ret = overtimeDao.semisaveOvertime(paramap);

		return ret;
	}

	@Override
	public String updateOvertime(String jsonData) {
		Map<String, String> paramap = new HashMap<String, String>();
		JSONObject jsonObj = JSONObject.fromObject(jsonData);
		String ret = "";
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		ret = overtimeDao.updateOvertime(paramap);
		return ret;
	}

}
