package com.wavem.convergence.dep_mem.book.controller;

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
import com.wavem.convergence.dep_mem.book.service.BookService;

@Controller
public class BookController {

	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	BookService bookService;
	
	@RequestMapping(value="/Book.do")
	public String loadBook(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		model.addAttribute("url", "/html/dep_mem/book/html/book.jsp");
		model.addAttribute("link", "<link rel='stylesheet' type='text/css' href='html/dep_mem/book/css/book.css'>");
		model.addAttribute("script", "<script type='text/javascript' src='html/dep_mem/book/js/book.js'></script>");
		
		return "WavemSystem";							// servlet-context.xml
	}
	
	@RequestMapping(value="/searchBook.do")
	public String searchBook(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		Map<String, String> paraMap = new HashMap<String, String>();
		
		paraMap.put("bok_type", request.getParameter("bok_type"));
		paraMap.put("search_menu", ComProc.urlDecoder(request.getParameter("search_menu")));
		paraMap.put("search_value", ComProc.urlDecoder(request.getParameter("search_value")));
		paraMap.put("dep_code_value", ComProc.urlDecoder(request.getParameter("dep_code_value")));
		paraMap.put("dep_code_value", ComProc.urlDecoder(request.getParameter("dep_code_value")));
		
		String strRet = bookService.searchBook(request, paraMap);
		
		logger.info(strRet);
		response.getWriter().write(strRet);
		
		return null;							// servlet-context.xml
	}
	
	@RequestMapping(value = "/saveBook.do")
	public String saveBook(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException  {
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		String jsonData = URLDecoder.decode(request.getParameter("data"), "UTF-8");
		String ret = "";
		ret = bookService.saveBook(jsonData);
		response.getWriter().write(ret);
			
		return null;          //servlet-context.xml
	}
	
	@RequestMapping(value = "/updateBook.do")
	public String updateBook(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {  
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		String jsonData = URLDecoder.decode(request.getParameter("data"), "UTF-8");
		
		String ret = "";
		ret = bookService.updateBook(jsonData);
		response.getWriter().write(ret);
		
		return null;          //servlet-context.xml
	}
}