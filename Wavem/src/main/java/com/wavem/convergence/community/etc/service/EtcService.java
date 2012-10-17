package com.wavem.convergence.community.etc.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface EtcService {
	
	public String saveEtcDetail(HttpServletRequest request, String strJson);
	
	public String updateEtcDetail(HttpServletRequest request, String strJson);
	
	public String searchEtc(HttpServletRequest request, Map<String, String> paraMap);
}
