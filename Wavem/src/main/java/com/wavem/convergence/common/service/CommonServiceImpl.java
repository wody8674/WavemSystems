package com.wavem.convergence.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavem.convergence.common.dao.CommonDao;
import com.wavem.convergence.common.vo.CommonVo;

@Service("commonService")
public class CommonServiceImpl implements CommonService {
	
	@Autowired
	CommonDao commonDao;
	
	// @Override
	// public String selectMenu() throws Exception {
	//		
	// List<CommonVo> ret = new ArrayList<CommonVo>();
	// String strRet = "";
	// ret = commonDao.selectMenu();
	//		
	// try {
	// JSONArray json = JSONArray.fromObject(ret);
	// strRet = json.toString();
	//			
	// } catch (Exception e) {
	//			
	// e.printStackTrace();
	// }
	//		
	// return strRet;
	// }
	
	@Override
	public String selectMenuHtml(HttpServletRequest request, Map paraMap) throws Exception {
		List<CommonVo> ret = new ArrayList<CommonVo>();
		StringBuffer strRet = new StringBuffer("");
		
		ret = commonDao.selectMenu(paraMap);
		
		// 메뉴 리스트에서 html 문서 작성
		if (ret.size() > 0) 
		{
			strRet.append("<div id='globalNavi' class='content'>");
			strRet.append("<ul id='nav'>");
			
			strRet.append(makeMenu(ret, request));

			strRet.append("</ul>");
			strRet.append("</div>");
			
		}
		else {
		}
		
		return strRet.toString();
	}
	
	public String makeMenu(List<CommonVo> menuList, HttpServletRequest request) {
		
		StringBuffer strMake = new StringBuffer("");
		String chkId = "00";
		
		int list_length = menuList.size();
		int i = 0;
		
		for (CommonVo menu : menuList) {
			if (!menu.getMn_menu_id().equals(chkId)) {
				i++;
				strMake.append("<li>");
				strMake.append("<font size=4 face=\"맑은 고딕\"><b>");
				strMake.append(menu.getMn_menu_nm());
				strMake.append("</b></font>");
				
				strMake.append("<ul>");
				
				for (CommonVo submenu : menuList) {
					if (menu.getMn_menu_id().equals(submenu.getMn_menu_id())) {
						i++;
						strMake.append("<li>");
						strMake.append("<a href='" + request.getContextPath() + submenu.getUrl() + "'>" + submenu.getSb_menu_nm() + "</a>");
						strMake.append("</li>");
					}
				}
				
				strMake.append("</ul>");
				strMake.append("</li>");
				
				if(i < list_length){
					strMake.append("<li>");
					strMake.append("<font size=2 face=\"맑은 고딕\" color=\"lightgray\">");
					strMake.append("|");
					strMake.append("</font>");
					strMake.append("</li>");
				}
			}
			
			chkId = menu.getMn_menu_id();
		}
		
		return strMake.toString();
	}
}
