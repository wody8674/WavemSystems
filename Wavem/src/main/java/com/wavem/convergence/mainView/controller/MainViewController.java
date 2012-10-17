package com.wavem.convergence.mainView.controller;

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

import com.wavem.convergence.mainView.service.MainViewService;

@Controller
public class MainViewController {
	private static final Logger logger = LoggerFactory.getLogger(MainViewController.class);
	
	@Autowired
	MainViewService mainViewService;
	
	@RequestMapping(value = "/mainView.do")
	public String selectParam(HttpServletRequest request, HttpServletResponse response, Model model)throws IOException{
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		model.addAttribute("url", "/html/mainView/html/mainView.jsp");
		model.addAttribute("link", "<link rel='stylesheet' type='text/css' href='html/mainView/css/mainView.css'/>");
		model.addAttribute("script", "<script type='text/javascript' src='html/mainView/js/mainView.js'></script>");
		
		Map<String, String> paraMap = new HashMap<String, String>();
				
		return "WSMainView";
	}
	
	@RequestMapping(value = "/selectMain.do")
	public String selectMain(HttpServletRequest request, HttpServletResponse response, Model model)throws IOException{
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("sel_cd", request.getParameter("sel_cd"));
		paraMap.put("user_mc", (String) request.getSession().getAttribute("userMC"));
		paraMap.put("user_dc", (String) request.getSession().getAttribute("userDC"));
		
		String ret = mainViewService.selectMain(paraMap);
		
		logger.debug(ret);
		response.getWriter().write(ret);
		
		return null;
	}

}
