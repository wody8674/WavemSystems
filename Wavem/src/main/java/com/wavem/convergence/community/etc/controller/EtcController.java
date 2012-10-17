package com.wavem.convergence.community.notice.controller;

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
import com.wavem.convergence.community.etc.service.EtcService;

@Controller
public class EtcController {
	
	private static final Logger logger = LoggerFactory.getLogger(EtcController.class);
	
	@Autowired
	EtcService etcService;
	
	@RequestMapping(value = "/Etc.do")
	public String loadNotice(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		model.addAttribute("url", "/html/community/etc/html/etc.jsp");
		model.addAttribute("script", "<script type='text/javascript' src='html/community/etc/js/etc.js'></script>");
		
		return "WSMainView"; // servlet-context.xml
	}
	
	@RequestMapping(value = "/saveEtcDetail.do")
	public String saveEtcDetail(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		String jsonData = URLDecoder.decode(request.getParameter("data"), "UTF-8");
		
		String ret = "";
		ret = etcService.saveEtcDetail(request, jsonData);
		response.getWriter().write(ret);
		
		return null; // servlet-context.xml
	}
	
	@RequestMapping(value = "/updateEtcDetail.do")
	public String updateEtcDetail(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		String jsonData = URLDecoder.decode(request.getParameter("data"), "UTF-8");
		
		String ret = "";
		ret = etcService.updateEtcDetail(request, jsonData);
		response.getWriter().write(ret);
		
		return null; // servlet-context.xml
	}
	
	@RequestMapping(value = "/searchEtc.do")
	public String searchEtc(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		Map<String, String> paraMap = new HashMap<String, String>();

		paraMap.put("search_gubun", ComProc.urlDecoder(request.getParameter("search_gubun")));
		paraMap.put("search_gubun_content", ComProc.urlDecoder(request.getParameter("search_gubun_content")));
		
		paraMap.put("boa_code", ComProc.urlDecoder(request.getParameter("boa_code")));
		
				
		String strRet = etcService.searchEtc(request, paraMap);
		
		logger.info(strRet);
		response.getWriter().write(strRet);
		
		return null; // servlet-context.xml
	}
}