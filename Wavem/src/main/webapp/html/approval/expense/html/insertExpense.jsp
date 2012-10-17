<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<!DOCTYPE html>
<html>
<head>
	<title>경비사용내역서 등록</title>
	<meta charset="UTF-8">
	<link href="../css/insertExpense.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	<script type="text/javascript" src="../js/insertExpense.js"></script>
	<script type="text/javascript" src="../../../com/lib/common.js"></script>
	<script type="text/javascript" src="../../../com/lib/jquery-ui.js"></script>
	<script type="text/javascript">
	
	    var datefield=document.createElement("input")
	    datefield.setAttribute("type", "date")
	    
		if (datefield.type!="date"){ //if browser doesn't support input type="date", initialize date picker widget:
	    	jQuery(function($){ //on document.ready
	    		$('#EXP_DATE').datepicker(clareCalendar);
	  	  });
	}
	</script>
</head>

<body>
<div id="wrapper">
	<form id="dataForm" method="post">
	<% 
		//key=EXP_CODE
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
		<input type="hidden" id="EXP_CODE" name="EXP_CODE" value="<%=key%>">
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
				<font size=4 face=굴림><h1><b>경비사용내역서</b></h1></font>
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
							<font size=2>성함&nbsp</font><select id="APP_TEAM" name="APP_TEAM" style="width:85px"></select>
						</td>
						<!-- 임원 -->
						<td align="right">
							<font size=2>직책&nbsp</font><select id="APP_EXECUTIVE_LEVEL"></select>
							<font size=2>성함&nbsp</font><select id="APP_EXECUTIVE" name="APP_EXECUTIVE"  style="width:85px"></select>
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
			⚫ 작성일자 :  <input type="date" id="EXP_DATE" name="EXP_DATE" value="<%=date%>"></br>
			⚫ 소 &nbsp&nbsp&nbsp&nbsp 속 : <input type="text" id="EXP_TEAM" name="EXP_TEAM" size="10"/>
		</div>
		
		<div id ="main2">
			⚫ 직 &nbsp&nbsp&nbsp&nbsp 책 : <input type="text" id="EXP_LEVEL" name="EXP_LEVEL" size="10"/> &nbsp&nbsp&nbsp&nbsp
			⚫ 성 &nbsp&nbsp&nbsp&nbsp 명 : <input type="text" id="EXP_NAME" name="EXP_NAME" size="10"/>
		</div>
		
		<div id="contents">
			<div id="text">
				⚫ 경비사용내역
			</div>
			
			<div id="btn">
				<input type="button" onclick="" value="추가">
	 			<input type="button" onclick=" " value="삭제">
			</div>
			 
			<!-- <div id="table"> -->
				<!-- <table id ="" cellspacing="0" border=1  align="center"> -->
				<table id ="" cellspacing="0" border=1 width=100%>
					<tr align="center">
						<!-- <td width="20">
						
						</td> -->
						<td>
							구분
						</td>
						<td>
							프로젝트
						</td>
						<td>
							규격
						</td>
						<td>
							수량
						</td>
						<td>
							단가
						</td>
						<td>
							날짜
						</td>
						<td>
							금액
						</td>
						<td>
							비고
						</td>
					</tr>
					
					<!-- <tr>
						<table cellpadding=0 cellspacing=0 id="dynamic_table" border="1">
					</tr> -->
					
					 <tr align="center">
						<td>
							<select name="sort">
								<option value="시간외 근무" selected>시간외 근무
								<option value="식비">식비
								<option value="교통비">교통비
								<option value="통행료">통행료
								<option value="대리운전비">대리운전비
								<option value="운반비">운반비
								<option value="유류대">유류대
								<option value="화환">화환
								<option value="경조사비">경조사비
								<option value="복리후생비">복리후생비
								<option value="출장여비">출장여비
								<option value="우편비">우편비
								<option value="통신비">통신비
								<option value="인쇄대금">인쇄대금
								<option value="문구대금">문구대금
								<option value="소모품비">소모품비
								<option value="접대비">접대비
								<option value="회의비">회의비
								<option value="기타">기타
							</select>
						</td>
						<td>
							<input type="text" size=15>
						</td>
						<td>
							<input type="text" size=8>
						</td>
						<td>
							<input type="text" size=8>
						</td>
						<td>
							<input type="text" size=12>
						</td>
						<td>
							<input type="text" size=12>
						</td>
						<td>
							<input type="text" size=12>
						</td>
						<td>
							<input type="text" size=12>
						</td>
					</tr>
				</table>
			<!-- </div> -->
		</div>
		
		<div id ="exp_rollback_reason">
			⚫ 반려사유 : </br>
			 <textarea rows="3" cols="134" id="ROLLBACK_REASON" name="ROLLBACK_REASON"></textarea>
		</div>
		
		<!-- 맨 아래 로고와 버튼 -->
		<div id="bottom">
		<hr>
			<div id="logo">
				<img src="../img/logo.png" height="60" width="120"/>
			</div>
			<div id="button">
				<input type=button value="임시저장"> <input type=button value="등록">
			</div>
		
		</div>
	</form>
</div>


</body>
</html>