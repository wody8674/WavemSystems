$(document).ready(function(){
	$.ajaxSetup({async:false});
	fn_getMemCd();
	fn_search();
	$("#EXP_LEVEL").change(fn_selectMem_EXP);
	$("#APP_DAM_LEVEL").change(fn_selectMemCom_DAM);
	$("#APP_EXECUTIVE_LEVEL").change(fn_selectMemCom_EXECUTIVE);
	$("#APP_TEAM_LEVEL").change(fn_selectMemCom_TEAM);
});

//휴가원 팝업창 조회
function fn_search() {
	var vCodeVal = $("#EXP_CODE").val();
	var vAppDam = $("#APP_DAM2").val();
	var vAppTEAM = $("#APP_TEAM2").val();
	var vAppEXECUTIVE = $("#APP_EXECUTIVE2").val();
	var vUserMc = $("#USER_MC").val();
	var vAppState = $("#APP_STATE").val();
	
//	alert("상태값 = "+vAppState);
	
	//상세보기 함수불러오기  (코드값이 있고 작성자가 로그인한 사람이 아닐 경우)- 결재버튼과 반려 생성하기
	  if(vCodeVal != 'null' && vAppDam != vUserMc){
		//상세보기 함수
		fn_selectUserInfo();
		//비활성화시키기 (타인일경우 무조건 비활성화이다)
		fn_disabled();
		fn_userDisabled();
		//버튼 value 바꾸기
		$("#btnSemiSave").attr("value", "반려");
		$("#btnSave").attr("value", "결재");
		//여기서 현재 결재 처리상태에 따라 버튼 처리 및 비활성화 처리해주기 
		 if(vAppState == 'AP020' && vAppTEAM == vUserMc){
				$("#exp_rollback_reason").show();
				$("#ROLLBACK_REASON").attr("value","");
				//버튼
				$("#btnSemiSave").click(fn_updateRollback);
				$("#btnSave").click(fn_updateApproval);
			}else if (vAppState == 'AP020'  && '00000000' == vUserMc){
				//버튼
				fn_btnDisabled();
			}else if(vAppState == 'AP020' && vAppEXECUTIVE == vUserMc){
				//버튼
				fn_btnDisabled();
			}else if(vAppState == 'AP030' && vAppTEAM == vUserMc){
				//버튼
				fn_btnDisabled();
			}else if(vAppState == 'AP040' && vAppEXECUTIVE == vUserMc){
				$("#exp_rollback_reason").show();
				$("#ROLLBACK_REASON").attr("value","");
				//버튼
				$("#btnSemiSave").click(fn_updateRollback);
				$("#btnSave").click(fn_updateApproval);
			}else if(vAppState == 'AP040' && vAppTEAM == vUserMc){
				//버튼
				fn_btnDisabled();
			}else if (vAppState == 'AP040'  && '00000000' == vUserMc){
				//버튼
				fn_btnDisabled();
			}else if(vAppState =='AP050' && vAppTEAM == vUserMc){
				//버튼
				fn_btnDisabled();
			}else if(vAppState =='AP050' && vAppEXECUTIVE == vUserMc){
				//버튼
				fn_btnDisabled();
			}else if (vAppState == 'AP050'  && '00000000' == vUserMc){
				$("#exp_rollback_reason").show();
				$("#ROLLBACK_REASON").attr("value","");
				//버튼
				$("#btnSemiSave").click(fn_updateRollback);
				$("#btnSave").click(fn_updateApproval);
			}else if (vAppState == 'AP060'){
				//버튼
				fn_btnDisabled();
				}
		 }
	  
	 
		  //상세보기 함수불러오기  (코드값이 있고 작성자가 로그인한 사람일 경우)
		  else if (vCodeVal != 'null' && vAppDam == vUserMc) {
			  //정보조회오는 함수
			  fn_selectUserInfo();
				if(vAppState == 'AP010'){
						$("#exp_rollback_reason").hide();
						
						$("#EXP_DATE").attr("disabled","true");
						$("#APP_DAM_LEVEL").attr("disabled","true");
						$("#APP_DAM").attr("disabled","true");
						$("#EXP_LEVEL").attr("disabled","true");
						$("#EXP_TEAM").attr("disabled","true");
						$("#EXP_NAME").attr("disabled","true");
						$("#APP_MASTER").attr("disabled","true");
						
						$("#btnSemiSave").attr("value", "수정");
						$("#btnSave").attr("value", "등록");
						
						$("#btnSemiSave").click(fn_updateExpense);
						$("#btnSave").click(fn_updateSRSave);
						
					}else if(vAppState == 'AP030' ){
						//반려일땐 반려사유가 보여져야한다	
						$("#exp_rollback_reason").show();
						$("#ROLLBACK_REASON").attr("disabled","true");
						
						$("#APP_MASTER").attr("disabled","true");
						$("#EXP_DATE").attr("disabled","true");
						$("#APP_DAM_LEVEL").attr("disabled","true");
						$("#APP_DAM").attr("disabled","true");
						$("#EXP_LEVEL").attr("disabled","true");
						$("#EXP_TEAM").attr("disabled","true");
						$("#EXP_NAME").attr("disabled","true");
						
						$("#btnSemiSave").attr("value", "수정");
						$("#btnSave").attr("value", "등록");
						$("#btnSemiSave").click(fn_updateExpense);
						$("#btnSave").click(fn_updateSRSave);
					}else if(vAppState == 'AP020'){
						//비활성화
						fn_disabled();
						fn_userDisabled();
						
						fn_btnDisabled();
						$("#btnSemiSave").attr("value", "반려");
						$("#btnSave").attr("value", "결재");
						$("#btnSemiSave").click(fn_updateRollback);
						$("#btnSave").click(fn_updateApproval);
					}else if(vAppState == 'AP040' || vAppState == 'AP050'||vAppState == 'AP060'){
						//비활성화
						fn_disabled();
						fn_userDisabled();
						fn_btnDisabled();
						
					}
				}
	  else{
		//신규등록 함수 불러오기( 코드값이 없을 경우 )
		fn_selectSession();
	}
}

