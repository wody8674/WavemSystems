package com.wavem.convergence.dep_mem.equipment.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface EquipmentService {
	public String searchEquipment(HttpServletRequest request, Map<String, String> param);	
	public String saveEquipment(HttpServletRequest request, String strJson);
	public String updateEquipment(HttpServletRequest request, String strJson);
}
