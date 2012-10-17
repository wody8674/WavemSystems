<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Enumeration" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="html/login/css/login.css">
</head>

<body>
	<table cellspacing="1" cellpadding="1" width="70%" border="0" align="center" bgcolor="white">
		<tr>
			<td>
			<h1>WaveM System에 오신것을 환영합니다.</h1>
			</td>
		</tr>
		<tr>
			<td>
			<h2>Login 후 사용 가능합니다.</br></h2>
			</td>
		</tr>
		<tr>
			<td style="border-bottom: #222 1px solid">&nbsp</td>
		</tr>
</table>
<br>
	<form id="myform" name="myform" action="/Wavem/login.go" method="post">
		<table cellspacing="1" cellpadding="1" width="450" border="0" align="center" bgcolor="white">
			<tr>
				<td width="60%" rowspan="5">
					<img src="html/login/img/LoginTitle.png">
				</td>
				<td>
					<font size="2px">아이디</font>
				</td>
				<tr>
					<td>   
						<input id="userid" type="text" name="userid" size="20" maxlength="12" class="input_textarea">
					</td>
				</tr>
				<tr>
					<td>
						<font size="2px">비밀번호</font>
					</td>
				</tr>
				<tr>
					<td>
						<input id="pwd" type="password" name="pwd" size="20" maxlength="12" class="input_textarea">
					</td>
				</tr>
				<tr>
					<td>
						<p align=right><input type="button" id="btnSubmit" style="font-family: 맑은 고딕" value="로그인" class="input_button" /></p>
					</td>
				</tr>
			</tr>
		</table>
	</form>
</body>
</html>