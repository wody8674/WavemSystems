package com.wavem.convergence.approval.breaks.controller;

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

import com.wavem.convergence.approval.breaks.service.BreakService;
import com.wavem.convergence.common.ComProc;

@Controller
public class BreakController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(BreakController.class);

	@Autowired
	BreakService breakService;

	@RequestMapping(value = "/break.do")
	public String loadBreak(HttpServletRequest request,
			HttpServletResponse response, Model model) throws IOException {

		response.setHeader("Content-Type", "text/html; charset=UTF-8");

		String link = "<link rel='stylesheet' type='text/css' href='html/approval/break/css/break.css'>";
		link += "<link rel='stylesheet' type='text/css' href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css'>";

		String script = "<script type='text/javascript' src='html/approval/break/js/break.js'></script>";
		script += "<script type='text/javascript' src='html/com/lib/jquery-ui.js'></script>";

		model.addAttribute("url", "/html/approval/break/html/break.jsp");
		model.addAttribute("link", link);
		model.addAttribute("script", script);

		return "WavemSystem";
	}
	
	//조회--------------------------------------------------------------------
	@RequestMapping(value = "/searchBreak.do")
	public String searchBreak(HttpServletRequest request,
			HttpServletResponse response, Model model) throws IOException {

		response.setHeader("Content-Type", "text/html; charset=UTF-8");

		Map<String, String> paraMap = new HashMap<String, String>();

		paraMap.put("MEM_CODE",	(String) request.getSession().getAttribute("userMC"));
		paraMap.put("BTN_FLAG", request.getParameter("btn_flag"));
		
		paraMap.put("SEARCH_STATE", request.getParameter("SEARCH_STATE"));
		paraMap.put("search_menu", ComProc.urlDecoder(request.getParameter("search_menu")));
		paraMap.put("search_value", ComProc.urlDecoder(request.getParameter("search_value")));
		
		
		System.out.println("paramap @@@@@@@@@@@@@@@@@@@@@@@@@@@@" +paraMap);

		String strRet = breakService.searchBreak(request, paraMap);
		logger.info(strRet);
		response.getWriter().write(strRet);

		return null; // servlet-context.xml
	}

	//팝업창 조회--------------------------------------------------------------------
	@RequestMapping(value="/searchInsertBreak.do")
	public String searchInsertBreak(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		System.out.println("searchInsertBreak.do");
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		Map<String, String> paraMap = new HashMap<String, String>();
		
		paraMap.put("BRE_CODE", request.getParameter("key"));
		paraMap.put("APP_CODE", request.getParameter("key2"));
		paraMap.put("APP_DAM2", request.getParameter("key3"));
		paraMap.put("APP_STATE", request.getParameter("key4"));
		paraMap.put("APP_TEAM2", request.getParameter("key5"));
		paraMap.put("APP_EXECUTIVE2", request.getParameter("key6"));
		paraMap.put("MEM_CODE",	(String) request.getSession().getAttribute("userMC"));
		paraMap.put("MEM_NAME",	(String) request.getSession().getAttribute("userNm"));
		
		
		System.out.println("Controller paraMap==" + paraMap);
		
		String strRet = breakService.searchInsertBreak(request, paraMap);
		logger.info(strRet);
		response.getWriter().write(strRet);
		
		return null;							// servlet-context.xml
	}
	
	//임시저장--------------------------------------------------------------------
	@RequestMapping(value = "/semisaveBreak.do")
	public String semisaveBreak(HttpServletRequest request,	HttpServletResponse response, Model model) throws IOException {

		response.setHeader("Content-Type", "text/html; charset=UTF-8");

		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("MEM_NAME",	(String) request.getSession().getAttribute("userNm"));
		paraMap.put("MEM_CODE",	(String) request.getSession().getAttribute("userMC"));
		
		String jsonData = URLDecoder.decode(request.getParameter("data"),"UTF-8");
		String ret = "";
		ret = breakService.semisaveBreak(request, jsonData);
		response.getWriter().write(ret);

		return null;
	}
	
	//등록--------------------------------------------------------------------
	@RequestMapping(value = "/saveBreak.do")
	public String saveBreak(HttpServletRequest request,	HttpServletResponse response, Model model) throws IOException {

		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("MEM_NAME",	(String) request.getSession().getAttribute("userNm"));
		paraMap.put("MEM_CODE",	(String) request.getSession().getAttribute("userMC"));
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		String jsonData = URLDecoder.decode(request.getParameter("data"),"UTF-8");
		String ret = "";
		ret = breakService.saveBreak(request, jsonData);
		response.getWriter().write(ret);

		return null;
	}
	
	//수정--------------------------------------------------------------------
	@RequestMapping(value = "/updateBreak.do")
	public String updateBreak(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException  {
		
		Map<String, String> paraMap = new HashMap<String, String>();	
		paraMap.put("MEM_CODE",	(String) request.getSession().getAttribute("userMC"));
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		String jsonData = URLDecoder.decode(request.getParameter("data"), "UTF-8");
		String ret = "";
		ret = breakService.updateBreak(jsonData);
		response.getWriter().write(ret);
		
		return null;          //servlet-context.xml
	}
	
	//임시저장이거나 반려일경우 날짜수정------------------------------------------------------------
		@RequestMapping(value = "/updateSRSave.do")
		public String updateSRSave(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException  {
			Map<String, String> paraMap = new HashMap<String, String>();	
			paraMap.put("APP_STATE_VALUE", request.getParameter("key4"));
			
			System.out.println("paraMap값값값값값값 ===" +paraMap);
			response.setHeader("Content-Type", "text/html; charset=UTF-8");
			String jsonData = URLDecoder.decode(request.getParameter("data"), "UTF-8");
			String ret = "";
			ret = breakService.updateSRSave(request, jsonData);
			response.getWriter().write(ret);
			
			return null;          //servlet-context.xml
		}
	
	//결재--------------------------------------------------------------------
	@RequestMapping(value = "/updateApproval.do")
	public String updateApproval(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException  {
		Map<String, String> paraMap = new HashMap<String, String>();	
		paraMap.put("APP_STATE_VALUE", request.getParameter("key4"));
		
		System.out.println("paraMap값값값값값값 ===" +paraMap);
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		String jsonData = URLDecoder.decode(request.getParameter("data"), "UTF-8");
		String ret = "";
		ret = breakService.updateApproval(request, jsonData);
		response.getWriter().write(ret);
		
		return null;          //servlet-context.xml
	}
	
	//반려--------------------------------------------------------------------
	@RequestMapping(value = "/updateRollback.do")
	public String updateRollback(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException  {
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		String jsonData = URLDecoder.decode(request.getParameter("data"), "UTF-8");
		String ret = "";
		ret = breakService.updateRollback(jsonData);
		response.getWriter().write(ret);
		
		return null;          //servlet-context.xml
	}

}
