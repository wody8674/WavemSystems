package com.wavem.convergence.approval.breaks.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavem.convergence.approval.breaks.dao.BreakDao;
import com.wavem.convergence.approval.breaks.vo.BreakVo;
import com.wavem.convergence.approval.breaks.service.BreakService;
import com.wavem.convergence.common.ComProc;

@Service("breakService")
public class BreakServiceImpl implements BreakService {
	
	@Autowired
	BreakDao breakDao;
	
	public String searchBreak(HttpServletRequest request, Map<String, String> paraMap) {
	
		List<Map> ret = new ArrayList<Map>();
		ret = breakDao.searchBreak(paraMap);
		
		JSONArray json = JSONArray.fromObject(ret);
		
		return json.toString();
	}

	@Override
	public String searchInsertBreak(HttpServletRequest request,	Map<String, String> paraMap) {
		List<Map> ret = new ArrayList<Map>();
		ret = breakDao.searchInsertBreak(paraMap);
		
		System.out.println("service ret==" + ret);
		
		JSONArray json = JSONArray.fromObject(ret);
		
		return json.toString();
	}

	@Override
	public String saveBreak(HttpServletRequest request, String strJson) {
		
		Map<String, String> paramap = new HashMap<String, String>();
		JSONObject jsonObj = JSONObject.fromObject(strJson);
		String ret = "";
		String btnFlg = ComProc.urlDecoder(request.getParameter("btn_flag"));
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		paramap.put("MEM_CODE", (String) request.getSession().getAttribute("userMC"));
		paramap.put("btn_flag", btnFlg);
		
		System.out.println("paramap ==" +paramap);
		ret = breakDao.saveBreak(paramap);

		return ret;
	}
	
	@Override
	public String semisaveBreak(HttpServletRequest request, String strJson) {
		
		Map<String, String> paramap = new HashMap<String, String>();
		JSONObject jsonObj = JSONObject.fromObject(strJson);
		String ret = "";
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		paramap.put("MEM_CODE", (String) request.getSession().getAttribute("userMC"));
		ret = breakDao.semisaveBreak(paramap);

		return ret;
	}

	@Override
	public String updateBreak(String jsonData) {
		Map<String, String> paramap = new HashMap<String, String>();
		JSONObject jsonObj = JSONObject.fromObject(jsonData);
		String ret = "";
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		ret = breakDao.updateBreak(paramap);
		return ret;
	}

	@Override
	public String updateApproval(HttpServletRequest request, String jsonData) {
		Map<String, String> paramap = new HashMap<String, String>();
		JSONObject jsonObj = JSONObject.fromObject(jsonData);
		String ret = "";
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		paramap.put("APP_STATE_VALUE", (String) request.getParameter("key4"));
		ret = breakDao.updateApproval(paramap);
		return ret;
	}

	@Override
	public String updateRollback(String jsonData) {
		Map<String, String> paramap = new HashMap<String, String>();
		JSONObject jsonObj = JSONObject.fromObject(jsonData);
		String ret = "";
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		ret = breakDao.updateRollback(paramap);
		return ret;
	}

	@Override
	public String updateSRSave(HttpServletRequest request, String jsonData) {
		Map<String, String> paramap = new HashMap<String, String>();
		JSONObject jsonObj = JSONObject.fromObject(jsonData);
		String ret = "";
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		paramap.put("APP_STATE_VALUE", (String) request.getParameter("key4"));
		ret = breakDao.updateSRSave(paramap);
		return ret;
	}
}
