<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="html/dep_mem_search/department/css/searchDepartment.css">
	</head>

	<body>
		<div width="100%" >
			<table cellspacing=0 cellpadding=0  width="100%" border="0" >
				<tr>
					<td>
						<h1>부서 조회</h1>
					</td>
				</tr>
			</table>
			<hr>
			<div width="100%" id="dep_info" align="center">
				<form id="searchDepartment" method="post">
					<table cellspacing=0 cellpadding=0  width="100%" border="0"  style="margin:10px 0px 5px 0px;">
						<tr>
							<td><input type="hidden" id="dep_code_value" name="dep_code_value"/></td>
							<td  width="80px" align="center" style="padding-right:10px">사용여부</td>
							<td>
								<select id="search_menu" name="search_menu">
									<option value="">전체</option>
									<option value="used">사용</option>
									<option value="unused">미사용</option>
								</select>
							</td>
						</tr>
					</table>
					<table class="tab_class-a" id="tabContain" width="100% ">
					</table>
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
		</div>
	</body>
</html>