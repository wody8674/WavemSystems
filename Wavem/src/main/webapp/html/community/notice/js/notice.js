$(document).ready(function(){
	fn_search('1');
	
	fn_Select_Gubun2();
	
	$("#btnSearch").click(fn_search_button);
	$("#btnReg").click(fn_pop);
	
	var datefield=document.createElement("input")
	datefield.setAttribute("type", "date")
	    
	if (datefield.type!="date"){
		jQuery(function($){
			$('.search_day').datepicker(clareCalendar);
		})
	}
	
	$("#notice_gubun1").change(fn_Select_Gubun2);
});

//*****************************************************************************
//ajax통신
function fn_Select_Gubun2(){
	var gubun_code = $("#notice_gubun1").val();
	
	if(gubun_code == "BA000"){
		fn_Select_Gubun2_com();
	}else if(gubun_code == "BA001"){
		fn_Select_Gubun2_dep();
	}
	else if(gubun_code == "BA002"){
		fn_Select_Gubun2_pro();
	}else{
		$("#notice_gubun2").html("");

		var vData = "<option value = ''>"+ "-" +"</option>";
		
		$("#notice_gubun2").append(vData);
	}
}

function fn_Select_Gubun2_com(){
	$("#notice_gubun2").html("");

	var vData = "<option value = ''>"+ "회사전체" +"</option>";
	
	$("#notice_gubun2").append(vData);
}

function fn_Select_Gubun2_dep(){
	$.getJSON(urlPathContext + "/searchGubun_Dep.do", {format: 'json'}, function(data) {
		
		var vData = "";
		var vCheck = "";
		
		$("#notice_gubun2").html("");
	
		var vData = "<option value = ''>"+ "부서전체" +"</option>";
		
		$.each(data, function(i, n) {
			vData += "<option value='"+n.DEP_CODE+"'>"+n.DEP_NAME+"</option>";
		});
		
		$("#notice_gubun2").append(vData);
	});
}

function fn_Select_Gubun2_pro(){
	$.getJSON(urlPathContext + "/searchGubun_Pro.do", {format: 'json'}, function(data) {
		
		var vData = "";
		var vCheck = "";
		
		$("#notice_gubun2").html("");
		
		var vData = "<option value = ''>"+ "프로젝트전체" +"</option>";
		
		$.each(data, function(i, n) {
			vData += "<option value='"+n.PRO_CODE+"'>"+n.PRO_NAME+"</option>";
		});
		
		$("#notice_gubun2").append(vData);
	});
}

function fn_pop() {
	var vData = '';
	
	vData = 'INT';
	
	window.open('html/community/notice/html/notice_popup.jsp?flag='+vData, '공지사항 등록/수정', 'width=600, height=350');
}

function fn_detailBtn(obj) {
	var vFlag = 'UPD';
	
	var data = new Array();
	var boa_code = "";
	var boa_type = "";
	
	$(obj).parent("td").parent("tr").children("td").children("input").each(function(i) {
		data[i] = $(this).val();
	});
	
	boa_code = data[0];
	boa_type = data[1];
	
	window.open('html/community/notice/html/notice_popup.jsp?flag='+vFlag + '&boa_code=' + boa_code + '&boa_type=' + boa_type, '공지사항 등록/수정', 'width=600, height=350');
}

function fn_search(vPage) {
	var parmData = $("#searchNotice").serialize();
	
	var param = "";
	
	$("#current_page").attr("value", vPage);
	param += "page=" + vPage;
	
	$("#page_count").attr("value", parseInt((vPage / 10), 10));
	
	var gubun1 = $("#notice_gubun1").val();
	param += "&notice_gubun1=" + gubun1;
	
	var gubun2 = $("#notice_gubun2").val();
	param += "&notice_gubun2=" + gubun2;
	
	var search_gubun12 = $("#search_gubun").val();
	param += "&search_gubun=" + search_gubun12;
	
	var search_gubun_content = $("#search_value").val();
	param += "&search_gubun_content=" + search_gubun_content;
		
	getData(param);
}

function fn_search_button() {
	var parmData = $("#searchNotice").serialize();
	
	var vPage = '1';
	
	var param = "";
	
	$("#current_page").attr("value", vPage);
	param += "page=" + vPage;
	
	$("#page_count").attr("value", parseInt((vPage / 10), 10));
	
	var gubun1 = $("#notice_gubun1").val();
	param += "&notice_gubun1=" + gubun1;
	
	var gubun2 = $("#notice_gubun2").val();
	param += "&notice_gubun2=" + gubun2;
	
	var search_gubun12 = $("#search_gubun").val();
	param += "&search_gubun=" + search_gubun12;
	
	var search_gubun_content = $("#search_value").val();
	param += "&search_gubun_content=" + search_gubun_content;
		
	getData(param);
}

function getData(param){
	$.getJSON(urlPathContext + "/searchNotice.do?" + param, {format:'json'}, function(data){
			var vData = "";
			$("#tabContain").html("");
			
			vData += "<tr align='center'>";
			vData += "<th>No</th>";
			vData += "<th>공지사항 분류</th>";
			vData += "<th>부서분류</th>";
			vData += "<th>프로젝트분류</th>";
			vData += "<th>제목</th>";
			vData += "<th>작성자</th>";
			vData += "<th>등록날짜</th>";
			vData += "<th>상세보기</th>";
			vData += "</tr>";
	
			$("#tabContain").append(vData);
			
			$.each(data, function(i, n) { 
				
				vData = "";
				vData += "<tr align='center'>";
				vData += "<td><input type='hidden' value='"+ n.BOA_CODE +"'></input>" +  (i+1) + "</td>";
				vData += "<td><input type='hidden' value='"+ n.BOA_TYPE +"'></input>" + n.BOA_TYPE_NAME + "</td>";
				vData += "<td>" + n.DEP_NAME + "</td>";
				vData += "<td>" + n.PRO_NAME + "</td>";
				vData += "<td>" + n.TITLE + "</td>";
				vData += "<td>" + n.MEM_NAME + "</td>";
				vData += "<td>" + n.FIR_REG_DAY + "</td>";
				vData += "<td width='60px'><input id='btnView' type='button' value='상세보기' onclick='fn_detailBtn(this)'/></td>";
				vData += "</tr>";
				
				$("#tabContain").append(vData);
				
				$("#total_page").attr("value", n.TOTALPAGE);
			});
			
			$("#pageNumber").html("");
			var vData = makePageButton(Number($("#total_page").val()), Number($("#current_page").val()), Number($("#page_count").val()));	
			$("#pageNumber").append(vData);
	});
}

//ajax통신 끝
//*****************************************************************************

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
