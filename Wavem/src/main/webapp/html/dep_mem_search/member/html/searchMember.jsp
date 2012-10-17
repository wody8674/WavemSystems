 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import="com.wavem.convergence.common.controller.PagingHelper" %>
<%
	String psCurrPage = request.getParameter("hdn_curr_page") == null ? "1" : request.getParameter("hdn_curr_page").trim();
%>
<%
	long plTotalCnt = 2436L; // 전체레코드수
	long plRowRange = 17L; // 결과목록에 보여지는 레코드수
	long plPageRange = 10L; // 페이지출력범위
	long plCurrPage = Long.parseLong(psCurrPage); // 현재페이지
%>

<html>
	<head>
		<link rel="stylesheet" type="text/css" href="html/dep_mem_search/department/css/searchMember.css">
	</head>

	<table cellspacing=0 cellpadding=0 width="100%">
		<tr>
			<h1>사원 조회</h1>
		</tr>
	</table>
	<hr>
	
	<div width="100%">	
		<form id="SearchMember" method="post">
			<table cellspacing=0 cellpadding=0 width="100%" border="0"   style="margin:10px 0px 5px 0px;">
				<tr>
					<td>&nbsp&nbsp&nbsp직책
						<select id="mem_post" name="mem_post" style="width:100px"></select>
						&nbsp&nbsp&nbsp계약형태
						<select id="con_type" name="con_type" style="width:100px"></select>
						&nbsp&nbsp&nbsp<select name="search_menu" style="width:80px">
							<option value="">선택</option>
							<option value="mem_id">ID</option>
							<option value="mem_code">사번</option>
							<option value="mem_name">이름</option>
						</select>
						<input type="text" name="search_value" size="30x">
						&nbsp&nbsp&nbsp<input type="button"  id="btnSearch" value="조회"	>
						<input type="hidden" id="dep_code_value" name="dep_code_value" value="">
				</tr>
				<tr><td colspan="3" height="1" bordercolor-top="#000"></td></tr>
			</table>
			<div id="mem_info" align="center" class="mainDiv">
				<table class="tab_class-a" id="tabContain" width="100%">
				</table>
			</div>
		</form>
		<table border="0" width="100%" cellspacing="0" cellpadding="0" align="center">
			<tr>
				<td>
					<form name="pageForm" method="post">
						<input type="hidden" name="hdn_curr_page" value="<%=psCurrPage%>"/>
					</form>
					<center>
						<table width="70%" height="30">
							<tr>
								<td style="text-alian:center;">
									<%=PagingHelper.instance.autoPaging(plTotalCnt, plRowRange, plPageRange, plCurrPage) %>
								</td>	
							</tr>
						</table>
					</center>
				</td>
			</tr>
		</table>	
	</div>
</html>