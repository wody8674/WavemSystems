package com.wavem.convergence.dep_mem.member.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.wavem.convergence.dep_mem.member.service.MemberService;

@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	MemberService memberService;
	
	@RequestMapping(value="/Member.do")
	public String loadMember(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		model.addAttribute("url", "/html/dep_mem/member/html/member.jsp");
		model.addAttribute("link", "<link rel='stylesheet' type='text/css' href='html/dep_mem/member/css/member.css'>");
		model.addAttribute("script", "<script type='text/javascript' src='html/dep_mem/member/js/member.js'></script>");
		
		return "WavemSystem";							// servlet-context.xml
	}
	
	@RequestMapping(value="/searchMember.do")
	public String searchMember(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		Map<String, String> paraMap = new HashMap<String, String>();
		
		paraMap.put("mem_post", request.getParameter("mem_post"));
		paraMap.put("con_type", request.getParameter("con_type"));
		paraMap.put("search_menu", ComProc.urlDecoder(request.getParameter("search_menu")));
		paraMap.put("search_value", ComProc.urlDecoder(request.getParameter("search_value")));
		paraMap.put("dep_code_value", ComProc.urlDecoder(request.getParameter("dep_code_value")));
		
		String strRet = memberService.searchMember(request, paraMap);
		
		logger.info(strRet);
		response.getWriter().write(strRet);
		
		return null;							// servlet-context.xml
	}

	@RequestMapping(value = "/saveMember.do")
	public String saveMember(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException  {
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		String jsonData = URLDecoder.decode(request.getParameter("data"), "UTF-8");
		String ret = "";
		ret = memberService.saveMember(request, jsonData);
		response.getWriter().write(ret);
		
		return null;          //servlet-context.xml
	}
	
	@RequestMapping(value = "/updateMember.do")
	public String updateMember(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException  {
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		String jsonData = URLDecoder.decode(request.getParameter("data"), "UTF-8");
		String ret = "";
		ret = memberService.updateMember(request, jsonData);
		response.getWriter().write(ret);
		
		return null;          //servlet-context.xml
	}
	
	//공통코드
	@RequestMapping(value="/memComCode.do")
	public String searchComCode(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		String selCd = request.getParameter("SEL_CD");
		String para1 = request.getParameter("param1");
		
		Map paraMap = new HashMap();
		
		paraMap.put("SEL_CD", selCd);
		paraMap.put("param1", para1);
		
		String strRet = memberService.memComCode(paraMap);
		
		logger.info(strRet);
		response.getWriter().write(strRet);
		
		return null;							// servlet-context.xml
	}
}
