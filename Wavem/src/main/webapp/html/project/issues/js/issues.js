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
	window.open('html/project/issues/html/issues_popup.jsp', '이슈 등록', 'width=700, height=500');
}

function fn_detail(obj) {
	
	var data = new Array();
	var vData = "";
	
	$(obj).children("td").children("input").each(function(i) {
		data[i] = $(this).val();
	});

	vData = data[0];
	window.open('html/project/issues/html/issues_popup.jsp?pcd='+vData, '이슈 등록', 'width=700, height=500');
}

function fn_detailBtn(obj) {
	
	var data = new Array();
	var vData = "";
	
	$(obj).parent("td").parent("tr").children("td").children("input").each(function(i) {
		data[i] = $(this).val();
	});
	
	vData = data[0];
	window.open('html/project/issues/html/issues_popup.jsp?pcd='+vData, '이슈 등록', 'width=700, height=500');
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
	
	var parmData = makeKr($("#searchIssues").serialize());
	
	$.getJSON(urlPathContext + "/searchIssues.do?"+parmData, {format:'json'}, function(data){
			var vData = "";
			$("#tabContain").html("");
			
			vData += "<tr align='center'>";
			vData += "<th>No</th>";
			vData += "<th>부서</th>";
			vData += "<th>프로젝트</th>";
			vData += "<th>제목</th>";
			vData += "<th>이슈등록자</th>";
			vData += "<th>이슈담당자</th>";
			vData += "<th>등록날짜</th>";
//			vData += "<th>비고</th>";
			vData += "<th>상세보기</th>";
			vData += "</tr>";
	
			$("#tabContain").append(vData);
			
			$.each(data, function(i, n) { 
				
			vData = "";
			vData += "<tr align='center' ondblclick='fn_detail(this)'>";
			vData += "<td><input type='hidden' value='"+n.ISS_CODE+"'/>" + (i+1) + "</td>";
			vData += "<td>" + n.DEP_NAME + "</td>";
			vData += "<td>" + n.PRO_NAME + "</td>";
			vData += "<td>" + n.TITLE + "</td>";
			vData += "<td>" + n.MEM_NAME + "</td>";
			vData += "<td>" + n.FIR_REG_NAME + "</td>";
			vData += "<td>" + n.FIR_REG_DAY + "</td>";
//			vData += "<td>" + n.ATT_FILE + "</td>";
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

function formToJson(data) {
	$("#saveForm").children("input:text").each(function(i) {
		alert($(this).val());
	});
}
