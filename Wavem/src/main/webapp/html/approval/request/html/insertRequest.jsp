<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>기안서 등록</title>
	<meta charset="UTF-8">
	<link href="../css/insertRequest.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	<script type="text/javascript" src="../js/insertRequest.js"></script>
	<script type="text/javascript" src="../../../com/lib/jquery-ui.js"></script>
	<script type="text/javascript">
	
	    var datefield=document.createElement("input")
	    datefield.setAttribute("type", "date")
	    
		if (datefield.type!="date"){ //if browser doesn't support input type="date", initialize date picker widget:
	    	jQuery(function($){ //on document.ready
	    		$('#REQ_DATE').datepicker(clareCalendar);
	  	  })
	}
	</script>
</head>

<body>
<div id="wrapper">
	<form id="dataForm" method="post">
		<% 
		//key=REQ_CODE
		String key = request.getParameter("key");
		//key2=APP_CODE
		String key2 = request.getParameter("key2");
		//key3=APP_DAM2
		String key3 = request.getParameter("key3");
		//key4=APP_STATE
		String key4 = request.getParameter("key4");
		//key5=APP_TEAM2
		String key5 = request.getParameter("key5");
		//key6=APP_EXECUTIVE2
		String key6 = request.getParameter("key6");
		//userMC=USER_MC
		String userMC = (String) request.getSession().getAttribute("userMC");
		%>
		<input type="hidden" id="REQ_CODE" name="REQ_CODE" value="<%=key%>">
		<input type="hidden" id="APP_CODE" name="APP_CODE" value="<%=key2%>">
		<input type="hidden" id="APP_DAM2" name="APP_DAM2" value="<%=key3%>">
		<input type="hidden" id="APP_STATE" name="APP_STATE" value="<%=key4%>">
		<input type="hidden" id="APP_TEAM2" name="APP_TEAM2" value="<%=key5%>">
		<input type="hidden" id="APP_EXECUTIVE2" name="APP_EXECUTIVE2" value="<%=key6%>">
		<input type="hidden" id="USER_MC" name="USER_MC" value="<%=userMC%>">
	<!-- 글씨와 결재란 박스 묶어주는 div -->
	<div id ="top">
		<!-- 글씨 -->
		<div id="lefttop">
			<font size=6 face=굴림><h1><b>기안서</b></h1></font>
		</div>
		<!-- 결재란 테이블 -->
		<div id = "righttop">
			<table border=1 width=350 cellspacing=0>
				<tr>
					<!-- rowspan으로 열을 합쳐준다 -->
					<td align="center" rowspan="2" width=30>
						<h4>결</br></br> </br>재</h4>
					</td>
					<td align="center" >
						<h4>담당</h4>
					</td>
					<td align="center">
						<h4>팀장</h4>
					</td>
					<td align="center">
						<h4>임원</h4>
					</td>
					<td align="center">
						<h4>대표이사</h4>
					</td>
				</tr>
				<tr>
					<td>
						<select id="APP_DAM_LEVEL"></select>
						<select id="APP_DAM" name="APP_DAM" ></select>
					</td>
					<td>
						<select id="APP_TEAM_LEVEL"></select>
						<select id="APP_TEAM" name="APP_TEAM" ></select>
					</td>
					<td>
						<select id="APP_EXECUTIVE_LEVEL"></select>
						<select id="APP_EXECUTIVE" name="APP_EXECUTIVE" ></select>
					</td>
					<td>
						<input type="hidden" name="APP_MASTER" size=8 value="00000000">
						<input type="text" id="APP_MASTER" size=8 value="김화룡">
					</td>
				</tr>
			</table>
		</div>
	</div>
	</br></br></br></br></br></br></br></br></br>
	
	<!-- 메인화면 여기선 입력란-->
	<div id ="main">
	<hr>
		⚫ 기안일자 :  <input type="date" id="REQ_DATE" name="REQ_DATE"></br>
		⚫ 소 &nbsp&nbsp&nbsp&nbsp 속 :  <select id="REQ_TEAM"></select>
	</div>
	
	<div id ="main2">
		⚫ 직 &nbsp&nbsp&nbsp&nbsp 책 :  <select id="REQ_LEVEL"></select>&nbsp&nbsp&nbsp&nbsp
		⚫ 성 &nbsp&nbsp&nbsp&nbsp 명 :  <select id="REQ_NAME"></select>&nbsp&nbsp&nbsp&nbsp
	</div>
	
	<div id="title">
		⚫ 제 &nbsp&nbsp&nbsp&nbsp 목 : <input type="text" id="REQ_TITLE" size=90 name="REQ_TITLE">
	</div>
	
	<div id ="contents">
		⚫ 내 &nbsp&nbsp&nbsp&nbsp 용 : </br>
		<textarea rows="4" cols="92" id="REQ_CONTENTS" name="REQ_CONTENTS"></textarea>
		<hr>
	</div>
	
	<div id="bottom">
		<div id="logo">
			<img src="../img/logo.png" height="60" width="120"/>
		</div>
		<div id="button">
			<input id="btnSemiSave" type=button value="임시저장"> <input id="btnSave" type=button value="등록">
		</div>
	</div>
	</form>
</div>


</body>
</html>