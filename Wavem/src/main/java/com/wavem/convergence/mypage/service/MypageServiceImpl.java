package com.wavem.convergence.mypage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavem.convergence.mypage.dao.MypageDao;

@Service("mypageService")
public class MypageServiceImpl implements MypageService {
	
	@Autowired
	MypageDao mypageDao;
	
	@Override
	public String searchMypage(HttpServletRequest request, Map<String, String> param) {
		
		List<Map> ret = new ArrayList<Map>();
		
		ret = mypageDao.searchMypage(param);
		
		JSONArray json = JSONArray.fromObject(ret);
		
		return json.toString();
	}
}
