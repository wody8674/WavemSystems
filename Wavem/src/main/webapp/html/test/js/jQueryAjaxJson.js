window.onload = initPage;

function initPage() {
	$("#submitBtn").click(getData);
}

function getData() {
	$.ajax({
		type : "POST",
		url : "http://192.168.0.73:8080/SpringProject/main/ajaxJson.do",
		data : $("#searchIdForm").serialize(),
		dataType : "text",
		success : function(data) {
			fn_handleRequest(data);
		},
		complete : function(xhr, textStatus) {
		},
		error : function() {
		}
	});
	
}

function fn_handleRequest(data) {
		
	var jsonData = JSON.parse(data);
	
	for (var property in jsonData) { // jsonData로부터 값의 property 추출
		
		var propertyValue = jsonData[property]; // property의 값을 추출
		
		if (!isArray(propertyValue)) { // 추출한 값이 배열이 아닌경우
		} else { // 배열인 경우
		};
		
	}
	
	document.getElementById("userid").innerHTML = jsonData["user_id"];
	document.getElementById("assigncd").innerHTML = jsonData["assign_cd"];
	document.getElementById("usernm").innerHTML = jsonData["user_nm"];
	document.getElementById("levelnm").innerHTML = jsonData["level_nm"];
	document.getElementById("hp").innerHTML = jsonData["hp"];
	document.getElementById("email").innerHTML = jsonData["email"];

};

//배열인지 아닌지 구분
function isArray(arg) {
	if(typeof arg == 'object') { // typeof -> 형
		var criteria = arg.constructor.toString().match(/array/i);
		
		return (criteria != null);
	}
	
	return false;
};