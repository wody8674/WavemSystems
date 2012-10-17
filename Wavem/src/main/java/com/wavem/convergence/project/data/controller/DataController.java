package com.wavem.convergence.project.data.controller;

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
import com.wavem.convergence.project.data.service.DataService;


@Controller
public class DataController {
	
	private static final Logger logger = LoggerFactory.getLogger(DataController.class);

	@Autowired
	DataService dataService;
	
	@RequestMapping(value="/Data.do")
	public String loadData(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		model.addAttribute("url", "/html/project/data/html/data.jsp");
		model.addAttribute("link", "<link rel='stylesheet' type='text/css' href='html/project/project/css/project.css'></link>");
		model.addAttribute("script", "<script type='text/javascript' src='html/project/data/js/data.js'></script>");
		
		return "WavemSystem";							// servlet-context.xml
	}
	
	@RequestMapping(value = "/saveData.do")
	public String savedata(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException  {
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		String jsonData = URLDecoder.decode(request.getParameter("data"), "UTF-8");
		String ret = "";
		
		ret = dataService.savedata(jsonData);
		response.getWriter().write(ret);
		
		return null;          //servlet-context.xml
	}
	
	@RequestMapping(value="/searchData.do")
	public String searchData(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		Map<String, String> paraMap = new HashMap<String, String>();
		
		paraMap.put("search", request.getParameter("search"));
		paraMap.put("search_value", ComProc.urlDecoder(request.getParameter("search_value")));
		
		String strRet = dataService.searchdata(request, paraMap);
		logger.info(strRet);
		response.getWriter().write(strRet);
		
		return null;							// servlet-context.xml
	}
	
	@RequestMapping(value = "/updateData.do")
	public String updateissues(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException  {
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		String jsonData = URLDecoder.decode(request.getParameter("data"), "UTF-8");
		String ret = "";
		
		ret = dataService.updatedata(jsonData);
		response.getWriter().write(ret);
		
		return null; 
	}
	
}
