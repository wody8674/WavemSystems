<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">	
		  <title>프로젝트 등록</title>
		  <link rel="stylesheet" type="text/css" href="../css/pro_popup.css">
		  <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
		  <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		  <script type="text/javascript" src="../../../com/lib/jquery-ui.js"></script>
		  <script type="text/javascript" src="../../../com/lib/common.js"></script>
		  <script type="text/javascript" src="../js/pro_popup.js"></script>
		  <script type="text/javascript">

		  var datefield=document.createElement("input");
	      datefield.setAttribute("type", "date");
		 	
	      setCal();
		  
		  function setCal() {
		    
			if (datefield.type!="date"){ //if browser doesn't support input type="date", initialize date picker widget:
		    	jQuery(function($){ //on document.ready
		    	    $('.pro_start_day').datepicker(clareCalendar);
		    	    $('.pro_end_day').datepicker(clareCalendar);
		    	});
		    }
		  }
		</script>
	</head>
	
	<body>	
		<div id="main">
			<form id="dataForm" method="POST">
				<table cellpadding="0" border="0" width="100%">
					<tr>
						<td align="left"><font size='4' style="font-weight:bold;">프로젝트 등록/수정</font></td>
						<td width="6%" style="padding-top:20px" align="center"><input id="btnSave" type="button" value="저장"/></td>
						<td width="7%" style="padding-top:20px" align="center"><input id="btnClose" type="button" value="닫기" onclick="window.close()"/></td>
					</tr>
				</table>
				<hr>
				
				<table id="project" cellpadding="3" border="0" width="100%">
					<tr>
						<th width="150px">프로젝트 명</th>
						<td colspan=3>
							<input id="pro_name" name="pro_name" type="text" size=80>
						</td>
					</tr>				
					<tr>
						<th>프로젝트 기간</th>
						<td colspan=3>
							<input class="pro_start_day" id="pro_start_day" name="pro_start_day" type="date" size="20"/> ~ <input class="pro_end_day" id="pro_end_day" name="pro_end_day" type="date"size="20"/>
						</td>
					</tr>		
					<tr>
						<th>부서</th>
						<td >
							<select id = "dep_code" name = "dep_code">
							</select>
						</td>
						<th>프로젝트 책임자</th>
						<td>
							<% 
							String userNm = (String) request.getSession().getAttribute("userNm"); 
							String mem_code = (String) request.getSession().getAttribute("userMC");
							%>
							<input id="mem_code" name="mem_code" type="hidden" value="<%=mem_code %>"/>
							<input id="mem_name" disabled="disabled" type="text" value="<%=userNm%>"/>
						</td>
					</tr>				
					
				<!-- 
				<tr>
					<td>프로젝트 코드</td>
					<td>
						<input id="pro_code" name="pro_code" type="text" size=20>
					</td>
				</tr>
				 -->
					<tr>
						<th>발주처</th>
						<td>
							<input id="ord_org" name="ord_org" type="text" size=20>
						</td>
						<th>계약 금액</th>
						<td>
							<input id="con_price" name="con_price" type="text" size=20>
						</td>
					</tr>

					<tr>
						<th>프로젝트 설명</th>
						<td colspan=3> 
							<textarea id="pro_explain" name="pro_explain" style="width:800px; height:140px;"></textarea>
						</td>
					</tr>
					</table>
						<% 
						String pro_code = request.getParameter("pcd");
						%>
						<input type="hidden" id="param_pro_code" name="pro_code_key" value="<%=pro_code %>"> 
				
			</form>
			&nbsp
			<form id="partForm" method="POST">		
				 <table id="project" cellpadding="3" border="0" width="100%" align="center">
					<tr>
						<th rowspan="2">프로젝트 참여자</th>
						<td>
							<div id = "button" align="right">
								<input id="btnSubAdd" type=button value="추가"> 
							</div>
						</td>
					</tr>		
					
					<tr>
						<td> 
							<div width="800px" height="130px">
								<table id="tabPart" width="100%" border="1" cellspacing="0" cellpadding="0" style="border-collapse:collapse" align="right">
									<tr>
										<th align="center">이름</th>
										<th align="center">참여 기간</th>
										<th align="center">계약형태</th>
										<th align="center" width="270px" height="25px">주요 업무</th>
										<th align="center">삭제</th>
									</tr>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>