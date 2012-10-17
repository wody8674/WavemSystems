package com.wavem.convergence.approval.breaks.service;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public interface BreakService {
	public String searchBreak(HttpServletRequest request, Map<String, String> paraMap);
	public String saveBreak(HttpServletRequest request,String strJson);
	public String searchInsertBreak(HttpServletRequest request,	Map<String, String> paraMap);
	public String semisaveBreak(HttpServletRequest request, String strJson);
	public String updateBreak(String jsonData);
	public String updateApproval(HttpServletRequest request, String jsonData);
	public String updateRollback(String jsonData);
	public String updateSRSave(HttpServletRequest request, String jsonData);
}

