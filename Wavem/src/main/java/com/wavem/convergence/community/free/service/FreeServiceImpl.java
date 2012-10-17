package com.wavem.convergence.community.free.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavem.convergence.community.free.dao.FreeDao;

@Service("freeService")
public class FreeServiceImpl  implements FreeService{

	@Autowired
	FreeDao freeDao;
	
	@Override
	public String searchfree(HttpServletRequest request, Map<String, String> paraMap) {
	
		List<Map> ret = new ArrayList<Map>();
		
		ret = freeDao.searchfree(paraMap);
		
		JSONArray json = JSONArray.fromObject(ret);
		
		return json.toString();
	}

	@Override
	public String savefree(String strJson) {
		
		Map<String, String> paramap = new HashMap<String, String>();
		JSONObject jsonObj = JSONObject.fromObject(strJson);
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		freeDao.savefree(paramap);
		
		return null;
	}
}