//비활성화 함수
function fn_disabled(){
	$("#EXP_DATE").attr("disabled","true");
	$("#EXP_TEAM").attr("disabled","true");
	$("#EXP_LEVEL").attr("disabled","true");
	$("#EXP_NAME").attr("disabled","true");
	$("#exp_rollback_reason").hide();
}

// 결재란 비활성화
function fn_userDisabled(){
	$("#APP_DAM_LEVEL").attr("disabled","true");
	$("#APP_DAM").attr("disabled","true");
	$("#APP_TEAM_LEVEL").attr("disabled","true");
	$("#APP_TEAM").attr("disabled","true");
	$("#APP_EXECUTIVE_LEVEL").attr("disabled","true");
	$("#APP_EXECUTIVE").attr("disabled","true");
	$("#APP_MASTER").attr("disabled","true");
}
function fn_btnDisabled(){
	//버튼
	$("#btnSave").hide();
	$("#btnSemiSave").hide();
}

//신규등록
function fn_selectSession() {
	$.getJSON(urlPathContext + "/sessionInfo.do", {format: 'json'}, function(data) {
		$.each(data, function(i, n) {
			$("#EXP_LEVEL").attr("value", n.userMP_NM);
			$("#EXP_TEAM").attr("value", n.userDC_NM);
			$("#EXP_NAME").attr("value", n.userNm);
			$("#APP_DAM").attr("value", n.userNm);
			$("#APP_DAM_LEVEL").attr("value", n.userMP_NM);
			
			$("#EXP_LEVEL").attr("disabled","true");
			$("#EXP_TEAM").attr("disabled","true");
			$("#EXP_NAME").attr("disabled","true");
			$("#APP_DAM").attr("disabled","true");
			$("#APP_DAM_LEVEL").attr("disabled","true");
			$("#EXPv_DATE").attr("disabled","true");
			$("#APP_MASTER").attr("disabled","true");
			$("#exp_rollback_reason").hide();
			
		});
		//등록버튼 클릭시 저장하는 함수불러오기
		$("#btnSave").click(fn_save);
		$("#btnSemiSave").click(fn_semisave);
	});
}


