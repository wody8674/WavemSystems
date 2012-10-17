package com.wavem.convergence.login.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {
	public String login(HttpServletRequest request, Map<String, String> paraMap);
	public String sessionInfo(HttpServletRequest request);
}
