$(document).ready(function(){
	$.ajaxSetup({async:false});
	fn_getMemCd();
	fn_search();
	$("#REQ_LEVEL").change(fn_selectMem_REQ);
	$("#APP_DAM_LEVEL").change(fn_selectMemCom_DAM);
	$("#APP_EXECUTIVE_LEVEL").change(fn_selectMemCom_EXECUTIVE);
	$("#APP_TEAM_LEVEL").change(fn_selectMemCom_TEAM);
});

//휴가원 전체화면 조회
function fn_search() {
	
	var vCodeVal 		= $("#REQ_CODE").val();
	var vAppDam 		= $("#APP_DAM2").val();
	var vAppTEAM 		= $("#APP_TEAM2").val();
	var vAppEXECUTIVE	= $("#APP_EXECUTIVE2").val();
	var vUserMc 		= $("#USER_MC").val();
	var vAppState 		= $("#APP_STATE").val();
	
	//상세보기 함수불러오기  (코드값이 있고 작성자가 로그인한 사람이 아닐 경우)- 결재버튼과 반려 생성하기
	  if(vCodeVal != 'null' && vAppDam != vUserMc){
		//상세보기 함수
		fn_selectUserInfoApproval();
		//비활성화시키기 (타인일경우 무조건 비활성화이다)
		fn_disabled();
		//버튼 value 바꾸기
		$("#btnSemiSave").attr("value", "반려");
		$("#btnSave").attr("value", "결재");
		//여기서 현재 결재 처리상태에 따라 버튼 처리 및 비활성화 처리해주기 
		if(vAppState == '0'){
			$("#btnSemiSave").hide();
			$("#btnSave").attr("value", "등록");
		}else if (vAppState == '1'  && vAppEXECUTIVE == vUserMc){
			//비활성화
			$("#APP_DAM_LEVEL").attr("disabled","true");
			$("#APP_DAM").attr("disabled","true");
			
			//버튼
			$("#btnSave").hide();
			$("#btnSemiSave").hide();
		}else if (vAppState == '1'  && '00000000' == vUserMc){
			//비활성화
			$("#APP_DAM_LEVEL").attr("disabled","true");
			$("#APP_DAM").attr("disabled","true");
			
			//버튼
			$("#btnSave").hide();
			$("#btnSemiSave").hide();
		}else if (vAppState == '1'){
			//비활성화
			$("#APP_DAM_LEVEL").attr("disabled","true");
			$("#APP_DAM").attr("disabled","true");
		}else if(vAppState == '2' && vAppTEAM == vUserMc){
			//비활성화
			$("#APP_DAM_LEVEL").attr("disabled","true");
			$("#APP_DAM").attr("disabled","true");
			$("#APP_TEAM_LEVEL").attr("disabled","true");
			$("#APP_TEAM").attr("disabled","true");
			
			//버튼
			$("#btnSave").hide();
			$("#btnSemiSave").hide();
		}else if (vAppState == '2'  && '00000000' == vUserMc){
			//비활성화
			$("#APP_DAM_LEVEL").attr("disabled","true");
			$("#APP_DAM").attr("disabled","true");
			$("#APP_TEAM_LEVEL").attr("disabled","true");
			$("#APP_TEAM").attr("disabled","true");
			
			//버튼
			$("#btnSave").hide();
			$("#btnSemiSave").hide();
		}else if(vAppState == '2'){
			//비활성화
			$("#APP_DAM_LEVEL").attr("disabled","true");
			$("#APP_DAM").attr("disabled","true");
			$("#APP_TEAM_LEVEL").attr("disabled","true");
			$("#APP_TEAM").attr("disabled","true");
		}else if(vAppState == '3' && vAppEXECUTIVE == vUserMc){
			//비활성화
			$("#APP_DAM_LEVEL").attr("disabled","true");
			$("#APP_DAM").attr("disabled","true");
			$("#APP_TEAM_LEVEL").attr("disabled","true");
			$("#APP_TEAM").attr("disabled","true");
			$("#APP_EXECUTIVE_LEVEL").attr("disabled","true");
			$("#APP_EXECUTIVE").attr("disabled","true");
			
			//버튼
			$("#btnSave").hide();
			$("#btnSemiSave").hide();
		}else if(vAppState == '3' && vAppTEAM == vUserMc){
			//비활성화
			$("#APP_DAM_LEVEL").attr("disabled","true");
			$("#APP_DAM").attr("disabled","true");
			$("#APP_TEAM_LEVEL").attr("disabled","true");
			$("#APP_TEAM").attr("disabled","true");
			$("#APP_EXECUTIVE_LEVEL").attr("disabled","true");
			$("#APP_EXECUTIVE").attr("disabled","true");
			
			//버튼
			$("#btnSave").hide();
			$("#btnSemiSave").hide();
		}else if(vAppState == '3'){
			//비활성화
			$("#APP_DAM_LEVEL").attr("disabled","true");
			$("#APP_DAM").attr("disabled","true");
			$("#APP_TEAM_LEVEL").attr("disabled","true");
			$("#APP_TEAM").attr("disabled","true");
			$("#APP_EXECUTIVE_LEVEL").attr("disabled","true");
			$("#APP_EXECUTIVE").attr("disabled","true");
		}else if(vAppState =='4'){
			//비활성화
			$("#APP_DAM_LEVEL").attr("disabled","true");
			$("#APP_DAM").attr("disabled","true");
			$("#APP_TEAM_LEVEL").attr("disabled","true");
			$("#APP_TEAM").attr("disabled","true");
			$("#APP_EXECUTIVE_LEVEL").attr("disabled","true");
			$("#APP_EXECUTIVE").attr("disabled","true");
			$("#APP_MASTER").attr("disabled","true");
			
			//버튼
			$("#btnSave").hide();
			$("#btnSemiSave").hide();
		}
	  }
	  
	  
	  //상세보기 함수불러오기  (코드값이 있고 작성자가 로그인한 사람일 경우)
	  else if (vCodeVal != 'null' && vAppDam == vUserMc) {
			//$("#btnSave").attr("value", "수정");
			$("#btnSemiSave").hide();
			fn_selectUserInfo();
			if(vAppState == '0'){
				$("#btnSemiSave").hide();
				$("#btnSave").attr("value", "등록");
			}else if(vAppState == '1'){
				$("#APP_DAM_LEVEL").attr("disabled","true");
				$("#APP_DAM").attr("disabled","true");
				$("#btnSemiSave").hide();
			}else if(vAppState == '2'){
				//비활성화
				fn_disabled();
				$("#APP_DAM_LEVEL").attr("disabled","true");
				$("#APP_DAM").attr("disabled","true");
				$("#APP_TEAM_LEVEL").attr("disabled","true");
				$("#APP_TEAM").attr("disabled","true");
				
				//버튼
				$("#btnSave").hide();
				$("#btnSemiSave").hide();
			}else if(vAppState == '3'){
				//비활성화
				fn_disabled();
				$("#APP_DAM_LEVEL").attr("disabled","true");
				$("#APP_DAM").attr("disabled","true");
				$("#APP_TEAM_LEVEL").attr("disabled","true");
				$("#APP_TEAM").attr("disabled","true");
				$("#APP_EXECUTIVE_LEVEL").attr("disabled","true");
				$("#APP_EXECUTIVE").attr("disabled","true");
				
				//버튼
				$("#btnSave").hide();
				$("#btnSemiSave").hide();
			}else if(vAppState == '4'){
				//비활성화
				fn_disabled();
				$("#APP_DAM_LEVEL").attr("disabled","true");
				$("#APP_DAM").attr("disabled","true");
				$("#APP_TEAM_LEVEL").attr("disabled","true");
				$("#APP_TEAM").attr("disabled","true");
				$("#APP_EXECUTIVE_LEVEL").attr("disabled","true");
				$("#APP_EXECUTIVE").attr("disabled","true");
				$("#").attr("disabled","true");
				
				//버튼
				$("#btnSave").hide();
				$("#btnSemiSave").hide();
			}
			
	  }
	  
	  
	  else{
		//신규등록 함수 불러오기( 코드값이 없을 경우 )
		fn_selectSession();
	}
}

