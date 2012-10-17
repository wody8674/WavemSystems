package com.wavem.convergence.dep_mem.enterprise.controller;

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

import com.wavem.convergence.dep_mem.enterprise.service.EnterpriseService;

@Controller
public class EnterpriseController {
	private static final Logger logger = LoggerFactory.getLogger(EnterpriseController.class);

	@Autowired
	EnterpriseService enterpriseService;
	
	@RequestMapping(value="/Enterprise.do")
	public String loadEnterprise(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		model.addAttribute("url", "/html/dep_mem/enterprise/html/enterprise.jsp");
		model.addAttribute("link", "<link rel='stylesheet' type='text/css' href='html/dep_mem/enterprise/css/enterprise.css'>");
		model.addAttribute("script", "<script type='text/javascript' src='html/dep_mem/enterprise/js/enterprise.js'></script>");
		
		return "WavemSystem";							// servlet-context.xml
	}
	
	@RequestMapping(value="/searchEnterprise.do")
	public String searchEnterprise(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		Map<String, String> paraMap = new HashMap<String, String>();
		
		String strRet = enterpriseService.searchEnterprise(request, paraMap);
		logger.info(strRet);
		response.getWriter().write(strRet);
		
		return null;							// servlet-context.xml
	}
	
	@RequestMapping(value = "/saveEnterprise.do")
	public String saveEnterprise(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException  {
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		String jsonData = URLDecoder.decode(request.getParameter("data"), "UTF-8");
		String ret = "";
		ret = enterpriseService.saveEnterprise(jsonData);
		response.getWriter().write(ret);
		
		return null;          //servlet-context.xml
	}
	
	
}
