var context;
var lastX;
var lastY;
var hue;

$(document).ready(function() {
	context = $("canvas")[0].getContext("2d");
	lastX = context.canvas.width * Math.random();
	lastY = context.canvas.height * Math.random();
	hue = 0;

	setInterval(line, 30);
	setInterval(blank, 100);
});

function line() {
	context.save(); // 저장
	context.beginPath();
	context.lineWidth = 5 + Math.random() * 10;
	context.moveTo(lastX, lastY);
	
	lastX = context.canvas.width * Math.random();
	lastY = context.canvas.height * Math.random();
	
	context.bezierCurveTo(
		context.canvas.width * Math.random(),
		context.canvas.height * Math.random(),
		context.canvas.width * Math.random(),
		context.canvas.height * Math.random(),
		lastX,
		lastY
	);
	
	hue = hue + 10 * Math.random();
	
	context.strokeStyle = 'hsl(' + hue + ', 50%, 50%)';
	context.shadowColor = 'white';
	context.shadowBlur = 10;
	context.stroke(); // 위에 세팅한 값 실제로 그림
	context.restore(); // 초기에 save()한 상태로 다시 돌림
}

function blank() {
	context.fillStyle = 'rgba(0,0,0,0.1)';
	context.fillRect(0, 0, context.canvas.width, context.canvas.height);
}

//var context = document.getElementsByTagName('canvas')[0].getContext('2d');
//var context = document.getElementsByTagName('canvas')[0].getContext('2d');
//var lastX = context.canvas.width * Math.random();
//var lastY = context.canvas.height * Math.random();
//var hue = 0;
//
//function line() {
//	context.save(); // 저장
//	context.beginPath();
//	context.lineWidth = 5 + Math.random() * 10;
//	context.moveTo(lastX, lastY);
//	
//	lastX = context.canvas.width * Math.random();
//	lastY = context.canvas.height * Math.random();
//	
//	context.bezierCurveTo(
//		context.canvas.width * Math.random(),
//		context.canvas.height * Math.random(),
//		context.canvas.width * Math.random(),
//		context.canvas.height * Math.random(),
//		lastX,
//		lastY
//	);
//	
//	hue = hue + 10 * Math.random();
//	
//	context.strokeStyle = 'hsl(' + hue + ', 50%, 50%)';
//	context.shadowColor = 'white';
//	context.shadowBlur = 10;
//	context.stroke(); // 위에 세팅한 값 실제로 그림
//	context.restore(); // 초기에 save()한 상태로 다시 돌림
//}
//
//setInterval(line, 30);
//
//function blank() {
//	context.fillStyle = 'rgba(0,0,0,0.1)';
//	context.fillRect(0, 0, context.canvas.width, context.canvas.height);
//}
//
//setInterval(blank, 5);
//
//
