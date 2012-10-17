$(document).ready(function(){
	
	$.ajaxSetup({async:false});
	
	fn_selectCombo();
	fn_searchIssues();
	
	$("#button").click(fn_save);
});


function fn_searchIssues() {
	
	var chkVal = $("#param_iss_code").val();
	
	if (chkVal != 'null') {
		
		$.getJSON(urlPathContext + "/searchIssues.do?search=issDetail&search_value="+chkVal, {format: 'json'}, function(data) {
			$.each(data, function(i, n) {
				$("#title").attr("value",n.TITLE);
				$("#pro_code").attr("value",n.PRO_CODE);
				$("#mem_code").attr("value",n.MEM_CODE);
				$("#issues_content").attr("value",n.CONTENTS);
			});
		});
	}
}

function fn_selectCombo() {
//사원 콤보
	$.getJSON(urlPathContext + "/searchComCode.do?SEL_CD=searchMemCom", {format: 'json'}, function(data) {
	
		var vData = "<option value=''>선택</option>";
		
		$("#mem_code").html("");
		
		$.each(data, function(i, n) {
			vData += "<option value='"+n.MEM_CODE+"'>"+n.MEM_NAME+"</option>";
		});
		
		$("#mem_code").append(vData);
		
	});
	
//프로젝트 콤보
	$.getJSON(urlPathContext + "/searchComCode.do?SEL_CD=searchProCom", {format: 'json'}, function(data) {
	
		var vData = "<option value=''>선택</option>";
		
		$("#pro_code").html("");
		
		$.each(data, function(i, n) {
			vData += "<option value='"+n.PRO_CODE+"'>"+n.PRO_NAME+"</option>";
		});
		
		$("#pro_code").append(vData);
		
	});
}

function fn_updateIssues() {

	var jsonData = makeJson($("#dataForm").serialize());
	
	$.ajax({
		type : "POST",
		url : urlPathContext + "/updateIssues.do",
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
	var mem_code = $.trim($("#mem_code").val());
	var contents = $.trim($("#issues_content").val());
	
		if(title.length == 0){
			alert("제목을 입력하세요.");
			$("#title").focus();
			return null;
		} else if (pro_code.length == 0) {
			alert("프로젝트를 선택하세요.");
			$("#pro_code").focus();
			return null;	
		} else if (mem_code.length == 0) {
			alert("담당자를 선택하세요.");
			$("#mem_code").focus();
			return null;
		} else if (contents.length == 0) {
				alert("내용을 입력하세요.");
				$("#issue_content").focus();
				return null;
		}

		var chkVal = $("#param_iss_code").val();
		
		if (chkVal == 'null') {
			fn_insert();
		} else {
			fn_updateIssues();
		}
	}

function fn_insert() {
	var jsonData = makeJsonEnter($("#dataForm").serialize());

	$.ajax({
		type : "POST",
		url : urlPathContext + "/saveIssues.do",
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
