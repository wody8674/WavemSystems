package com.wavem.convergence.dep_mem.equipment.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavem.convergence.dep_mem.equipment.dao.EquipmentDao;

@Service("equipmentService")
public class EquipmentServiceImpl implements EquipmentService {

	@Autowired
	EquipmentDao equipmentDao;
	
	@Override
	public String searchEquipment(HttpServletRequest request,	Map<String, String> param) {
		List<Map> ret = new ArrayList<Map>();
		
		ret = equipmentDao.searchEquipment(param);
		
		JSONArray json = JSONArray.fromObject(ret);
		
		return json.toString();
	}

	
	@Override
	public String saveEquipment(HttpServletRequest request, String strJson) {
		
		Map<String, String> paramap = new HashMap<String, String>();
		JSONObject jsonObj = JSONObject.fromObject(strJson);
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		paramap.put("user_mc", (String)request.getSession().getAttribute("userMC"));
		
		String ret = equipmentDao.saveEquipment(paramap);
		
		return ret;
	}


	@Override
	public String updateEquipment(HttpServletRequest request, String strJson) {
		Map<String, String> paramap = new HashMap<String, String>();
		JSONObject jsonObj = JSONObject.fromObject(strJson);
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		paramap.put("user_mc", (String)request.getSession().getAttribute("userMC"));
		
		String ret = equipmentDao.updateEquipment(paramap);
		
		return ret;
	}
}
