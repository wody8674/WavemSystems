window.onload = initPage;

var ajaxReq = new AjaxRequest();

function initPage() {
	document.getElementById("submitBtn").onclick = fn_search;
}

function fn_search() {
	if (ajaxReq == null) {
		alert("failed");
		return;
	}

	var url = "http://localhost:8080/SpringProject/main/ajaxJson.do";
	var getData = "";
	
	getData = "user_id=" + document.getElementById("user_id").value;

	/*
	 * type			: GET & POST
	 * url			: Server Url
	 * handler		: 성공 시 
	 * postDataType	: Post일 경우 
	 * postData		: Post일 경우 
	 */
	ajaxReq.send("POST"
				, url
				, fn_handleRequest
				, "application/x-www-form-urlencoded; charset=UTF-8"
				, getData);
	
}

function fn_handleRequest() {
	if (ajaxReq.getReadyState() == 4 && ajaxReq.getStatus() == 200) {
		
		var jsonData = JSON.parse(ajaxReq.getResponseText());
		
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
};

//배열인지 아닌지 구분
function isArray(arg) {
	if(typeof arg == 'object') { // typeof -> 형
		var criteria = arg.constructor.toString().match(/array/i);
		
		return (criteria != null);
	}
	
	return false;
};


