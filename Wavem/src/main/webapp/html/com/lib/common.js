var urlPathContext = "/Wavem";

/**
 * 	to json data
 * 
 *	@function : makeJson()
 *	@author   : 정재요
 *	@param    : N/A
 *	@return   : N/A
 */
//json형태로 변환
function makeJson(data) {
	
	if (data.length > 0) {
		var retData = "{\""+data.replace(/%/g, '%25')+"\"}";
		
		retData = retData.replace(/&/gi,"\",\"");
		retData = retData.replace(/=/gi,"\":\"");
	} else {
		retData = data;
	}
	
	return retData;
};

/**
 * 	엔터키 들어간 데이터 처리 
 * 
 *	@function : makeJsonEnter()
 *	@author   : 정재요
 *	@param    : N/A
 *	@return   : N/A
 */
//json형태로 변환
function makeJsonEnter(data) {
	
	if (data.length > 0) {
		data = data.replace(/%0D%0A/g, '<br />');
		var retData = "{\""+data.replace(/%/g, '%25')+"\"}";
		
		retData = retData.replace(/&/gi,"\",\"");
		retData = retData.replace(/=/gi,"\":\"");
	} else {
		retData = data;
	}
	return retData;
};

/**
 * 	utf-8
 * 
 *	@function : makeKr()
 *	@author   : 정재요
 *	@param    : N/A
 *	@return   : N/A
 */
function makeKr(data) {
	var retData = data.replace(/%/g, '%25');
	return retData;
}

/**
 * 	utf-8
 * 
 *	@function : makeEnter()
 *	@author   : 정재요
 *	@param    : N/A
 *	@return   : N/A
 */
function makeEnter(data) {
	var retData = data.replace(/<br \/>/g, '\n');
	return retData;
}

/**
 * 	array check
 * 
 *	@function : isArray()
 *	@author   : 정재요
 *	@param    : N/A
 *	@return   : N/A
 */
function isArray(arg) {
	if(typeof arg == 'object') { // typeof -> 형
		var criteria = arg.constructor.toString().match(/array/i);
		
		return (criteria != null);
	}
	
	return false;
};

/**
 * 	logout
 * 
 *	@function : fn_logout()
 *	@author   : 정재요
 *	@param    : N/A
 *	@return   : N/A
 */
function fn_logout() {
	
	$.ajax({
		type : "POST",
		url : urlPathContext + "/logout.do",
		data : "",
		dataType : "text",
		success : function(data) {
			alert("로그아웃되었습니다.");
			location.replace("");
		},
		complete : function(xhr, textStatus) {
		},
		error : function() {
			alert("error");
		}
	});
	
};
