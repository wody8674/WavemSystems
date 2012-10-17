$(document).ready(function(){
	
	$.ajaxSetup({async:false});
	
	fn_selectCombo();
	fn_selectOwnMemCom();
	fn_selectSaleMemCom();
	fn_searchInfo();
	
	$("#bok_code").attr("disabled","true");
	$("#sale_post").change(fn_selectSaleMemCom);
	$("#own_post").change(fn_selectOwnMemCom);
});

function fn_selectCombo() {
	
	$.getJSON(urlPathContext + "/searchPostCom.do", {format: 'json'}, function(data) {
		
		var vData = "";
		
		$("#sale_post").html("");
		vData += "<option value= ''>"+ "선택" +"</option>";
		
		$.each(data, function(i, n) {
			vData += "<option value='"+n.COM_GROUP+n.COM_CODE+"'>"+n.NAME_1+"</option>";
		});

		$("#sale_post").append(vData);
		
		fn_selectSaleMemCom();
	});
	
	$.getJSON(urlPathContext + "/searchPostCom.do", {format: 'json'}, function(data) {
		
		var vData = "";
		
		$("#own_post").html("");
		vData += "<option = ''>"+ "선택" +"</option>";
		
		$.each(data, function(i, n) {
			vData += "<option value='"+n.COM_GROUP+n.COM_CODE+"'>"+n.NAME_1+"</option>";
		});

		$("#own_post").append(vData);
		
		fn_selectOwnMemCom();
	});
	
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

function fn_selectSaleMemCom() {
	var memCode = $("#sale_post").val();
	$.getJSON(urlPathContext + "/searchComCode.do?SEL_CD=searchMemCom&param1="+memCode, {format: 'json'}, function(data) {
		var vData = "";
		$("#sale_name").html("");
		
		vData += "<option value = ''>"+ "선택" +"</option>";
		
		$.each(data, function(i, n) {
			vData += "<option value='"+n.MEM_CODE+"'>"+n.MEM_NAME+"</option>";
		});
		
		$("#sale_name").append(vData);
	});
}

function fn_selectOwnMemCom() {
	var memCode = $("#own_post").val();
	
	$.getJSON(urlPathContext + "/searchComCode.do?SEL_CD=searchMemCom&param1="+memCode, {format: 'json'}, function(data) {
		var vData = "";
		$("#own_name").html("");
		
		vData += "<option value=''>"+ "선택" +"</option>";
		
		$.each(data, function(i, n) {
			vData += "<option value='"+n.MEM_CODE+"'>"+n.MEM_NAME+"</option>";
		});
		
		$("#own_name").append(vData);
	});
	
}

function fn_searchInfo() {
	
	var parmData = $("#bok_code").val();
	
	if(parmData != 'null') {
		
		// 데이터 조회
		$.getJSON(urlPathContext + "/searchBook.do?search_menu=bok_info&search_value="+parmData, {format: 'json'}, function(data) {
			
			$.each(data, function(i, n) {
				$("#bok_name").attr("value", n.BOK_NAME);
				$("#bok_type").attr("value", n.BOK_TYPE);
				$("#publisher").attr("value", n.PUBLISHER);
				$("#sale_post").attr("value", n.SALE_POST);
				if (	$("#sale_post").val() == "null") 
				fn_selectSaleMemCom();
				$("#sale_name").attr("value", n.SALE_MEM);
				$("#sale_day").attr("value", n.SALE_DAY);
				$("#own_post").attr("value", n.OWN_POST);
//				if (	$("#own_post").val().length == 0) {
//					alert("00000");
//					$("#own_post").attr("disabled", "");
//					$("#own_name").attr("disabled", "");
//				} else {
//					alert("111111");
//					fn_selectOwnMemCom();
//					$("#own_name").attr("value", n.OWNER);
//				}
				fn_selectOwnMemCom();
				$("#own_name").attr("value", n.OWNER);
				$("#own_day").attr("value", n.OWN_DAY);
			});
			
			$("#bok_code").attr("disabled","true");
			
			$("#btnSave").click(fn_updateBook);
		});
	} else {
		$("#bok_code").attr("value","");
		$("#btnSave").click(fn_check);
	}

}


function fn_updateBook() {
	
	$("#bok_code").removeAttr("disabled");
	
	var jsonData = makeJson($("#dataForm").serialize());
	$.ajax({
		type : "POST",
		url : urlPathContext + "/updateBook.do",
		data : "data=" + jsonData,
		dataType : "text",
		success : function(data) {
			if (data == 1) {
				alert("저장되었습니다.");
				window.opener.location.reload(); 
				self.close();
			} else {
				$("#bok_code").attr("disabled", "true");
				alert("저장 실패하였습니다.");
			}
		},
		complete : function(xhr, textStatus) {
		},
		error : function() {
			$("#bok_code").attr("disabled", "true");
			alert("error");
		}
	});
}
function fn_save() {
	
//	fn_validate();
	
	var jsonData = makeJson($("#dataForm").serialize());
	$.ajax({
		type : "POST",
		url : urlPathContext + "/saveBook.do",
		data : "data=" + jsonData,
		dataType : "text",
		success : function(data) {
			if (data > 0) {
				alert("저장되었습니다.");
				window.opener.location.reload(); 
				self.close();
			} else {
				alert("저장 실패하였습니다.");
			}
		},
		complete : function(xhr, textStatus) {
		},
		error : function() {
			alert("error");
		}
	});
}

function fn_check() {

	if (!((($("#bok_name").val()).length)>0)) {
		alert("도서명을 입력해주세요.");
		$("#bok_name").focus();
	} else 	if (!((($("#publisher").val()).length)>0)) {
		alert("출판사를 입력해주세요.");
		$("#publisher").focus();
	} else if (!((($("#bok_type").val()).length)>0)) {
		alert("도서 분류를 선택해주세요.");
		$("#bok_type").focus();
	} else { 
		fn_save();
	}
}
