
$(document).ready(function(){
	fn_search();
	$("#btnSearch").click(fn_search);
	$("#btnNew").click(fn_pop);
});

//*****************************************************************************
//ajax통신

function fn_pop() {
	window.open('html/approval/contract/html/insertContract.html', '계약체결품의서 등록','resizable=no scrollbars=yes width=800 height=600');
}

function fn_search() {
	
	var parmData = $("#searchContract").serialize();
	
	$.getJSON(urlPathContext + "/searchContract.do?"+parmData, {format: 'json'}, function(data) {
		var vData = "";
		$("#tabContain").html("");
		
		vData += "<tr align='center' bgcolor=#404040 >";
		vData += "	<td>번호</td>";
		vData += "	<td>제목</td>";
		vData += "	<td>등록일</td>";
		vData += "	<td>작성자</td>";
		vData += "	<td>상태</td>";
		vData += "	<td>비고</td>";
		vData += "</tr>";
		
		$("#tabContain").append(vData);
		
		$.each(data, function(i, n) {
			
			vData = "";
			vData += "<tr align='center'>";
			vData += "	<td>" + (i+1) + "</td>";
			vData += "	<td>" + n.BRE_DATE +"  "+"계약체결품의서"+"</td>";
			vData += "	<td>" + n.BRE_DATE + "</td>";
			vData += "	<td>" + n.BRE_NAME + "</td>";
			vData += "	<td>" + n.APP_CODE + "</td>";
			vData += "	<td>" + n.BRE_TYPE + "</td>";
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
