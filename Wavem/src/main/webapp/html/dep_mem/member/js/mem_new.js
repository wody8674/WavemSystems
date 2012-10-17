$(document).ready(function(){
	
	$.ajaxSetup({async:false});
	
	fn_selectCombo();
	fn_searchInfo();
	fn_disabled();
	
	$("#mem_code").attr("disabled", "true");
	
	$("#con_type").change(fn_conTypeChange);
	
	$("#dep_code").change(fn_checkHead);
});

function fn_checkHead() {
	var memCode = $("#mem_code").val();
	var check = 0;
	
	$.getJSON(urlPathContext + "/memComCode.do?SEL_CD=searchHead&param1="+memCode, {format: 'json'}, function(data) {
		$.each(data, function(i, n) {
			if (n.CNT>0) {
				alert("부서장은 부서 이동이 불가합니다.");
				$("#dep_code").attr("value", n.DEP_CODE);
				check = 1;
			} else {
				fn_searchInfo();
			}
		});
	});
}

function fn_selectCombo() {
	// 부서콤보
	$.getJSON(urlPathContext + "/searchDepCom.do", {format: 'json'}, function(data) {
		
		var vData = "";
	
		$("#dep_code").html("");
		
		vData += "<option>"+ "선택" +"</option>";
		
		$.each(data, function(i, n) {
			vData += "<option value='"+n.DEP_CODE+"'>"+n.DEP_NAME+"</option>";
		});

		$("#dep_code").append(vData);
	});
	
	//직책콤보
	$.getJSON(urlPathContext + "/searchPostCom.do", {format: 'json'}, function(data) {
		
		var vData = "";
		
		$("#mem_post").html("");
		
		vData += "<option>"+ "선택" +"</option>";
		
		$.each(data, function(i, n) {
			vData += "<option value='"+n.COM_GROUP+n.COM_CODE+"'>"+n.NAME_1+"</option>";
		});

		$("#mem_post").append(vData);
	});
	
	//계약형태콤보
	$.getJSON(urlPathContext + "/searchComCode.do?SEL_CD=searchConCom", {format: 'json'}, function(data) {
		
		var vData = "";
		
		$("#con_type").html("");
		
		vData += "<option>"+ "선택" +"</option>";
		
		$.each(data, function(i, n) {
			vData += "<option value='"+n.COM_GROUP+n.COM_CODE+"'>"+n.NAME_1+"</option>";
		});

		$("#con_type").append(vData);
	});
	
	//업체콤보
	$.getJSON(urlPathContext + "/searchComCode.do?SEL_CD=searchEntCom", {format: 'json'}, function(data) {
		
		var vData = "";
		
		$("#ent_name").html("");
		
		vData += "<option>"+ "선택" +"</option>";
		
		$.each(data, function(i, n) {
			vData += "<option value='"+n.ENT_CODE+"'>"+n.ENT_NAME+"</option>";
		});

		$("#ent_name").append(vData);
	});
}

function fn_disabled() {
	if ((($("#retire_day").val()).length)>1) {
		$("#password").attr("disabled", "true");
		$("#mem_name").attr("disabled", "true");
		$("#dep_code").attr("disabled", "true");
		$("#mem_post").attr("disabled", "true");
		$("#con_type").attr("disabled", "true");
		$("#hp").attr("disabled", "true");
		$("#tel").attr("disabled", "true");
		$("#address").attr("disabled", "true");
		$("#mail").attr("disabled", "true");
		$("#messenger").attr("disabled", "true");
		$("#birthday").attr("disabled", "true");
		$("#special_day").attr("disabled", "true");
		$("#hired_day").attr("disabled", "true");
		$("#retire_day").attr("disabled", "true");
	}
}

