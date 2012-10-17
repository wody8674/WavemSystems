$(document).ready(function(){
	
	$.ajaxSetup({async:false});
	$("#equ_name").html("* 모델명 : \n* OS : \n* CPU : \n* Main Memory : \n* HDD : \n* LCD : ");
	
	fn_selectPosCombo();
	$("#equ_code").attr("disabled","true");
	$("#mem_post").change(fn_selectMemCom);

	fn_searchInfo();
});

function fn_selectPosCombo() {
	
	//직책콤보
	$.getJSON(urlPathContext + "/searchPostCom.do", {format: 'json'}, function(data) {
		
		var vData = "";
		
		$("#mem_post").html("");
		
		vData += "<option = ''>"+ "선택" +"</option>";
		
		$.each(data, function(i, n) {
			vData += "<option value='"+n.COM_GROUP+n.COM_CODE+"'>"+n.NAME_1+"</option>";
		});

		$("#mem_post").append(vData);
		
		fn_selectMemCom();
	});
	
	//장비구분콤보
	$.getJSON(urlPathContext + "/searchComCode.do?SEL_CD=searchEquTypeCom", {format: 'json'}, function(data) {
		var vData = "";
		$("#equ_type").html("");
		vData += "<option>"+ "선택" +"</option>";
		$.each(data, function(i, n) {
			vData += "<option value='"+n.COM_GROUP+n.COM_CODE+"'>"+n.NAME_1+"</option>";
		});
		
		$("#equ_type").append(vData);
	});
	
	//사용구분콤보
	$.getJSON(urlPathContext + "/searchComCode.do?SEL_CD=searchUseTypeCom", {format: 'json'}, function(data) {
		var vData = "";
		$("#use_type").html("");
		vData += "<option value = ''>"+ "선택" +"</option>";
		$.each(data, function(i, n) {
			vData += "<option value='"+n.COM_GROUP+n.COM_CODE+"'>"+n.NAME_1+"</option>";
		});
		
		$("#use_type").append(vData);
	});
}

function fn_selectMemCom() {
	var memCode = $("#mem_post").val();
	
	$.getJSON(urlPathContext + "/searchComCode.do?SEL_CD=searchMemCom&param1="+memCode, {format: 'json'}, function(data) {
		
		var vData = "";
		
		$("#mem_name").html("");
		
		vData += "<option value = ''>"+ "선택" +"</option>";
		
		$.each(data, function(i, n) {
			
			vData += "<option value='"+n.MEM_CODE+"'>"+n.MEM_NAME+"</option>";
		
		});
		
		$("#mem_name").append(vData);
	});
}

function fn_searchInfo() {
	
	var parmData = $("#equ_code").val();
	
	if(parmData != 'null') {

		// 데이터 조회
		$.getJSON(urlPathContext + "/searchEquipment.do?search_menu=equ_info&search_value="+parmData, {format: 'json'}, function(data) {

			$.each(data, function(i, n) {
				$("#equ_code").attr("value", n.EQU_CODE);
				$("#mem_post").attr("value", n.MEM_POST);
				fn_selectMemCom();
				$("#mem_name").attr("value", n.MEM_CODE);
				$("#equ_type").attr("value", n.EQU_TYPE);
				$("#use_type").attr("value", n.USE_TYPE);
				$("#equ_no").attr("value", n.SERIAL_NO);
				$("#equ_name").attr("value", makeEnter(n.EQU_NAME));
				$("#manufacturer").attr("value", n.MANUFACTURER);
				$("#sale_day").attr("value", n.SALE_DAY);
			});
			
			$("#equ_code").attr("disabled","true");
			
			$("#btnSave").click(fn_updateDepartment);
		});
	} else {
		$("#equ_code").attr("value","");
		$("#btnSave").click(fn_check);
	}
	
}

function fn_updateDepartment() {
	
	$("#equ_code").removeAttr("disabled");
	
	var jsonData = makeJsonEnter($("#dataForm").serialize());
	$.ajax({
		type : "POST",
		url : urlPathContext + "/updateEquipment.do",
		data : "data=" + jsonData,
		dataType : "text",
		success : function(data) {
			if (data > 0) {
				alert("저장되었습니다.");
				window.opener.location.reload(); 
				self.close();
			} else {
				$("#equ_code").attr("disabled", "true");
				alert("저장 실패하였습니다.");
			}
		},
		complete : function(xhr, textStatus) {
		},
		error : function() {
			$("#equ_code").attr("disabled", "true");
			alert("error");
		}
	});
}


function fn_save() {
	
//	fn_validate();
	
	var jsonData = makeJsonEnter($("#dataForm").serialize());
	
	$.ajax({
		type : "POST",
		url : urlPathContext + "/saveEquipment.do",
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
	
	if (!((($("#equ_type").val()).length)>0)) {
		alert("장비의 구분을 입력해주세요.");
		$("#equ_type").focus();
	} else 	if (!((($("#use_type").val()).length)>0)) {
		alert("사용의 구분을 입력해주세요.");
		$("#use_type").focus();
	} else {
		fn_save();
	}
}
