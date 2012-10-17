 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">	
		<title>장비 등록(수정)</title>
		<link rel="stylesheet" type="text/css" href="../css/equ_new.css">
		<script type="text/javascript" src="../../../com/lib/common.js"></script>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		<script type="text/javascript" src="../js/equ_new.js"></script>
		
		<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../../../com/lib/jquery-ui.js"></script>
		<script type="text/javascript">
	    var datefield=document.createElement("input")
	    datefield.setAttribute("type", "date")
	    
		if (datefield.type!="date"){ //if browser doesn't support input type="date", initialize date picker widget:
	    	jQuery(function($){ //on document.ready
	    		$('#sale_day').datepicker(clareCalendar);
	  	  })
	}
	</script>
	</head>

	<body>
		<div id="main">			
			<form id="dataForm" method="POST"	>
				<table cellpadding="0" border="0" width="100%">
					<tr>
						<td align="left"><font size='4' style="font-weight:bold">장비 등록/수정</font></td>
						<td width="15%" style="padding-top:20px" align="center"><input id="btnSave" type="button" value="저장"></td>
						<td width="15%" style="padding-top:20px" align="center"><input id="btnClose" type="button" value="닫기" onclick="window.close()"></td>
					</tr>
				</table>
				<hr>
				<table id="equipment" cellpadding="3" border="0" width="100%">
					<tr>
						<th align="">장비코드</th>
						<%
							String equCode = request.getParameter("param");
						%>
						<td colspan="3"><input type="text" id="equ_code" name="equ_code" value="<%=equCode %>" size="13px"></td>
					</tr>
					<tr>
						<th align="right">소유자</th>
						<td colspan="3"><select id="mem_post" name="mem_post" style="width:120px">
						</select>&nbsp<select id="mem_name" name="mem_name" style="width:120px">
						
						</select></td>
					</tr>
					<tr>
						<th align="right">장비구분</th>
						<td><select id="equ_type" name="equ_type" style="width:120px"><option></option></select></td>
						<th align="right">사용구분</th>
						<td><select id="use_type" name="use_type" style="width:122px"><option></option></select></td>
					</tr>
					<tr>
						<th align="right">Serial No.</th>
						<td colspan="3"><input type="text" id="equ_no" name="equ_no" size="50px"></td>
					</tr>
					<tr>
						<th align="right">상세정보</th>
						<td colspan="3"><textarea id="equ_name" name="equ_name" style="width:330px; height:130px"></textarea></td>
					</tr>
					<tr>
						<th align="right">제조사</th>
						<td><input type="text" id="manufacturer" name="manufacturer" size="13px"></td>
						
						<th align="right">구입일</th>
						<td><input type="date" id="sale_day" name="sale_day" size="13px"></td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>