package com.wavem.convergence.community.notice.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface NoticeService {
	
	public String searchGubun_Dep(HttpServletRequest request);
	
	public String searchGubun_Pro(HttpServletRequest request);
	
	public String saveNoticeDetail(HttpServletRequest request, String strJson);
	
	public String updateNoticeDetail(HttpServletRequest request, String strJson);
	
	public String searchNotice(HttpServletRequest request, Map<String, String> paraMap);
	
}
