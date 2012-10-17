$(document).ready(function(){
	
	$.ajaxSetup({async:false});
	
	fn_selectProCom();
	fn_searchData();
	
	$("#button").click(fn_save);
});


function fn_searchData() {
	
	var chkVal = $("#param_iss_code").val();
	
	if (chkVal != 'null') {
		
		$.getJSON(urlPathContext + "/searchData.do?search=dataDetail&search_value="+chkVal, {format: 'json'}, function(data) {
			$.each(data, function(i, n) {
				$("#title").attr("value",n.TITLE);
				$("#pro_code").attr("value",n.PRO_CODE);
				$("#mem_code").attr("value",n.MEM_CODE);
				$("#data_content").attr("value",n.CONTENTS);
			});
		});
	}
}


function fn_selectProCom() {

	$.getJSON(urlPathContext + "/searchComCode.do?SEL_CD=searchProCom", {format: 'json'}, function(data) {
	
		var vData = "<option value=''>선택</option>";
		
		$("#pro_code").html("");
		
		$.each(data, function(i, n) {
			vData += "<option value='"+n.PRO_CODE+"'>"+n.PRO_NAME+"</option>";
		});
		
		$("#pro_code").append(vData);
		
	});
}

function fn_updateData() {
	
	var jsonData = makeJson($("#dataForm").serialize());
	$.ajax({
		type : "POST",
		url : urlPathContext + "/updateData.do",
		data : "data=" + jsonData,
		dataType : "text",
		success : function(data) {
			if (data == "1") {
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

function fn_save() {

	var title = $.trim($("#title").val());
	var pro_code = $.trim($("#pro_code").val());
	var contents = $.trim($("#data_content").val());
	
		if(title.length == 0){
			alert("제목을 입력하세요.");
			$("#title").focus();
			return null;
		} else if (pro_code.length == 0) {
			alert("프로젝트를 선택하세요.");
			$("#pro_code").focus();
			return null;	
		} else if (contents.length == 0) {
			alert("내용을 입력하세요.");
			$("#data_content").focus();
			return null;
		}	
		
		var chkVal = $("#param_data_code").val();
		
		if (chkVal == 'null') {
			fn_insert();
		} else {
			fn_updateData();
		}
	}

function fn_insert() {
	var jsonData = makeJsonEnter($("#dataForm").serialize());

	$.ajax({
		type : "POST",
		url : urlPathContext + "/saveData.do",
		data : "data=" + jsonData,
		dataType : "text",
		success : function(data) {
			if (data > '0') {
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
