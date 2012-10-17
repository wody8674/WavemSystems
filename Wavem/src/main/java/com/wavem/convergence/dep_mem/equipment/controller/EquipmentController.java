package com.wavem.convergence.dep_mem.equipment.controller;

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
import com.wavem.convergence.dep_mem.equipment.service.EquipmentService;
import com.wavem.convergence.dep_mem.member.controller.MemberController;

@Controller
public class EquipmentController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	EquipmentService equipmentService;
	
	@RequestMapping(value="/Equipment.do")
	public String loadEquipment(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		model.addAttribute("url", "/html/dep_mem/equipment/html/equipment.jsp");
		model.addAttribute("link", "<link rel='stylesheet' type='text/css' href='html/dep_mem/equipment/css/equipment.css'>");
		model.addAttribute("script", "<script type='text/javascript' src='html/dep_mem/equipment/js/equipment.js'></script>");
		
		return "WavemSystem";							// servlet-context.xml
	}
	
	@RequestMapping(value="/searchEquipment.do")
	public String searchEquipment(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		Map<String, String> paraMap = new HashMap<String, String>();
		
		paraMap.put("equ_type", request.getParameter("equ_type"));
		paraMap.put("use_type", request.getParameter("use_type"));
		paraMap.put("search_menu", ComProc.urlDecoder(request.getParameter("search_menu")));
		paraMap.put("search_value", ComProc.urlDecoder(request.getParameter("search_value")));
		paraMap.put("dep_code_value", ComProc.urlDecoder(request.getParameter("dep_code_value")));
		
		String strRet = equipmentService.searchEquipment(request, paraMap);
		
		logger.info(strRet);
		response.getWriter().write(strRet);
		
		return null;							// servlet-context.xml
	}
	
	@RequestMapping(value = "/saveEquipment.do")
	public String saveEquipment(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException  {
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		String jsonData = URLDecoder.decode(request.getParameter("data"), "UTF-8");
		
		String ret = equipmentService.saveEquipment(request, jsonData);
		response.getWriter().write(ret);

		return null;          //servlet-context.xml
	}
	
	@RequestMapping(value = "/updateEquipment.do")
	public String updateEquipment(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException  {
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		String jsonData = URLDecoder.decode(request.getParameter("data"), "UTF-8");
		
		String ret = equipmentService.updateEquipment(request, jsonData);
		response.getWriter().write(ret);
		
		return null;          //servlet-context.xml
	}
	
}

