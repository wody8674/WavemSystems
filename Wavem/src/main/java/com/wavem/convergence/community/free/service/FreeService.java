package com.wavem.convergence.community.free.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface FreeService {

	public String searchfree(HttpServletRequest request, Map<String, String> paraMap);
	public String savefree(String strJson);
}
