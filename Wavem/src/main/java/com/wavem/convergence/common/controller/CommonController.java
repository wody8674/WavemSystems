package com.wavem.convergence.common.controller;

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
import com.wavem.convergence.common.service.CommonService;


@Controller
public class CommonController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	@Autowired
	CommonService commonService;
	
	@RequestMapping(value = "/selectMenu.go")
	public String selectMenu(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		String user_mc = request.getParameter("param");
		
		Map paraMap = new HashMap();
		paraMap.put("SEL_MN", "selectMenu");
		paraMap.put("user_mc", user_mc);
		String ret = commonService.selectMenuHtml(request, paraMap);
		
		try {
			System.out.println(ret);
			response.getWriter().write(ret);
			
		} catch (Exception e) {
			System.out.println("controller error");
			e.printStackTrace();
		}
			
		return null;
	}
	
	@RequestMapping(value = "/test.do")
	public String selectTest(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		model.addAttribute("url", "/html/dep_mem/member/html/memberCon.html");
		
		return "WavemSystem";
	}
}
