package com.wavem.convergence.approval.request.controller;

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

import com.wavem.convergence.approval.request.service.RequestService;

@Controller
public class RequestController {
	private static final Logger logger = LoggerFactory.getLogger(RequestController.class);
	
	@Autowired
	RequestService requestService;
	
	@RequestMapping(value="/request.do")
	public String loadRequest(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException  {
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		String link = "<link rel='stylesheet' type='text/css' href='html/approval/request/css/request.css'>";
		link += "<link rel='stylesheet' type='text/css' href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css'>";
		
		String script ="<script type='text/javascript' src='html/approval/request/js/request.js'></script>";
		script += "<script type='text/javascript' src='html/com/lib/jquery-ui.js'></script>";
		
		model.addAttribute("url", "/html/approval/request/html/request.jsp");
		model.addAttribute("link", link);
		model.addAttribute("script", script);
		
		return "WavemSystem";        
	}
	
	//조회--------------------------------------------------------------------
	@RequestMapping(value="/searchRequest.do")
	public String searchRequest(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		Map<String, String> paraMap = new HashMap<String, String>();
		
		paraMap.put("MEM_CODE",	(String) request.getSession().getAttribute("userMC"));
		String strRet = requestService.searchRequest(request, paraMap);
		logger.info(strRet);
		response.getWriter().write(strRet);
		
		return null;							// servlet-context.xml
	}
	
	//팝업창 조회--------------------------------------------------------------------
		@RequestMapping(value="/searchInsertRequest.do")
		public String searchInsertRequest(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
			
			response.setHeader("Content-Type", "text/html; charset=UTF-8");
			
			Map<String, String> paraMap = new HashMap<String, String>();
			
			paraMap.put("REQ_CODE", request.getParameter("key"));
			paraMap.put("APP_CODE", request.getParameter("key2"));
			paraMap.put("APP_DAM2", request.getParameter("key3"));
			paraMap.put("APP_STATE", request.getParameter("key4"));
			paraMap.put("APP_TEAM2", request.getParameter("key5"));
			paraMap.put("APP_EXECUTIVE2", request.getParameter("key6"));
			
			System.out.println("Controller paraMap==" + paraMap);
			
			String strRet = requestService.searchInsertRequest(request, paraMap);
			logger.info(strRet);
			response.getWriter().write(strRet);
			
			return null;							// servlet-context.xml
		}
	
	//임시저장--------------------------------------------------------------------
		@RequestMapping(value = "/semisaveRequest.do")
		public String semisaveRequest(HttpServletRequest request,	HttpServletResponse response, Model model) throws IOException {

			response.setHeader("Content-Type", "text/html; charset=UTF-8");

			Map<String, String> paraMap = new HashMap<String, String>();

			String jsonData = URLDecoder.decode(request.getParameter("data"),"UTF-8");
			String ret = "";
			ret = requestService.semisaveRequest(request, jsonData);
			response.getWriter().write(ret);

			return null;
		}
	
	//등록--------------------------------------------------------------------
	@RequestMapping(value = "/saveRequest.do")
	public String saveRequest(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException  {
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		String jsonData = URLDecoder.decode(request.getParameter("data"), "UTF-8");
		String ret ="";
		ret = requestService.saveRequest(request, jsonData);
		response.getWriter().write(ret);
			
		return null;        
	}
	
	//수정--------------------------------------------------------------------
		@RequestMapping(value = "/updateRequest.do")
		public String updateRequest(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException  {
			response.setHeader("Content-Type", "text/html; charset=UTF-8");
			String jsonData = URLDecoder.decode(request.getParameter("data"), "UTF-8");
			String ret = "";
			ret = requestService.updateRequest(jsonData);
			response.getWriter().write(ret);
			
			return null;          //servlet-context.xml
		}
}
