package com.wavem.convergence.mypage.controller;

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

import com.wavem.convergence.mypage.service.MypageService;

@Controller
public class MypageController {

private static final Logger logger = LoggerFactory.getLogger(MypageController.class);
	
	@Autowired
	MypageService mypageService;
	
	@RequestMapping(value = "/Mypage.do")
	public String loadMypage(HttpServletRequest request, HttpServletResponse response, Model model)throws IOException{
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		model.addAttribute("url", "/html/mypage/html/mypage.jsp");
		model.addAttribute("link", "<link rel='stylesheet' type='text/css' href='html/mypage/css/mypage.css'/>");
		model.addAttribute("script", "<script type='text/javascript' src='html/mypage/js/mypage.js'></script>");
		
		Map<String, String> paraMap = new HashMap<String, String>();
				
		return "WSMainView";
	}
	
	@RequestMapping(value = "/searchMypage.do")
	public String searchMypage(HttpServletRequest request, HttpServletResponse response, Model model)throws IOException{
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		Map<String, String> paraMap = new HashMap<String, String>();
		
		paraMap.put("memCode", request.getParameter("key"));
		
		String ret = mypageService.searchMypage(request, paraMap);
		
		logger.info(ret);
		response.getWriter().write(ret);
		
		return null;
	}
	
}
