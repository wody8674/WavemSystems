$(document).ready(function(){
	$("#btnSubmit").click(fn_login);
	$("#myform").keydown(event, fn_enterkey);
})

function fn_login(){
	var userid = $("#userid").val();
	var pwd = $("#pwd").val();
	
	if(!(userid.length>0)){
		alert("ID를 입력시오!");
		$("#userid").focus();
		return;
	}else if(!(pwd.length>0)){
		alert("비밀번호를 입력하시오!");
		$("#pwd").focus();
		return;
	}
//	}else{
//		alert("ID 혹은 비밀번호를 확인하시기바랍니다");
//		$("#userid").attr("value","");
//		$("#pwd").attr("value","");
//		$("#userid").focus();
//		return;
//	}
	
	$("#myform").submit();
}

function fn_enterkey(event) {
	if (event.keyCode == "13") {
		fn_login();
	}
}