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
<link rel="stylesheet" type="text/css" href="html/enterprise/css/enterprise.css">
</head>

<body>
	<table cellspacing=0 cellpadding=0  width="100%" border="0" >
		<tr>
			<td>
				<h1>업체 관리</h1>
			</td>
		</tr>
	</table>
	<hr>
	<div width="100%">
		<form id="searchEnterprise" method="post">
			<table cellspacing=0 cellpadding=0 width="100%" border="0"   style="margin:10px 0px 5px 0px;">
				<tr>
					<td width="50px" align="right" style="padding-right:10px">
						<input id="btnPop" type="button" value="신규업체등록">
						<input type="hidden" id="dep_code_value" name="dep_code_value">
					</td>
				</tr>
			</table>
			<table class="tab_class-a" id="tabContain" width="100%">
			</table>
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
</html>