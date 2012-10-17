
$(document).ready(function(){
	fn_search();
	$("#btnSearch").click(fn_search);
	$("#btnNew").click(fn_pop);
	
	var datefield=document.createElement("input")
	datefield.setAttribute("type", "date")
	    
	if (datefield.type!="date"){ //if browser doesn't support input type="date", initialize date picker widget:
		jQuery(function($){ //on document.ready
			$('#SEARCH_DATE').datepicker(clareCalendar);
		})
	}
});


function fn_detail(obj){
	var data = new Array();
	var vKey = "";
	var vKey2 = "";
	var vKey3 = "";
	var vkey4 = "";
	var vkey5 = "";
	var vkey6 = "";
	
	$(obj).children("td").children("input").each(function(i) {
		data[i] = $(this).val();
	});

	vKey = data[0];
	vKey2 = data[1];
	vKey3 = data[2];
	vKey4 = data[3];
	vKey5 = data[4];
	vKey6 = data[5];
	
	window.open('html/approval/request/html/insertRequest.jsp?key='+vKey+'&key2='+vKey2+'&key3='+vKey3+'&key4='+vKey4+'&key5='+vKey5+'&key6='+vKey6, '기안서 등록', ',width=650, height=450');
	
//	$("#APP_CODE").attr("value", vKey);
}


function fn_detailBtn(obj) {
	var data = new Array();
	var vKey = "";
	var vKey2 = "";
	var vKey3 = "";
	var vKey4 = "";
	var vKey5 = "";
	var vKey6 = "";
	
	$(obj).parent("td").parent("tr").children("td").children("input").each(function(i) {
		data[i] = $(this).val();
	});
	
	vKey = data[0];
	vKey2 = data[1];
	vKey3 = data[2];
	vKey4 = data[3];
	vKey5 = data[4];
	vKey6 = data[5];
	
	window.open('html/approval/request/html/insertRequest.jsp?key='+vKey+'&key2='+vKey2+'&key3='+vKey3+'&key4='+vKey4+'&key5='+vKey5+'&key6='+vKey6, '기안서 등록', ',width=650, height=450');
}

function fn_pop() {
	window.open('html/approval/request/html/insertRequest.jsp', '기안서 등록','resizable=no scrollbars=yes width=650 height=450');
}

//*****************************************************************************
//ajax통신


function fn_search() {
	
	var parmData = $("#searchRequest").serialize();
	
	$.getJSON(urlPathContext + "/searchRequest.do?"+parmData, {format: 'json'}, function(data) {
		var vData = "";
		var size = data.length;
		
		$("#tabContain").html("");
		
		vData += "<tr align='center'>";
		vData += "	<th>번호</th>";
		vData += "	<th>제목</th>";
		vData += "	<th>등록일</th>";
		vData += "	<th>작성자</th>";
		vData += "	<th>상태</th>";
		vData += "	<th>비고</th>";
		vData += "	<th>상세보기</th>";
		vData += "</tr>";
		
		$("#tabContain").append(vData);
		
		$.each(data, function(i, n) {
			
			vData = "";
			vData += "<tr align='center' ondblclick='fn_detail(this);'>";
			vData += "	<td><input type='hidden' value='"+n.REQ_CODE+"'>"+ (size-i) + "</td>";
			vData += "	<td><input type='hidden' value='"+n.APP_CODE+"'>"+ n.REQ_TITLE +"</td>";
			vData += "	<td><input type='hidden' value='"+n.APP_DAM+"'>" + n.REQ_DATE + "</td>";
			vData += "	<td><input type='hidden' value='"+n.APP_STATE+"'>" + n.REQ_NAME + "</td>";
			vData += "	<td><input type='hidden' value='"+n.APP_TEAM+"'>" + n.APP_STATE_NM + "</td>";
			vData += "	<td><input type='hidden' value='"+n.APP_EXECUTIVE+"'>" + "" + "</td>";
			vData += "	<td width='60px'><input type='button' id='btnView' value='상세보기' onclick='fn_detailBtn(this)'></td>";
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
