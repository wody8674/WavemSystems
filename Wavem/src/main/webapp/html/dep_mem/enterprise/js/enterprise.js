$(document).ready(function(){
	fn_search();
	$("#btnPop").click(fn_pop);
});

//*****************************************************************************
//ajax통신
function fn_pop() {
	window.open('html/dep_mem/enterprise/html/ent_new.jsp', '업체등록', 'width=620, height=310');
}

function fn_detail(obj) {
	var data = new Array();
	var vData = "";
	
	$(obj).children("td").each(function(i) {
		data[i] = $(this).html();
	});
	
	vData = data[0];
	window.open('html/dep_mem/enterprise/html/ent_new.jsp?param='+vData, '업체등록', 'width=620, height=310');

}

function fn_detailBtn(obj) {
	var data = new Array();
	var vData = "";
	
	$(obj).parent("td").parent("tr").children("td").each(function(i) {
		data[i] = $(this).html();
	});
	
	vData = data[0];
	window.open('html/dep_mem/enterprise/html/ent_new.jsp?param='+vData, '업체등록', 'width=620, height=310');
}

function fn_search() {
	
	var parmData = $("#searchEnterprise").serialize();
	
	$.getJSON(urlPathContext + "/searchEnterprise.do?"+parmData, {format: 'json'}, function(data) {
		var vData = "";
		
		$("#tabContain").html("");

		vData += "<tr align='center'>";
		vData += "<th >업체코드</th>";
		vData += "<th>이름</th>";
		vData += "<th>사업자번호</th>";
		vData += "<th>사업자명</th>";
		vData += "<th>주소</th>";
		vData += "<th>연락처</th>";
		vData += "<th>생성일자</th>";
		vData += "<th>소멸일자</th>";
		vData += "<th>비고</th>";
		vData += "<th>상세보기</th>"
		vData += "</tr>";
		
		$("#tabContain").append(vData);
		
		$.each(data, function(i, n) {
			
			vData = "";
			vData += "<tr align='center' ondblclick='fn_detail(this)'>";
			vData += "	<td >" + n.ENT_CODE + "</td>";
			vData += "	<td>" + n.ENT_NAME + "</td>";
			vData += "	<td>" + n.ENT_NO + "</td>";
			vData += "	<td>" + n.ENT_HEAD + "</td>";
			vData += "	<td>" + n.ADDERSS + "</td>";
			vData += "	<td>" + n.TEL + "</td>";
			vData += "	<td>" + n.CREATION_DAY + "</td>";
			vData += "	<td>" + n.DESTRUCTION_DAY + "</td>";
			vData += "	<td>" + n.ETC + "</td>";
			vData += "	<td width='60px'><input id='btnView' type='button' value='상세보기' onclick='fn_detailBtn(this)'></td>";
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
