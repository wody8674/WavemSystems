package com.wavem.convergence.project.issues.controller;

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
import com.wavem.convergence.project.issues.service.IssuesService;


@Controller
public class IssuesController {
	
	private static final Logger logger = LoggerFactory.getLogger(IssuesController.class);

	@Autowired
	IssuesService issuesService;
	
	@RequestMapping(value="/Issues.do")
	public String loadIssues(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		model.addAttribute("url", "/html/project/issues/html/issues.jsp");
		model.addAttribute("link", "<link rel='stylesheet' type='text/css' href='html/project/project/css/project.css'></link>");
		model.addAttribute("script", "<script type='text/javascript' src='html/project/issues/js/issues.js'></script>");
		
		return "WavemSystem";					
	}
	
	@RequestMapping(value = "/saveIssues.do")
	public String saveIssues(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException  {
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		String jsonData = URLDecoder.decode(request.getParameter("data"), "UTF-8");
		String ret ="";
		
		ret = issuesService.saveissues(jsonData);
		response.getWriter().write(ret);
			
		return null;          
	}
	
	@RequestMapping(value="/searchIssues.do")
	public String searchIssues(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		Map<String, String> paraMap = new HashMap<String, String>();
		
		paraMap.put("search", request.getParameter("search"));
		paraMap.put("search_value", ComProc.urlDecoder(request.getParameter("search_value")));
		
//		System.out.println(request.getParameter("search"));
//		System.out.println(request.getParameter("search_value"));
		
		String strRet = issuesService.searchissues(request, paraMap);
		logger.info(strRet);
		response.getWriter().write(strRet);
		
		return null;						
	}
	
	@RequestMapping(value = "/updateIssues.do")
	public String updateissues(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException  {
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		String jsonData = URLDecoder.decode(request.getParameter("data"), "UTF-8");
		String ret = "";
		
		ret = issuesService.updateissues(jsonData);
		response.getWriter().write(ret);
		
		return null; 
	}
	
}
