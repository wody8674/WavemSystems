
$(document).ready(function(){
	fn_combo(); 
	fn_search();
	fn_checkMemPost();
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
//*********************************************************************************************************************************************************
//로그인한사람이 대표이사거나 이사 직책이면 등록버튼이 보여지지않는다
function fn_checkMemPost(){
	var userMP = $("#USER_MP").val();
	if(userMP == 'PS001' || userMP == 'PS000'){
		$("#btnNew").hide();
	}
}
//*********************************************************************************************************************************************************
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
	
	window.open('html/approval/break/html/insertBreak.jsp?key='+vKey+'&key2='+vKey2+'&key3='+vKey3+'&key4='+vKey4+'&key5='+vKey5+'&key6='+vKey6, '휴가원 등록','resizable=no scrollbars=yes width=680 height=550');
	
//	$("#APP_CODE").attr("value", vKey);
}
//*********************************************************************************************************************************************************

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
	
	window.open('html/approval/break/html/insertBreak.jsp?key='+vKey+'&key2='+vKey2+'&key3='+vKey3+'&key4='+vKey4+'&key5='+vKey5+'&key6='+vKey6, '휴가원 등록', ',width=680, height=550');
}

//*********************************************************************************************************************************************************

function fn_pop() {
	window.open('html/approval/break/html/insertBreak.jsp', '휴가원 등록','resizable=no scrollbars=yes width=680 height=550');
}

//*********************************************************************************************************************************************************

function fn_combo() {
	//결재상태
	$.getJSON(urlPathContext + "/searchComCode.do?SEL_CD=searchAppStateCom", {format: 'json'}, function(data) {
		var vData = "";
		$("#SEARCH_STATE").html("");
		vData += "<option value=''>"+ "전체" +"</option>";
		$.each(data, function(i, n) {
			vData += "<option value='"+n.COM_GROUP+n.COM_CODE+"'>"+n.NAME_1+" "+n.NAME_2+"</option>";
		});
		
		$("#SEARCH_STATE").append(vData);
	});
}

//*********************************************************************************************************************************************************

function fn_search() {
	var parmData = makeKr($("#searchBreak").serialize());
//	alert(parmData);
	$.getJSON(urlPathContext + "/searchBreak.do?"+parmData, {format: 'json'}, function(data) {
		var vData = "";
		var size = data.length;

		$("#tabContain").html("");
		
		vData += "<tr align='center'>";
		vData += "	<th>번호</th>";
		vData += "	<th>시작일</th>";
		vData += "	<th>종료일</th>";
//		vData += "	<th>제목</th>";
		vData += "	<th>등록일</th>";
		vData += "	<th>작성자</th>";
		vData += "	<th>상태</th>";
		vData += "	<th>휴가종류</th>";
		vData += "	<th>상세보기</th>";
		vData += "</tr>";
		
		$("#tabContain").append(vData);
		
		$.each(data, function(i, n) {
			vData = "";
			vData += "<tr align='center' ondblclick='fn_detail(this);'>";
			vData += "	<td><input type='hidden' value='"+n.BRE_CODE+"'>"+ (size-i) + "</td>";
			vData += "	<td><input type='hidden' value='"+n.APP_CODE+"'>"+n.BRE_START+"</td>";
			vData += "	<td>"+n.BRE_OVER+"</td>";
//			vData += "	<td>"+"["+ n.BRE_START +" ~ "+ n.BRE_OVER +"]"+" - "+ n.BRE_NAME +"</td>";
			vData += "	<td><input type='hidden' value='"+n.APP_DAM+"'>" + n.BRE_DATE + "</td>";
			vData += "	<td><input type='hidden' value='"+n.APP_STATE+"'>" + n.BRE_NAME + "</td>";
			vData += "	<td><input type='hidden' value='"+n.APP_TEAM+"'>" + n.APP_STATE_NM+ "</td>";
			vData += "	<td><input type='hidden' value='"+n.APP_EXECUTIVE+"'>"+ n.BRE_TYPE_NM+"</td>";
			vData += "	<td width='60px'><input type='button' id='btnView' value='상세보기' onclick='fn_detailBtn(this)'></td>";
			vData += "</tr>";
			
			$("#tabContain").append(vData);
		});
	});
}
	//*********************************************************************************************************************************************************

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
