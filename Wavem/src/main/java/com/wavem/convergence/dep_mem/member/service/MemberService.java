package com.wavem.convergence.dep_mem.member.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface MemberService {
	public String searchMember(HttpServletRequest request, Map<String, String> param);
	public String saveMember(HttpServletRequest request, String strJson);
	public String updateMember(HttpServletRequest request, String strJson);
	public String memComCode(Map paraMap);
}
