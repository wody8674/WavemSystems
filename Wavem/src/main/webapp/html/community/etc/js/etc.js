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
	
	$("#notice_gubun1").change(fn_Select_Gubun2);
});

//*****************************************************************************
//ajax통신

function fn_pop() {
	var vData = '';
	
	vData = 'INT';
	
	window.open('html/community/etc/html/etc_popup.jsp?flag='+vData, '통합자료실 등록/수정', 'width=600, height=350');
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
	
	window.open('html/community/etc/html/etc_popup.jsp?flag='+vFlag + '&boa_code=' + boa_code + '&boa_type=' + boa_type, '공지사항 등록/수정', 'width=600, height=350');
}

function fn_search() {
	var parmData = $("#searchEtc").serialize();

	var param = "";

	var search_gubun12 = $("#search_gubun").val();
	param += "&search_gubun=" + search_gubun12;
	
	var search_gubun_content = $("#search_value").val();
	param += "&search_gubun_content=" + search_gubun_content;
		
	$.getJSON(urlPathContext + "/searchEtc.do?" + param, {format:'json'}, function(data){
			var vData = "";
			$("#tabContain").html("");
			
			vData += "<tr align='center'>";
			vData += "<th>No</th>";
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
			vData += "<td>" + n.TITLE + "</td>";
			vData += "<td>" + n.MEM_NAME + "</td>";
			vData += "<td>" + n.FIR_REG_DAY + "</td>";
			vData += "<td width='60px'><input id='btnView' type='button' value='상세보기' onclick='fn_detailBtn(this)'/></td>";
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
