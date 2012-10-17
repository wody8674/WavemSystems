var getMemCd = "";
var getConType = "";

$(document).ready(function(){
	$.ajaxSetup({async:false});
	fn_getMemCd();
	fn_depCode();
	
	$("#btnSave").click(fn_save);
	$("#pro_code").blur(fn_setProjectCode);
	$("#btnSubAdd").click(fn_add);
	
});

function fn_searchPro() {
	
	var chkVal = $("#param_pro_code").val();
	
	if (chkVal != 'null') {
		
		$.getJSON(urlPathContext + "/searchProject.do?search=proDetail&search_value="+chkVal, {format: 'json'}, function(data) {
			$.each(data, function(i, n) {
				$("#pro_name").attr("value",n.PRO_NAME);
				$("#pro_start_day").attr("value",n.PRO_START_DAY);
				$("#pro_end_day").attr("value",n.PRO_END_DAY);
				$("#dep_code").attr("value",n.DEP_CODE);
				$("#mem_name").attr("value",n.MEM_NAME);
				$("#pro_code").attr("value",n.PRO_CODE);
				$("#ord_org").attr("value",n.ORD_ORG);
				$("#con_price").attr("value",n.CON_PRICE);
				$("#pro_explain").attr("value",n.PRO_EXPLAIN);
			});
		});
		
		$.getJSON(urlPathContext + "/searchProjectPart.do?proCode="+chkVal, {format: 'json'}, function(data) {
			$.each(data, function(i, n) {
				var addData = "";
				
				addData += "<tr>";
				addData += "	<td align='center'><select class='part_mem_cd' id='mem_code"+i+"' name = 'mem_code' disabled='disabled'>"+getMemCd+"</select></td>";
				addData += "	<td align='center'><input style='width:100px' class='pro_start_day' id='pro_start_day"+i+"' name='pro_start_day' type='text'/> ~ <input style='width:100px' class='pro_end_day' id='pro_end_day"+i+"' name='pro_end_day' type='text'/></td>";
				addData += "	<td align='center'><select class='part_con_type' id = 'con_type"+i+"' name = 'con_type'>"+getConType+"</select></td>";
				addData += "	<td align='center' width='270px' height='25px'><input style='width:250px' id='main_work"+i+"' name='main_work' type='text'/></td>";
				addData += "	<td align='center'><input id='use_check' name='use_check' type='hidden' value='1'/><input id='use_check2' name='use_check2' type='hidden' value='1'/><input id='delRowBtn' type='button' value='삭제' onclick='fn_delrow(this);'/></td>";
				addData += "</tr>";

				$("#tabPart").append(addData);
				
				$("#mem_code"+i).attr("value",n.MEM_CODE);
				$("#pro_start_day"+i).attr("value",n.PRO_START_DAY);
				$("#pro_end_day"+i).attr("value",n.PRO_END_DAY);
				$("#con_type"+i).attr("value",n.CON_TYPE);
				$("#main_work"+i).attr("value",n.MAIN_WORK);
				
			});
			
			// 달력 선택할 수 있도록 세팅
			setCal();
		});
	} else {
	}
	
}

function fn_save() {
	
	// 프로젝트 데이터 체크
	var pro_name = $.trim($("#pro_name").val());
	var pro_start_day = $.trim($("#pro_start_day").val());
	var pro_end_day = $.trim($("#pro_end_day").val());
	var dep_code = $.trim($("#dep_code").val());
	var ord_org = $.trim($("#ord_org").val());
	var con_price = $.trim($("#con_price").val());
	
	if (pro_name.length == 0) {
		alert("프로젝트 명을 입력하세요.");
		$("#pro_name").focus();
		return null;
	} else if (pro_start_day.length == 0) {
		alert("프로젝트 시작일을 입력하세요.");
		$("#pro_start_day").focus();
		return null;
	} else if (pro_end_day.length == 0) {
		alert("프로젝트 종료일을 입력하세요.");
		$("#pro_end_day").focus();
		return null;
	} else if (dep_code.length == 0) {
		alert("부서를 선택하세요.");
		$("#dep_code").focus();
		return null;
	} else if (ord_org.length == 0) {
		alert("발주처를 입력하세요");
		$("#ord_org").focus();
		return null;
	} else if (con_price.length == 0) {
		alert("계약 금액을 입력하세요");
		$("#con_price").focus();
		return null;
	}
	
	// 프로젝트 참여자 데이터 체크
	var chk = 0;
	$(".part_mem_cd").each(function() {
		if ($(this).val().length == 0) {
			alert("프로젝트 참여자 이름을 선택하세요");
			$(this).focus();
			chk = 1;
		}
	});
	
	if (chk == 1) return null; 
	
	// 저장 및 수정 분기
	var chkVal = $("#param_pro_code").val();
	
	if (chkVal == 'null') {
		fn_insert();
	} else {
		fn_update();
	}
}

