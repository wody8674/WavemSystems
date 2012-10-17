package com.wavem.convergence.common.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface CommonService {
	public String selectMenuHtml(HttpServletRequest request, Map paraMap) throws Exception;
}
