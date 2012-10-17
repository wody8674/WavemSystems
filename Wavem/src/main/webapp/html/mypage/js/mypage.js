$(document).ready(function(){
	$.ajaxSetup({async:false});
	fn_searchInfo();
	
	$("#btnSave").click(fn_updateMypage);
});

function fn_searchInfo() {
	var parmData = $("#mem_code").val();

	if(parmData.length > 0){
		// 데이터 조회
		$.getJSON(urlPathContext + "/searchMypage.do?key="+parmData, {format: 'json'}, function(data) {
			$.each(data, function(i, n){
				$("#mem_id").attr("value", n.MEM_ID);
				$("#mem_name").attr("value", n.MEM_NAME);
				$("#password").attr("value", n.PASSWORD);
				$("#dep_name").attr("value",n.DEP_NAME);
				$("#mem_post").attr("value",n.MEM_POST);
				$("#con_type").attr("value",n.CON_TYPE);
				$("#ent_name").attr("value",n.ENT_NAME);
				$("#hp").attr("value",n.HP);
				$("#tel").attr("value",n.TEL);
				$("#adderss").attr("value",n.ADDRESS);
				$("#birthday").attr("value",n.BIRTHDAY);
				$("#special_day").attr("value",n.SPECIAL_DAY);
				$("#hired_day").attr("value",n.HIRED_DAY);
				$("#retire_day").attr("value",n.RETIRE_DAY);
			});
			
			$("#btnSave").click(fn_updateMypage);
		});
	} else {
		alert("error");
	}
}

function fn_updateMypage() {
	if ($("#password_1") == $("#password_2")) {
		
	}
}