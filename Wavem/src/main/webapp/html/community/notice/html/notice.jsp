 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="html/community/notice/css/notice.css">
</head>

<body>
	<center>
	<div id= "main" width="100%">
		<table cellspacing=0 cellpadding=0  width="100%" border="0" >
			<tr>
				<td>
					<h1>공지사항</h1>
				</td>
			</tr>
		</table>
		<hr>
		<form id="searchNotice" method="post">				
				<table cellspacing=0 cellpadding=0 width="100%" border="0"   style="margin:10px 0px 5px 0px;">
					<tr>
						<td>
						 구분
						</td>
						
						<td>
							<select id="notice_gubun1" name="notice_gubun1" style="width: 100px">
								<option value = "">전체</option>
								<option value = "BA000">회사공지</option>
								<option value = "BA001">부서공지</option>
								<option value = "BA002">프로젝트공지</option>
							</select>
							<select id="notice_gubun2" name="notice_gubun2" style="width: 130px">
							</select>
							
							 &nbsp
							<select id="search_gubun">
								<option value = "">검색</option>
								<option value = "title">제목</option>
								<option value = "writer">작성자</option>
							</select>
							<input type="text" id="search_value">
							<input type="button" id="btnSearch" value="조회">
						</td>
						
						<td width="50px" align="right" style="padding-right:10px">
							<input id="btnReg" type="button" value="공지사항 등록"> 
						</td>
					</tr>
				</table>

				<div id="mem_info" align="center" width="100%">
					<table class="tab_class-a" id="tabContain" width="100%">
					</table>
				</div>
		</form>
		<table border="0" width="100%" cellspacing="0" cellpadding="0" align="center">
			<tr>
		 		<td>
		 			<form name="pageForm" method="post">
						<input type="hidden" id="total_page" name="total_page"/>
						<input type="hidden" id="current_page" name="current_page"/>
		 				<input type="hidden" id="page_count" name="page_count"/>
		 			</form>
		 			<center>
						<table id="pageNumber" width="70%" height="30">
							<tr>
								<td style="text-alian:center;">
								</td>	
							</tr>
						</table>
					</center>
		 		</td>
		 	</tr>
		</table>
	</div>
	</center>
</body>
</html>