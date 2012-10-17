package com.wavem.convergence.dep_mem.book.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavem.convergence.common.MultiInsertCtrl;
import com.wavem.convergence.dep_mem.book.dao.BookDao;

@Service("bookService")
public class BookServiceImpl implements BookService {

	
	@Autowired
	BookDao bookDao;
	
	@Override
	public String searchBook(HttpServletRequest request, Map<String, String> param) {
		
		List <Map> ret = new ArrayList<Map>();
		
		ret = bookDao.searchBook(param);
		
		JSONArray json = JSONArray.fromObject(ret);
		
		return json.toString();
	}
	
	
	@Override
	public String saveBook(String strJson) {
		
		Map<String, String> paramap = new HashMap<String, String>();
		
		JSONObject jsonObj = JSONObject.fromObject(strJson);
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		
		String ret = bookDao.saveBook(paramap);
		
		return ret;
	}

	
	@Override
	public String updateBook(String strJson) {
		Map<String, String> paramap = new HashMap<String, String>();
		JSONObject jsonObj = JSONObject.fromObject(strJson);
		String ret = "";
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		ret = bookDao.updateBook(paramap);
		
		return ret;
	}

}
