package com.wavem.convergence.dep_mem.department.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface DepartmentService {
	public String searchDepartment(HttpServletRequest request, Map<String, String> param);
	
	public String saveDepartment(HttpServletRequest request, String strJson);
	
	public String updateDepartment(HttpServletRequest request, String strJson);
	
	public String updateHeadDep(HttpServletRequest request, String strJson);
	
	public String depComCode(Map paraMap);
}
