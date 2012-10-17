package com.wavem.convergence.community.suggest.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface SuggestService {

	public String searchsuggest(HttpServletRequest request, Map<String, String> paraMap);
	public String savesuggest(String strJson);
}
