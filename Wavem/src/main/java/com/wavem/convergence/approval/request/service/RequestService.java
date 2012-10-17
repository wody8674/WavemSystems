package com.wavem.convergence.approval.request.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface RequestService {

	public String searchRequest(HttpServletRequest request, Map<String, String> paraMap);
	public String saveRequest(HttpServletRequest request, String jsonData);
	public String searchInsertRequest(HttpServletRequest request,	Map<String, String> paraMap);
	public String semisaveRequest(HttpServletRequest request, String strJson);
	public String updateRequest(String jsonData);
}
