<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서 등록/수정/</title>

<script type="text/javascript" src="../../../com/lib/common.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/dep_new.js"></script>
<script type="text/javascript" src="../../../com/lib/jquery-ui.js"></script>

<link rel="stylesheet" type="text/css" href="../css/dep_new.css">
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
var datefield = document.createElement("input")
	datefield.setAttribute("type", "date")
    
if (datefield.type!="date"){ //if browser doesn't support input type="date", initialize date picker widget:
   	jQuery(
		function($){ //on document.ready
   	   		$('#creation_day').datepicker(clareCalendar);
   			$('#destruction_day').datepicker(clareCalendar);
 	 	}
 	)
}
</script>
</head>

<body>
<div id="main">
	<form id="dataForm" method="POST">
		<table cellpadding="0" border="0" width="100%">
			<tr>
				<td align="left"><font size='4' style="font-weight:bold">부서등록/수정</font></td>
				<td width="15%" style="padding-top:20px" align="right"><input id="btnSave" type="button" value="저장"></td>
				<td width="15%" style="padding-top:20px" align="center"><input id="btnClose" type="button" value="닫기" onclick="window.close()"></td>
			</tr>
		</table>
		<hr>

		<table id="department" cellpadding="3" border="0" width="100%">
			<tr>
				<th align="center">부서명</th>
				<td >
					<input type="text" id="dep_name" name="dep_name" size="15px">
				</td>
				<th align="right">부서코드</th>
				<%
					String depCode = request.getParameter("param");
				%>
				<td>
					<input type="text" id="dep_code" name="dep_code" value="<%=depCode %>" size="8px">
				</td>
			</tr>
			<tr>
				<th align="right">부서장</th>
				<td colspan="3">
					<select id="mem_post" name="mem_post" style="width: 125px"></select>
					<select id="dep_head" name="dep_head" style="width: 130px"></select>
				</td>
			</tr>
			<tr>
				<th>상위부서</th>
				<td colspan="3">
					<%
						String dep_code_value = request.getParameter("side");
					%>
					<input type="hidden" id="dep_code_value" name="dep_code_value" value="<%=dep_code_value%>"/>
					<select id="upper_dep" name="upper_dep" style="width: 125px"></select>
				</td>
			</tr>
			<tr>
				<th>생성일자</th>
				<td colspan="3">
					<%
						SimpleDateFormat today = new SimpleDateFormat("yyyy/MM/dd");
						String date = today.format(Calendar.getInstance().getTime());
					%> 
					<input type="date" id="creation_day" name="creation_day" value="<%=date %>" size="15px">
				</td>
			</tr>
			<tr>
				<th>소멸일자</th>
				<td colspan="3">
					<input type="date" id="destruction_day" name="destruction_day" size="15px">
				</td>
			</tr>
			<tr>
				<th>비고</th>
				<td colspan="3">
					<input type="text" id="etc" name="etc" size="40px">
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>