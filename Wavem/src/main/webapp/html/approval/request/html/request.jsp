<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<div id="group3">
	<table cellspacing=0 cellpadding=0 width="100%">
		<tr>
			<td>
				<h1>기안서</h1>
			</td>
		</tr>
		<tr>
			<td align="left" width=200>
				<label name="searchdate">조회일자</label>&nbsp&nbsp
				<input id="SEARCH_DATE" name="SEARCH_DATE" type="date" size=12 />
			</td>
				
			<td align="left" width=180>
				<label name="search">결재상태</label>&nbsp
				<!-- 결재상태 선택 -->
				<select name="state">
					<option value="결재대기" selected>결재대기
					<option value="결재처리">결재처리
					<option value="결재완료">결재완료
				</select>
			</td>
			
			<td align="left">
				<!-- 검색방법 선택 -->
				<select id="search_menu" name="search_menu">
					<option value="작성자">작성자
					<option value="제목" selected>제목
				</select>	
				<!-- 검색어 입력란 -->
				<input id="search_value" name="search_value" type="text" size=15>
				<!-- 조회버튼 -->
				<input id="btnSearch" type=button value="조회">
			</td>
			
			<td align="right">
				<!-- 등록 버튼을 클릭 시 새로운 창이 뜨게된다(휴가원 등록창) -->
				<input id="btnNew" type=button value="등록">
			</td>
		</tr>
	</table>
	
	<hr>
	
	<div id="request_info" align="center">
		<table class="tab_class-a" id=tabContain  width=100% >
			
			<tr align="center">
			</tr>
		</table>
	</div>
</div>
