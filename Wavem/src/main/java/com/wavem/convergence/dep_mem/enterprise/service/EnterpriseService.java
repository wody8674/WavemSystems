package com.wavem.convergence.dep_mem.enterprise.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface EnterpriseService {
	public String saveEnterprise(String strJson);
	public String searchEnterprise(HttpServletRequest request, Map<String, String> param);
}
