$(document).ready(function(){
	
	fn_search();

	$("#btnSearch").click(fn_search);
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
	window.open('html/project/data/html/data_popup.jsp', '자료 등록', 'width=700, height=500');
}

function fn_detail(obj) {
	
	var data = new Array();
	var vData = "";
	
	$(obj).children("td").children("input").each(function(i) {
		data[i] = $(this).val();
	});

	vData = data[0];
	window.open('html/project/data/html/data_popup.jsp?pcd='+vData, '자료 등록', 'width=700, height=500');
}

function fn_detailBtn(obj) {
	
	var data = new Array();
	var vData = "";
	
	$(obj).parent("td").parent("tr").children("td").children("input").each(function(i) {
		data[i] = $(this).val();
	});
	
	vData = data[0];
	window.open('html/project/data/html/data_popup.jsp?pcd='+vData, '자료 등록', 'width=700, height=500');
}


function fn_search() {
	if ($.trim($("#search").val()).length == 0 && $.trim($("#search_value").val()).length > 0) {
		alert("검색분류를 선택하세요");
		$("#search").focus();
		return null;
	} else if ($.trim($("#search").val()).length > 0 && $.trim($("#search_value").val()).length == 0) {
		alert("검색어를 입력하세요");
		$("#search_value").focus();
		return null;
	}
	
	var parmData = makeKr($("#searchData").serialize());

	$.getJSON(urlPathContext + "/searchData.do?"+parmData, {format:'json'}, function(data){
			var vData = "";
			$("#tabContain").html("");
			
			vData += "<tr align='center'>";
			vData += "<th>No</th>";
			vData += "<th>프로젝트명</th>";
			vData += "<th>제목</th>";
			vData += "<th>작성자</th>";
			vData += "<th>등록날짜</th>";
			vData += "<th>상세보기</th>";
			vData += "</tr>";
	
			$("#tabContain").append(vData);
			
			$.each(data, function(i, n) { 
				
			vData = "";
			vData += "<tr align='center'>";
			vData += "<td>" + (i+1) + "</td>";
			vData += "<td>" + n.PRO_NAME + "</td>";
			vData += "<td>" + n.TITLE + "</td>";
			vData += "<td>" + n.MEM_NAME + "</td>";
			vData += "<td>" + n.FIR_REG_DAY + "</td>";
			vData += "<td width='60px'><input type='button' value='상세보기' id='btnView' onclick='fn_detailBtn(this)'></td>";
			vData += "</tr>";

			$("#tabContain").append(vData);
			
		});
	});
}

function goPage(v_page)
{
	var frm = document.pageForm;
	frm.hdn_curr_page.value = v_page;
	frm.submit();
}
//ajax통신 끝
//*****************************************************************************

////배열인지 아닌지 구분
//function isArray(arg) {
//	if(typeof arg == 'object') { // typeof -> 형
//		var criteria = arg.constructor.toString().match(/array/i);
//		
//		return (criteria != null);
//	}
//	
//	return false;
//};
//
//// json형태로 변환
//function makeJson(data) {
//	var retData = "{\""+data.replace(/%/g, '%25')+"\"}";
//	
//	retData = retData.replace(/&/gi,"\",\"");
//	retData = retData.replace(/=/gi,"\":\"");
//	
//	return retData;
//};

function formToJson(data) {
	$("#saveForm").children("input:text").each(function(i) {
		alert($(this).val());
	});
}