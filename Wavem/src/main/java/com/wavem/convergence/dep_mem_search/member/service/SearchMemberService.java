package com.wavem.convergence.dep_mem_search.member.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface SearchMemberService {
	public String searchMember(HttpServletRequest request, Map<String, String> param);
}
