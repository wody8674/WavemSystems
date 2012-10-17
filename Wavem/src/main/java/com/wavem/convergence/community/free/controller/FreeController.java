package com.wavem.convergence.community.free.controller;

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

import com.wavem.convergence.community.free.service.FreeService;



@Controller
public class FreeController {
	
	private static final Logger logger = LoggerFactory.getLogger(FreeController.class);

	@Autowired
	FreeService freeService;
	
	@RequestMapping(value="/Free.do")
	public String loadFree(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		model.addAttribute("url", "/html/community/free/html/free.jsp");
		model.addAttribute("link", "<link rel='stylesheet' type='text/css' href='html/project/project/css/project.css'></link>");
		model.addAttribute("script", "<script type='text/javascript' src='html/community/free/js/free.js'></script>");
		
		return "WavemSystem";							// servlet-context.xml
	}
	
	@RequestMapping(value = "/saveFree.do")
	public String saveFree(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException  {
		
		String jsonData = URLDecoder.decode(request.getParameter("data"), "UTF-8");
		freeService.savefree(jsonData);
			
		return null;          //servlet-context.xml
	}
	
	@RequestMapping(value="/searchFree.do")
	public String searchFree(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		Map<String, String> paraMap = new HashMap<String, String>();
		
		paraMap.put("search", request.getParameter("search"));
		paraMap.put("search_value", request.getParameter("search_value"));
		
		System.out.println(request.getParameter("search"));
		System.out.println(request.getParameter("search_value"));
		
		String strRet = freeService.searchfree(request, paraMap);
		logger.info(strRet);
		response.getWriter().write(strRet);
		
		return null;							// servlet-context.xml
	}
}
