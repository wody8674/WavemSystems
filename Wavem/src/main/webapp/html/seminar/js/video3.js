﻿
var video;
var copy;
var copycanvas;
var draw;

var SOURCERECT = {x:0,y:0,w:0,h:0};
var PAINTRECT  = {x:0,y:0,w:1000,h:640};

function init(){
	video = document.getElementById('sourcevid');
	copycanvas = document.getElementById('sourcecopy');
	copy = copycanvas.getContext('2d');
	var outputcanvas = document.getElementById('output');
	draw = outputcanvas.getContext('2d');
	setInterval("processFrame()", 33);
}

var RAD = Math.PI/180;
var randomJump = false;
var debug = false;
var degree = 180;

function processFrame(){
	if(!isNaN(video.duration)){
		if(SOURCERECT.w == 0){
			SOURCERECT = {x:0,y:0,w:video.videoWidth,h:video.videoHeight};
		}
		//this is to keep my sanity while developing
		if(randomJump){
			randomJump = false;
			video.currentTime = Math.random()*video.duration;
		}
		//loop
		if(video.currentTime == video.duration){
			video.currentTime = 0;
		}
	}
	var debugStr = "";
	copy.drawImage(video, 0, 0);
	draw.clearRect(PAINTRECT.x, PAINTRECT.y, PAINTRECT.w, PAINTRECT.h);
	
	var centerx = PAINTRECT.w/2;
	var centery = PAINTRECT.h/2-50;
	var radius = SOURCERECT.w/2;
	
	degree += 1;
	degree %= 360;
	
	var angle = degree*RAD;
	var prepx = Math.cos(angle);
	var prepz = Math.sin(angle);
	
	var shadow1;
	var shadow2;
	
	draw.save();
	var lineargradient = draw.createLinearGradient(0,0,0,PAINTRECT.h-centery);  
	lineargradient.addColorStop(0,'#CCCCCC');  
	lineargradient.addColorStop(1,'#FFFFFF'); 
	draw.fillStyle = lineargradient;
	draw.fillRect(0,centery,PAINTRECT.w,PAINTRECT.h-centery);
	draw.restore();
	
	for(var i=0; i<SOURCERECT.w; i+=4){
		var dist = radius-i;
		var x = prepx*dist;
		var y = 0;
		var z = prepz*dist;
		var height = SOURCERECT.h+(z*0.5);
		
		draw.save();
		if(degree < 90 || degree > 270){
			draw.translate(PAINTRECT.w, 0);
			draw.scale(-1, 1);
			x *= -1;
		}
		var _x = Math.round(x+centerx);
		var _y = y+centery;
		draw.drawImage(copycanvas, i, 0, 4, SOURCERECT.h, _x, _y-(height/2), 4, height);
		draw.restore();
		if(i == 0){
			shadow1 = {x:_x, y:_y+(height*1.4/2)};
		}else if(i == SOURCERECT.w-4){
			shadow2 = {x:_x, y:_y+(height*1.4/2)};
		}
	}
	if(SOURCERECT.w != 0){
		draw.save();
		if(degree < 90 || degree > 270){
			draw.translate(PAINTRECT.w, 0);
			draw.scale(-1, 1);
			x *= -1;
		}
		draw.shadowOffsetX = 0;  
		draw.shadowOffsetY = 0;  
		draw.shadowBlur = 7;  
		draw.shadowColor = "rgba(0, 0, 0, 1)"; 
		draw.strokeStyle = "#DDDDDD";
		draw.lineWidth = 1;
		draw.beginPath();
		draw.moveTo(shadow1.x, shadow1.y);
		draw.lineTo(shadow2.x, shadow2.y);
		draw.stroke();
		draw.restore();
	}
	if(debug){
		//debug = false;
		document.getElementById('trace').innerHTML = ""+shadow1.x+","+shadow1.y+"-"+shadow2.x+","+shadow2.y;
	}
}

var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));

try {
	var pageTracker = _gat._getTracker("UA-15981974-1");
	pageTracker._trackPageview();
} catch(err) {}