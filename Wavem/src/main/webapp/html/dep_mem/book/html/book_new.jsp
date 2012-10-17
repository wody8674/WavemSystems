 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">	
		<title>도서 등록(수정)</title>
		<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../../../com/lib/common.js"></script>
		<script type="text/javascript" src="../../../com/lib/jquery-1.7.1.js"></script>
		<script type="text/javascript" src="../js/book_new.js"></script>
		<script type="text/javascript" src="../../../com/lib/jquery-ui.js"></script>
		<link href="../css/book_new.css" rel="stylesheet" type="text/css" media="screen" />
		<script type="text/javascript">
	    var datefield=document.createElement("input")
	    datefield.setAttribute("type", "date")
	    
		if (datefield.type!="date"){ //if browser doesn't support input type="date", initialize date picker widget:
	    	jQuery(function($){ //on document.ready
	    		$('#own_day').datepicker(clareCalendar);
		        $('#sale_day').datepicker(clareCalendar);
	  	  });
	}
	</script>
	</head>
	
	<body>
		<div id="main">
			<form id="dataForm" method="POST"	>
				<table cellpadding="0" border="0" width="100%">
					<tr>
						<td align="left"><font size='4' style="font-weight:bold">도서등록/수정</font></td>
						<td width="15%" style="padding-top:20px" align="center"><input id="btnSave" type="image" src="../../../com/img/btn_Save.png"></td>
						<td width="15%" style="padding-top:20px" align="center"><input id="btnClose" type="image" src="../../../com/img/btn_Close.png" onclick="window.close()"></td>
					</tr>
				</table>
				<hr>
				
				<table id="book" cellpadding="3" border="0" width="100%">
					<tr>
					<%
						String bokCode = request.getParameter("param");
					%>
						<th align="right">코드</th>
						<td width="50px"><input type="text"	id="bok_code" name="bok_code" value="<%=bokCode%>"></td>
						<th align="right">분류</th>
						<td><select id="bok_type" name="bok_type"  style="width:125px"></select>
						</td>
					</tr>
					<tr>
						<th align="right">도서명</th>
						<td colspan="3"><input type="text" id="bok_name" name="bok_name" size="56px"></td>
					</tr>
					<tr>
						<th align="right">출판사</th>
						<td colspan="3"><input type="text" id="publisher" name="publisher" size="30px"></td>
					</tr>
					<tr>
						<th align="right">구매자</th>
						<td colspan="3">
							<select  id="sale_post" name="sale_post"  style="width:100px">
								<option></option>
							</select>
							<select id="sale_name" name="sale_name"  style="width:100px">
								<option></option>
							</select>
						</td>
					</tr>
					<tr>
						<th align="right">구입일</th>
						<td colspan="3"><input type="date" id="sale_day" name="sale_day"></td>
					</tr>
					<tr>
						<th align="right">보유자</th>
						<td colspan="3">
							<select id="own_post" name="own_post" style="width:100px">
								<option></option>
							</select>
							<select id="own_name" name="own_name"  style="width:100px">
								<option></option>
							</select>
						</td>
					</tr>
					<tr>
						<th align="right">보유일</th>
						<td colspan="3"><input type="date" id="own_day" name="own_day"></td>
					</tr>
				</table>
			</form> 
		</div>
	</body>
</html>