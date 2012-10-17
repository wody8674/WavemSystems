package com.wavem.convergence.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavem.convergence.common.dao.SidemenuDao;
import com.wavem.convergence.common.vo.SidemenuVo;

@Service("sidemenuService")
public class SidemenuServiceImpl implements SidemenuService {
	
	@Autowired
	SidemenuDao sidemenuDao;
	
	@Override
	public String selectSidemenu() throws Exception {
		
		List<Map> ret = new ArrayList<Map>();
		String strRet = "";
		ret = sidemenuDao.selectSidemenu();
		
		try {
			
			JSONArray json = JSONArray.fromObject(ret);
			strRet = json.toString();
			
		}
		catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public String selectSidemenuHtml() throws Exception {
		
		List<Map> ret = new ArrayList<Map>();
		StringBuffer strRet = new StringBuffer("");
		
		ret = sidemenuDao.selectSidemenu();
		
		// 메뉴 리스트에서 html 문서 작성
		if (ret.size() > 0) {
			strRet.append("<div id='list' class='content'>");
			strRet.append("<ul><li class='liSideMenu' value=''><h3>WaveM</h3></li></ul>");
//			strRet.append("<ul id='tree'>");
			
			strRet.append(makeSidemenu(ret));
			
//			strRet.append("</ul>");
			strRet.append("</div>");
		}
		else {
		}
		System.out.println(strRet.toString());
		return strRet.toString();
	}
	
	public String makeSidemenu(List<Map> sidemenuList) {
		
		StringBuffer strMake = new StringBuffer("");
		int preLevel = 0;
		
		for (Map sidemenu : sidemenuList) {			
			
			int dep_level = Integer.parseInt((String) sidemenu.get("DEP_LEVEL"));
			int max_level = Integer.parseInt((String) sidemenu.get("MAX_LVL"));
	
			if ( dep_level <= max_level ) {
				
				if ( preLevel < dep_level ) {
					
					String dep_code = (String) sidemenu.get("DEP_CODE");
					
					if (dep_code.equals("00000001")) dep_code = "";
					
					strMake.append("<ul>");
					strMake.append("<li class='liSideMenu' value='" + dep_code + "'>" + sidemenu.get("DEP_NAME"));
					
					preLevel = dep_level;
			
				} else if ( preLevel == dep_level ) {
					
					strMake.append("</li>");
					strMake.append("<li class='liSideMenu' value='" + sidemenu.get("DEP_CODE") + "'>" + sidemenu.get("DEP_NAME"));
					
				} else if ( preLevel > dep_level ) {
					
					int roopVal = preLevel;
					
					while(roopVal >= dep_level) {
						
						if (roopVal > dep_level) strMake.append("</ul>");

						if (roopVal == dep_level) {
							strMake.append("<li class='liSideMenu' value='" + sidemenu.get("DEP_CODE") + "'>" + sidemenu.get("DEP_NAME"));
							preLevel = dep_level;
						}

						roopVal--;
					}
				}
			} 
		}
		strMake.append("</li>");
		strMake.append("</ul>");
		
		return strMake.toString();
	}
	
}
