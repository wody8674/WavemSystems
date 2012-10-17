package com.wavem.convergence.approval.contract.controller;

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

import com.wavem.convergence.approval.contract.service.ContractService;


@Controller
public class ContractController {
	
	private static final Logger logger = LoggerFactory.getLogger(ContractController.class);

	@Autowired
	ContractService contractService;
		
	@RequestMapping(value = "/contract.do")
	public String loadContract(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException  {
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		model.addAttribute("url", "html/approval/contract/html/contract.html");
		model.addAttribute("link", "<link rel='stylesheet' type='text/css' href='html/approval/contract/css/contract.css'>");
		model.addAttribute("script", "<script type='text/javascript' src='html/approval/contract/js/contract.js'></script>");
		
		return "WavemSystem";        
	}
	
	@RequestMapping(value="/searchContract.do")
	public String searchContract(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		Map<String, String> paraMap = new HashMap<String, String>();
		
		paraMap.put("search_menu", request.getParameter("search_menu"));
		paraMap.put("search_value", request.getParameter("search_value"));
		paraMap.put("bre_code_value", request.getParameter("bress_code_value"));
		
		//System.out.println(request.getParameter("search_menu"));
		//System.out.println(request.getParameter("search_value"));
		
		String strRet = contractService.searchContract(request, paraMap);
		logger.info(strRet);
		response.getWriter().write(strRet);
		
		return null;							// servlet-context.xml
	}
	
	@RequestMapping(value = "/saveContract.do")
	public String saveContract(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException  {
		
		String jsonData = URLDecoder.decode(request.getParameter("data"), "UTF-8");
		contractService.saveContract(jsonData);
			
		return null;        
	}

}
