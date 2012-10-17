<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.URLConnection"%>
<%@page import="java.net.URL"%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
String urlPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+""+request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">	
	<title>WaveM</title>
	<link rel="stylesheet" type="text/css" href="html/com/css/bgView_Main.css">
</head >

<body>

<header>
	<table border="0" width="100%" height="100%">
		<tr>
			<td width="15%">
				<center>
					<a href="mainView.do"><img src="html/com/img/logo.png" width="150" height="80"></a>
				</center>
			</td>
			<td>
				<%
				String user_mc = (String) request.getSession().getAttribute("userMC");
				BufferedReader br = new BufferedReader(new InputStreamReader((new URL(urlPath+"/selectMenu.go?param="+user_mc)).openConnection().getInputStream(), "UTF-8"));	
				String strMenu = br.readLine();
				out.print(strMenu);
				%>
			</td>
			<td width = "10%">
				<center>
					<font size=3 face="맑은 고딕">
					&nbsp<%out.print((String) request.getSession().getAttribute("userNm"));%>님. 
					</font>
					</br></br>
					<a href="Mypage.do"><img src="html/com/img/logout-button-md.png" width="40" height="40"></a>
					<!-- <input id="btnMypage" type="image"  src="html/com/img/logout-button-md.png" width="40" height="40"/> -->
					<input id="btnLogout" type="image"  src="html/com/img/logout-button-md.png" width="40" height="40"/>
				</center>
			</td>
		</tr>
	</table>
</header>

<section>
	<jsp:include page="${url}"></jsp:include>
</section>


<footer>
</footer>

<script type="text/javascript" src="html/com/lib/common.js"></script>
<script type="text/javascript" src="html/com/lib/jquery-1.7.1.js"></script>
<script type="text/javascript" src="html/com/lib/jquery.droppy.js"></script>
<script type="text/javascript" src="html/com/lib/jquery-ui.js"></script>
<script type="text/javascript">
   	$(function() {
       	$("#nav").droppy(); 
       	$(".liSideMenu").click(function() {
       		
       		$("#dep_code_value").attr({value: "0000000"+$(this).val()});
       		fn_search();
       	});
       	
       	$("#btnLogout").click(fn_logout);
   	});
</script>
${script}

</body>
</html>