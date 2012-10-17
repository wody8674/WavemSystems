package com.wavem.convergence.dep_mem.book.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface BookService {
	public String searchBook(HttpServletRequest request, Map<String, String> param);
	public String saveBook(String strJson);
	public String updateBook(String strJson);
}
