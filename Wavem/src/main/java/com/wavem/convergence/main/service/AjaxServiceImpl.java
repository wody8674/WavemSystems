package com.wavem.convergence.main.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.dsig.XMLObject;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavem.convergence.main.dao.AjaxDao;
import com.wavem.convergence.main.vo.AjaxVo;

@Service("ajaxService")
public class AjaxServiceImpl implements AjaxService {
	
	@Autowired
	AjaxDao ajaxDao;

	@Override
	public String selectParam(Map param) throws Exception {
		
		List ret = new ArrayList();
		AjaxVo avo = new AjaxVo();
		Map retMap = new HashMap();
		
		StringBuffer xmlData = new StringBuffer("");
		
		ret = ajaxDao.selectParam(param);
		
		if (ret.size() > 0) {		
			avo = (AjaxVo) ret.get(0);
			
			xmlData.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			xmlData.append("<userid>\n");
			xmlData.append(avo.getUser_id()+"\n");
			xmlData.append("</userid>\n");
			
			xmlData.append("<assigncd>\n");
			xmlData.append(avo.getAssign_cd()+"\n");
			xmlData.append("</assigncd>\n");
			
			xmlData.append("<usernm>\n");
			xmlData.append(avo.getUser_nm()+"\n");
			xmlData.append("</usernm>\n");
			
			xmlData.append("<levelnm>\n");
			xmlData.append(avo.getLevel_nm()+"\n");
			xmlData.append("</levelnm>\n");
			
			xmlData.append("<hp>\n");
			xmlData.append(avo.getHp()+"\n");
			xmlData.append("</hp>\n");
			
			xmlData.append("<email>\n");
			xmlData.append(avo.getEmail()+"\n");
			xmlData.append("</email>\n");
		}
		
		return xmlData.toString();
	}

	@Override
	public String selectJson(Map<String, String> param) throws Exception {
		
		List<AjaxVo> ret = new ArrayList<AjaxVo>();
		AjaxVo ajaxVo = new AjaxVo();
		
		ret = ajaxDao.selectJson(param);
		
		if (ret.size() > 0) {
			ajaxVo = ret.get(0);
		}
		
		JSONObject jsonObj = JSONObject.fromObject(ajaxVo);
		
		return jsonObj.toString();
	}

	@Override
	public String saveJson(String param) throws Exception {
		
		Map<String, String> paramap = new HashMap<String, String>();
		JSONObject jsonObj = JSONObject.fromObject(param);
		
		paramap = (Map<String, String>) JSONObject.toBean(jsonObj, java.util.HashMap.class, paramap);
		System.out.println(paramap);

		return null;
	}
}