function fn_searchInfo() {
	
	var parmData = $("#mem_code").val();
	
	if(parmData != 'null') {
		
		// 데이터 조회
		$.getJSON(urlPathContext + "/searchMember.do?search_menu=mem_info&search_value="+parmData, {format: 'json'}, function(data) {
			 
			$.each(data, function(i, n) {
				$("#dep_code").attr("value", n.DEP_CODE);
				$("#mem_name").attr("value", n.MEM_NAME);
				$("#mem_post").attr("value", n.MEM_POST);
				$("#mem_id").attr("value", n.MEM_ID);
				$("#con_type").attr("value", n.CON_TYPE);
				$("#ent_name").attr("value", n.ENT_NAME);
				$("#hp").attr("value", n.HP);
				$("#tel").attr("value", n.TEL);
				$("#address").attr("value", n.ADDRESS);
				$("#mail").attr("value", n.MAIL);
				$("#messenger").attr("value", n.MESSENGER);
				$("#birthday").attr("value", n.BIRTHDAY);
				$("#password").attr("value", n.PASSWORD);
				$("#special_day").attr("value", n.SPECIAL_DAY);
				$("#password").attr("value", n.PASSWORD);
				$("#hired_day").attr("value", n.HIRED_DAY);
				$("#retire_day").attr("value", n.RETIRE_DAY);
			});
			
			$("#mem_id").attr("disabled","true");
			$("#ent_name").attr("disabled", "true");
			
			$("#btnSave").click(fn_updateMember);
		});
	} else {
		$("#mem_code").attr("value","");
		$("#retire_day").attr("disabled", "true");
		$("#btnSave").click(fn_save);
	}
	
}

function fn_updateMember() {
	if (!fn_check()) return null;

	$("#mem_code").removeAttr("disabled");
	
	var jsonData = makeJson($("#dataForm").serialize());
	$.ajax({
		type : "POST",
		url : urlPathContext + "/updateMember.do",
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
	
	if (!fn_check()) return null;
		
	var jsonData = makeJson($("#dataForm").serialize());
	$.ajax({
		type : "POST",
		url : urlPathContext + "/saveMember.do",
		data : "data=" + jsonData,
		dataType : "text",
		success : function(data) {
			if (data == "1") {
				alert("저장되었습니다.");
				window.opener.location.reload(); 
				self.close();
			} else {
				$("#mem_code").attr("disabled", "true");
				alert("저장 실패하였습니다.");
			}
		},
		complete : function(xhr, textStatus) {
		},
		error : function() {
			$("#mem_code").attr("disabled", "true");
			alert("error");
		}
	});
}

function fn_conTypeChange() {
	
	var data = $(this).val();

	if (data == 'EM001'){
		$("#ent_name").attr("disabled", "");
	} else {
		$("#ent_name").removeAttr("disabled", "");
	}
	
}

function fn_check() {

	var memCode = $("#mem_code").val();
	
	if (!((($("#mem_id").val()).length)>0)) {
		alert("ID를 입력해주세요.");
		$("#mem_id").focus();
		return false;
	} 
	
	if (!((($("#password").val()).length)>0)) {
		alert("password를 입력해주세요.");
		$("#password").focus();
		return false;
	}
	
	if (!((($("#mem_name").val()).length)>0)) {
		alert("이름을 입력해주세요.");
		$("#mem_name").focus();
		return false;
	}
	
	if (!((($("#hired_day").val()).length)>0)) {
		alert("입사일을 입력해주세요.");
		$("#hired_day").focus();
		return false;
	}
	
	if((($("#retire_day").val()).length)>1) {
		if(!fn_depHeadDel()) {
			return false;
		} else return true;
	}
	
	if (!confirm("저장하시겠습니까?")) {
		return false;
	} else {	// yes
		return true;
	}
}

function fn_depHeadDel() {
	var memCode = $("#mem_code").val();

	$.getJSON(urlPathContext + "/memComCode.do?SEL_CD=searchHead&param1="+memCode, {format: 'json'}, function(data) {
		$.each(data, function(i, n) {
			if (n.CNT>0) {
				alert("부서장은 퇴사할 수 없습니다.");
				return true;
			} else {
				return false;
			}
		});
	});
}