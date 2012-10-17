package com.wavem.convergence.common.controller;

public class PagingHelper {
	public static PagingHelper instance = new PagingHelper();
	
	private PagingHelper() {
		
	}
	
	public String autoPaging(long plTotalCnt, long plRowRange, long plPageRange, long plCurrPage) {
		StringBuffer tsRetVal = new StringBuffer();
		
		if (plTotalCnt == 0L) {
			return "";
		}
		
		long plPageCnt = plTotalCnt % plRowRange;
		
		if (plPageCnt == 0L) {
			plPageCnt = plTotalCnt / plRowRange;
		}
		else {
			plPageCnt = plTotalCnt / plRowRange + 1L;
		}
		
		tsRetVal.append("<table cellpadding=0 cellspacing=0 border=0 width = '100%' height = '100%'>\n");
		tsRetVal.append("<tr>");
		tsRetVal.append("<td>");
		
		long plRangeCnt = plCurrPage / plPageRange;
		
		if(plCurrPage % plPageRange == 0L){
			plRangeCnt = plCurrPage / plPageRange - 1L;
		}
		
		long tlFirstPage = plCurrPage - plPageRange;
		if (tlFirstPage > 0) {
			tsRetVal.append("<input type='button' style=\"color:black; background-color:lightgray;\" id='btnView' value='처음' onclick='goPage(1)'/>\n");
		}
		else {
			tsRetVal.append("<input type='button' style=\"color:black; background-color:lightgray;\" id='btnView' value='처음'/>\n");
		}
		
		tsRetVal.append("</td>");
		tsRetVal.append("<td>");
		
		if (plCurrPage > plPageRange) {
			String s2;
			if (plCurrPage % plPageRange == 0L) {
				s2 = Long.toString(plCurrPage - plPageRange);
			}
			else {
				s2 = Long.toString(plCurrPage - plCurrPage % plPageRange);
			}
			
			tsRetVal.append("<input type='button' style=\"color:black; background-color:lightgray;\" id='btnView' value='이전 10개' onclick='goPage(").append(s2).append(")'/>\n");
			
		}else{
			tsRetVal.append("<input type='button' style=\"color:black; background-color:lightgray;\" id='btnView' value='이전 10개'/>\n");
		}
		tsRetVal.append("</td>");
		
		for(long index = plRangeCnt * plPageRange + 1L; index < plRangeCnt * plPageRange + 11L; index++)
		{
			String tsFontBegin = "<font size=3>";
			String tsFontEnd = "</font>\n";
			
			if(index == plCurrPage){
				tsFontBegin = "<font size=3><b>";
				tsFontEnd = "</b></font>\n";
			}
			
			tsRetVal.append("<td>");
			tsRetVal.append(tsFontBegin);
			
			
			String str_ButtonStyle = "";
			
			if(index == plCurrPage){
				str_ButtonStyle = "style=\"font-weight:bold; color:white; background-color:teal;\"";
			}else{
				str_ButtonStyle = "style=\"color:black; background-color:lightgray;\"";
			}
			
			
			tsRetVal.append("<input type='button' ").append(str_ButtonStyle).append(" id='btnView' value='").append(Long.toString(index)).append("' onclick='goPage(").append(Long.toString(index)).append(")'/>\n");
			
			tsRetVal.append(tsFontEnd);
			tsRetVal.append("</td>");
						
			if(index == plPageCnt){
				break;
			}
		}
		
		tsRetVal.append("<td>");
		
		if (plPageCnt > (plRangeCnt + 1L) * plPageRange) {
			tsRetVal.append("<input type='button' style=\"color:black; background-color:lightgray;\" id='btnView' value='다음 10개' onclick='goPage(").append(Long.toString((plRangeCnt + 1L) * plPageRange + 1L)).append(")'/>\n");
		}else{
			tsRetVal.append("<input type='button' style=\"color:black; background-color:lightgray;\" id='btnView' value='다음 10개'/>\n");
		}
		
		tsRetVal.append("</td>");
		tsRetVal.append("<td>");
		
		long tlEndPage = plCurrPage + plPageRange;
		if (tlEndPage < plPageCnt) {
			tsRetVal.append("<input type='button' style=\"color:black; background-color:lightgray;\" id='btnView' value='마지막' onclick='goPage(").append(Long.toString(plPageCnt)).append(")'/>\n");
		}
		else {
			tsRetVal.append("<input type='button' style=\"color:black; background-color:lightgray;\" id='btnView' value='마지막'/>\n");
		}
		
		tsRetVal.append("</td>");
		tsRetVal.append("</tr>");
		tsRetVal.append("</table>\n");
		
		return tsRetVal.toString();
	}
}
