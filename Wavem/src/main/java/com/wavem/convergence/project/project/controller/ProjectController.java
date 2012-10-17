package com.wavem.convergence.project.project.controller;

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
import com.wavem.convergence.project.project.service.ProjectService;


@Controller
public class ProjectController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

	@Autowired
	ProjectService projectService;
	
	@RequestMapping(value="/Project.do")
	public String loadProject(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		model.addAttribute("url", "/html/project/project/html/project.jsp");
		model.addAttribute("link", "<link rel='stylesheet' type='text/css' href='html/project/project/css/project.css'></link>");
		model.addAttribute("script", "<script type='text/javascript' src='html/project/project/js/project.js'></script>");
	
		return "WavemSystem";							// servlet-context.xml
	}
	
	@RequestMapping(value="/searchProject.do")
	public String searchProject(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		Map<String, String> paraMap = new HashMap<String, String>();

		paraMap.put("dep_code_value", ComProc.urlDecoder(request.getParameter("dep_code_value")));
		paraMap.put("startDate", ComProc.urlDecoder(request.getParameter("startDate")));
		paraMap.put("endDate", ComProc.urlDecoder(request.getParameter("endDate")));
		paraMap.put("search", request.getParameter("search"));
		paraMap.put("search_value", ComProc.urlDecoder(request.getParameter("search_value")));
		paraMap.put("page", ComProc.urlDecoder(request.getParameter("page")));
		
//		System.out.println(paraMap);
		
		String strRet = projectService.searchproject(request, paraMap);
		logger.info(strRet);
		response.getWriter().write(strRet);
		
		return null;							// servlet-context.xml
	}

	@RequestMapping(value="/searchProjectPart.do")
	public String searchProjectPart(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		// 1_ 파라미터 값 받아오기
		String proCode = request.getParameter("proCode");
		
		// 2_ 파라미터 맵으로 생성
		Map<String, String> parMap = new HashMap<String, String>();
		parMap.put("ibatis_proCode", proCode);
		
		// 3_ 서비스에 요청 
		String retJsonData = projectService.searchProjectPart(parMap);
		
		// 4_ 응답 처리
		response.getWriter().write(retJsonData);
		
		return null;							// servlet-context.xml
	}
	
	@RequestMapping(value = "/saveProject.do")
	public String saveProject(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException  {
		
		String jsonData = URLDecoder.decode(request.getParameter("data"), "UTF-8");
		String jsonData2 = URLDecoder.decode(request.getParameter("part"), "UTF-8");
		
		System.out.println("jsonData = "+jsonData);
		System.out.println("jsonData2 = "+jsonData2);
		
		String ret = projectService.saveproject(jsonData,jsonData2);
		
		response.getWriter().write(ret);
		
		return null;          //servlet-context.xml
	}
	
	@RequestMapping(value = "/updateProject.do")
	public String updateProject(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException  {
		
		String jsonData = URLDecoder.decode(request.getParameter("data"), "UTF-8");
		String jsonData2 = URLDecoder.decode(request.getParameter("part"), "UTF-8");
		
		System.out.println("jsonData = "+jsonData);
		System.out.println("jsonData2 = "+jsonData2);
		
		String ret = projectService.updateProject(jsonData,jsonData2);
		
		response.getWriter().write(ret);
		
		return null;          //servlet-context.xml
	}
}
















































