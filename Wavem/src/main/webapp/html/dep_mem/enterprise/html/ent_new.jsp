<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">	
		<title>업체 등록(수정)</title>
		<link rel="stylesheet" type="text/css" href="../css/ent_new.css">
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		<script type="text/javascript" src="../js/ent_new.js"></script>
		
		<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../../../com/lib/jquery-ui.js"></script>
		<script type="text/javascript">
		    var datefield=document.createElement("input")
		    datefield.setAttribute("type", "date")
		    
			if (datefield.type!="date"){ //if browser doesn't support input type="date", initialize date picker widget:
		    	jQuery(function($){ //on document.ready
		    		$('#creation_day').datepicker(clareCalendar);
		    		$('#destruction_day').datepicker(clareCalendar);
		  	  })
		}
		</script>
	</head>
	
	<body>
		<div id="main">
			<form id="dataForm" method="post">
				<table cellpadding="0" border="0" width="100%">
					<tr>
						<td align="left"><font size='4' style="font-weight:bold">업체등록/수정</font></td>
						<td width="15%" style="padding-top:20px" align="center"><input id="btnSave" type="button"  value="저장"></td>
						<td width="15%" style="padding-top:20px" align="center"><input id="btnClose" type="button" value="닫기" onclick="window.close()"></td>
					</tr>
				</table>
				<hr>
				<table id="enterprise" cellpadding="3" border="0" width="100%">
					<tr>
						<th align="right">업체명</th>
						<td><input type="text" name="ent_name"></td>
						<th align="right">코드</th>
						<td><input type="text"	name="ent_code"></td>	
					</tr>
					<tr>
						<th align="right">사업자명</th>
						<td><input type="text" name="ent_head"></td>
						<th align="right">사업자등록번호</th>
						<td><input type="text" name="ent_no"></td>
					</tr>
					<tr>
						<th align="right">주소</th>
						<td colspan="3"><input type="text" name="address" size="70px"></td>
					</tr>
					<tr>
						<th align="right">연락처</th>
						<td colspan="3"><input type="text" name="tel"></td>
					</tr>
					<tr>
						<th align="right">생성일자</th>
						<td><input type="date" id="creation_day" name="creation_day"></td>
						
						<th align="right">소멸일자</th>
						<td><input type="date" id="destruction_day" name="destruction_day"></td>
					</tr>
					<tr>
						<th  align="right">비고</th>
						<td colspan="3"><input type="text" name="etc" size="70px"></td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>