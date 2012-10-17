<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
</head>

<body>
	<div width="100%" >
		<table cellspacing=0 cellpadding=0  width="100%" border="0" >
			<tr>
				<td>
					<h1>프로젝트 관리</h1>
				</td>
			</tr>
		</table>
		<hr>
		
		<div width="100%">
			<form id="searchProject" method="post">				
				<table cellspacing=0 cellpadding=0  width="100%" border="0"  style="margin:10px 0px 5px 0px;">
					<tr>
						<td>조회일자</td>
						<td>
							<input type="hidden" id="dep_code_value" name="dep_code_value" value=""> 
							<%
								SimpleDateFormat today = new SimpleDateFormat("yyyy/MM/dd");
								String date = today.format(Calendar.getInstance().getTime());
							%> 
							<input class="search_day" name="startDate" type="date" value="<%=date %>"/> ~ <input class="search_day" name="endDate" type="date" value="<%=date %>"/>
						</td>
						<td>  
							<select id="search" name="search">
								<option value = "">검색</option>
								<option value = "title">제목</option>
								<option value = "depname">부서명</option>
							</select>
							<input type="text" id="search_value" name="search_value" size="30"/>
						</td>
						<td width="50px" align="right" style="padding-right:10px">
							<input type="button" id="btnSearch" value="조회"/>
						</td>
						<td width="60px" align="right" style="padding-right:10px">
							<input id="btnReg" type="button" value="신규등록"/> 
						</td>	
					</tr>
				</table>
				
				<div id="group4" align="center">
					<table class="tab_class-a" id="tabContain" width="100%" >
					</table>
				</div>
			</form>
		</div>
		<table border="0" width="100%" cellspacing="0" cellpadding="0" align="center">
			<tr>
		 		<td>
		 			<form name="pageForm" method="post">
						<input type="hidden" id="total_page" name="total_page"/>
						<input type="hidden" id="current_page" name="current_page"/>
		 				<input type="hidden" id="page_count" name="page_count"/>
		 			</form>
		 			<center>
						<table id="pageNumber" width="70%" height="30">
							<tr>
								<td style="text-alian:center;">
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