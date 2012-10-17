package com.wavem.convergence.dep_mem.member.service;

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

import com.wavem.convergence.dep_mem.member.dao.MemberDao;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDao memberDao;

	
	@Override
	public String searchMember(HttpServletRequest request, Map<String, String> param) {
		
		List<Map> ret = new ArrayList<Map>();
		
		ret = memberDao.searchMember(param);
		
		JSONArray json = JSONArray.fromObject(ret);
		
		return json.toString();
	}

	
	@Override
	public String saveMember(HttpServletRequest request, String strJson) {
		
		Map<String, String> paramap = new HashMap<String, String>();
		JSONObject jsonObj = JSONObject.fromObject(strJson);
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		paramap.put("user_mc", (String)request.getSession().getAttribute("userMC"));
		
		String ret = memberDao.saveMember(paramap);
		
		return ret;
	}


	@Override
	public String updateMember(HttpServletRequest request, String strJson) {
		Map<String, String> paramap = new HashMap<String, String>();
		JSONObject jsonObj = JSONObject.fromObject(strJson);
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		paramap.put("user_mc", (String)request.getSession().getAttribute("userMC"));
		
		String ret = memberDao.updateMember(paramap);
		
		return ret;
	}
	
	@Override
	public String memComCode(Map paraMap) {
		List<Map> ret = new ArrayList<Map>();
		
		ret = memberDao.memComCode(paraMap);
		
		JSONArray json = JSONArray.fromObject(ret);
		
		return json.toString();
	}
}
