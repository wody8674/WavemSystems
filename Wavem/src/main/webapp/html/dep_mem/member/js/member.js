$(document).ready(function(){
	fn_search();
	fn_selectCombo();
	
	$("#btnSearch").click(fn_search);
	$("#btnPop").click(fn_pop);
});

//*****************************************************************************
//ajax통신

function fn_pop() {
	var retData = window.open('html/dep_mem/member/html/mem_new.jsp', '사원 등록', ',width=530, height=350');
}

function fn_detail(obj) {
	
	var data = new Array();
	var vData = "";
	
	$(obj).children("td").each(function(i) {
		data[i] = $(this).html();
	});
	
	vData = data[1];
	window.open('html/dep_mem/member/html/mem_new.jsp?param='+vData, '사원 등록', ',width=530, height=350');
}

function fn_detailBtn(obj) {
	var data = new Array();
	var vData = "";
	
	$(obj).parent("td").parent("tr").children("td").each(function(i) {
		data[i] = $(this).html();
	});
	
	vData = data[1];
	window.open('html/dep_mem/member/html/mem_new.jsp?param='+vData, '사원 등록', ',width=530, height=350');
}

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
	
	var parmData = makeKr($("#searchMember").serialize());
	$.getJSON(urlPathContext + "/searchMember.do?"+parmData, {format: 'json'}, function(data) {
		var vData = "";
		$("#tabContain").html("");
		
		vData += "<tr align='center'>";
		vData += "<th width='11%'>ID</th>";
		vData += "<th width='10%'>사번</th>";
		vData += "<th width='13%'>이름</th>";
		vData += "<th width='10%'>직책</th>";
		vData += "<th width='9%'>계약형태</th>";
		vData += "<th>업체명</th>";
		vData += "<th width='13%'>입사일</th>";
		vData += "<th width='13%'>퇴사일</th>";
		vData += "<th width='8%'>상세보기</th>";
		vData += "</tr>";
		
		$("#tabContain").append(vData);
		
		$.each(data, function(i, n) {
			
			vData = "";
			vData += "<tr align='center' ondblclick='fn_detail(this)'>";
			vData += "<td >" + n.MEM_ID + "</td>";
			vData += "<td>" + n.MEM_CODE + "</td>";
			vData += "<td>" + n.MEM_NAME + "</td>";
			vData += "<td>" + n.MEM_POST_NM + "</td>";
			vData += "<td>" + n.CON_COD_NM + "</td>";
			vData += "<td>" + n.ENT_NAME + "</td>";
			vData += "<td>" + n.HIRED_DAY + "</td>";
			vData += "<td>" + n.RETIRE_DAY + "</td>";
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