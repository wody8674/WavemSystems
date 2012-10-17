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

	var url = "http://localhost:8080/SpringProject/main/ajaxDataPost.do";
	var getData = "";
	
	getData = "user_id=" + document.getElementById("user_id").value;

	/*
	 * type			: GET & POST
	 * url			: Server Url
	 * handler		: 성공 시 
	 * postDataType	: Post����� �� ������ (Post�� ��� �ʿ�)
	 * postData		: Post����� �� ������ (Post�� ��� �ʿ� Get�� ��쿡�� �ּҿ� �־���)
	 * 
	 */
	ajaxReq.send("POST"
				, url
				, fn_handleRequest
				, "application/x-www-form-urlencoded; charset=UTF-8"
				, getData);
	
}

function fn_handleRequest() {
	if (ajaxReq.getReadyState() == 4 && ajaxReq.getStatus() == 200) {
		
		/*xml파싱 실패 ㅡㅡ*/
		var ret = ajaxReq.getResponseText();
		
		alert(ajaxReq.getResponseXML());
		
		var parser = new DOMParser();
		var xmlRet = parser.parseFromString(ret, "text/xml");

		var userId = xmlRet.getElementsByTagName("userid");
		var assignCd = xmlRet.getElementsByTagName("assigncd");
		var userNm = xmlRet.getElementsByTagName("usernm");
		var levelNm = xmlRet.getElementsByTagName("levelnm");
		var hp = xmlRet.getElementsByTagName("hp");
		var email = xmlRet.getElementsByTagName("email");
		
		document.getElementById("userid").value = getText(userId);
		document.getElementById("assigncd").value = getText(assignCd);
		document.getElementById("usernm").value = getText(userNm);
		document.getElementById("levelnm").value = getText(levelNm);
		document.getElementById("hp").value = getText(hp);
		document.getElementById("email").value = getText(email);

//		if(flag == 1){
//			msPopulate();
//		}else{
//			nonMSPopulae();     
//		}  

	}
}

function getText(elem) {
	var text = "";
	
	if(elem) {
		if(elem.childNodes) {
			for(var i=0; i<elem.childNodes.length; i++) {
				var child = elem.childNodes[i];
				
				if(child.nodeValue) {
					text += child.nodeValue;
				} else {
					if(child.childNodes[0]) {
						if(child.childNodes[0].nodeValue) {
							text += child.childNodes[0].nodeValue;
						}
					}
				}
			}
		}
	}
	
	return text;
}


function nonMSPopulae(){

	//비어있는 XML 문서 object를 만드는 부분
	//var xmlDoc = document.implementation.createDocument("","",null); //==> 찾아봐서 수정하겠슴
	var resp = ajaxReq.getResponseText();

	//XML 문서 object를 만드는 부분이다.
	var parser = new DOMParser();

	//String을 로디하기 위해서 파서에게 알리는 부분
	var dom = parser.parseFromString(resp, "text/xml");

	/* 참고 XML 문서를 파서로 로딩하는 부분

	var xmlDoc = document.implementation.createDocument("","",null);

    //문서가 전부 로딩되기전에 스크립트가 실행되지 않게 유지하기 위해서 비동기 로딩을 끄는 부분

    xmlDoc.async="false";          --| ====>찾아 봐서 수정하겠슴

    xmlDoc.load("sample.xml");   --|

   */
	memVal = dom.getElementsByTagName("userid");
	alert(memVal[0].childNodes.length);
	alert(memVal[0].childNodes[0].nodeName);
	alert(memVal[0].childNodes[1].nodeName);
	alert(memVal[0].childNodes[0].childNodes[0].nodeValue);
	alert(memVal[0].childNodes[1].childNodes[0].nodeValue);
   
}
  
function msPopulate(){
   var resp = ajaxReq.getResponseText();
   var xmlDoc = new ActiveXObject("Msxml2.DOMDocument");
   xmlDoc.async = "true";//파싱하는 동안 동기, 비동기 방식을 지정
   xmlDoc.loadXML(resp);
   memVal = xmlDoc.getElementsByTagName("userid");
   alert(memVal[0].childNodes.length);
   alert(memVal[0].childNodes[0].nodeName);
   alert(memVal[0].childNodes[1].nodeName);
   alert(memVal[0].childNodes[0].childNodes[0].nodeValue);
   alert(memVal[0].childNodes[1].childNodes[0].nodeValue);
         
}





