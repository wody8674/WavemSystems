package com.wavem.convergence.login.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavem.convergence.login.dao.LoginDao;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	@Autowired
	LoginDao loginDao;
	
	@Override
	public String sessionInfo(HttpServletRequest request) {
		Map<String, String> loginMap = new HashMap<String, String>();
		
		loginMap.put("userNm", (String) request.getSession().getAttribute("userNm"));
		loginMap.put("userID", (String) request.getSession().getAttribute("userID"));
		loginMap.put("userMC", (String) request.getSession().getAttribute("userMC"));
		loginMap.put("userDC", (String) request.getSession().getAttribute("userDC"));
		loginMap.put("userDC_NM", (String) request.getSession().getAttribute("userDC_NM"));
		loginMap.put("userMP", (String) request.getSession().getAttribute("userMP"));
		loginMap.put("userMP_NM", (String) request.getSession().getAttribute("userMP_NM"));
		loginMap.put("userCT", (String) request.getSession().getAttribute("userCT"));
		loginMap.put("userHD", (String) request.getSession().getAttribute("userHD"));
		loginMap.put("userRD", (String) request.getSession().getAttribute("userRD"));
		
		
		JSONArray json = JSONArray.fromObject(loginMap);
		System.out.println(json.toString());
		return json.toString();
	}
	
	
	
	@Override
	public String login(HttpServletRequest request, Map<String, String> paraMap) {
		
		Map<String, String> loginMap = new HashMap<String, String>();
		List<Map> ret = new ArrayList<Map>();
		String strRet = "fail";
		ret = loginDao.login(paraMap);
		
		if(ret.size() > 0){
			strRet = "success";
			//세션처리
			
			HttpSession session = request.getSession();
			loginMap = ret.get(0);
			
			//세션부여
			session.setAttribute("userNm", loginMap.get("MEM_NAME"));
			session.setAttribute("userID", loginMap.get("MEM_ID"));
			session.setAttribute("userMC", loginMap.get("MEM_CODE"));
			
			session.setAttribute("userDC", loginMap.get("DEP_CODE"));
			session.setAttribute("userDC_NM", loginMap.get("DEP_CODE_NM"));
			session.setAttribute("userMP", loginMap.get("MEM_POST"));
			session.setAttribute("userMP_NM", loginMap.get("MEM_POST_NM"));
			
			session.setAttribute("userCT", loginMap.get("CON_TYPE"));
			session.setAttribute("userHD", loginMap.get("HIRED_DAY"));
			session.setAttribute("userRD", loginMap.get("RETIRE_DAY"));
			
		}
		return strRet;
	}


}
