
$(document).ready(function(){
	fn_search();
	fn_combo();
	
	$("#btnSearch").click(fn_search);
	$("#btnPop").click(fn_pop);
});

//*****************************************************************************
//ajax통신
function fn_pop() {
	window.open('html/dep_mem/book/html/book_new.jsp', '도서등록', 'width=515, height=340');
}

function fn_detail(obj) {
	var data = new Array();
	var vData = "";
	
	$(obj).children("td").each(function(i) {
		data[i] = $(this).html();
	});
	
	vData = data[0];
	window.open('html/dep_mem/book/html/book_new.jsp?param='+vData, '도서등록', 'width=515, height=340');

}

function fn_detailBtn(obj) {
	var data = new Array();
	var vData = "";
	
	$(obj).parent("td").parent("tr").children("td").each(function(i) {
		data[i] = $(this).html();
	});
	
	vData = data[0];
	
	window.open('html/dep_mem/book/html/book_new.jsp?param='+vData, '도서등록', 'width=515, height=340');

}

function fn_combo() {
	$.getJSON(urlPathContext + "/searchComCode.do?SEL_CD=searchBokTypeCom", {format: 'json'}, function(data) {
		var vData = "";
		$("#bok_type").html("");
		
		vData += "<option value=''>"+ "선택" +"</option>";
		$.each(data, function(i, n) {
			vData += "<option value='"+n.COM_GROUP+n.COM_CODE +"'>"+n.NAME_1+"</option>";
		});
		
		$("#bok_type").append(vData);
	});
}

function fn_search() {
	
	var parmData = makeKr($("#searchBook").serialize());
	
	$.getJSON(urlPathContext + "/searchBook.do?"+parmData, {format: 'json'}, function(data) {
		var vData = "";
		$("#tabContain").html("");
		
		vData += "<tr align='center'>";
		vData += "<th >도서코드</th>";
		vData += "<th>도서명</th>";
		vData += "<th>도서분류</th>";
		vData += "<th>출판사</th>";
		vData += "<th>구매자</th>";
		vData += "<th>구매일자</th>";
		vData += "<th>보유자</th>";
		vData += "<th>보유일자</th>";
		vData += "<th>상세보기</th>";
		vData += "</tr>";
		
		$("#tabContain").append(vData);
		
		$.each(data, function(i, n) {
			
			vData = "";
			vData += "<tr align='center' ondblclick='fn_detail(this)'>";
			vData += "	<td >" + n.BOK_CODE + "</td>";
			vData += "	<td>" + n.BOK_NAME + "</td>";
			vData += "	<td>" + n.BOK_TYPE_NM + "</td>";
			vData += "	<td>" + n.PUBLISHER + "</td>";
			vData += "	<td>" + n.SALE_MEM_NM + "</td>";
			vData += "	<td>" + n.SALE_DAY + "</td>";
			vData += "	<td>" + n.OWNER_NM + "</td>";
			vData += "	<td>" + n.OWN_DAY + "</td>";
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

//ajax통신 끝
//*****************************************************************************