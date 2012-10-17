<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<div id="group3">
	<%
	//userMP=USER_MP
	String userMP = (String) request.getSession().getAttribute("userMP");
	%>
	<form id="searchBreak" method="post">
	<table cellspacing=0 cellpadding=0 border=0 width="100%">
		<tr>
			<h1>휴가원</h1>
		</tr>
		<tr>
			<td align="left" width=310>
				<label name="searchdate">조회일자</label>&nbsp&nbsp
				<%
					SimpleDateFormat today = new SimpleDateFormat("yyyy/MM/dd");
					String date = today.format(Calendar.getInstance().getTime());
				%> 
				<input id="SEARCH_DATE" name="SEARCH_DATE" type="date" value="<%=date%>" size=12 /> ~ <input id="SEARCH_DATE" name="SEARCH_DATE" type="date" value="<%=date%>" size=12 />
			</td>
				
			<td align="left" width=190>&nbsp&nbsp
				<label name="search">결재상태</label>&nbsp
				<!-- 결재상태 선택 -->
				<select id="SEARCH_STATE" name="SEARCH_STATE" style="width:100px"></select>
			</td>
			
			<td align="left">&nbsp&nbsp
				<!-- 검색방법 선택 -->
				<select name="search_menu">
					<option value="">선택</option>
					<option value="mem_name">작성자</option>
					<option value="search_title">휴가종류</option>
				</select>	
				<!-- 검색어 입력란 -->
				<input id="search_value" name="search_value" type="text" size=15>
				<!-- 조회버튼 -->
				<input id="btnSearch" type=button value="조회">
			</td>
			
			<td align="right">
				<input type="hidden" id="USER_MP" name="USER_MP" value="<%=userMP%>">
				<!-- 등록 버튼을 클릭 시 새로운 창이 뜨게된다(휴가원 등록창) -->
				<input id="btnNew" type=button value="등록">
			</td>
		</tr>
	</table>
	</form>
	<hr>
	
	<div id="break_info" align="center">
		<table class="tab_class-a" id=tabContain width=100% >
			
			<tr align="center">
			</tr>
		</table>
	</div>
</div>




