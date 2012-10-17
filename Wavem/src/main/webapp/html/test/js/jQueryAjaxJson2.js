
$(document).ready(function(){
	
	
	$("#btnSearch").click(fn_search);
	$("#user_id").keydown(function(event) {
		if (event.keyCode == '13') {
			fn_search();
		}
	});
	$("#btnSave").click(fn_save); //handler라고 되어있는 부분에는 함수를 적는다.
	
});

//*****************************************************************************
//ajax통신
function fn_search() {
	
	$.ajaxPrefilter('json', function(options, orig, jqXHR) {
		return 'text';
	});
	
	$.ajax({
		type : "get",
		//url : "http://127.0.0.1:8080/SpringProject/main/ajaxJson.do",
		url : "/SpringProject/main/ajaxJson.do",
		data : $("#searchIdForm").serialize(), //태그에 name 필요
		dataType : "text",
		success : function(data) {
			fn_handleRequest(data);
		},
		complete : function(xhr, textStatus) {
		},
		error : function() {
			alert("error");
		}
	});
	
	$("p").css("display", "inline");
	$("#divText").children(".text").load("/SpringProject/main/ajaxJson.do?" +$("#searchIdForm").serialize());
	$("#divText .text2").load("/SpringProject/main/ajaxJson.do?" +$("#searchIdForm").serialize());
}

function fn_save() {
	
	var jsonData = makeJson($("#saveForm").serialize()); // 한글처리 공통으로 처리 필요
	
	$.ajax({
		type : "POST",
		url : "http://localhost:8080/SpringProject/main/ajaxJsonSave.do",
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

//ajax통신 끝
//*****************************************************************************

function fn_handleRequest(data) {
		
	var jsonData = JSON.parse(data);
	
	for (var property in jsonData) { // jsonData로부터 값의 property 추출
		
		var propertyValue = jsonData[property]; // property의 값을 추출
		
		if (!isArray(propertyValue)) { // 추출한 값이 배열이 아닌경우
		} else { // 배열인 경우
		};
		
	}
	
	$("#userid").attr({value: jsonData["user_id"]});
	$("#assigncd").attr({value: jsonData["assign_cd"]});
	$("#usernm").attr({value: jsonData["user_nm"]});
	$("#levelnm").attr({value: jsonData["level_nm"]});
	$("#hp").attr({value: jsonData["hp"]});
	$("#email").attr({value: jsonData["email"]});

};

//배열인지 아닌지 구분
function isArray(arg) {
	if(typeof arg == 'object') { // typeof -> 형
		var criteria = arg.constructor.toString().match(/array/i);
		
		return (criteria != null);
	}
	
	return false;
};

// json형태로 변환
function makeJson(data) {
	var retData = "{\""+data.replace(/%/g, '%25')+"\"}";
	
	retData = retData.replace(/&/gi,"\",\"");
	retData = retData.replace(/=/gi,"\":\"");
	
	return retData;
};

function formToJson(data) {
	$("#saveForm").children("input:text").each(function(i) {
		alert($(this).val());
	});
}

