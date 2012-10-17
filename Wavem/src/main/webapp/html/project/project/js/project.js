$(document).ready(function(){
	fn_search('1');
	
	$("#btnSearch").click(fn_search_button);
	$("#btnReg").click(fn_pop);
	
	var datefield=document.createElement("input")
	datefield.setAttribute("type", "date")
	    
	if (datefield.type!="date"){ //if browser doesn't support input type="date", initialize date picker widget:
		jQuery(function($){ //on document.ready
			$('.search_day').datepicker(clareCalendar);
		})
	}
});

//*****************************************************************************
//ajax통신

function fn_pop() {
	 window.open('html/project/project/html/pro_popup.jsp', '프로젝트 생성', 'width=970, height=600');
}

function fn_detail(obj) {
	
	var data = new Array();
	var vData = "";
	
	$(obj).children("td").children("input").each(function(i) {
		data[i] = $(this).val();
	});

	vData = data[0];
	window.open('html/project/project/html/pro_popup.jsp?pcd='+vData, '프로젝트 생성', 'width=970, height=600');
}

function fn_detailBtn(obj) {
	var data = new Array();
	var vData = "";
	
	$(obj).parent("td").parent("tr").children("td").children("input").each(function(i) {
		data[i] = $(this).val();
	});

	vData = data[0];
	window.open('html/project/project/html/pro_popup.jsp?pcd='+vData, '프로젝트 생성', 'width=970, height=600');
}

function fn_search_button(){
	fn_search('1');
}

function fn_search(vPage) {
	if ($.trim($("#search").val()).length == 0 && $.trim($("#search_value").val()).length > 0) {
		alert("검색분류를 선택하세요");
		$("#search").focus();
		return null;
	} else if ($.trim($("#search").val()).length > 0 && $.trim($("#search_value").val()).length == 0) {
		alert("검색어를 입력하세요");
		$("#search_value").focus();
		return null;
	}
	
	var parmData = makeKr($("#searchProject").serialize());
	
	var param = "";
	
	$("#current_page").attr("value", vPage);
	param += "page=" + vPage;
	
	$("#page_count").attr("value", parseInt((vPage / 10), 10));
	
	$.getJSON(urlPathContext + "/searchProject.do?"+parmData + "&" + param, {format:'json'}, function(data){
			var vData = "";
			
			$("#tabContain").html("");
			
			vData += "<tr align='center'>";
			vData += "<th>No</th>";
			vData += "<th>구분</th>";
			vData += "<th>프로젝트명</th>";
			vData += "<th>프로젝트 시작일</th>";
			vData += "<th>프로젝트 종료일</th>";
			vData += "<th>발주처</th>";
			vData += "<th>계약금액</th>";
			vData += "<th>상세보기</th>";
			vData += "</tr>";
	
			
			$("#tabContain").append(vData);
			
			$.each(data, function(i, n) { 

			vData = "";
			vData += "<tr align='center' ondblclick='fn_detail(this);'>";
			vData += "<td><input type='hidden' value='"+n.PRO_CODE+"'/>" + (i+1) + "</td>";
			vData += "<td>" + n.DEP_NAME + "</td>";
			vData += "<td>" + n.PRO_NAME + "</td>";
			vData += "<td>" + n.PRO_START_DAY + "</td>";
			vData += "<td>" + n.PRO_END_DAY + "</td>";
			vData += "<td>" + n.ORD_ORG + "</td>";
			vData += "<td>" + n.CON_PRICE + "</td>";
			vData += "<td width='60px'><input id='btnView' value='상세보기' type='button' onclick='fn_detailBtn(this);'></td>";
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

//ajax통신 끝
//*****************************************************************************

//배열인지 아닌지 구분
function isArray(arg) {
	if(typeof arg == 'object') { // typeof -> 형
		var criteria = arg.constructor.toString().match(/array/i);
		
		return (criteria != null);
	}
	
	return false;
};

// json형태로 변환
function makeJson(data) {
	var retData = "{\""+data.replace(/%/g, '%25')+"\"}";
	
	retData = retData.replace(/&/gi,"\",\"");
	retData = retData.replace(/=/gi,"\":\"");
	
	return retData;
};

function formToJson(data) {
	$("#saveForm").children("input:text").each(function(i) {
		alert($(this).val());
	});
}
