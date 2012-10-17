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
</head>

<body>
	<table cellspacing=0 cellpadding=0  width="100%" border="0" >
		<tr>
			<td>
				<h1>이슈 관리</h1>
			</td>
		</tr>
	</table>
	<hr>
	
	<div width="100%">
		<form id="searchIssues" method="post">
			<table cellspacing=0 cellpadding=0 width="100%" border="0" style="margin:10px 0px 5px 0px;">
			<tr>
				<td><center>조회일자</center></td>
				<td>
					<input class="search_day" name="startDate" type="date"/> ~ <input class="search_day" name="endDate" type="date"/>
				</td>
				<td >
					<select name="search">
						<option value = "">검색</option>
						<option value = "title">제목</option>
						<option value = "writer">작성자</option>
						<option value = "depname">부서명</option>
					</select>
					<input type="text" name="search_value" size="30">
				</td>
				
				<td width="50px" align="right" style="padding-right:10px">
					<input type="button" id="btnSearch" value="조회">
					<input type="hidden" id="dep_code_value" name="dep_code_value" value="">
				</td>
					
				<td width="50px" align="right" style="padding-right:10px">
					<input id="btnReg" type="button" value="이슈 등록"> 
				</td>
			</tr>
		</table>	
			
			<div id="mem_info" align="center" width="100%">
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
</html>