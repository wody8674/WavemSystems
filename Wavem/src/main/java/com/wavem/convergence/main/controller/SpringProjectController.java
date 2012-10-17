package com.wavem.convergence.main.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wavem.convergence.main.service.SpringProjectService;
import com.wavem.convergence.main.vo.SpringProjectVo;

@Controller
public class SpringProjectController {
	
	@Autowired
	private SpringProjectService springProjectService;
	
	@RequestMapping(value = "/main/SpringProject.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		List<SpringProjectVo> ret = new ArrayList<SpringProjectVo>();
		SpringProjectVo vo = new SpringProjectVo();
		
		try {
			ret = springProjectService.selectTest();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		vo = ret.get(0);

		model.addAttribute("userid", vo.getUser_id());
		model.addAttribute("assigncd", vo.getAssign_cd());
		model.addAttribute("usernm", vo.getUser_nm());
		model.addAttribute("levelnm", vo.getLevel_nm());
		model.addAttribute("hp", vo.getHp());
		model.addAttribute("email", vo.getEmail());
		
		return "myInfo2";
	}
	
	@RequestMapping(value = "/main/insertSpringProject.do", method = RequestMethod.GET)
	public void insert(Locale locale, Model model) {
		
		int ret = 0;
		
		try {
			ret = springProjectService.insertTest();
			System.out.println("Insert 결과 = " + ret);
			
			/*ret 결과에 따른 처리로직 추가*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/main/updateSpringProject.do", method = RequestMethod.GET)
	public void update(Locale locale, Model model) {
		
		int ret = 0;
		
		try {
			ret = springProjectService.updateTest();
			System.out.println("Update 결과 = " + ret);
			
			/*ret 결과에 따른 처리로직 추가*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/main/deleteSpringProject.do", method = RequestMethod.GET)
	public void delete(Locale locale, Model model) {
		
		int ret = 0;
		
		try {
			ret = springProjectService.deleteTest();
			System.out.println("Delete 결과 = " + ret);
			
			/*ret 결과에 따른 처리로직 추가*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/main/selectParam.do", method = RequestMethod.GET)
	public String selectParam(HttpServletRequest request, HttpServletResponse response, Model model) {

		List<SpringProjectVo> ret = new ArrayList<SpringProjectVo>();
		SpringProjectVo paramVo = new SpringProjectVo();
		SpringProjectVo vo = new SpringProjectVo();
		
		// parameter 
		paramVo.setParam_user_id(request.getParameter("user_id"));
		
		try {
			ret = springProjectService.selectParam(paramVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (ret.size() > 0) {
			vo = ret.get(0);
			
			model.addAttribute("userid", vo.getUser_id());
			model.addAttribute("assigncd", vo.getAssign_cd());
			model.addAttribute("usernm", vo.getUser_nm());
			model.addAttribute("levelnm", vo.getLevel_nm());
			model.addAttribute("hp", vo.getHp());
			model.addAttribute("email", vo.getEmail());
		}
		
		return "myInfo2";
	}
	
	@RequestMapping(value = "/main/selectParamPost.do")
	public String selectParamPost(HttpServletRequest request, HttpServletResponse response, Model model) {

		List<SpringProjectVo> ret = new ArrayList<SpringProjectVo>();
		SpringProjectVo paramVo = new SpringProjectVo();
		SpringProjectVo vo = new SpringProjectVo();

		// parameter 
		paramVo.setParam_user_id(request.getParameter("user_id"));
		
		try {
			ret = springProjectService.selectParam(paramVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (ret.size() > 0) {
			vo = ret.get(0);
			
			model.addAttribute("userid", vo.getUser_id());
			model.addAttribute("assigncd", vo.getAssign_cd());
			model.addAttribute("usernm", vo.getUser_nm());
			model.addAttribute("levelnm", vo.getLevel_nm());
			model.addAttribute("hp", vo.getHp());
			model.addAttribute("email", vo.getEmail());
		}
		
		return "myInfo3";
	}
}
