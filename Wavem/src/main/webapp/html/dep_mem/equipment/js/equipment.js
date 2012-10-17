$(document).ready(function(){
	fn_search();
	fn_combo();
	$("#btnSearch").click(fn_search);
	$("#btnPop").click(fn_pop);
});

//*****************************************************************************
//ajax통신

function fn_pop() {
	window.open('html/dep_mem/equipment/html/equ_new.jsp', '장비 등록', ',width=500, height=450');
}

function fn_combo() {
	//장비구분콤보
	$.getJSON(urlPathContext + "/searchComCode.do?SEL_CD=searchEquTypeCom", {format: 'json'}, function(data) {
		var vData = "";
		$("#equ_type").html("");
		vData += "<option value=''>"+ "선택" +"</option>";
		$.each(data, function(i, n) {
			vData += "<option value='"+n.COM_GROUP+n.COM_CODE+"'>"+n.NAME_1+"</option>";
		});
		
		$("#equ_type").append(vData);
	});
	
	//사용구분콤보
	$.getJSON(urlPathContext + "/searchComCode.do?SEL_CD=searchUseTypeCom", {format: 'json'}, function(data) {
		var vData = "";
		$("#use_type").html("");
		vData += "<option value=''>"+ "선택" +"</option>";
		$.each(data, function(i, n) {
			vData += "<option value='"+n.COM_GROUP+n.COM_CODE+"'>"+n.NAME_1+"</option>";
		});
		
		$("#use_type").append(vData);
	});
}
function fn_detail(obj) {
	
	var data = new Array();
	var vData = "";
	
	$(obj).children("td").children("input").each(function(i) {
		data[i] = $(this).val();
	});

	vData = data[0];
	window.open('html/dep_mem/equipment/html/equ_new.jsp?param='+vData, '장비 등록', ',width=500, height=420');
}

function fn_detailBtn(obj) {
	var data = new Array();
	var vData = "";
	
	$(obj).parent("td").parent("tr").children("td").children("input").each(function(i) {
		data[i] = $(this).val();
	});
	
	vData = data[0];
	window.open('html/dep_mem/equipment/html/equ_new.jsp?param='+vData, '장비 등록', ',width=500, height=420');
}

function fn_search() {
	
	var parmData = makeKr($("#searchEquipment").serialize());
	$.getJSON("searchEquipment.do?"+parmData, {format: 'json'}, function(data) {
		var vData = "";
		$("#tabContain").html("");
		
		vData += "<tr align='center'>";
		vData += "<th >소유자</th>";
		vData += "<th>장비분류</th>";
		vData += "<th>사용분류</th>";
		vData += "<th>Serial No.</th>";
		vData += "<th width='250px'>상세정보</th>";
		vData += "<th>제조사</th>";
		vData += "<th>구입년도</th>";
		vData += "<th>상세보기</tr>"
		vData += "</tr>";
		
		$("#tabContain").append(vData);
		
		$.each(data, function(i, n) {

			vData = "";
			vData += "<tr align='center' ondblclick='fn_detail(this)'>";
			vData += "	<td ><input type='hidden' value='"+n.EQU_CODE+"'>" + n.MEM_NAME + "</td>";
			vData += "	<td>" + n.EQU_TYPE_NM + "</td>";
			vData += "	<td>" + n.USE_TYPE_NM + "</td>";
			vData += "	<td>" + n.SERIAL_NO + "</td>";
			vData += "	<td align='left'>" + n.EQU_NAME + "</td>";
			vData += "	<td>" + n.MANUFACTURER + "</td>";
			vData += "	<td>" + n.SALE_DAY + "</td>";
			vData += "	<td width='60px'><input type='button' id='btnView' value='상세보기' onclick='fn_detailBtn(this)'></td>";
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