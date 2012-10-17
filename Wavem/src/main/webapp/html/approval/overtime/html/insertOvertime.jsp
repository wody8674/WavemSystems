<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<!DOCTYPE html>
<html>
<head>
	<title>야간/휴일 근무 확인서 등록</title>
	<meta charset="UTF-8">
	<link href="../css/insertOvertime.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	<script type="text/javascript" src="../js/insertOvertime.js"></script>
	<script type="text/javascript" src="../../../com/lib/common.js"></script>
	<script type="text/javascript" src="../../../com/lib/jquery-ui.js"></script>
	<script type="text/javascript">
	
	    var datefield=document.createElement("input")
	    datefield.setAttribute("type", "date")
	    
		if (datefield.type!="date"){ //if browser doesn't support input type="date", initialize date picker widget:
	    	jQuery(function($){ //on document.ready
	    	    $('#NIG_DATE').datepicker(clareCalendar);
	    	    $('#WORK_DATE').datepicker(clareCalendar);
	  	  });
	}
	</script>
</head>

<body>
<div id="wrapper">
	<form id="dataForm" method="post">
		<% 
			//key=BRE_CODE
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
		<input type="hidden" id="NIG_CODE" name="NIG_CODE" value="<%=key%>">
		<input type="hidden" id="APP_CODE" name="APP_CODE" value="<%=key2%>">
		<input type="hidden" id="APP_DAM2" name="APP_DAM2" value="<%=key3%>">
		<input type="hidden" id="APP_STATE" name="APP_STATE" value="<%=key4%>">
		<input type="hidden" id="APP_TEAM2" name="APP_TEAM2" value="<%=key5%>">
		<input type="hidden" id="APP_EXECUTIVE2" name="APP_EXECUTIVE2" value="<%=key6%>">
		<input type="hidden" id="USER_MC" name="USER_MC" value="<%=userMC%>">
	<!--  글씨와 결재란 박스 묶어주는 div -->
	<div id ="top">
		<!-- 글씨 -->
		<div id="lefttop">
			<font size=4 face=굴림><h1><b>야간/휴일 근무 확인서</b></h1></font>
		</div>
		<!-- 결재란 테이블 -->
		<div id = "righttop">
			<table width=470 border=1 width=350 cellspacing=0>
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
					<!-- 담당 -->
					<td align="right">
						<font size=2>직책&nbsp</font><input type="text" id="APP_DAM_LEVEL" size="4">
						<font size=2>성함&nbsp</font><input type="text" id="APP_DAM" name="APP_DAM" size="4">
					</td>
					<!-- 팀장 -->
					<td align="right">
						<font size=2>직책&nbsp</font><select id="APP_TEAM_LEVEL"></select>
						<font size=2>성함&nbsp</font><select id="APP_TEAM" name="APP_TEAM" style="width:85px" ></select>
					</td>
					<!-- 임원 -->
					<td align="right">
						<font size=2>직책&nbsp</font><select id="APP_EXECUTIVE_LEVEL"></select>
						<font size=2>성함&nbsp</font><select id="APP_EXECUTIVE" name="APP_EXECUTIVE" style="width:85px"></select>
					</td>
					<!-- 대표이사 -->
					<td width=40>
						<input type="hidden" name="APP_MASTER" size=7 value="00000000">
						<input type="text" id="APP_MASTER" size=7 value="김화룡">
					</td>
				</tr>
			</table>
		</div>
	</div>
	</br></br></br></br></br></br></br></br></br>
	
	<div id ="main">
	<hr>
	<%
		SimpleDateFormat today = new SimpleDateFormat("yyyy/MM/dd");
		String date = today.format(Calendar.getInstance().getTime());
	%> 
		⚫ 작성일자 :  <input type="date" id="NIG_DATE" name="NIG_DATE" value="<%=date%>"></br>
		⚫ 소 &nbsp&nbsp&nbsp&nbsp 속 : <input type="text" id="NIG_TEAM" name="NIG_TEAM" size="10"/> 
	</div>
	
	<div id ="main2">
		⚫ 직 &nbsp&nbsp&nbsp&nbsp 책 : <input type="text" id="NIG_LEVEL" name="NIG_LEVEL" size="10"/> &nbsp&nbsp&nbsp&nbsp
		⚫ 성 &nbsp&nbsp&nbsp&nbsp 명 : <input type="text" id="NIG_NAME" name="NIG_NAME" size="10"/>
	</div>
	
	<div id="kind">
		⚫  구 &nbsp&nbsp&nbsp&nbsp 분 :
	</div>
	
	<div id ="work_date">
		⚫ 근무일자 : <input type="date" id="WORK_DATE" name="WORK_DATE" value="<%=date%>">
	</div>
	
	<div id ="time">
		⚫ 근무시간 : 출근시간 <input type="text" id="NIG_STARTTIME" name="NIG_STARTTIME">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
					프로젝트 : <select id="NIG_PROJECT" name="NIG_PROJECT"> </select>
					</br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
				  	퇴근시간 <input type="text" id="NIG_OVERTIME" name="NIG_OVERTIME">
	</div>
	
	<!-- textarea를 사용 -->
	<div id ="reason">
		⚫ 근무사유 : </br><textarea rows="4" cols="125" id="NIG_REASON" name="NIG_REASON"></textarea>
	</div>
	
	<div id ="nig_rollback_reason">
		⚫ 반려사유 : </br>
		 <textarea rows="3" cols="125s" id="ROLLBACK_REASON" name="ROLLBACK_REASON"></textarea>
	</div>
	
	<!-- 맨 아래 로고와 버튼 -->
	<div id="bottom">
	<hr>
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