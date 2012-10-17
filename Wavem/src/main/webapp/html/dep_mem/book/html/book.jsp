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
<link rel="stylesheet" type="text/css" href="html/book/css/book.css">
</head>

<body>
	<table cellspacing=0 cellpadding=0  width="100%" border="0" >
		<tr>
			<td>
				<h1>도서 관리</h1>
			</td>
		</tr>
	</table>
	<hr>
	<div width="100%" bgcolor="red">
		<form id="searchBook" method="post">
			<table cellspacing=0 cellpadding=0 width="100%" border="0"   style="margin:10px 0px 5px 0px;">
				<tr>
					<td>&nbsp&nbsp&nbsp도서분류
						<select id="bok_type" name="bok_type" style="width:100px"></select>
						&nbsp&nbsp&nbsp<select name="search_menu">
							<option value="">선택</option>
							<option value="bok_name">도서명</option>
							<option value="publisher">출판사</option>
							<option value="owner">보유자</option>
						</select>
						<input name="search_value" type="text" size="30px">
					</td>
					<td width="50px" align="right" style="padding-right:10px">
						<input id="btnSearch" type="button" name="search"	value="조회"	>
						<input id="dep_code_value" type="hidden" name="dep_code_value">
					</td>				
					<td width="50px" align="right" style="padding-right:10px">
						<input id="btnPop" type="button" value="신규등록">
					</td>
				</tr>
			</table>
			<div id="mem_info" align="center">
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
</body>