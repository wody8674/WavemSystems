package com.wavem.convergence.dep_mem_search.member.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavem.convergence.dep_mem_search.member.dao.SearchMemberDao;

@Service("searchMemberService")
public class SearchMemberServiceImpl implements SearchMemberService {
	
	@Autowired
	SearchMemberDao searchMemberDao;
	
	@Override
	public String searchMember(HttpServletRequest request, Map<String, String> param) {
		
		List<Map> ret = new ArrayList<Map>();
		
		ret = searchMemberDao.searchMember(param);
		
		JSONArray json = JSONArray.fromObject(ret);
		
		return json.toString();
	}
}
