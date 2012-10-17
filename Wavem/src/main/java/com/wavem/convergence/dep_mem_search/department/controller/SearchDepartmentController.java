package com.wavem.convergence.dep_mem_search.department.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wavem.convergence.common.ComProc;
import com.wavem.convergence.dep_mem_search.department.service.SearchDepartmentService;

@Controller
public class SearchDepartmentController {

	private static final Logger logger = LoggerFactory.getLogger(SearchDepartmentController.class);
	
	@Autowired
	SearchDepartmentService searchDepartmentService;
	
	@RequestMapping(value="/SearchDepartment.do")
	public String loadDepartment(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		model.addAttribute("url", "/html/dep_mem_search/department/html/searchDepartment.jsp");
		model.addAttribute("link", "<link rel='stylesheet' type='text/css' href='html/dep_mem_search/department/css/searchDepartment.css'>");
		model.addAttribute("script", "<script type='text/javascript' src='html/dep_mem_search/department/js/searchDepartment.js'></script>");
		
		return "WavemSystem";
	}
	
	@RequestMapping(value="/searchDepartmentInfo.do")
	public String searchDepartment(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		Map<String, String> paraMap = new HashMap<String, String>();
		
		paraMap.put("dep_code_value", request.getParameter("dep_code_value"));
		paraMap.put("search_menu", ComProc.urlDecoder(request.getParameter("search_menu")));
		paraMap.put("search_value", ComProc.urlDecoder(request.getParameter("search_value")));
		
		String strRet = searchDepartmentService.searchDepartment(request, paraMap);
		
		logger.info(strRet);
		response.getWriter().write(strRet);
		
		return null;							// servlet-context.xml
	}
}
