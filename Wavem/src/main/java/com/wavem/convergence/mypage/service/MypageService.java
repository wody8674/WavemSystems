package com.wavem.convergence.mypage.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface MypageService {
	public String searchMypage(HttpServletRequest request, Map<String, String> param);
}
