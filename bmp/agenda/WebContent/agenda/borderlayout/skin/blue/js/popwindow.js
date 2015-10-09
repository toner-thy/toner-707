// JavaScript Document
var DARKSPEED = 6; //变黑的速度

var boxCloseCallback;
var Obj=''
document.onmouseup=MUp
document.onmousemove=MMove
var message_box = GE('message_box')

//用于移动窗口(在firefox下暂不可用)，在需要拖动的窗口上设置 onmousedown="MDown(message_box)"
function MDown(Object){
	Obj=Object.id
	//document.all(Obj).setCapture()
	GE( Obj ).setCapture()
	pX=event.x-GE( Obj ).style.pixelLeft;
	pY=event.y-GE( Obj ).style.pixelTop;
}

function MMove(){
if(Obj!=''){
  GE( Obj ).style.left=event.x-pX;
  GE( Obj ).style.top=event.y-pY;
  }
}

function MUp(){
if(Obj!=''){
  GE( Obj ).releaseCapture();
  Obj='';
  }
}

var isIe=(document.all)?true:false;
//设置select的可见状态
function setSelectState(state)
{
	var objl=document.getElementsByTagName('select');
	for(var i=0;i<objl.length;i++)
	{
		if( objl[i] != null )
		{
			objl[i].style.visibility = state;
		}
	}
}
//获取鼠标点击位置
function mousePosition(ev)
{
	if(ev.pageX || ev.pageY)
	{
	return {x:ev.pageX, y:ev.pageY};
	}
	return {
	x:ev.clientX + document.body.scrollLeft - document.body.clientLeft,y:ev.clientY + document.body.scrollTop - document.body.clientTop
	};
}
//获取鼠标点击位置后，窗口应显示的合适位置
function suitMousePosition(ev,wWidth)
{
	var pos = mousePosition(ev);
	
	return{ x:(((pos.x-wWidth)>0)?(pos.x-wWidth):pos.x), y:(pos.y) };
}

function CentralPosition(mesBoxWidth,mesBoxHeight)
{
	return {
		x:((document.documentElement.clientWidth - mesBoxWidth)/2) , y:((document.documentElement.clientHeight-mesBoxHeight)/2)
	}
}

//弹出窗口方法，默认的样式
function showMessageBox(wTitle,content,pos,wWidth)
{
	closeWindow();
	var bWidth=parseInt(document.body.clientWidth);
	var bHeight=parseInt(document.body.clientHeight);
	if(isIe){
	  ;//setSelectState('hidden');
	}
	var back=document.createElement("div");
	back.id="backmask";
	var styleStr="top:0px;left:0px;position:absolute;z-index:40;background:#666;width:"+bWidth+"px;height:"+bHeight+"px;";
	styleStr+=(isIe)?"filter:alpha(opacity=0);":"opacity:0;";
	back.style.cssText=styleStr;
	document.body.appendChild(back);
	showBackground(back,50);
	var mesW=document.createElement("div");
	mesW.id="mesWindow";
	mesW.className="mesWindow";
	mesW.innerHTML="<div class='mesWindowTop'><table width='100%' height='100%'><tr><td>"+wTitle+"</td><td style='width:1px;'><input type='button' onclick='closeWindow();' title='关闭窗口' class='close' value='关闭' /></td></tr></table></div><div class='mesWindowContent' id='mesWindowContent'>"+content+"</div><div class='mesWindowBottom'></div>";
	//获取鼠标点击后的适当的窗口显示位置
	//styleStr="left:"+(((pos.x-wWidth)>0)?(pos.x-wWidth):pos.x)+"px;top:"+(pos.y)+"px;position:absolute;width:"+wWidth+"px;";
	//获取居中显示位置
	styleStr="left:"+ pos.x +"px;top:" + (pos.y) + "px;position:absolute;width:" + wWidth +"px;";
	
	mesW.style.cssText=styleStr;
	document.body.appendChild(mesW);
}

//弹出窗口方法，窗口由用户的DIV定义好了，只是显示出来
function showDivBox(divBoxToShow , callback )
{
	divBox = divBoxToShow;
	boxCloseCallback = callback; //在窗口关闭后，回调用函数
	
	//把DIVBOX从原来的位置移走，放到body内，以防止被别的元素挡住
	divBox.parentNode.removeChild( divBox ) 
	document.body.appendChild(divBox);
	
	//closeWindow();
	bodywidth = parseInt(document.body.clientWidth);
	bodyheight = parseInt(document.body.clientHeight);
	docwidth = parseInt(document.documentElement.clientWidth);
	docheight = parseInt(document.documentElement.clientHeight);
	var bWidth=  bodywidth > docwidth? bodywidth : docwidth;
	var bHeight= bodyheight > docheight ? bodyheight : docheight; 
	if(isIe){
		;//setSelectState('hidden');
	}
	
	var back=document.createElement("div");
	back.id="backmask";
	var styleStr="top:0px;left:0px;position:absolute;background:#666;width:"+bWidth+"px;height:"+bHeight+"px;";
	styleStr+=(isIe)?"filter:alpha(opacity=0);":"opacity:0;";
	back.style.cssText=styleStr;
	document.body.appendChild(back);
	
	
	showBackground(back,50);
	divBoxToShow.style.visibility = "visible"
	divBoxToShow.style.display = "block";
	
	//showDivWithBackIframe(divBoxToShow.id , true)
	
	GE('message_box').childNodes[0].onmousedown=function(){MDown( GE('message_box') ) }
	if(!isIE())
	{	//获取居中显示位置，用于非IE浏览器, firefox
		var pos = CentralPosition(350,200);
		styleStr="left:"+ pos.x +"px;top:" + (pos.y - 150) + "px;position:absolute;width:" + 350 +"px;";
		divBoxToShow.style.cssText += styleStr;
	}
	
}

//让背景渐渐变暗
function showBackground(obj,endInt)
{
	if(isIe)
	{
		obj.filters.alpha.opacity+= DARKSPEED;
	if(obj.filters.alpha.opacity<endInt)
	{
		setTimeout(function(){showBackground(obj,endInt)},50);}
	}
	else{
		var al=parseFloat(obj.style.opacity);al+=0.02;
		obj.style.opacity=al;
		if(al<(endInt/100))
		{setTimeout(function(){showBackground(obj,endInt)},50);}
	}
}
//关闭窗口
var divBox;
function closeWindow()
{
	
	if(document.getElementById('backmask')!=null)
	{
		document.getElementById('backmask').parentNode.removeChild(document.getElementById('backmask'));
	}
	if(document.getElementById('mesWindow')!=null)
	{
		document.getElementById('mesWindow').parentNode.removeChild(document.getElementById('mesWindow'));
	}
	if(divBox !=null)
	{
		

		divBox.style.visibility = 'hidden';
		divBox.style.display = "none";
		//showDivWithBackIframe( divBox.id , false)
		
		if(boxCloseCallback != null)
		{
			boxCloseCallback();
		}
	}
	
	

	if(isIe){
		;//setSelectState('');
	}
}
//测试弹出
/*
function testMessageBox(ev)
{
  var objPos = suitMousePosition(ev,350);
	//var objPos = CentralPosition(350,200);
	messContent="<div style='padding:20px 0 20px 0;text-align:center'>消息正文</div>";
	showMessageBox('窗口标题',messContent,objPos,350);
}
function testShowDivBox(divObj)
{
	showDivBox(divObj);
}
*/