//등록된 정보 조회
function fn_selectUserInfo() {
	var paramap = $("#EXP_CODE").val();
	var paramap2 = $("#APP_CODE").val();
	var paramap3 = $("#APP_DAM").val();
	var vAppState = $("#APP_STATE").val();
	
//	alert("exp코드="+paramap);
//	alert("app코드="+paramap2);
//	alert("담당="+paramap3);
//	alert("상태="+vAppState);
	$.getJSON(urlPathContext + "/searchInsertExpense.do?key="+paramap+"&key2="+paramap2, {format: 'json'}, function(data) {
		$.each(data, function(i, n) {
			//조회된 휴가원 정보들
			$("#EXP_NAME").attr("value", n.MEM_NAME);
			// 임시저장일 경우 작성일자 현재일자로 세팅
			if (n.APP_STATE != "AP010") {
				$("#EXP_DATE").attr("value", n.EXP_DATE);
			}
			$("#EXP_TEAM").attr("value", n.DEP_NAME);
			$("#EXP_LEVEL").attr("value", n.MEM_POST_NM);
			$("#ROLLBACK_REASON").attr("value",n.ROLLBACK_REASON);
			//결재란
			$("#APP_MASTER").attr("value", n.APP_MASTER_NM);
			$("#APP_DAM_LEVEL").attr("value",n.MEM_POST_NM);
			$("#APP_DAM").attr("value",n.MEM_NAME);
			$("#APP_TEAM_LEVEL").attr("value",n.APP_TEAM_LEVEL_NM);
			$("#APP_TEAM").attr("value",n.APP_TEAM);
			$("#APP_EXECUTIVE_LEVEL").attr("value",n.APP_EXECUTIVE_LEVEL_NM);
			$("#APP_EXECUTIVE").attr("value",n.APP_EXECUTIVE);
		});
	});
}


function fn_getMemCd(){
	
	// 결재란에 직책 콤보박스 뿌려주기
	$.getJSON(urlPathContext + "/searchPostCom.do", {format: 'json'}, function(data) {
		
		var vData = "<option value=''>선택</option>";
		
		$("#APP_TEAM_LEVEL").html("");
		
		$.each(data, function(i, n) {
			vData += "<option value='"+n.COM_GROUP+n.COM_CODE+"'>"+n.NAME_1+"</option>";
		});
		$("#APP_DAM_LEVEL").append(vData);
		$("#APP_TEAM_LEVEL").append(vData);
		$("#APP_EXECUTIVE_LEVEL").append(vData);
		$("#EXP_LEVEL").append(vData);
		
		fn_selectMem_EXP();
		fn_selectMemCom_DAM();
		fn_selectMemCom_TEAM();
		fn_selectMemCom_EXECUTIVE();
	});
}

//성명란에 이름 콤보박스 뿌려주기 
function fn_selectMem_EXP(){
	var memCode = $("#EXP_LEVEL").val();
	
	$.getJSON(urlPathContext + "/searchComCode.do?SEL_CD=searchMemCom&param1="+memCode, {format: 'json'}, function(data) {
		
		var vData="";
		
		$("#EXP_NAME").html("");
		
		var vData = "<option value=''>선택</option>";
		
		$.each(data, function(i, n) {
			vData += "<option value='"+n.MEM_CODE+"'>"+n.MEM_NAME+"</option>";
		});
		$("#EXP_NAME").append(vData);
	});
}

//결재란-담당란에 이름 콤보박스 뿌려주기 
function fn_selectMemCom_DAM(){
	var memCode = $("#APP_DAM_LEVEL").val();
	
	$.getJSON(urlPathContext + "/searchComCode.do?SEL_CD=searchMemCom&param1="+memCode, {format: 'json'}, function(data) {
		
		var vData="";
		
		$("#APP_DAM").html("");
		var vData = "<option value=''>선택</option>";
		$.each(data, function(i, n) {
			vData += "<option value='"+n.MEM_CODE+"'>"+n.MEM_NAME+"</option>";
		});
		$("#APP_DAM").append(vData);
	});
}

