package com.wavem.convergence.main.service;

import java.util.Map;

public interface AjaxService {
	public String selectParam(Map param) throws Exception;
	public String selectJson(Map<String, String> param) throws Exception;
	public String saveJson(String param) throws Exception;
}
