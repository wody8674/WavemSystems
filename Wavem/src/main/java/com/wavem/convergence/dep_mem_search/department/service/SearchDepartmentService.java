package com.wavem.convergence.dep_mem_search.department.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface SearchDepartmentService {
	public String searchDepartment(HttpServletRequest request, Map<String, String> param);
}
