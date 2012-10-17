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

<body>
<table cellspacing=0 cellpadding=0  width="100%" border="0" >
	<tr>
		<td>
			<h1>장비 관리</h1>
		</td>
	</tr>
</table>
<hr>
	<div width="100%">
		<form id="searchEquipment" method="post">
				<table cellspacing=0 cellpadding=0 width="100%" border="0"   style="margin:10px 0px 5px 0px;">
					<tr>
						<td>장비분류</td>
						<td>
							<select id="equ_type" name="equ_type" style="width:100px"></select>
						</td>
						<td>사용분류</td>
						<td>
						<select id="use_type" name="use_type" style="width:100px"></select>
						</td>
						<td>
							<select name="search_menu" style="width:80px">
								<option value="">전체</option>
								<option value="mem_name">소유자</option>
								<option value="equ_name">상세정보</option>
								<option value="manufacturer">제조사</option>
							</select>
							<input type="text" name="search_value" size="30px">
						</td>
						<td width="50px" align="right" style="padding-right:10px">
							<input type="button" id="btnSearch" value="조회"	>
						</td>
						<td>
							<input type="hidden" id="dep_code_value" name="dep_code_value" value="">
						</td>
						<td width="50px" align="right" style="padding-right:10px">
							<input id="btnPop" type="button" value="신규장비등록">
						</td>
					</tr>
				</table>
				<!-- <div id="equ_info" align="center"  style="overflow-x: hidden; overflow-y: auto; height:700px;"> -->
				<div id="equ_info" align="center" >
					<table class="tab_class-a"id="tabContain" width="100%">
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
