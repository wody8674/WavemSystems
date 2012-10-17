package com.wavem.convergence.project.data.service;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public interface DataService {

	public String searchdata(HttpServletRequest request, Map<String, String> paraMap);
	public String savedata(String strJson);
	public String updatedata(String strJson);

}