//비활성화 함수
function fn_disabled(){
	$("#REQ_DATE").attr("disabled","true");
	$("#REQ_TEAM").attr("disabled","true");
	$("#REQ_LEVEL").attr("disabled","true");
	$("#REQ_NAME").attr("disabled","true");
	$("#REQ_TITLE").attr("disabled","true");
	$("#REQ_CONTENTS").attr("disabled","true");
}


//신규등록
function fn_selectSession() {
	$.getJSON(urlPathContext + "/sessionInfo.do", {format: 'json'}, function(data) {
		$.each(data, function(i, n) {
			$("#REQ_LEVEL").attr("value", n.userMP);
			$("#REQ_TEAM").attr("value", n.userDC);
			$("#REQ_NAME").attr("value", n.userMC);
			$("#APP_DAM").attr("value", n.userMC);
			$("#APP_DAM_LEVEL").attr("value", n.userMP)
		});
		//등록버튼 클릭시 저장하는 함수불러오기
		$("#btnSave").click(fn_save);
		$("#btnSemiSave").click(fn_semisave);
	});
}
//등록된 정보 조회
function fn_selectUserInfo() {
	//휴가종류 함수불러오기
	var paramap = $("#REQ_CODE").val();
	var paramap2 = $("#APP_CODE").val();
	var paramap3 = $("#APP_DAM").val();

	$.getJSON(urlPathContext + "/searchInsertRequest.do?key="+paramap+"&key2="+paramap2, {format: 'json'}, function(data) {
		$.each(data, function(i, n) {
			//조회된 기안서 정보들
			$("#REQ_DATE").attr("value", n.REQ_DATE);
			$("#REQ_NAME").attr("value", n.MEM_CODE);
			$("#REQ_TEAM").attr("value", n.DEP_CODE);
			$("#REQ_LEVEL").attr("value", n.MEM_POST);
			$("#REQ_TITLE").attr("value", n.REQ_TITLE);
			$("#REQ_CONTENTS").attr("value", n.REQ_CONTENTS);
			//결재란
			$("#APP_MASTER").attr("value", n.APP_MASTER_NM);
			$("#APP_DAM_LEVEL").attr("value",n.MEM_POST);
			$("#APP_DAM").attr("value",n.APP_DAM);
			$("#APP_TEAM_LEVEL").attr("value",n.APP_TEAM_LEVEL_NM);
			$("#APP_TEAM").attr("value",n.APP_TEAM);
			$("#APP_EXECUTIVE_LEVEL").attr("value",n.APP_EXECUTIVE_LEVEL_NM);
			$("#APP_EXECUTIVE").attr("value",n.APP_EXECUTIVE);
		});
		//수정버튼 클릭시 수정하는함수 불러오기
		$("#btnSave").click(fn_updateBreak);
	});
}