//결재란-팀장란에 이름 콤보박스 뿌려주기 
function fn_selectMemCom_TEAM(){
	var memCode = $("#APP_TEAM_LEVEL").val();
	
	$.getJSON(urlPathContext + "/searchComCode.do?SEL_CD=searchMemCom&param1="+memCode, {format: 'json'}, function(data) {
		
		var vData="";
		
		$("#APP_TEAM").html("");
		var vData = "<option value=''>선택</option>";
		$.each(data, function(i, n) {
			vData += "<option value='"+n.MEM_CODE+"'>"+n.MEM_NAME+"</option>";
		});
		$("#APP_TEAM").append(vData);
	});
}

//결재란-임원란에 이름 콤보박스 뿌려주기 
function fn_selectMemCom_EXECUTIVE(){
	var memCode = $("#APP_EXECUTIVE_LEVEL").val();
	
	$.getJSON(urlPathContext + "/searchComCode.do?SEL_CD=searchMemCom&param1="+memCode, {format: 'json'}, function(data) {
		
		var vData="";
		
		$("#APP_EXECUTIVE").html("");
		var vData = "<option value=''>선택</option>";
		$.each(data, function(i, n) {
			vData += "<option value='"+n.MEM_CODE+"'>"+n.MEM_NAME+"</option>";
		});
		$("#APP_EXECUTIVE").append(vData);
	});
}



// 수정버튼 클릭 이벤트 함수
function fn_updateExpense() {
	if (!fn_check()) return null;
	var paramap = $("#EXP_CODE").val();
	var jsonData = makeJson($("#dataForm").serialize());
	$.ajax({
		type : "POST",
		url : urlPathContext + "/updateExpense.do",
		data : "data=" + jsonData,
		dataType : "text",
		success : function(data) {
		if (data > 0) {
				alert("수정 성공");
				window.opener.location.reload(); 
				self.close();
			} else {
				alert("수정 실패");
			}
		},
		complete : function(xhr, textStatus) {
		},
		error : function() {
			alert("error");
		}
	});
}





// 반려이거나 임시저장일때 등록버튼 클릭 이벤트 함수
function fn_updateSRSave() {
	if (!fn_check()) return null;
	var paramap = $("#EXP_CODE").val();
	var vAppState = $("#APP_STATE").val();
	var vUpdateState = "";
	
	if(vAppState =='AP030' || vAppState =='AP010'){
		vUpdateState = 'AP020';
	}
	
	
	var jsonData = makeJson($("#dataForm").serialize());
	$.ajax({
		type : "POST",
		url : urlPathContext + "/updateSRSave.do?key4="+vUpdateState,
		data : "data=" + jsonData,
		dataType : "text",
		success : function(data) {
			if (data > 0) {
				alert("등록 성공");
				window.opener.location.reload(); 
				self.close();
			} else {
				alert("등록 실패");
			}
		},
		complete : function(xhr, textStatus) {
		},
		error : function() {
			alert("error");
		}
	});
}




// 결재버튼 클릭 이벤트 함수
function fn_updateApproval() {
	var paramap = $("#EXP_CODE").val();
	var vAppState = $("#APP_STATE").val();
	var vUpdateState = "";
	
	if(vAppState == 'AP010'){
		vUpdateState = 'AP020';
	}
	else if(vAppState =='AP030'){
		vUpdateState = 'AP020';
	}
	else if(vAppState =='AP020'){
		vUpdateState = 'AP040';
	}
	else if(vAppState =='AP040'){
		vUpdateState = 'AP050';
	}
	else if(vAppState =='AP050'){
		vUpdateState = 'AP060';
	}
	
	var jsonData = makeJson($("#dataForm").serialize());
	$.ajax({
		type : "POST",
		url : urlPathContext + "/updateApproval.do?key4="+vUpdateState,
		data : "data=" + jsonData,
		dataType : "text",
		success : function(data) {
			if (data > 0) {
				alert("결재 성공");
				window.opener.location.reload(); 
				self.close();
			} else {
				alert("결재 실패");
			}
		},
		complete : function(xhr, textStatus) {
		},
		error : function() {
			alert("error");
		}
	});
}


