<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form id="searchFree" method="post">				
	<div id="group3">
		<table cellspacing=0 cellpadding=0 width="100%">
			<tr>
				<h1>자유 게시판</h1>
			</tr>
		
			<tr>
				<td>  
					&nbsp 
					<select name="search">
						<option value = "">검색</option>
						<option value = "title">제목</option>
						<option value = "writer">작성자</option>
						<option value = "depname">부서명</option>
					</select>
					<input type="text" name="search_value">
					<input type="button" id="btnSearch" value="조회">
				</td>
				
				<td align="right">
					<input id="btnReg" type="button" value="등록"> 
				</td>	
			</tr>
		</table>

		<hr>
		</br>
		
		<div id="group4" align="center">
			<table class="tab_class-a" id=tabContain width=100% >
				<tr align="center">
		
				</tr>
			</table>
		</div>
	</div>
</form>