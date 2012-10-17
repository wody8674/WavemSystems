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
	window.open('html/community/free/html/free_popup.html', '자유게시판 등록', 'width=700, height=400');
}

function fn_search() {
	
	var parmData = $("#searchFree").serialize();
	
	$.getJSON(urlPathContext + "/searchFree.do?"+parmData, {format:'json'}, function(data){
			var vData = "";
			$("#tabContain").html("");
			
			vData += "<tr align='center'>";
			vData += "<td>No</td>";
			vData += "<td>제목</td>";
			vData += "<td>작성자</td>";
			vData += "<td>등록날짜</td>";
			vData += "<td>비고</td>";
			vData += "</tr>";
	
			
			$("#tabContain").append(vData);
			
			$.each(data, function(i, n) { 
				
			vData = "";
			vData += "<tr align='center'>";
			vData += "<td>" + (i+1) + "</td>";
			vData += "<td>" + n.TITLE + "</td>";
			vData += "<td>" + n.FIR_REG + "</td>";
			vData += "<td>" + n.FIR_REG_DAY + "</td>";
			vData += "</tr>";
			
			$("#tabContain").append(vData);
			
		});
	});
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