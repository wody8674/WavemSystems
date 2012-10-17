package com.wavem.convergence.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wavem.convergence.common.service.SidemenuService;

@Controller
public class SidemenuController {

	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	@Autowired
	SidemenuService sidemenuService;
	
	@RequestMapping(value = "/selectSidemenu.go")
	public String selectSidemenu(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		String ret = sidemenuService.selectSidemenuHtml();
		
		try {
			System.out.println(ret);
			response.getWriter().write(ret);
		} catch (Exception e) {
			System.out.println("controller error");
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/sidemenu.go")
	public String selectSideTest(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		System.out.println(sidemenuService.selectSidemenuHtml());
		
		model.addAttribute("sidemenu", sidemenuService.selectSidemenuHtml());
		model.addAttribute("url", "/html/");
		
		return "WavemSystem";
	}
}
