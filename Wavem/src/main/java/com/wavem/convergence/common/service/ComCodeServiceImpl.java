package com.wavem.convergence.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavem.convergence.common.dao.ComCodeDao;

@Service("comCodeService")
public class ComCodeServiceImpl implements ComCodeService {

	@Autowired
	ComCodeDao comCodeDao;
	
	@Override
	public String searchDepCom() {
		List<Map> ret = new ArrayList<Map>();
		ret = comCodeDao.searchDepCom();
		JSONArray json = JSONArray.fromObject(ret);
		return json.toString();
	}
	
	@Override
	public String searchProCom() {
		List<Map> ret = new ArrayList<Map>();
		ret = comCodeDao.searchProCom();
		JSONArray json = JSONArray.fromObject(ret);
		return json.toString();
	}


	@Override
	public String searchPostCom() {
		List<Map> ret = new ArrayList<Map>();
		ret = comCodeDao.searchPostCom();
		JSONArray json = JSONArray.fromObject(ret);
		return json.toString();
	}

	@Override
	public String searchComCode(Map paraMap) {
		List<Map> ret = new ArrayList<Map>();
		ret = comCodeDao.searchComCode(paraMap);
		JSONArray json = JSONArray.fromObject(ret);
		return json.toString();
	}

}
