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
import com.wavem.convergence.community.notice.service.NoticeService;

@Controller
public class NoticeController {
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	@Autowired
	NoticeService noticeService;
	
	@RequestMapping(value = "/Notice.do")
	public String loadNotice(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		model.addAttribute("url", "/html/community/notice/html/notice.jsp");
		model.addAttribute("link", "<link rel='stylesheet' type='text/css' href='html/project/project/css/project.css'></link>");
		model.addAttribute("script", "<script type='text/javascript' src='html/community/notice/js/notice.js'></script>");
		
		return "WSMainView"; // servlet-context.xml
	}
	
	@RequestMapping(value = "/searchGubun_Dep.do")
	public String searchGubun_Dep(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		String strRet = noticeService.searchGubun_Dep(request);
		
		logger.info(strRet);
		response.getWriter().write(strRet);
		
		return null; // servlet-context.xml
	}
	
	@RequestMapping(value = "/searchGubun_Pro.do")
	public String searchGubun_Pro(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		String strRet = noticeService.searchGubun_Pro(request);
		
		logger.info(strRet);
		response.getWriter().write(strRet);
		
		return null; // servlet-context.xml
	}
	
	@RequestMapping(value = "/saveNoticeDetail.do")
	public String saveNoticeDetail(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		String jsonData = URLDecoder.decode(request.getParameter("data"), "UTF-8");
		
		String ret = "";
		ret = noticeService.saveNoticeDetail(request, jsonData);
		response.getWriter().write(ret);
		
		return null; // servlet-context.xml
	}
	
	@RequestMapping(value = "/updateNoticeDetail.do")
	public String updateNoticeDetail(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		String jsonData = URLDecoder.decode(request.getParameter("data"), "UTF-8");
		
		String ret = "";
		ret = noticeService.updateNoticeDetail(request, jsonData);
		response.getWriter().write(ret);
		
		return null; // servlet-context.xml
	}
	
	@RequestMapping(value = "/searchNotice.do")
	public String searchNotice(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		Map<String, String> paraMap = new HashMap<String, String>();
		
		paraMap.put("page", ComProc.urlDecoder(request.getParameter("page")));
		paraMap.put("notice_gubun1", ComProc.urlDecoder(request.getParameter("notice_gubun1")));
		paraMap.put("notice_gubun2", ComProc.urlDecoder(request.getParameter("notice_gubun2")));
		paraMap.put("search_gubun", ComProc.urlDecoder(request.getParameter("search_gubun")));
		paraMap.put("search_gubun_content", ComProc.urlDecoder(request.getParameter("search_gubun_content")));
		
		paraMap.put("boa_code", ComProc.urlDecoder(request.getParameter("boa_code")));
		
		String strRet = noticeService.searchNotice(request, paraMap);
		
		logger.info(strRet);
		response.getWriter().write(strRet);
		
		return null; // servlet-context.xml
	}
}