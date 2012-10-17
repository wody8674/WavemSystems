package com.wavem.convergence.approval.expense.controller;

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

import com.wavem.convergence.approval.expense.service.ExpenseService;
import com.wavem.convergence.common.ComProc;

@Controller
public class ExpenseController {
	private static final Logger logger = LoggerFactory.getLogger(ExpenseController.class);

	@Autowired
	ExpenseService expenseService;
		
	@RequestMapping(value = "/expense.do")
	public String loadExpense(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException  {
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		String link = "<link rel='stylesheet' type='text/css' href='html/approval/expense/css/expense.css'>";
		link += "<link rel='stylesheet' type='text/css' href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css'>";
		
		String script ="<script type='text/javascript' src='html/approval/expense/js/expense.js'></script>";
		script += "<script type='text/javascript' src='html/com/lib/jquery-ui.js'></script>";
		
		model.addAttribute("url", "/html/approval/expense/html/expense.jsp");
		model.addAttribute("link", link);
		model.addAttribute("script", script);
		
		return "WavemSystem";        
	}
	//조회--------------------------------------------------------------------
	@RequestMapping(value="/searchExpense.do")
	public String searchExpense(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		Map<String, String> paraMap = new HashMap<String, String>();
		
		paraMap.put("MEM_CODE",	(String) request.getSession().getAttribute("userMC"));
		paraMap.put("BTN_FLAG", request.getParameter("btn_flag"));
		
		paraMap.put("SEARCH_STATE", request.getParameter("SEARCH_STATE"));
		paraMap.put("search_menu", ComProc.urlDecoder(request.getParameter("search_menu")));
		paraMap.put("search_value", ComProc.urlDecoder(request.getParameter("search_value")));
		
		
		System.out.println("paramap @@@@@@@@@@@@@@@@@@@@@@@@@@@@" +paraMap);
		String strRet = expenseService.searchExpense(request, paraMap);
		logger.info(strRet);
		response.getWriter().write(strRet);
		
		return null;							// servlet-context.xml
	}
	
	//팝업창 조회--------------------------------------------------------------------
	@RequestMapping(value="/searchInsertExpense.do")
	public String searchInsertExpense(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		Map<String, String> paraMap = new HashMap<String, String>();
		
		paraMap.put("EXP_CODE", request.getParameter("key"));
		paraMap.put("APP_CODE", request.getParameter("key2"));
		paraMap.put("APP_DAM2", request.getParameter("key3"));
		paraMap.put("APP_STATE", request.getParameter("key4"));
		paraMap.put("APP_TEAM2", request.getParameter("key5"));
		paraMap.put("APP_EXECUTIVE2", request.getParameter("key6"));
		paraMap.put("MEM_CODE",	(String) request.getSession().getAttribute("userMC"));
		paraMap.put("MEM_NAME",	(String) request.getSession().getAttribute("userNm"));
		
		System.out.println("Controller paraMap==" + paraMap);
		
		String strRet = expenseService.searchInsertExpense(request, paraMap);
		logger.info(strRet);
		response.getWriter().write(strRet);
		
		return null;							// servlet-context.xml
	}
	
	//임시저장--------------------------------------------------------------------
	@RequestMapping(value = "/semisaveExpense.do")
	public String semisaveExpense(HttpServletRequest request,	HttpServletResponse response, Model model) throws IOException {

		response.setHeader("Content-Type", "text/html; charset=UTF-8");

		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("MEM_NAME",	(String) request.getSession().getAttribute("userNm"));
		paraMap.put("MEM_CODE",	(String) request.getSession().getAttribute("userMC"));

		String jsonData = URLDecoder.decode(request.getParameter("data"),"UTF-8");
		String ret = "";
		ret = expenseService.semisaveExpense(request, jsonData);
		response.getWriter().write(ret);

		return null;
	}

	//등록--------------------------------------------------------------------
	@RequestMapping(value = "/saveExpense.do")
	public String saveExpense(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException  {
		
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("MEM_NAME",	(String) request.getSession().getAttribute("userNm"));
		paraMap.put("MEM_CODE",	(String) request.getSession().getAttribute("userMC"));
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		String jsonData = URLDecoder.decode(request.getParameter("data"), "UTF-8");
		String ret="";
		
		ret= expenseService.saveExpense(request, jsonData);
		response.getWriter().write(ret);
			
		return null;        
	}
	
	//수정--------------------------------------------------------------------
		@RequestMapping(value = "/updateExpense.do")
		public String updateExpense(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException  {
			
			Map<String, String> paraMap = new HashMap<String, String>();	
			paraMap.put("MEM_CODE",	(String) request.getSession().getAttribute("userMC"));
			
			response.setHeader("Content-Type", "text/html; charset=UTF-8");
			String jsonData = URLDecoder.decode(request.getParameter("data"), "UTF-8");
			String ret = "";
			ret = expenseService.updateExpense(jsonData);
			response.getWriter().write(ret);
			
			return null;          //servlet-context.xml
		}

}
