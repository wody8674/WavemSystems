<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
	
	<head>
		<meta charset="UTF-8">	
			<title>자료 등록</title>
			<link rel="stylesheet" type="text/css" href="../css/data_popup.css">
			<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
			<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
			<script type="text/javascript" src="../../../com/lib/jquery-ui.js"></script>
			<script type="text/javascript" src="../../../com/lib/common.js"></script>
			<script type="text/javascript" src="../js/data_popup.js"></script>
	</head>

<form id="dataForm" method="POST">	
	<div id = "all">
		<div id = "group1">
 			제 &nbsp&nbsp&nbsp&nbsp목 &nbsp
 			<input type="text" id="title" name="title" style="width:593px; height:23px;">
 		</div>	
 	
 		<div id = "group2">
 			첨부파일&nbsp
 			<input type="file" name="upload" multiple/>
 		</div>
 	
 		<div id = "group3">
 			프로젝트&nbsp
 			<select id = "pro_code" name = "pro_code">
			</select>
		</div>	
 
 		<div id = "group4">
	 		내&nbsp&nbsp&nbsp&nbsp 용 &nbsp
	 		<textarea id="data_content" name="data_content" style="width:590px; height:350px;"></textarea>
 		</div>
 		
 		<div id = "button1" align="right">
 			<input id="button" type="button" value="저장">             
 		</div>
 		
 	</div>
		<% String pcd = request.getParameter("pcd"); %>
		<input id="param_data_code" type="hidden" value="<%=pcd%>">	

</form>	