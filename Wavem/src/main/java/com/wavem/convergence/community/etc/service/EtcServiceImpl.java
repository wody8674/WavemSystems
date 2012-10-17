package com.wavem.convergence.community.etc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavem.convergence.community.etc.dao.EtcDao;

@Service("etcService")
public class EtcServiceImpl  implements EtcService{

	@Autowired
	EtcDao etcDao;
	
	@Override
	public String saveEtcDetail(HttpServletRequest request, String strJson) {
		
		Map<String, String> paramap = new HashMap<String, String>();
		
		JSONObject jsonObj = JSONObject.fromObject(strJson);
		
		String ret = "";
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		paramap.put("user_mc", (String)request.getSession().getAttribute("userMC"));
		
		ret = etcDao.saveEtcDetail(paramap);
		
		return ret;
	}
	
	@Override
	public String updateEtcDetail(HttpServletRequest request, String strJson) {
		
		Map<String, String> paramap = new HashMap<String, String>();
		
		JSONObject jsonObj = JSONObject.fromObject(strJson);
		
		String ret = "";
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		paramap.put("user_mc", (String)request.getSession().getAttribute("userMC"));
		
		ret = etcDao.updateEtcDetail(paramap);
		
		return ret;
	}
	
	@Override
	public String searchEtc(HttpServletRequest request, Map<String, String> paraMap) {
		
		List<Map> ret = new ArrayList<Map>();
		
		ret = etcDao.searchEtc(paraMap);
		
		JSONArray json = JSONArray.fromObject(ret);
		
		return json.toString();
	}
}
