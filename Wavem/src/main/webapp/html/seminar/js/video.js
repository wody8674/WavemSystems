/*
var video;
var width;
var height;
var canvas;
var ctx;
var play;
var pause;
var mute;
var capture;

$(document).ready(function() {
	video = $("#v");
	width = $("#v").width();
	height = $("#v").height();
	canvas = $("#c");
	ctx = $("#c")[0].getContext("2d");
	play = $("#play");
	pause = $("#pause");
	mute = $("#mute");
	capture = $("#capture");
	
	if(!(video.paused && video.ended)) {
		var _intervalId = setInterval(function() {
			ctx.clearRect(0, 0, width, height);
			try {
				ctx.drawImage(video, 0, 0, width, height);
			} catch(e) {
			}
		}, 1000 / 10);
	}
});

play.addEventListener("click", function() {
	if(video.paused || video.ended){
		video.play();
		play.disabled = true;
		pause.disabled = false;
	}
});

mute.addEventListener("click", function() {
	video.muted = video.muted ? false : true;
});

pause.addEventListener("click", function() {
	if(video.played){
		video.pause();
		pause.disabled = true;
		play.disabled = false;
	}
});
capture.addEventListener("click", function() {
	var img = document.createElement("img");
	img.src = canvas.toDataURL("image/jpeg");
	document.body.appendChild(img);
}, false);
*/
/*
var video;
var width;
var height;
var canvas;
var ctx;
var play;
var pause;
var mute;
var capture;
var _intervalId;

$(document).ready(function() {
	video = $("#v");
	width = $("#v").width();
	height = $("#v").height();
	canvas = $("#c");
	ctx = $("#c")[0].getContext("2d");
	play = $("#play");
	pause = $("#pause");
	mute = $("#mute");
	capture = $("#capture");
	
	if(!(video.paused && video.ended)) {
		_intervalId = setInterval(fn_drowVideo, 1000/10);
	}
	
	play.click(fn_play);
	pause.click(fn_pause);
	mute.click(fn_mute);
	capture.click(fn_capture);
});


function fn_drowVideo() {
	ctx.clearRect(0, 0, width, height);
	try {
		alert("asdf");
		ctx.drawImage(video, 0, 0, width, height);
	} catch(e) {
	}
}

function fn_play() {
	if(video.paused || video.ended){
		video.play();
		play.disabled = true;
		pause.disabled = false;
	}
}

function fn_pause() {
	if(video.played){
		video.pause();
		pause.disabled = true;
		play.disabled = false;
	}
}

function fn_mute() {
	video.muted = video.muted ? false : true;
}

function fn_capture() {
	var img = $("img").attr({src: canvas.toDataURL("image/jpeg")});
	
	$("body").append(img);
}
*/

var video = document.getElementById("v");
var width = document.getElementById("v").width;
var height = document.getElementById("v").height;
var canvas = document.getElementById("c");
var ctx = document.getElementById("c").getContext("2d");
var play = document.querySelector("#play");
var pause = document.querySelector("#pause");
var mute = document.querySelector("#mute");
var capture = document.querySelector("#capture");

if(!(video.paused && video.ended)) {
	var _intervalId = setInterval(function() {
		ctx.clearRect(0, 0, width, height);
		try {
			ctx.drawImage(video, 0, 0, width, height);
		} catch(e) {
		}
	}, 1000 / 10);
}

play.addEventListener("click", function() {
	if(video.paused || video.ended){
		video.play();
		play.disabled = true;
		pause.disabled = false;
	}
});

mute.addEventListener("click", function() {
	video.muted = video.muted ? false : true;
});

pause.addEventListener("click", function() {
	if(video.played){
		video.pause();
		pause.disabled = true;
		play.disabled = false;
	}
});
capture.addEventListener("click", function() {
	var img = document.createElement("img");
	img.src = canvas.toDataURL("image/jpeg");
	document.body.appendChild(img);
}, false);

