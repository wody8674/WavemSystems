package com.wavem.convergence.project.project.service;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public interface ProjectService {
	
	public String searchproject(HttpServletRequest request, Map<String, String> paraMap);
	
	public String saveproject(String strJson, String strJson2);
	
	public String searchProjectPart(Map<String, String> parMap);
	
	public String updateProject(String strJson, String strJson2);
}
