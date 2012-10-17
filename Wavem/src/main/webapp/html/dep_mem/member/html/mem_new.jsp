<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">	
		<title>사원 등록(수정)</title>
		
		<script type="text/javascript" src="../../../com/lib/common.js"></script>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		<script type="text/javascript" src="../js/mem_new.js"></script>
		
		<link rel="stylesheet" type="text/css" href="../css/mem_new.css"/>
		<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../../../com/lib/jquery-ui.js"></script>
		<script type="text/javascript">
	    var datefield=document.createElement("input")
	    datefield.setAttribute("type", "date")
	    
		if (datefield.type!="date"){ //if browser doesn't support input type="date", initialize date picker widget:
	    	jQuery(function($){ //on document.ready
	    		$('#birthday').datepicker(clareCalendar);
	    		$('#special_day').datepicker(clareCalendar);
	    	    $('#hired_day').datepicker(clareCalendar);
	    	    $('#retire_day').datepicker(clareCalendar);
	  	  })
	}
	</script>
	</head>


	<body>
		<div id="main">
			<form id="dataForm" method="POST">
				<table cellpadding="0" border="0" width="100%">
					<tr>
						<td align="left"><font size='4' style="font-weight:bold">사원등록/수정</font></td>
						<td width="15%" style="padding-top:20px" align="right"><input id="btnSave" type="button" value="저장"></td>
						<td width="15%" style="padding-top:20px" align="center"><input id="btnClose" type="button" value="닫기" onclick="window.close()"></td>
					</tr>
				</table>
				<hr>
		
				<table id="member" cellpadding="3" border="0" width="100%">
					<tr>
						<th>아이디</th>
						<td >
							<input type="text"	id="mem_id" name="mem_id" value=""/>
						</td>
						<th>패스워드</th>
						<td>
							<input type="password" id="password" name="password"/>
						</td>
					</tr>
					
					<tr>
						<th>이름</th>
						<td >
							<input type="text" id="mem_name" name="mem_name">
						</td>
						<th>사번</th>
						<% 
							String memCode = request.getParameter("param");
						%>
						<td>
							<input type="text"	id="mem_code" name="mem_code" value="<%=memCode %>">
						</td>
					</tr>
					
					<tr>
						<th>부서</th>
						<td >
							<select id="dep_code" name="dep_code" style="width:157px"></select>
						</td>
						<th>직책</th>
						<td>
							<select id="mem_post" name="mem_post" style="width:157px"></select>
						</td>
					</tr>
					
					<tr>
						<th>계약형태</th>
						<td >
							<select id="con_type" name="con_type" style="width:157px"></select>
						</td>
						<th>업체명</th>
						<td>
							<select id="ent_name" name="ent_name" style="width:157px"></select>
						</td>
					</tr>
					
					<tr>
						<th>H.P</th>
						<td >
							<input type="text" id="hp" name="hp">
						</td>
						<th>TEL</th>
						<td>
							<input type="text" id="tel" name="tel">
						</td>
					</tr>
					
					<tr>
						<th>주소</th>
						<td colspan="3">
							<input type="text" id="address" name="address" size="59px">
						</td>
					</tr>
					
					<tr>
						<th>E-Mail</th>
						<td >
							<input type="text" id="mail" name="mail" >
						</td>
						<th>메신져</th>
						<td>
							<input type="text" id="messenger" name="messenger">
						</td>
					</tr>
					
					<tr>
						<th>생일</th>
						<td>
							<input type="date" id="birthday" name="birthday">
						</td>
						<th>기념일</th>
						<td>
							<input type="date" id="special_day" name="special_day">
						</td>
					</tr>
					<tr>
						<th>입사일</th>
						<td>
							<input type="date" id="hired_day" name="hired_day">
						</td>
						<th>퇴사일</th>
						<td>
							<input type="date" id="retire_day" name="retire_day">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>