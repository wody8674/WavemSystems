package com.wavem.convergence.login.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wavem.convergence.login.service.LoginService;

@Controller
public class LoginController  {
//	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	LoginService loginService;
	
	@RequestMapping(value ="/login.go")
	public String selectParam(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
	
		if (request.getParameter("userid") != null) {
			login(request, response);
		}
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		model.addAttribute("url", "/html/login/html/login.jsp");
	
		return "WSLoginView";
	}
	
	public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, String> paraMap = new HashMap<String, String>();
		HttpSession session = request.getSession();
	
		paraMap.put("userid", request.getParameter("userid"));
		paraMap.put("pwd", request.getParameter("pwd"));
		
		String strRet = loginService.login(request, paraMap);
		logger.info("로그인 결과 : "+strRet);
		
		if(strRet.equals("success")){
			
			response.sendRedirect(request.getContextPath()+ "/mainView.do");// 메인화면 주소
			
			//로그아웃 시 로그아웃된 화면으로 접근
//			if (session.getAttribute("first_url") != null) {
//				response.sendRedirect(request.getContextPath()+ session.getAttribute("first_url"));//주소
//			} else {
//				response.sendRedirect(request.getContextPath()+ "/mainView.do");// 메인화면 주소
//			}
		}
		
		return strRet;
	}
	
	@RequestMapping(value="/sessionInfo.do")
	public String sessionInfo(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		/*Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("", request.getParameter(""));
		*/
		
		String strRet = loginService.sessionInfo(request);
		
		logger.info(strRet);
		response.getWriter().write(strRet);
		
		return null;							// servlet-context.xml
	}
	
	@RequestMapping(value="/logout.do")
	public String logout(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		request.getSession().invalidate();
		response.getWriter().write("1");
		response.sendRedirect(request.getContextPath());//주소
		
		return null;							// servlet-context.xml
	}
}
