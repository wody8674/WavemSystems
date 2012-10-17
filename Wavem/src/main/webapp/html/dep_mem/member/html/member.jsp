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
					<h1>사원 관리</h1>
				</td>
			</tr>
		</table>
		<hr>

		<div width="100%">
			<form id="searchMember" method="post">
				<table cellspacing=0 cellpadding=0 width="100%" border="0"   style="margin:10px 0px 5px 0px;">
					<tr>
						<td><center>직책</center></td>
						<td width="10px">
							<select id="mem_post" name="mem_post" style="width:100px"></select>
						</td>
						<td width="90px"><center>계약형태</center></td>
						<td width="150px">
							<select id="con_type" name="con_type" style="width:100px"></select>
						</td>
						<td >
							<select name="search_menu" style="width:80px">
								<option value="">전체</option>
								<option value="mem_id">ID</option>
								<option value="mem_code">사번</option>
								<option value="mem_name">이름</option>
							</select>
							<input type="text" name="search_value" size="30x">
						</td>
						<td>
						
						<td width="50px" align="right" style="padding-right:10px">
							<input id="btnSearch"  type="button" value="조회">
								<input type="hidden" id="dep_code_value" name="dep_code_value" value="">
						</td>
							
						<td width="50px" align="right" style="padding-right:10px">
							<input id="btnPop" type="button" value="신규등록"/>
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
