$(document).ready(function(){
	
	$.ajaxSetup({async:false});
	
	fn_selectDepCom();
	fn_selectPostCom();
	fn_searchInfo();
	fn_disabled();
	$("#dep_code").attr("disabled","true");
	$("#mem_post").change(fn_selectMemCom);
});

function fn_selectDepCom() {
	
	var depCode = $("#dep_code").val();
	$.getJSON(urlPathContext + "/searchComCode.do?SEL_CD=searchDepCom2&param1="+depCode, {format: 'json'}, function(data) {
		
		var vData = "";
		var vCheck = "";
		
		$("#upper_dep").html("");
		
		vData += "<option value=''>"+ "선택" +"</option>";
		
		vCheck = $("#dep_code_value").val();
		
		$.each(data, function(i, n) {
			vData += "<option value='"+n.DEP_CODE+"'>"+n.DEP_NAME+"</option>";
		});
		
		$("#upper_dep").append(vData);


		if(vCheck.length > 0) {
			$("#upper_dep").attr("value", vCheck);
		} else {
			$("#upper_dep").attr("value", "00000001");
		}
	});
}

function fn_selectPostCom() {
	
	$.getJSON(urlPathContext + "/searchPostCom.do", {format: 'json'}, function(data) {
		
		var vData = "";
		
		vData += "<option value=''>"+ "선택" +"</option>";
		
		$("#mem_post").html("");
		
		$.each(data, function(i, n) {
			vData += "<option value='"+n.COM_GROUP+n.COM_CODE+"'>"+n.NAME_1+"</option>";
		});

		$("#mem_post").append(vData);

		fn_selectMemCom();
	});
	
}

function fn_selectMemCom() {
	var memCode = $("#mem_post").val();
	
	$.getJSON(urlPathContext + "/searchComCode.do?SEL_CD=searchMemCom&param1="+memCode, {format: 'json'}, function(data) {
		
		var vData = "";
		
		$("#dep_head").html("");
		
		vData += "<option>"+ "선택" +"</option>";
		
		$.each(data, function(i, n) {
			
			vData += "<option value='"+n.MEM_CODE+"'>"+n.MEM_NAME+"</option>";
		});
		
		$("#dep_head").append(vData);
	});
}

function fn_disabled() {
	if ((($("#destruction_day").val()).length)>1) {
		$("#dep_name").attr("disabled", "true");
		$("#mem_post").attr("disabled", "true");
		$("#dep_head").attr("disabled", "true");
		$("#upper_dep").attr("disabled", "true");
		$("#creation_day").attr("disabled", "true");
		$("#destruction_day").attr("disabled", "true");
		$("#etc").attr("disabled", "true");
	}
}

function fn_searchInfo() {
	
	var parmData = $("#dep_code").val();
	
	if(parmData != 'null') {
		
		// 데이터 조회
		$.getJSON(urlPathContext + "/searchDepartment.do?search_menu=dep_info&search_value="+parmData, {format: 'json'}, function(data) {
			
			$.each(data, function(i, n) {
				$("#dep_name").attr("value", n.DEP_NAME);
				$("#mem_post").attr("value", n.MEM_POST);
				fn_selectMemCom();
				$("#dep_head").attr("value", n.MEM_CODE);
				$("#upper_dep").attr("value", n.UPPER_DEP);
				$("#creation_day").attr("value", n.CREATION_DAY);
				$("#destruction_day").attr("value", n.DESTRUCTION_DAY);
				$("#etc").attr("value", n.ETC);
			});
			
			$("#dep_code").attr("disabled","true");
			
			$("#btnSave").click(fn_updateDepartment);
		});
	} else {
		$("#dep_code").attr("value","");
		$("#destruction_day").attr("disabled","true");
		$("#btnSave").click(fn_save);
	}
	
}


function fn_save() {
	
	if (!fn_check()) return null;
	
	var jsonData = makeJson($("#dataForm").serialize());
	$.ajax({
		type : "POST",
		url : urlPathContext + "/saveDepartment.do",
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


function fn_updateDepartment() {
	
	if (!fn_check()) return null;
	
	$("#dep_code").removeAttr("disabled");
	
	var jsonData = makeJson($("#dataForm").serialize());
	$.ajax({
		type : "POST",
		url : urlPathContext + "/updateDepartment.do",
		data : "data=" + jsonData,
		dataType : "text",
		success : function(data) {
			if (data == "1") {
				alert("저장되었습니다.");
				if ((($("#destruction_day").val()).length)>1) {
					fn_updateHeadDep();
				}
				window.opener.location.reload(); 
				self.close();
			} else {
				$("#dep_code").attr("disabled", "true");
				alert("저장 실패하였습니다.");
			}
		},
		complete : function(xhr, textStatus) {
		},
		error : function() {
			$("#dep_code").attr("disabled", "true");
			alert("error");
		}
	});
	
}


function fn_updateHeadDep() {
	
	var jsonData = makeJson($("#dataForm").serialize());
	$.ajax({
		type : "POST",
		url : urlPathContext + "/updateHeadDep.do",
		data : "data=" + jsonData,
		dataType : "text",
		success : function(data) {
			if (data == "1") {
				alert("부서장은 WaveM으로 이동합니다");				
			} else {
				alert("부서장 부서이동 실패.");
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
	var depCode = $("#dep_code").val();

	if (!((($("#dep_name").val()).length)>0)) {
		alert("부서 이름을 입력해주세요.");
		$("#dep_name").focus();
		return false;
	} 
	
	if (!((($("#mem_post").val()).length)>0)){
		alert("부서장의 직책을 입력해주세요.");
		$("#mem_post").focus();
		return false;
	}
	
	if (!((($("#dep_head").val()).length)>0)) {
		alert("부서장의 이름을 입력해주세요.");
		$("#dep_head").focus();
		return false;
	}
	
	if (!((($("#creation_day").val()).length)>0)) {
		alert("생성일자를 입력해주세요.");
		$("#creation_day").focus();
		return false;
	}
	
	var cnt = 0;
	
	if((($("#destruction_day").val()).length)>1) {
		// [update] 소멸일자 선택시 해당 부서에 부서장을 제외한 멤버가 있는지 확인
		$.getJSON(urlPathContext + "/depComCode.do?SEL_CD=desDepMemSearch&param1="+depCode, {format: 'json'}, function(data) {
			$.each(data, function(i, n) {
				if (n.CNT>1) {
					alert("해당부서에 소속된 인원이 있습니다. 부서 소멸 전 부서장을 제외한 사원은 부서 이동이 필요합니다.");
					cnt = 1;
				}	
			});
		});
		
		// [update] 소멸일자 선택시 해당 부서의 하위 부서가 있는지 확인
		$.getJSON(urlPathContext + "/depComCode.do?SEL_CD=depUppderDep&param1="+depCode, {format: 'json'}, function(data) {
			$.each(data, function(i, n) {
				if (n.CNT>0) {
					alert("해당부서의 하위부서가 존재합니다.");
					cnt = 1;
				}	
			});
		});
	}
	
	if ( cnt==0 ) {
		if ( (($("#destruction_day").val()).length)>1 ) {
			if (!confirm("소멸일자가 지정되었습니다. 저장하시면 부서가 소멸됩니다. 저장하시겠습니까?")) {
				return false;
			} else {
				return true;
			}
		} else if (!confirm("저장하시겠습니까?")) {
			return false;
		} else {	// yes
			return true;
		}
	} else {
		return false;
	}
}