package com.wavem.convergence.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MultiInsertCtrl {
	
public List<Map<String, String>> getDataListFormJsonMap(Map dataMap) {
	
		List retList = new ArrayList();
		Iterator<String> a = dataMap.keySet().iterator(); //키값 조회
		List equalList = new ArrayList(); 
		
		String[] mapKey = new String[dataMap.size()];
		int mapKeyNum = 0;
		int strListSize = 0;
		
		while(a.hasNext()) {
			mapKey[mapKeyNum++] = a.next();
		}
		
		// 객체가 list인지 판별
		if (equalList.getClass().equals(dataMap.get(mapKey[0]).getClass())) {
			
			strListSize = ((List) dataMap.get(mapKey[0])).size();
			
			for (int i=0; i<strListSize; i++) {
				
				Map<String, String> paraMap = new HashMap<String, String>();

				for (int j=0; j<mapKeyNum; j++) {
					paraMap.put(mapKey[j], (String) ((List) dataMap.get(mapKey[j])).get(i));
				}
				retList.add(paraMap);
			}
		} else {
			retList.add(dataMap);
		}
		
		return retList;
	}
}
