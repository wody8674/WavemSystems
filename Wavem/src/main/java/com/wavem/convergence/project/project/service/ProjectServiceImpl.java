package com.wavem.convergence.project.project.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wavem.convergence.common.MultiInsertCtrl;
import com.wavem.convergence.project.project.dao.ProjectDao;
import com.wavem.convergence.project.project.service.ProjectService;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	ProjectDao projectDao;
	
	@Override
	public String searchproject(HttpServletRequest request, Map<String, String> paraMap) {
		
		List<Map> ret = new ArrayList<Map>();
		
		paraMap.put("user_mc", (String) request.getSession().getAttribute("userMC"));
		ret = projectDao.searchproject(paraMap);
		
		JSONArray json = JSONArray.fromObject(ret);
		
		return json.toString();
	}
	
	@Override
	public String saveproject(String strJson, String strJson2) {
		
		Map<String, String> paramap = new HashMap<String, String>();
		Map paramap2 = new HashMap();
		List<Map<String, String>> paraList = new ArrayList<Map<String, String>>();
		MultiInsertCtrl mic = new MultiInsertCtrl();
		
		JSONObject jsonObj = JSONObject.fromObject(strJson);
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		System.out.println(strJson2);
		if (strJson2.length() > 0) {
			JSONObject jsonObj2 = JSONObject.fromObject(strJson2);
			paramap2 = (Map) JSONObject.toBean(jsonObj2, java.util.HashMap.class, paramap2);
			
			paraList = mic.getDataListFormJsonMap(paramap2);
		}
		
		String ret = projectDao.saveproject(paramap, paraList);
		
		return ret;
	}
	
	@Override
	public String searchProjectPart(Map<String, String> parMap) {
		
		// 1_ 리스트 객체 생성
		List<Map> ret = new ArrayList<Map>();
		
		// 2_ Dao에 요청
		ret = projectDao.searchProjectPart(parMap);
		
		// 3_ 조회 결과값을 Json으로 변환
		JSONArray jsonArry = JSONArray.fromObject(ret);
		
		return jsonArry.toString();
	}
	
	@Override
	public String updateProject(String strJson, String strJson2) {
		
		Map<String, String> paramap = new HashMap<String, String>();
		Map paramap2 = new HashMap();
		List<Map<String, String>> paraList = new ArrayList<Map<String, String>>();
		MultiInsertCtrl mic = new MultiInsertCtrl();
		
		JSONObject jsonObj = JSONObject.fromObject(strJson);
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		
		if (strJson2.length() > 0) {
			JSONObject jsonObj2 = JSONObject.fromObject(strJson2);
			paramap2 = (Map) JSONObject.toBean(jsonObj2, java.util.HashMap.class, paramap2);
			System.out.println(paramap2);
			paraList = mic.getDataListFormJsonMap(paramap2);
		}
		
		String ret = projectDao.updateProject(paramap, paraList);
		
		return ret;
	}
	
}
