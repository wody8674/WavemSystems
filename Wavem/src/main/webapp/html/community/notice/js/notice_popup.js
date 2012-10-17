$(document).ready( function() {
	var vData = $("#popup_flag").val();

	if (vData == 'UPD') {
		var boa_type = $("#boa_type").val();

		if (boa_type == 'BA000') {
			fn_Select_Gubun2_com();
		} else if (boa_type == 'BA001') {
			fn_Select_Gubun2_dep();
		} else if (boa_type == 'BA002') {
			fn_Select_Gubun2_pro();
		}
		pause(500);
	}

	fn_Init_NoticeDetail();
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

function fn_Init_NoticeDetail() {
	var vData = $("#popup_flag").val();

	if (vData == 'INT') {
		fn_Select_Gubun2();
		$("#btnSave").click(fn_Save_NoticeDetail);
	} else {

		fn_Select_NoticeDetail();

		$("#btnSave").click(fn_Update_NoticeDetail);
	}

	$("#notice_gubun1").change(fn_Select_Gubun2);
}

//*****************************************************************************
// ajax통신

function fn_Select_Gubun2() {
	var gubun_code = $("#notice_gubun1").val();

	if (gubun_code == "BA000") {
		fn_Select_Gubun2_com();
	} else if (gubun_code == "BA001") {
		fn_Select_Gubun2_dep();
	} else if (gubun_code == "BA002") {
		fn_Select_Gubun2_pro();
	}
}

function fn_Select_Gubun2_com() {
	$("#notice_gubun2").html("");

	var vData = "<option value = ''>" + "회사전체" + "</option>";

	$("#notice_gubun2").append(vData);
}

function fn_Select_Gubun2_dep() {
	$.getJSON(urlPathContext + "/searchGubun_Dep.do", {
		format : 'json'
	}, function(data) {

		var vData = "";
		var vCheck = "";

		$("#notice_gubun2").html("");

		// vCheck = $("#dep_code_value").val();

			$.each(data, function(i, n) {
				vData += "<option value='" + n.DEP_CODE + "'>" + n.DEP_NAME
						+ "</option>";
			});

			$("#notice_gubun2").append(vData);

			// if(vCheck.length > 0) {
			// $("#upper_dep").attr("value", vCheck);
			// } else {
			// $("#upper_dep").attr("value", "00000001");
			// }
		});
}

function fn_Select_Gubun2_pro() {
	$.getJSON(urlPathContext + "/searchGubun_Pro.do", {
		format : 'json'
	}, function(data) {

		var vData = "";
		var vCheck = "";

		$("#notice_gubun2").html("");

		// vCheck = $("#dep_code_value").val();

			$.each(data, function(i, n) {
				vData += "<option value='" + n.PRO_CODE + "'>" + n.PRO_NAME
						+ "</option>";
			});

			$("#notice_gubun2").append(vData);

			// if(vCheck.length > 0) {
			// $("#upper_dep").attr("value", vCheck);
			// } else {
			// $("#upper_dep").attr("value", "00000001");
			// }
		});
}

function fn_Select_NoticeDetail() {
	var vBoa_Code = $("#boa_code").val();

	if (vBoa_Code != 'null') {

		// 데이터 조회
		$.getJSON(urlPathContext + "/searchNotice.do?boa_code=" + vBoa_Code, {
			format : 'json'
		}, function(data) {

			$.each(data, function(i, n) {
				$("#notice_gubun1").attr("value", n.BOA_TYPE);

				if (n.BOA_TYPE == 'BA001') {
					$("#notice_gubun2").attr("value", n.DEP_CODE);
				} else if (n.BOA_TYPE == 'BA002') {
					$("#notice_gubun2").attr("value", n.PRO_CODE);
				}

				$("#mem_code").attr("value", n.MEM_CODE);
				
				if ($("#mem_code").val() != $("#user_mc").val()) {
					var changeTarget = document.getElementById("btnSave");
					changeTarget.type = "hidden";
				}
				
				$("#notice_title").attr("value", n.TITLE);
				$("#notice_content").attr("value", n.CONTENTS);
			});
		});
	}
}

function fn_Save_NoticeDetail() {
	if (!fn_check())
		return null;
	var jsonData = makeJson($("#dataForm").serialize());
	$.ajax( {
		type : "POST",
		url : urlPathContext + "/saveNoticeDetail.do",
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

function fn_Update_NoticeDetail() {
alert();
	if (!fn_check())
		return null;

	var jsonData = makeJson($("#dataForm").serialize());
	$.ajax( {
		type : "POST",
		url : urlPathContext + "/updateNoticeDetail.do",
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
// *****************************************************************************

// 배열인지 아닌지 구분
function isArray(arg) {
	if (typeof arg == 'object') { // typeof -> 형
		var criteria = arg.constructor.toString().match(/array/i);

		return (criteria != null);
	}

	return false;
};

// json형태로 변환
function makeJson(data) {
	var retData = "{\"" + data.replace(/%/g, '%25') + "\"}";

	retData = retData.replace(/&/gi, "\",\"");
	retData = retData.replace(/=/gi, "\":\"");

	return retData;
};

function formToJson(data) {
	$("#saveForm").children("input:text").each( function(i) {
		alert($(this).val());
	});
}

function fn_check() {
	if (!((($("#notice_title").val()).length) > 0)) {
		alert("제목을 입력해주세요.");
		$("#notice_title").focus();
		return false;
	} else if (!((($("#notice_content").val()).length) > 0)) {
		alert("내용을 입력해주세요.");
		$("#notice_content").focus();
		return false;
	}
	
	return true;
}
