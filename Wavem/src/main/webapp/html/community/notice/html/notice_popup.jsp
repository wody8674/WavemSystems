<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>공지사항 등록/수정/</title>
	
	<script type="text/javascript" src="../../../com/lib/common.js"></script>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	<script type="text/javascript" src="../js/notice_popup.js"></script>
	<script type="text/javascript" src="../../../com/lib/jquery-ui.js"></script>
	
	<link rel="stylesheet" type="text/css" href="../css/notice_popup.css">
	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
</head>
 
<BODY>
	<div id="main">
		<form id="dataForm" method="POST">
			<%
				String popup_flag = request.getParameter("flag");
			%>
			<input type="hidden" id="popup_flag" name="popup_flag" value="<%=popup_flag%>"/>
			<%
				String boa_code = request.getParameter("boa_code");
			%>
			<input type="hidden" id="boa_code" name="boa_code" value="<%=boa_code%>"/>
			<%
				String boa_type = request.getParameter("boa_type");
			%>
			<input type="hidden" id="boa_type" name="boa_type" value="<%=boa_type%>"/>
			<%
				String user_mc = (String)request.getSession().getAttribute("userMC");
			%>
			<input type="hidden" id="user_mc" name="user_mc" value="<%=user_mc%>"/>
			
			<input type="hidden" id="mem_code" name="mem_code" />
			
			<table cellpadding="0" border="0" width="100%">
			<tr>
				<td align="left"><font size='4' style="font-weight:bold; font-family: 맑은 고딕;">공지사항 등록/수정</font></td>
				<td width="15%" style="padding-top:20px" align="right"><input id="btnSave" type="button" value="저장"></td>
				<td width="15%" style="padding-top:20px" align="center"><input id="btnClose" type="button" value="닫기" onclick="window.close()"></td>
			</tr>
			</table>
			<hr>
			<table id="notice" cellpadding="3" border="0" width="100%">
				<tr>
					<th>구분</th>
					<td>
						<select id="notice_gubun1" name="notice_gubun1" style="width: 125px">
							<option value = "BA000">회사</option>
							<option value = "BA001">부서</option>
							<option value = "BA002">프로젝트</option>
						</select>
						<select id="notice_gubun2" name="notice_gubun2" style="width: 130px">
						</select>
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>
						<input type="text" id="notice_title" name="notice_title" style="width:95%; height:25px;"/>
					</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td>
						<input type="file" name="upload" multiple/>
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea id="notice_content" name="notice_content" style="width:95%; height:140px;"></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>
 </BODY>
</HTML>