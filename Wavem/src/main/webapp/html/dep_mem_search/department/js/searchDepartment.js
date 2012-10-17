$(document).ready(function(){
	fn_search('1')
	$("#search_menu").change(fn_search_select);
});

//*****************************************************************************
//ajax통신

function fn_search_select(){
	fn_search('1');
}

function fn_search(vPage) {
	
	var parmData = $("#searchDepartment").serialize();
	
	var param = "";
	
	$("#current_page").attr("value", vPage);
	param += "page=" + vPage;
	
	$("#page_count").attr("value", parseInt((vPage / 10), 10));

	$.getJSON(urlPathContext + "/searchDepartment.do?"+parmData + "&" + param, {format: 'json'}, function(data) {
		var vData = "";
		
		$("#tabContain").html("");
		
		vData += "<tr align='center'>";
		vData += "<th width='10%'>부서코드</th>";
		vData += "<th width='17%'>부서명</th>";
		vData += "<th colspan='2' width='22%'>부서장</th>";
		vData += "<th width='15%'>상위부서</th>";
		vData += "<th>생성일자</th>";
		vData += "<th>소멸일자</th>";
		vData += "<th>비고</th>";
		vData += "</tr>";
		
		$("#tabContain").append(vData);
		
		$.each(data, function(i, n) {
			vData = "";
			vData += "<tr align='center' ondblclick='fn_detail(this)'>";
			vData += "	<td >" + n.DEP_CODE + "</td>";
			vData += "	<td >" + n.DEP_NAME + "</td>";
			vData += "	<td width='9%'>" + n.MEM_POST_NM + "</td>";
			vData += "	<td>" + n.MEM_NAME + "</td>";
			vData += "	<td>" + n.UPPER_DEP_NM + "</td>";
			vData += "	<td>" + n.CREATION_DAY + "</td>";
			vData += "	<td>" + n.DESTRUCTION_DAY + "</td>";
			vData += "	<td>" + n.ETC + "</td>";
			vData += "</tr>";
			
			$("#tabContain").append(vData);
			
			$("#total_page").attr("value", n.TOTALPAGE);
		});
		
		$("#pageNumber").html("");
		var vData = makePageButton(Number($("#total_page").val()), Number($("#current_page").val()), Number($("#page_count").val()));	
		$("#pageNumber").append(vData);
	});
}

function makePageButton(plTotalCnt, plCurrPage, plPageCount)
{
	var vData = "";
	
	if (plTotalCnt == 0) {
		return "";
	}
		
	vData += "<table cellpadding=0 cellspacing=0 border=0 width = '100%' height = '100%'>\n";
	vData += "<tr>";
	vData += "<td>";

	if (plCurrPage > 1) {
		vData += "<input type='button' style=\"color:black; background-color:lightgray;\" id='btnView' value='처음' onclick='goPage(1)'/>\n";
	}
	else {
		vData += "<input type='button' style=\"color:black; background-color:lightgray;\" id='btnView' value='처음'/>\n";
	}
		
	vData += "</td>";
	vData += "<td>";
		
	if (plCurrPage > 10) {
		vData += "<input type='button' style=\"color:black; background-color:lightgray;\" id='btnView' value='이전 10개' onclick='goPage(" + plCurrPage - (plPageCount * 10) + ")'/>\n";
	}else{
		vData += "<input type='button' style=\"color:black; background-color:lightgray;\" id='btnView' value='이전 10개'/>\n";
	}
	vData += "</td>";
	
	var startPage = (10 * plPageCount) + 1;
	var endPage = 0;
	
	if(plTotalCnt < 10){
		endPage = (plTotalCnt + 1);
	}
	else{
		endPage = startPage + 10;
	}
		
	for(var index = startPage; index < endPage; index++)
	{
		var tsFontBegin = "<font size=3>";
		var tsFontEnd = "</font>\n";
		
		if(index == plCurrPage){
			tsFontBegin = "<font size=3><b>";
			tsFontEnd = "</b></font>\n";
		}
		
		vData += "<td>";
		vData += tsFontBegin;
		
		
		var str_ButtonStyle = "";
		
		if(index == plCurrPage){
			str_ButtonStyle = "style=\"font-weight:bold; color:white; background-color:teal;\"";
		}else{
			str_ButtonStyle = "style=\"color:black; background-color:lightgray;\"";
		}

		vData += "<input type='button' " + str_ButtonStyle + " id='btnView' value='" + index + "' onclick='goPage(" + index + ")'/>\n";
		
		vData += tsFontEnd;
		vData += "</td>";
	}
		
	vData += "<td>";
		
	if ((plPageCount * 10) + 1 <= plTotalCnt) {
		vData += "<input type='button' style=\"color:black; background-color:lightgray;\" id='btnView' value='다음 10개' onclick='goPage(" + (plPageCount * 10) + 1 + ")'/>\n";
	}else{
		vData += "<input type='button' style=\"color:black; background-color:lightgray;\" id='btnView' value='다음 10개'/>\n";
	}
		
	vData += "</td>";
	vData += "<td>";
	
	if (plTotalCnt > plCurrPage) {
		vData += "<input type='button' style='color:black; background-color:lightgray;' id='btnView' value='마지막' onclick='goPage(" + plTotalCnt + ")'/>\n";
	}
	else {
		vData += "<input type='button' style=\"color:black; background-color:lightgray;\" id='btnView' value='마지막'/>\n";
	}
	
	vData += "</td>";
	vData += "</tr>";
	vData += "</table>\n";
	
	return vData;
}

function goPage(v_page)
{
	fn_search(Number(v_page));
}