package com.wavem.convergence.main;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionFilter implements Filter{

	private static final Logger logger = LoggerFactory.getLogger(SessionFilter.class);
	private FilterConfig config;

	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		if (logger.isDebugEnabled()) {
			logger.debug(" SessionFilter  시작 합니다. ");
		}
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		       
		Object siteLogin = request.getSession().getAttribute("SiteLogin");
		 
		if (siteLogin == null) {
			if (logger.isDebugEnabled()) {
				logger.debug(" 인증값이 없습니다. ");
			}
			
//			response.sendRedirect("/SpringProject/login.do"); // 이동할 페이지....
		}else{
			chain.doFilter(req, res);
		}
		 
		if (logger.isDebugEnabled()) {
			logger.debug(" SessionFilter  종료 합니다. ");
		}        
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}



/*
public class SessionFilter extends HttpServlet{

	private static final long serialVersionUID = -159479775790211187L;
	private static final Logger logger = LoggerFactory.getLogger(SessionFilter.class);
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		if (logger.isDebugEnabled()) {
			logger.debug(" SessionFilter  시작 합니다. ");
		}
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		       
		Object siteLogin = request.getSession().getAttribute("SiteLogin");
		 
		if (siteLogin == null) {
			if (logger.isDebugEnabled()) {
				logger.debug(" 인증값이 없습니다. ");
			}
			
			response.sendRedirect("/login.do"); // 이동할 페이지....
		}else{
			chain.doFilter(req, res);
		}
		 
		if (logger.isDebugEnabled()) {
			logger.debug(" SessionFilter  종료 합니다. ");
		}        
	}

}
*/
