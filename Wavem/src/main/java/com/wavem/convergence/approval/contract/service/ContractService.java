package com.wavem.convergence.approval.contract.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface ContractService {
	public String searchContract(HttpServletRequest request, Map<String, String> paraMap);
	public String saveContract(String jsonData);

}
