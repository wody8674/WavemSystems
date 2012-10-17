package com.wavem.convergence.dep_mem_search.department.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavem.convergence.dep_mem_search.department.dao.SearchDepartmentDao;

@Service("searchDepartmentService")
public class SearchDepartmentServiceImpl implements SearchDepartmentService{

	@Autowired
	SearchDepartmentDao searchDepartmentDao;
	
	public String searchDepartment(HttpServletRequest request, Map<String, String> param) {
		List<Map> ret = new ArrayList<Map>();
		
		ret = searchDepartmentDao.searchDepartment(param);
		
		JSONArray json = JSONArray.fromObject(ret);
		
		return json.toString();
	}
}
