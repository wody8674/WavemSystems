package com.wavem.convergence.dep_mem.department.controller;

import java.io.IOException;
import java.net.URLDecoder;
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
import com.wavem.convergence.dep_mem.department.service.DepartmentService;

@Controller
public class DepartmentController {
	
	private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	
	@Autowired
	DepartmentService departmentService;
	
	@RequestMapping(value="/Department.do")
	public String loadDepartment(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		model.addAttribute("url", "/html/dep_mem/department/html/department.jsp");
		model.addAttribute("link", "<link rel='stylesheet' type='text/css' href='html/dep_mem/department/css/department.css'>");
		model.addAttribute("script", "<script type='text/javascript' src='html/dep_mem/department/js/department.js'></script>");
		
		return "WavemSystem";
	}
	
	@RequestMapping(value="/searchDepartment.do")
	public String searchDepartment(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		Map<String, String> paraMap = new HashMap<String, String>();
		
		paraMap.put("dep_code_value", request.getParameter("dep_code_value"));
		paraMap.put("search_menu", ComProc.urlDecoder(request.getParameter("search_menu")));
		paraMap.put("search_value", ComProc.urlDecoder(request.getParameter("search_value")));
		paraMap.put("page", ComProc.urlDecoder(request.getParameter("page")));
		
		String strRet = departmentService.searchDepartment(request, paraMap);
		
		logger.info(strRet);
		response.getWriter().write(strRet);
		
		return null;							// servlet-context.xml
	}
	
	@RequestMapping(value = "/saveDepartment.do")
	public String saveDepartment(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException  {
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		String jsonData = URLDecoder.decode(request.getParameter("data"), "UTF-8");
		String ret = "";
		ret = departmentService.saveDepartment(request, jsonData);
		response.getWriter().write(ret);
		
		return null;          //servlet-context.xml
	}
	
	@RequestMapping(value = "/updateDepartment.do")
	public String updateDepartment(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException  {
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		String jsonData = URLDecoder.decode(request.getParameter("data"), "UTF-8");
		String ret = "";
		ret = departmentService.updateDepartment(request, jsonData);
		response.getWriter().write(ret);
		
		return null;          //servlet-context.xml
	}
	
	@RequestMapping(value = "/updateHeadDep.do")
	public String updateHeadDep(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException  {
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		String jsonData = URLDecoder.decode(request.getParameter("data"), "UTF-8");
		String ret = "";
		ret = departmentService.updateHeadDep(request, jsonData);
		response.getWriter().write(ret);
		
		return null;          //servlet-context.xml
	}
	
	//공통코드
	@RequestMapping(value="/depComCode.do")
	public String searchComCode(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		String selCd = request.getParameter("SEL_CD");
		String para1 = request.getParameter("param1");
		
		Map paraMap = new HashMap();
		
		paraMap.put("SEL_CD", selCd);
		paraMap.put("param1", para1);
		
		String strRet = departmentService.depComCode(paraMap);
		
		logger.info(strRet);
		response.getWriter().write(strRet);
		
		return null;							// servlet-context.xml
	}
	
}
