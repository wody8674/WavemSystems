package com.wavem.convergence.common.controller;

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
import com.wavem.convergence.common.service.ComCodeService;
import com.wavem.convergence.dep_mem.department.controller.DepartmentController;

@Controller
public class ComCodeController {
	
private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	
	@Autowired
	ComCodeService comCodeService;
	
	//부서
	@RequestMapping(value="/searchDepCom.do")
	public String searchDepCom(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		String strRet = comCodeService.searchDepCom();
		logger.info(strRet);
		response.getWriter().write(strRet);
		
		return null;							// servlet-context.xml
	}
	
	//프로젝트
	@RequestMapping(value="/searchProCom.do")
	public String searchProCom(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		String strRet = comCodeService.searchProCom();
		logger.info(strRet);
		response.getWriter().write(strRet);
		
		return null;							
	}
	
	//직책
	@RequestMapping(value="/searchPostCom.do")
	public String searchPostCom(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		String strRet = comCodeService.searchPostCom();
		logger.info(strRet);
		response.getWriter().write(strRet);
		
		return null;							// servlet-context.xml
	}
	
	//공통코드
	@RequestMapping(value="/searchComCode.do")
	public String searchComCode(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		String selCd = ComProc.urlDecoder(request.getParameter("SEL_CD"));
		String para1 = ComProc.urlDecoder(request.getParameter("param1"));
		
		Map paraMap = new HashMap();
		paraMap.put("SEL_CD", selCd);
		paraMap.put("param1", para1);
		
		System.out.println("ComCode = " +paraMap);
		
		String strRet = comCodeService.searchComCode(paraMap);
		
		logger.info(strRet);
		response.getWriter().write(strRet);
		
		return null;							// servlet-context.xml
	}
}
