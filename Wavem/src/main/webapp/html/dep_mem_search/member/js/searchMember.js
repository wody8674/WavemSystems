$(document).ready(function(){
	fn_search();
	fn_selectCombo();
	
	$("#btnSearch").click(fn_search);
});

//*****************************************************************************
//ajax통신

function fn_selectCombo() {
	//직책콤보
	$.getJSON(urlPathContext + "/searchPostCom.do", {format: 'json'}, function(data) {
		
		var vData = "";
		
		$("#mem_post").html("");
		
		vData += "<option value=''>"+ "선택" +"</option>";
		
		$.each(data, function(i, n) {
			vData += "<option value='"+n.COM_GROUP+n.COM_CODE+"'>"+n.NAME_1+"</option>";
		});

		$("#mem_post").append(vData);
	});
	
	//계약형태콤보
	$.getJSON(urlPathContext + "/searchComCode.do?SEL_CD=searchConCom", {format: 'json'}, function(data) {
		
		var vData = "";
		
		$("#con_type").html("");
		
		vData += "<option value=''>"+ "선택" +"</option>";
		
		$.each(data, function(i, n) {
			vData += "<option value='"+n.COM_GROUP+n.COM_CODE+"'>"+n.NAME_1+"</option>";
		});

		$("#con_type").append(vData);
	});
}

function fn_search() {
	
	var parmData = makeKr($("#SearchMember").serialize());
	$.getJSON(urlPathContext + "/searchMemberInfo.do?"+parmData, {format: 'json'}, function(data) {
		var vData = "";
		$("#tabContain").html("");
		
		$.each(data, function(i, n) {
			
			vData = "";
			vData += "<tr align='center'>";
			vData += "	<th rowspan='3' width='10%'>" + n.MEM_NAME + "</th>";
			vData += "	<th width='7%'>ID</th><td width='12%'>" + n.MEM_ID + "</td>";
			vData += "	<th width='8%'>직책</th><td width='12%'>" + n.MEM_POST_NM + "</td>";
			vData += "	<th width='7%'>H.P</th><td width='18%'>" + n.HP + "</td>";
			vData += "	<th width='8%'>TEL</th><td width='18%'>" + n.TEL + "</td>";
			vData += "</tr>";
			vData += "<tr align='center'>";
			vData += "	<th>생일</th><td>" + n.BIRTHDAY + "</td>";
			vData += "	<th>기념일</th><td>" + n.SPECIAL_DAY + "</td>";
			vData += "	<th>메일</th><td>" + n.MAIL + "</td>";
			vData += "	<th>메신져</th><td>" + n.MESSENGER + "</td>";
			vData += "</tr>";
			vData += "<tr align='center'>";
			vData += "	<th>주소</th><td colspan='7'>" + n.ADDRESS + "</td>";
			vData += "</tr>";
			
			$("#tabContain").append(vData);
			
		});
	});
}