function fn_insert() {
	var jsonData = makeJsonEnter($("#dataForm").serialize());
	var jsonData2 = makeJson($("#partForm").serialize());
	
	$.ajax({
		type : "POST",
		url : urlPathContext + "/saveProject.do",
		data : "data=" + jsonData + "&part=" + jsonData2,
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

function fn_update() {

	$(".part_mem_cd").removeAttr("disabled");
	
	var jsonData = makeJsonEnter($("#dataForm").serialize());
	var jsonData2 = makeJson($("#partForm").serialize());
	
	$.ajax({
		type : "POST",
		url : urlPathContext + "/updateProject.do",
		data : "data=" + jsonData + "&part=" + jsonData2,
		dataType : "text",
		success : function(data) {
			if (data > '0') {
				alert("저장되었습니다.");
				window.opener.location.reload(); 
				self.close();
			} else {
				$(".part_mem_cd").attr("disabled","true");
				alert("저장 실패하였습니다.");
			}
		},
		complete : function(xhr, textStatus) {
		},
		error : function() {
			$(".part_mem_cd").attr("disabled","true");
			alert("error");
		}
	});
}

function fn_add() {
	
	var addData = "";

	addData += "<tr>";
	addData += "	<td align='center'><select class='part_mem_cd' name = 'mem_code' onchange='fn_memchk(this);'>"+getMemCd+"</select></td>";
	addData += "	<td align='center'><input style='width:100px' class='pro_start_day' name='pro_start_day' type='text'/> ~ <input style='width:100px' class='pro_end_day' name='pro_end_day' type='text'/></td>";
	addData += "	<td align='center'><select class='part_con_type' name = 'con_type'>"+getConType+"</select></td>";
	addData += "	<td align='center' width='270px' height='25px'><input style='width:250px' name='main_work' type='text'/></td>";
//	addData += "	<td align='center'><input name='use_check' type='hidden' value='1'/><button id='delRowBtn'>삭제</button></td>";
	addData += "	<td align='center'><input id='use_check' name='use_check' type='hidden' value='1'/><input id='use_check2' name='use_check2' type='hidden' value='0'/><input id='delRowBtn' type='button' value='삭제' onclick='fn_delrow(this);'/></td>";
	addData += "</tr>";

	$("#tabPart").append(addData);

	//참여자 이름 콤보 조회
//	fn_getMemCd();
	
	// 달력 선택할 수 있도록 세팅
	setCal();
}

function fn_delrow(obj) {
	var useck = $(obj).parent().children("#use_check").val(); //DB에서 사용유무 체크값
	var useck2 = $(obj).parent().children("#use_check2").val(); //DB에 저장되어있는지 새로 추가한 데이터인지 체크
	
	if (useck == 1 && useck2 == 1) {
		$(obj).parent().children("#use_check").attr("value","0");
		$(obj).parent().parent().hide();
	} else if(useck == 1 && useck2 == 0) {
		$(obj).parent().parent().remove();
	} 
}

function fn_depCode() {
	
	$.getJSON(urlPathContext + "/searchDepCom.do", {format: 'json'}, function(data) {
		
		var vData = "<option value=''>선택</option>";

		$("#dep_code").html("");
		
		$.each(data, function(i, n) {
			vData += "<option value='"+n.DEP_CODE+"'>"+n.DEP_NAME+"</option>";
		});

		$("#dep_code").append(vData);
		
		fn_searchPro();
	});
	
}

function fn_getMemCd() {
	
	$.getJSON(urlPathContext + "/searchComCode.do?SEL_CD=searchMemCom", {format: 'json'}, function(data) {
		
		var vData = "<option value=''>선택</option>";
		
		$.each(data, function(i, n) {
			vData += "<option value='"+n.MEM_CODE+"'>"+n.MEM_NAME+"</option>";
		});
		getMemCd = vData;
//		$(".part_mem_cd").append(vData);
		
	});

	$.getJSON(urlPathContext + "/searchComCode.do?SEL_CD=searchConCom", {format: 'json'}, function(data) {
		
		var vData = "<option value=''>선택</option>";
	
		$.each(data, function(i, n) {
			vData += "<option value='"+n.COM_GROUP+n.COM_CODE +"'>"+n.NAME_1+"</option>";
		});
		getConType = vData;
//		$(".part_con_type").append(vData);
});

}

function fn_setProjectCode() {
	$(".pro_code").attr({value: $("#pro_code").val()});
}

function fn_memchk(obj) {
	
	var data = $(obj).val();
	var cnt = 0; //선택한 사람이 2사람일 경우 메시지 발생
	
	$(".part_mem_cd").each(function() {

		if ($(this).val() == data) {
			cnt = cnt+1;
			
			if (cnt == 2) {
				alert("이미 등록하였습니다.");
				$(this).attr("value","");
			}

			return;
		}
		
	});
}