// 반려버튼 클릭 이벤트 함수
function fn_updateRollback() {
	if (!fn_checkRollback()) return null;
	
	var paramap = $("#EXP_CODE").val();
	var jsonData = makeJson($("#dataForm").serialize());
	$.ajax({
		type : "POST",
		url : urlPathContext + "/updateRollback.do",
		data : "data=" + jsonData,
		dataType : "text",
		success : function(data) {
			if (data > 0) {
				alert("반려 성공");
				window.opener.location.reload(); 
				self.close();
			} else {
				alert("반려 실패");
			}
		},
		complete : function(xhr, textStatus) {
		},
		error : function() {
			alert("error");
		}
	});
}



// 임시저장 버튼 이벤트 
function fn_semisave() {
//	fn_validate();	
//	if (!fn_check()) return null;
	var jsonData = makeJson($("#dataForm").serialize());
	
	$.ajax({
		type : "POST",
		url : urlPathContext + "/saveExpense.do?btn_flag=AP010",
		data : "data=" + jsonData,
		dataType : "text",
		success : function(data) {
			if (data > "0") {
				alert("임시저장 성공");
				window.opener.location.reload(); 
			    self.close();
			} else {
				alert("임시저장 실패");
			}
		},
		complete : function(xhr, textStatus) {
		},
		error : function() {
			alert("error");
		}
	});
}



// 등록 버튼 이벤트 
function fn_save() {
//	fn_validate();
	if (!fn_check()) return null;
	var jsonData = makeJson($("#dataForm").serialize());
	
	$.ajax({
		type : "POST",
		url : urlPathContext + "/saveExpense.do?btn_flag=AP020",
		data : "data=" + jsonData,
		dataType : "text",
		success : function(data) {
			if (data > "0") {
				alert("등록 성공");
				window.opener.location.reload(); 
			    self.close();
			} else {
				alert("등록 실패");
			}
		},
		complete : function(xhr, textStatus) {
		},
		error : function() {
			alert("error");
		}
	});
}


// 입력 폼 체크하기 (미작성시 경고창 뜨기) 
function fn_check() {
	if (!((($("#APP_TEAM_LEVEL").val()).length)>0)) {
		alert("결재란의 팀장 직책을 선택하시오.");
		$("#APP_TEAM_LEVEL").focus();
		return false;
	}else if (!((($("#APP_TEAM").val()).length)>0)) {
		alert("결재란의 팀장 성함을 선택하시오.");
		$("#APP_TEAM").focus();
		return false;
	}else if (!((($("#APP_EXECUTIVE_LEVEL").val()).length)>0)) {
		alert("결재란의 임원 직책을 선택하시오.");
		$("#APP_EXECUTIVE_LEVEL").focus();
		return false;
	}else if (!((($("#APP_EXECUTIVE").val()).length)>0)) {
		alert("결재란의 임원 성함을 선택하시오.");
		$("#APP_EXECUTIVE").focus();
		return false;
	}else {
		if(!confirm("저장하시겠습니까?")) {
			return false;
		} else {
			return true;
		}
	}
}

//반려사유 체크
function fn_checkRollback(){
	if (!((($("#ROLLBACK_REASON").val()).length)>0)) {
		alert("반려사유를 입력하시오.");
		$("#ROLLBACK_REASON").focus();
		return false;
	}else {
		if(!confirm("저장하시겠습니까?")) {
			return false;
		} else {
			return true;
		}
	}
}










//function fn_save() {
////	fn_validate();
//	var jsonData = makeJson($("#dataForm").serialize());
//	
//	$.ajax({
//		type : "POST",
//		url : urlPathContext + "/saveExpense.do",
//		data : "data=" + jsonData,
//		dataType : "text",
//		success : function(data) {
//		},
//		complete : function(xhr, textStatus) {
//		},
//		error : function() {
//			alert("error");
//		}
//	});
//}

//json형태로 변환
function makeJson(data) {
	var retData = "{\""+data.replace(/%/g, '%25')+"\"}";
	
	retData = retData.replace(/&/gi,"\",\"");
	retData = retData.replace(/=/gi,"\":\"");
	
	return retData;
};