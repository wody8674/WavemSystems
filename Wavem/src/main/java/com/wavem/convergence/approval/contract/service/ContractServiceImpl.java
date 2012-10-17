package com.wavem.convergence.approval.contract.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavem.convergence.approval.contract.dao.ContractDao;

@Service("contractService")
public class ContractServiceImpl implements ContractService {
	
	@Autowired
	ContractDao contractDao;

	@Override
	public String searchContract(HttpServletRequest request, Map<String, String> paraMap) {
		
		List<Map> ret = new ArrayList<Map>();
		ret = contractDao.searchContract(paraMap);
		
		JSONArray json = JSONArray.fromObject(ret);
		
		return json.toString();
	}

	@Override
	public String saveContract(String jsonData) {
		
		Map<String, String> paramap = new HashMap<String, String>();
		JSONObject jsonObj = JSONObject.fromObject(jsonData);
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		System.out.println(paramap);
		contractDao.saveContract(paramap);
		
		return null;
	}

}
