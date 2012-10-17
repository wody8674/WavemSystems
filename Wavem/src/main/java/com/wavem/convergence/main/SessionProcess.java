package com.wavem.convergence.main;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SessionProcess{

	@RequestMapping(value = "/getSession.do", method = RequestMethod.GET)
	public String getSession(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("user_id");
		  
		
//		response.getWriter().write(userid);
		System.out.println("getSession : " + userid);
		System.out.println(session.getAttribute("SiteLogin"));
		
//		response.sendRedirect(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/login_form.html"); // 이동할 페이지....
		System.out.println("getScheme : "+request.getScheme());
		System.out.println("getServerName : "+request.getServerName());
		System.out.println("getServerPort : "+request.getServerPort());
		System.out.println("getContextPath : "+request.getContextPath());
		
		return null;
	}

}
