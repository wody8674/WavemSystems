/*브라우서부터 Request객체 받아옴.. 브라우저마다 상이하기에 따로 처리 필요*/
function AjaxRequest() {
	if(window.XMLHttpRequest) {
		try {
			this.request = new XMLHttpRequest();
		} catch (e) {
			this.request = null;
		}
	} else if(window.ActiveXObject) {
		try {
			this.request = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				this.request = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				this.request = null;
			}
		}
	}
	
	if (this.request == null) {
		alert("Ajax error creating the request.\n" + "Details: " + e);
	}
}

AjaxRequest.prototype.send = function(type, url, handler, postDataType, postData) {
	if(this.request != null) {
		// kill the previous request
		this.request.abort();
		
		url += "?dummy=" + new Date().getTime();
		
		try {
			this.request.onreadystatechange = handler; // 함수호출시 () 존재하면 바로 호출  ,, 없으면 임시로 바인딩된 상태로 존재
			this.request.open(type, url, true);
			
			if(type.toLowerCase() == "get") {
				this.request.send(null);
			} else {
				this.request.setRequestHeader("Content-Type", postDataType);
				this.request.send(postData);
			}
		} catch (e) {
			alert("Ajax error communicating with the server.\n" + "Details: " + e);
		}
	}
};

AjaxRequest.prototype.getReadyState = function() {
	return this.request.readyState;
};

AjaxRequest.prototype.getStatus = function() {
	return this.request.status;
};

AjaxRequest.prototype.getResponseText = function() {
	return this.request.responseText;
};

AjaxRequest.prototype.getResponseXML = function() {
	return this.request.responseXML;
};

/*
 * prototype -> 객체 생성시 메서드 중복 생성 문제 해결
 * 				클래스 객체 내에 속하게 됨 (메모리 따로 관리)
 * 				
 */