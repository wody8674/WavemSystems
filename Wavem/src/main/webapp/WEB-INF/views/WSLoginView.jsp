<%@ page import="java.io.InputStreamReader"%>
<%@ page import="java.io.BufferedReader"%>
<%@ page import="java.net.URLConnection"%>
<%@ page import="java.net.URL"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
String urlPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+""+request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">	
	<title>WaveM System</title>
	<link href="html/com/img/wavem.ico" rel="icon" type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="html/com/css/bgView_Login.css">
	<script>
		document.createElement('header');
		document.createElement('section');
		document.createElement('footer');
	</script>
</head >

<body>
<header>
	<!-- 
	<table id="header">
		<tr>
			<td rowspan="2" valign="middle">
				<a href="mainView.do" class=yim><img src="html/com/img/logo.png" width="150px" height="100px"></a>
			</td>
		</tr>
	</table>
	 -->
 </header>
 
 <section>
 <table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0" align="center">
 	<tr>
 		<td width="100%" valign="middle">
	 		<table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0" align="center">
	 			<tr>
	 				<td width="100%">
	 					<jsp:include page="${url}"  ></jsp:include>
	 				</td>
	 			</tr>	 		
	 		</table>
 		</td>
 	</tr>
 </table>
 </section>

<footer>
	<table align="center">
		<tr>
			<td>
				서울 강남구 역삼1동 838-9 거암빌딩 9층 &nbsp &nbsp &nbsp TEL : 02-553-2525&nbsp &nbsp &nbsp FAX : 02-553-0049
			</td>
		</tr>
	</table>
</footer>

<script type="text/javascript" src="html/com/lib/jquery-1.7.1.js"></script>
<script type="text/javascript" src="html/com/lib/jquery.droppy.js"></script>
<script type="text/javascript" src="html/login/js/login.js"></script>
<script type="text/javascript">
   	$(function() {
       	$("#nav").droppy(); 
   	});
</script>
${script}
</body>
</html>