//등록된 정보 조회 (결재)
function fn_selectUserInfoApproval() {
	//휴가종류 함수불러오기
	var paramap = $("#REQ_CODE").val();
	var paramap2 = $("#APP_CODE").val();
	
	$.getJSON(urlPathContext + "/searchInsertRequest.do?key="+paramap+"&key2="+paramap2, {format: 'json'}, function(data) {
		$.each(data, function(i, n) {
			//조회된 휴가원 정보들
			$("#REQ_DATE").attr("value", n.REQ_DATE);
			$("#REQ_NAME").attr("value", n.MEM_CODE);
			$("#REQ_TEAM").attr("value", n.DEP_CODE);
			$("#REQ_LEVEL").attr("value", n.MEM_POST);
			$("#REQ_TITLE").attr("value", n.REQ_TITLE);
			$("#REQ_CONTENTS").attr("value", n.REQ_CONTENTS);
			//결재란
			$("#APP_MASTER").attr("value", n.APP_MASTER_NM);
			$("#APP_DAM_LEVEL").attr("value",n.MEM_POST);
			$("#APP_DAM").attr("value",n.APP_DAM);
			$("#APP_TEAM_LEVEL").attr("value",n.APP_TEAM_LEVEL_NM);
			$("#APP_TEAM").attr("value",n.APP_TEAM);
			$("#APP_EXECUTIVE_LEVEL").attr("value",n.APP_EXECUTIVE_LEVEL_NM);
			$("#APP_EXECUTIVE").attr("value",n.APP_EXECUTIVE);
		});
		//반려버튼 클릭시 반려하는 함수 불러오기
		$("#btnSemiSave").click(fn_updateRollback);
		//결재버튼 클릭시 결재하는함수 불러오기
		$("#btnSave").click(fn_updateApproval);
	});
}


