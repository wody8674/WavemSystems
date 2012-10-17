package com.wavem.convergence.main.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;

import com.wavem.convergence.main.service.AjaxService;
import com.wavem.convergence.main.vo.AjaxVo;

@Controller
public class AjaxController {
	
	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);

	@Autowired
	private AjaxService ajaxService;
	
	@RequestMapping(value = "/main/ajaxData.do", method = RequestMethod.GET)
	public String selectParam(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		AjaxVo paramVo = new AjaxVo();
		Map param = new HashMap();
		String xmlData = "";
		
		// parameter
		paramVo.setParam_user_id(request.getParameter("user_id"));
		param.put("param_user_id", request.getParameter("user_id"));
		
		try {
			xmlData = ajaxService.selectParam(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("xmlData",xmlData);
		
		return "ajax";
	}
	
	@RequestMapping(value = "/main/ajaxDataPost.do")
	public String selectParamPost(HttpServletRequest request, HttpServletResponse response, Model model) {

		Map param = new HashMap();
		String xmlData = "";
		
		// parameter
		param.put("param_user_id", request.getParameter("user_id"));
		
		try {
			xmlData = ajaxService.selectParam(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.setHeader("Content-Type", "text/xml; charset=UTF-8");
		
		try {
			response.getWriter().write(xmlData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@RequestMapping(value = "/main/ajaxJson.do")
	public String selectJson(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		Map<String, String> param = new HashMap<String, String>();
		String jsonData = "";
		
		// parameter
		param.put("param_user_id", request.getParameter("user_id"));
		
		jsonData = ajaxService.selectJson(param);
		response.getWriter().write(jsonData);
		System.out.println(jsonData);
		return null;
	}
	
	@RequestMapping(value = "/main/ajaxJsonSave.do")
	public String selectJsonSave(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		String jsonData = URLDecoder.decode(request.getParameter("data"), "UTF-8"); // jQuery Ajax 한글 처리
		
		// parameter
		ajaxService.saveJson(jsonData);
		
		return null;
	}
}
