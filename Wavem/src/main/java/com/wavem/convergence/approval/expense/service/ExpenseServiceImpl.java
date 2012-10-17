package com.wavem.convergence.approval.expense.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavem.convergence.approval.expense.dao.ExpenseDao;
import com.wavem.convergence.common.ComProc;

@Service("expenseService")
public class ExpenseServiceImpl implements ExpenseService {
	
	@Autowired
	ExpenseDao expenseDao;
	
	public String searchExpense(HttpServletRequest request, Map<String, String> paraMap) {
	
		List<Map> ret = new ArrayList<Map>();
		ret = expenseDao.searchExpense(paraMap);
		
		JSONArray json = JSONArray.fromObject(ret);
		
		return json.toString();
	}
	
	@Override
	public String searchInsertExpense(HttpServletRequest request, Map<String, String> paraMap) {
		List<Map> ret = new ArrayList<Map>();
		ret = expenseDao.searchInsertExpense(paraMap);
		
		System.out.println("service ret==" + ret);
		
		JSONArray json = JSONArray.fromObject(ret);
		
		return json.toString();
	}


	@Override
	public String saveExpense(HttpServletRequest request, String strJson) {
		
		Map<String, String> paramap = new HashMap<String, String>();
		JSONObject jsonObj = JSONObject.fromObject(strJson);
		String ret="";
		String btnFlg = ComProc.urlDecoder(request.getParameter("btn_flag"));
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		paramap.put("MEM_CODE", (String) request.getSession().getAttribute("userMC"));
		paramap.put("btn_flag", btnFlg);
		
		System.out.println("paramap ==" +paramap);
		ret = expenseDao.saveExpense(paramap);
		
		return ret;
	}
	
	@Override
	public String semisaveExpense(HttpServletRequest request, String strJson) {
		
		Map<String, String> paramap = new HashMap<String, String>();
		JSONObject jsonObj = JSONObject.fromObject(strJson);
		String ret = "";
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		paramap.put("MEM_CODE", (String) request.getSession().getAttribute("userMC"));
		ret = expenseDao.semisaveExpense(paramap);

		return ret;
	}

	@Override
	public String updateExpense(String jsonData) {
		Map<String, String> paramap = new HashMap<String, String>();
		JSONObject jsonObj = JSONObject.fromObject(jsonData);
		String ret = "";
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		ret = expenseDao.updateExpense(paramap);
		return ret;
	}



}
