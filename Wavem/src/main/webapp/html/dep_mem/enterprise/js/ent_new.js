$(document).ready(function(){
	fn_searchDepCom();
	$("#btnSave").click(fn_save);
});

function fn_searchDepCom() {
	$.getJSON(urlPathContext + "/searchDepCom.do", {format: 'json'}, function(data) {
	});
}

function fn_save() {
	
//	fn_validate();
	
	var jsonData = makeJson($("#dataForm").serialize());
	
	$.ajax({
		type : "POST",
		url : urlPathContext + "/saveEnterprise.do",
		data : "data=" + jsonData,
		dataType : "text",
		success : function(data) {
		},
		complete : function(xhr, textStatus) {
		},
		error : function() {
			alert("error");
		}
	});
}

//json형태로 변환
function makeJson(data) {
	var retData = "{\""+data.replace(/%/g, '%25')+"\"}";
	
	retData = retData.replace(/&/gi,"\",\"");
	retData = retData.replace(/=/gi,"\":\"");
	
	return retData;
};