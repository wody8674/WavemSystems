package com.wavem.convergence.community.notice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavem.convergence.community.notice.dao.NoticeDao;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	NoticeDao noticeDao;
	
	@Override
	public String searchGubun_Dep(HttpServletRequest request) {
		
		List<Map> ret = new ArrayList<Map>();
		
		ret = noticeDao.searchGubun_Dep();
		
		JSONArray json = JSONArray.fromObject(ret);
		
		return json.toString();
	}
	
	@Override
	public String searchGubun_Pro(HttpServletRequest request) {
		
		List<Map> ret = new ArrayList<Map>();
		
		ret = noticeDao.searchGubun_Pro();
		
		JSONArray json = JSONArray.fromObject(ret);
		
		return json.toString();
	}
	
	@Override
	public String saveNoticeDetail(HttpServletRequest request, String strJson) {
		
		Map<String, String> paramap = new HashMap<String, String>();
		
		JSONObject jsonObj = JSONObject.fromObject(strJson);
		
		String ret = "";
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		paramap.put("user_mc", (String)request.getSession().getAttribute("userMC"));
		
		ret = noticeDao.saveNoticeDetail(paramap);
		
		return ret;
	}
	
	@Override
	public String updateNoticeDetail(HttpServletRequest request, String strJson) {
		
		Map<String, String> paramap = new HashMap<String, String>();
		
		JSONObject jsonObj = JSONObject.fromObject(strJson);
		
		String ret = "";
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		paramap.put("user_mc", (String)request.getSession().getAttribute("userMC"));
		
		ret = noticeDao.updateNoticeDetail(paramap);
		
		return ret;
	}
	
	@Override
	public String searchNotice(HttpServletRequest request, Map<String, String> paraMap) {
		
		List<Map> ret = new ArrayList<Map>();
		
		ret = noticeDao.searchNotice(paraMap);
		
		JSONArray json = JSONArray.fromObject(ret);
		
		return json.toString();
	}
}