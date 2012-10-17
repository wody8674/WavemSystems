package com.wavem.convergence.approval.expense.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface ExpenseService {

	public String searchExpense(HttpServletRequest request,	Map<String, String> paraMap);
	public String saveExpense(HttpServletRequest request, String jsonData);
	public String searchInsertExpense(HttpServletRequest request, Map<String, String> paraMap);
	public String semisaveExpense(HttpServletRequest request, String strJson);
	public String updateExpense(String jsonData);

}
