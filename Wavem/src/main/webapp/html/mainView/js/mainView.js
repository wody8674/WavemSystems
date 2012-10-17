$(document).ready(function() {
	fn_select_Notice();
	fn_select_anniversary();
	fn_select_project_iss();
	fn_select_library();
	fn_select_approval();
});

/**
 * 	공지사항 조회
 * 
 *	@function : fn_select_Notice()
 *	@author   : 정재요
 *	@param    : N/A
 *	@return   : N/A
 */
function fn_select_Notice() {
	$.getJSON("selectMain.do?sel_cd=selectNotice", {format: 'json'}, function(data) {
		
		// 공지사항 데이터
		var noticeData = "";
		var row = 0;
		
		$.each(data, function(i, n) {
			noticeData += "<tr>";
			noticeData += "	<td align='center'>"+(n.CNT-i)+"</td>";
			noticeData += "	<td>"+n.TYPE+"</td>";
			noticeData += "	<td>"+n.TITLE+"</td>";
			noticeData += "	<td>"+n.FIR_REG_DAY+"</td>";
			noticeData += "</tr>";
			row = i+1;
		});
		
		for (var j=0; j<(5-row); j++) {
			noticeData += "<tr>";
			noticeData += "	<td colspan='4' align='center'> - </td>";
			noticeData += "</tr>";
		}
		
		$("#tab_notice").html(noticeData);
		
	});
}

/**
 * 	기념일 조회
 * 
 *	@function : fn_select_anniversary()
 *	@author   : 정재요
 *	@param    : N/A
 *	@return   : N/A
 */
function fn_select_anniversary() {
$.getJSON("selectMain.do?sel_cd=selectAnniversary", {format: 'json'}, function(data) {
		
		// 공지사항 데이터
		var anniversaryData = "";
		var row = 0;
		
		$.each(data, function(i, n) {
			anniversaryData += "<tr>";
			anniversaryData += "	<td align='center'>"+(data.length-i)+"</td>";
			anniversaryData += "	<td>"+n.SUBTITLE+"- "+n.MEM_NAME+"</td>";
			anniversaryData += "	<td>"+n.ANNIVERSARY_DAY+"</td>";
			anniversaryData += "</tr>";
			row = i+1;
		});
		
		for (var j=0; j<(5-row); j++) {
			anniversaryData += "<tr>";
			anniversaryData += "	<td colspan='3' align='center'> - </td>";
			anniversaryData += "</tr>";
		}

		$("#tab_anniversary").html(anniversaryData);
		
	});
}

/**
 * 	프로젝트 이슈
 * 
 *	@function : fn_select_project_iss()
 *	@author   : 정재요
 *	@param    : N/A
 *	@return   : N/A
 */
function fn_select_project_iss() {
	$.getJSON("selectMain.do?sel_cd=selectProjectIss", {format: 'json'}, function(data) {
		
		// 공지사항 데이터
		var projectIssData = "";
		var row = 0;
		
		$.each(data, function(i, n) {
			projectIssData += "<tr>";
			projectIssData += "	<td align='center'>"+(n.CNT-i)+"<input type='hidden' value='"+n.PRO_CODE+"'></td>";
			projectIssData += "	<td title='"+n.PRO_NAME+"'>"+n.PRO_NAME_THUMNAIL+"</td>";
			projectIssData += "	<td title='"+n.TITLE+"'>"+n.TITLE_THUMNAIL+"</td>";
			projectIssData += "	<td>"+n.FIR_REG_DAY+"</td>";
			projectIssData += "</tr>";
			row = i+1;
		});
		
		for (var j=0; j<(5-row); j++) {
			projectIssData += "<tr>";
			projectIssData += "	<td colspan='4' align='center'> - </td>";
			projectIssData += "</tr>";
		}

		$("#tab_project_iss").html(projectIssData);
		
	});
}

/**
 * 	자료실
 * 
 *	@function : fn_select_library()
 *	@author   : 정재요
 *	@param    : N/A
 *	@return   : N/A
 */
function fn_select_library() {
	$.getJSON("selectMain.do?sel_cd=selectLibrary", {format: 'json'}, function(data) {
		
		// 공지사항 데이터
		var libraryData = "";
		var row = 0;
		
		$.each(data, function(i, n) {
			libraryData += "<tr>";
			libraryData += "	<td align='center'>"+(n.CNT-i)+"<input type='hidden' value='"+n.PRO_CODE+"'></td>";
			libraryData += "	<td title='"+n.TITLE+"'>"+n.TITLE+"</td>";
			libraryData += "	<td>"+n.FIR_REG_DAY+"</td>";
			libraryData += "</tr>";
			row = i+1;
		});

		for (var j=0; j<(5-row); j++) {
			libraryData += "<tr>";
			libraryData += "	<td colspan='3' align='center'> - </td>";
			libraryData += "</tr>";
		}

		$("#tab_library").html(libraryData);
		
	});
}

/**
 * 	결재현황
 * 
 *	@function : fn_select_approval()
 *	@author   : 정재요
 *	@param    : N/A
 *	@return   : N/A
 */
function fn_select_approval() {
	$.getJSON("selectMain.do?sel_cd=selectApproval", {format: 'json'}, function(data) {
		
		// 공지사항 데이터
		var approvalData = "";
		var row = 0;
		
		$.each(data, function(i, n) {
			approvalData += "<tr>";
			approvalData += "	<td align='center'>"+(n.CNT-i)+"<input type='hidden' value='"+n.APP_CODE+"'></td>";
			approvalData += "	<td>"+n.MEM_NAME+"</td>";
			approvalData += "	<td title='"+n.TITLE+" ("+n.APP_STATE_NM+")"+"'>"+n.TITLE_THUMBNAIL+"</td>";
			approvalData += "	<td>"+n.APPR_DATE+"</td>";
			approvalData += "</tr>";
			row = i+1;
		});

		for (var j=0; j<(5-row); j++) {
			approvalData += "<tr>";
			approvalData += "	<td colspan='4' align='center'> - </td>";
			approvalData += "</tr>";
		}

		$("#tab_approval").html(approvalData);
		
	});
}























