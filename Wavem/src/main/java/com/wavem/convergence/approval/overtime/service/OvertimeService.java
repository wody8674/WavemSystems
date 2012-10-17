package com.wavem.convergence.approval.overtime.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface OvertimeService {
	public String searchOvertime(HttpServletRequest request, Map<String, String> paraMap);
	public String searchInsertOvertime(HttpServletRequest request, Map<String, String> paraMap);
	public String saveOvertime(HttpServletRequest request, String strJson);
	public String semisaveOvertime(HttpServletRequest request, String strJson);
	public String updateOvertime(String jsonData);
}
