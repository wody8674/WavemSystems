package com.wavem.convergence.mainView.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavem.convergence.mainView.dao.MainViewDao;

@Service("mainViewService")
public class MainViewServiceImpl implements MainViewService{

	@Autowired
	MainViewDao mainViewDao;
	
	@Override
	public String selectMain(Map<String, String> paraMap) {

		List<Map> ret = new ArrayList<Map>();
		ret = mainViewDao.selectMain(paraMap);
		JSONArray json = JSONArray.fromObject(ret);

		return json.toString();
	}
}