function fn_getMemCd(){
	
	//결재란과 성명란에 부서 콤보박스 뿌려주기 
	$.getJSON(urlPathContext + "/searchDepCom.do", {format: 'json'}, function(data) {
		
		var vData = "<option value=''>선택</option>";
		
		$("#APP_DAM").html("");
		
		$.each(data, function(i, n) {
			vData += "<option value='"+n.DEP_CODE+"'>"+n.DEP_NAME+"</option>";
		});
		$("#REQ_TEAM").append(vData);
	});
	


	// 결재란과 직책란에 직책 콤보박스 뿌려주기
	$.getJSON(urlPathContext + "/searchPostCom.do", {format: 'json'}, function(data) {
		
		var vData = "<option value=''>선택</option>";
		
		$("#APP_DAM_LEVEL").html("");
		
		$.each(data, function(i, n) {
			vData += "<option value='"+n.COM_GROUP+n.COM_CODE+"'>"+n.NAME_1+"</option>";
		});
		$("#APP_DAM_LEVEL").append(vData);
		$("#APP_TEAM_LEVEL").append(vData);
		$("#APP_EXECUTIVE_LEVEL").append(vData);
		$("#REQ_LEVEL").append(vData);
		
		fn_selectMem_REQ();
		fn_selectMemCom_DAM();
		fn_selectMemCom_TEAM();
		fn_selectMemCom_EXECUTIVE();
	});
}

//성명란에 이름 콤보박스 뿌려주기 
function fn_selectMem_REQ(){
	var memCode = $("#REQ_LEVEL").val();
	
	$.getJSON(urlPathContext + "/searchComCode.do?SEL_CD=searchMemCom&param1="+memCode, {format: 'json'}, function(data) {
		
		var vData="";
		
		$("#REQ_NAME").html("");
		
		var vData = "<option value=''>선택</option>";
		
		$.each(data, function(i, n) {
			vData += "<option value='"+n.MEM_CODE+"'>"+n.MEM_NAME+"</option>";
		});
		$("#REQ_NAME").append(vData);
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
function fn_updateRequest() {
	var paramap = $("#REQ_CODE").val();
	var jsonData = makeJson($("#dataForm").serialize());
	$.ajax({
		type : "POST",
		url : urlPathContext + "/updateRequest.do",
		data : "data=" + jsonData,
		dataType : "text",
		success : function(data) {
		alert(data);
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


// 결재버튼 클릭 이벤트 함수
function fn_updateApproval() {
	var paramap = $("#REQ_CODE").val();
	var jsonData = makeJson($("#dataForm").serialize());
	$.ajax({
		type : "POST",
		url : urlPathContext + "/updateApproval.do",
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
	var paramap = $("#REQ_CODE").val();
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
	var jsonData = makeJson($("#dataForm").serialize());
	
	$.ajax({
		type : "POST",
		url : urlPathContext + "/semisaveRequest.do",
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
	var jsonData = makeJson($("#dataForm").serialize());
	
	$.ajax({
		type : "POST",
		url : urlPathContext + "/saveRequest.do",
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



//json형태로 변환
function makeJson(data) {
	var retData = "{\""+data.replace(/%/g, '%25')+"\"}";
	
	retData = retData.replace(/&/gi,"\",\"");
	retData = retData.replace(/=/gi,"\":\"");
	
	return retData;
};