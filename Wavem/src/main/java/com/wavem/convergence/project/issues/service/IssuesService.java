package com.wavem.convergence.project.issues.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface IssuesService {

	public String searchissues(HttpServletRequest request, Map<String, String> paraMap);
	public String saveissues(String strJson);
	public String updateissues(String strJson);
}
