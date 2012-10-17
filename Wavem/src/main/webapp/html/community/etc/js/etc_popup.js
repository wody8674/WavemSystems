$(document).ready(function(){	
	fn_Init_EtcDetail();
});

function pause(numberMillis) {
    var now = new Date();
    var exitTime = now.getTime() + numberMillis;

    while (true) {
         now = new Date();
         if (now.getTime() > exitTime)
             return;
    }
}

function fn_Init_EtcDetail(){
	var vData = $("#popup_flag").val();
		
	if(vData == 'INT'){
		
		$("#btnSave").click(fn_Save_EtcDetail);
	}
	else{
		fn_Select_EtcDetail();
		
		$("#btnSave").click(fn_Update_EtcDetail);
	}
}



//*****************************************************************************
//ajax통신

function fn_Select_EtcDetail(){
	var vBoa_Code = $("#boa_code").val();
		
	if(vBoa_Code != 'null') {
		
		// 데이터 조회
		$.getJSON(urlPathContext + "/searchEtc.do?boa_code="+vBoa_Code, {format: 'json'}, function(data) {
			
			$.each(data, function(i, n) {
				$("#mem_code").attr("value", n.MEM_CODE);
				
				if ($("#mem_code").val() != $("#user_mc").val()) {
					var changeTarget = document.getElementById("btnSave");
					changeTarget.type = "hidden";
				}
				
				$("#etc_title").attr("value", n.TITLE);
				$("#etc_content").attr("value", n.CONTENTS);
			});
		});
	}
}

function fn_Save_EtcDetail(){
	if (!fn_check()) return null;
	
	var jsonData = makeJson($("#dataForm").serialize());
	$.ajax({
		type : "POST",
		url : urlPathContext + "/saveEtcDetail.do",
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

function fn_Update_EtcDetail(){
	if (!fn_check()) return null;
	
	var jsonData = makeJson($("#dataForm").serialize());
	$.ajax({
		type : "POST",
		url : urlPathContext + "/updateEtcDetail.do",
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

//ajax통신 끝
//*****************************************************************************

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

function fn_check() {
	
	if (!((($("#etc_title").val()).length)>0)) {
		alert("제목을 입력해주세요.");
		$("#etc_title").focus();
	} else 	if (!((($("#etc_content").val()).length)>0)) {
		alert("내용을 입력해주세요.");
		$("#etc_content").focus();
	}
}


