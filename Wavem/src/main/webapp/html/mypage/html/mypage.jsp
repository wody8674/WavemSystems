<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="html/mypage/css/mypage.css">
	</head >

	<body>
		<table cellspacing=0 cellpadding=0  width="100%" border="0" >
			<tr>
				<td>
					<h1><font size="5">My Page</font></h1>
				</td>
			</tr>
		</table>
		
		<div width="80%">
			<form id="myPage" method="post">
				<table class="tab_class-a" id="tabContain">
					<tr height="50px">
						<th align="right">ID</th>
						<td><input type="text" id="mem_id" name="mem_id" disabled="true"></td>
						<td></td>
						<td></td>
					</tr>
					<tr height="50px">
						<%
							String mem_code = (String) request.getSession().getAttribute("userMC");
						%>
						
						<td align="right">사번</td>
						<td><input type="text" id="mem_code" name="mem_code" value="<%= mem_code %>" disabled="true"></td>
					</tr >
					<tr  height="50px">
						<td align="right">이름</td>
						<td><input type="text" id="mem_name" name="mem_name"></td>
					</tr>
					<tr  height="50px">
						<td align="right">P.W</td>
						<td><input type="text" id="password_1" name="password_1"></td>
						<td align="right">확인</td>
						<td><input type="text" id="password_2" name="password_2"></td>
					</tr>
					<tr  height="50px">
						<td align="right">부서</td>
						<td><input type="text" id="dep_name" name="dep_name" disabled="true"></td>
						<td align="right">직책</td>
						<td><input type="text" id="mem_post" name="mem_post" disabled="true"></td>
					</tr>
					<tr  height="50px">	
						<td align="right">계약형태</td>
						<td><input type="text" id="con_type" name="con_type" disabled="true"></td>
						<td align="right">업체명</td>
						<td><input type="text" id="con_name" name="ent_name" disabled="true"></td>
					</tr>
					<tr  height="50px">
						<td align="right">H.P</td>
						<td><input type="text" id ="hp" name="ph"></td>
						<td align="right">자택</td>
						<td><input type="text" id ="tel" name="tel"></td>
					<tr  height="50px">
						<td align="right">주소</td>
						<td colspan="5"><input type="text" id="adderss" name="ph" size="65px"></td>
					</tr>
					<tr  height="50px">
						<td align="right">생일</td>
						<td><input type="date" id="birthday"name="birthday"></td>
						<td align="right">기념일</td>
						<td><input type="date" id="special_day" name="special_day"></td>
					</tr>
					<tr  height="50px">
						<td align="right">입사일</td>
						<td><input type="date" id="hired_day" name="hired_day" disabled="true"></td>
						<td align="right">퇴사일</td>
						<td><input type="date" id="retire_day" name="retire_day" disabled="true"></td>
					</tr>
					<tr  height="50px">
						<td colspan="4" align="right"><input type="button" value="저장"></td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>