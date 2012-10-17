package com.wavem.convergence.dep_mem_search.member.controller;

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
import com.wavem.convergence.dep_mem_search.member.service.SearchMemberService;

@Controller
public class SearchMemberController {

	private static final Logger logger = LoggerFactory.getLogger(SearchMemberController.class);
	
	@Autowired
	SearchMemberService searchMemberService;
		
	@RequestMapping(value="/SearchMember.do")
	public String loadMember(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		model.addAttribute("url", "/html/dep_mem_search/member/html/searchMember.jsp");
		model.addAttribute("link", "<link rel='stylesheet' type='text/css' href='html/dep_mem_search/member/css/searchMember.css'>");
		model.addAttribute("script", "<script type='text/javascript' src='html/dep_mem_search/member/js/searchMember.js'></script>");
		
		return "WavemSystem";							// servlet-context.xml
	}
	
	@RequestMapping(value="/searchMemberInfo.do")
	public String searchMember(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		Map<String, String> paraMap = new HashMap<String, String>();
		
		paraMap.put("mem_post", request.getParameter("mem_post"));
		paraMap.put("con_type", request.getParameter("con_type"));
		paraMap.put("search_menu", ComProc.urlDecoder(request.getParameter("search_menu")));
		paraMap.put("search_value", ComProc.urlDecoder(request.getParameter("search_value")));
		paraMap.put("dep_code_value", ComProc.urlDecoder(request.getParameter("dep_code_value")));
		
		String strRet = searchMemberService.searchMember(request, paraMap);
		
		logger.info(strRet);
		response.getWriter().write(strRet);
		
		return null;							// servlet-context.xml
	}
}
