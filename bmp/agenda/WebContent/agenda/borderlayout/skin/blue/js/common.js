//From Seamoon , Sunson
function GE( id )
{
	var me ;
	if(typeof(id) == 'string'){
		me = document.getElementById(id)
	}
	else{
		me = id;
	}
	return me;
}

function isIE(){ //ie? 
   if (window.navigator.userAgent.toLowerCase().indexOf("msie")>=1) 
    return true; 
   else 
    return false; 
}

function redirectTo(ref)
{
//	window.location.href = ref;
	this.location = ref; //兼容firefox
}

function parentRedirectTo(ref)
{
	parent.location = ref;
}


function addClass(element,value) {
  if (!element.className) {
    element.className = value;
  } else {
    newClassName = element.className;
    newClassName+= " ";
    newClassName+= value;
    element.className = newClassName;
  }
}

String.prototype.Trim = function()
{
return this.replace(/(^\s*)|(\s*$)/g, "");
}
String.prototype.LTrim = function()
{
return this.replace(/(^\s*)/g, "");
}
String.prototype.Rtrim = function()
{
return this.replace(/(\s*$)/g, "");
}


/*显示或隐藏一个或多个元素。通过设置display属性，非visibility属性*/
/*第一个参数是对象ID，第二个参数是""(暂未启用) ,第三个参数是'show'或'hide'，往后的参数如此类推*/
function showHideLayers_Display() { //v9.0
  var i,p,v,obj,args=showHideLayers_Display.arguments;
  for (i=0; i<(args.length-2); i+=3) 
  with (document) 
  	if (getElementById && ((obj=getElementById(args[i]))!=null)) { v=args[i+2];
  		var objStyle;
    	if (obj.style) { 
			objStyle=obj.style; 
			v=  (v=='show')?'block':( ( v=='hide')?'none':v ); 
		}
		//alert(objStyle.toString());
    	objStyle.display=v; 
	}
}

function showHideLayers_Visibility() { //v9.0
  var i,p,v,obj,args=showHideLayers_Visibility.arguments;
  for (i=0; i<(args.length-2); i+=3) 
  with (document) if (getElementById && ((obj=getElementById(args[i]))!=null)) { v=args[i+2];
    if (obj.style) { obj=obj.style; v=(v=='show')?'visible':(v=='hide')?'hidden':v; }
    obj.visibility=v; }
}


function addStyleText( objId , styleString )
{
	var	obj = GE( objId )
	obj.style = obj.style.cssText + (";" + styleString) ;
}

//======domReady =============
function domReady(f) {   
    // 假如 DOM 已经加载，马上执行函数   
    if (domReady.done) return f();   
    // 假如我们已经增加了一个函数   
    if (domReady.timer) {   
        // 把它加入待执行函数清单中,ready变成了数组，子元素内容是函数   
         domReady.ready.push(f);
		 
     } else {   
        // 为页面加载完毕绑定一个事件，   
        // 以防它最先完成。使用addEvent   
         __attach_event("load", __isDOMReady);

        // 初始化待执行函数的数组   
         domReady.ready = [f];   
        // 尽可能快地检查DOM是否已可用   
         domReady.timer = setInterval(__isDOMReady, 50);
     }   
}

function __isDOMReady() {   

    // 如果我们能判断出DOM已可用，忽略   
    if (domReady.done) return false;   
    // 检查若干函数和元素是否可用   
    if (document && document.getElementsByTagName && document.getElementById && document.body) {   
        // 如果可用，我们可以停止检查   
         clearTimer(domReady.timer);   
         domReady.timer = null;   
        // 执行所有正等待的函数
		if(typeof domReady.ready != "undefined" && domReady.ready != null)
		{
			for ( var i = 0; i < domReady.ready.length; i++ ) {   
				 domReady.ready[i](); //  获取的子元素内容是函数，执行之 
			 }   
		}
		// 记录我们在此已经完成   
		domReady.ready = null;
        domReady.done = true;   
     }   
} 

function clearTimer(timer){   
     clearTimeout(timer);   
     clearInterval(timer);   
    return null;   
};   
   
function __attach_event(evt, callback) {   
    if (window.addEventListener) {   
         window.addEventListener(evt, callback, false);   
         
     } else if (window.attachEvent) {   
         window.attachEvent("on" + evt, callback);   
     }   
}   
   
//====添加onload事件====
function addLoadEvent(func) {
  var oldonload = window.onload;
  
  if (typeof window.onload != 'function') {
    window.onload = func;
  } else {
    window.onload = function() {
      oldonload();
      func();
    }
  }
}

//=====切换是否显示=====
//displayString可不写
function toggleDisplay(objOrId, displayString ){
	var me = GE(objOrId)
   if (me.style.display =="none"){
    me.style.display= (displayString == null ? "" : displayString  );
    }
   else {
    me.style.display="none";
    }
}

function toggleVisibility(objId){
	var me = GE(objOrId)
   if (me.style.visibility=="hidden"){
    me.style.visibility="visible";
    }
   else {
    me.style.visibility="hidden";
    }
}

//获取下拉列表选中项的文本
function getSelectedText(name){
	var obj= GE(name);
	return obj.options[obj.selectedIndex].innerText  //也可直接写成obj[i]，关键是通过option对象的innerText属性获取到选项文本
}

//获取下拉列表选中项的值
function getSelectedValue( name ){
var obj= GE(name);
return obj.value;      //如此简单，直接用其对象的value属性便可获取到
}

//=============DIV控制 ================
//**************HideShowDivSection***************/
var SectionDivTimer;
var relativepath = "";

var showedPicName = "ball-top1.gif";
var collapsedPicName = "ball-top2.gif";

function setShowSectionPic(showedPic , collapsedPic) //
{
	showedPicName = showedPic;
	collapsedPicName = collapsedPic;
}

function ShowSection(str) //
{
	obj=GE(str);

	obj.style.position='relative';
	obj.style.overflow='hidden';
	
	if( SectionDivTimer != null )
	{
		window.clearInterval(SectionDivTimer);
	}
	
	
	try
	{
		if(	relativepath == "")
		{
			var iconpath = GE( 'Icon' + str ).src ;
			
			pathLastIndex  = iconpath.lastIndexOf( "/" ) ; //获取相对路径
			//alert( iconpath + pathLastIndex)
			
			if(  pathLastIndex > 0 )
			{
				relativepath = iconpath.substring( 0 , pathLastIndex + 1 ); //获取相对路径
				//relativepath = iconpath.replace("ball-top.gif", "");
			}
			else
			{
				relativepath = "";
			}
		}
	}
	catch(e){}
	if(obj.style.display!='none')
	{

		//document.all.item(obj.Icon).src='../../../images/ball-top2.gif';
		try
		{
			GE( 'Icon' + str ).src= relativepath + collapsedPicName;
		}
		catch(e){}
		

		SectionDivTimer=window.setInterval("ShowOrHideSection(obj,'hide');",1);
	}
	else
	{

		try
		{
			//document.all.item(obj.Icon).src='../../../images/ball-top.gif';
			GE( 'Icon' + str ).src= relativepath + showedPicName;
		}
		catch(e){}
		
		obj.style.display='block';
		obj.style.position='';
		obj.style.overflow='';
		SectionDivTimer=window.setInterval("ShowOrHideSection(obj,'show');", 50);
	}
}


function ShowOrHideSection(obj,str) 
{
	var objOpacity
	//alert( obj.style )

	if(obj.filters != null )
	{
		objOpacity = obj.filters[0].opacity;
	}
	else
	{
		objOpacity = obj.style.opacity; //兼容firefox
	}
		
	if(obj.primaHeight==null) obj.primaHeight=obj.offsetHeight;
	if(str=='show')
	{
		if(obj.offsetHeight+10>obj.primaHeight&&objOpacity+5>100) 
		{
		window.clearInterval(SectionDivTimer);
		}
		
		if(obj.offsetHeight+10>obj.primaHeight) 
		{
			obj.style.height=obj.primaHeight + "px"; //兼容firefox需要追加"px"
		}
		else obj.style.height=obj.offsetHeight+7*(obj.primaHeight-obj.offsetHeight)/8 + "px";

		
		if(objOpacity+10>100)
		
			objOpacity=100;
		
		else objOpacity=objOpacity+20;  //*/
		
	}
	else if(str=='hide')
	{
		if(obj.offsetHeight-10<1)
		{
			//objOpacity=0;
			//alert(objOpacity);
			window.clearInterval(SectionDivTimer);
		}
		
		if(obj.offsetHeight - 10 < 1) 
		{
			obj.style.height= 2 + "px";
			obj.style.display='none';
			objOpacity=0;
		}
		else
		{
			obj.style.height = obj.offsetHeight / 3 + "px";
		}
		
		if( obj.filters && objOpacity-10<0) 
		{	
			objOpacity=0;
		}
		else
		{
			//objOpacity=objOpacity-10;
			objOpacity= objOpacity - 10
		}
	}
	if(obj.filters != null )
	{
		 obj.filters[0].opacity = objOpacity;
	}
	else
	{
		obj.style.opacity = 100//objOpacity; //兼容firefox
	}
}

//只允许输入数字
function inputNumericOnly(textControl, boolWithMinus, boolWithDot)
{
	if(boolWithMinus && boolWithDot)
	{
		textControl.onkeyup = function(){textControl.value=textControl.value.replace(/[^\d\-.]/g,'') };
		textControl.onbeforepaste= function(){ clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d\-.]/g,'')) };
	}
	else
	{
		if(boolWithMinus && !boolWithDot)
		{
			textControl.onkeyup = function(){textControl.value=textControl.value.replace(/[^\d\-]/g,'') };
			textControl.onbeforepaste= function(){ clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d\-]/g,'')) };
		}
		else
		{
				if(!boolWithMinus && boolWithDot)
				{
					textControl.onkeyup = function(){textControl.value=textControl.value.replace(/[^\d.]/g,'') };
					textControl.onbeforepaste= function(){ clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d.]/g,'')) };
				}
		}
	}
}
/*页面调用例子如下
addLoadEvent(setInputFormal);

function setInputFormal()
{

	inputNumericOnly(GE('txtSaleCount'),true,true);
}
*/


//把对象加入到队列，返回对像保存的位置，相同的对象不会重复添加
//var inputObjArray = new Array(); //对象数组 ，用于作为arrayHolder
function storedUniqueObjToArray(obj , arrayHolder)  
{
	//检测该对像是否已经被加入过。
	var found = false;
	var foundIndex ;
	for(var i=0 ; i < arrayHolder.length ; i ++)
	{
		if(arrayHolder[i] == obj)
		{
			
			found = true //已找到对象
			foundIndex = i;
			//window.alert("found :" + foundIndex)
			break;
		}
	}
	
	if ( found == false ) //找不到对象
	{
		arrayHolder[ arrayHolder.length ] = obj ; //把对象保存起来

		foundIndex = arrayHolder.length - 1 ; 
	}
	return foundIndex;
}

/*====== 处理浏览器兼容性问题  =====*/

//让firefox支持innerText
try
{
	if(!isIE()){ //firefox innerText define
	   HTMLElement.prototype.__defineGetter__(     "innerText", 
		function(){
		 var anyString = "";
		 var childS = this.childNodes;
		 for(var i=0; i<childS.length; i++) {
		  if(childS[i].nodeType==1)
		   anyString += childS[i].tagName=="BR" ? '\n' : childS[i].innerText;
		  else if(childS[i].nodeType==3)
		   anyString += childS[i].nodeValue;
		 }
		 return anyString;
		} 
	   ); 
	   HTMLElement.prototype.__defineSetter__(     "innerText", 
		function(sText){ 
		 this.textContent=sText; 
		} 
	   ); 
	}
}
catch(e){}
/*END====== 处理FireFox兼容性问题  =====END*/


function CtoH( id) 
{
	var str = GE(id).value;
	var result = "";
	for (var i = 0; i < str.length; i++) {
		if (str.charCodeAt(i) == 8220 || str.charCodeAt(i) == 8221) {
			result += String.fromCharCode(34);
		} else if (str.charCodeAt(i) == 8216 || str.charCodeAt(i) == 8217) {
			result += String.fromCharCode(39);
		} else if (str.charCodeAt(i) == 12290) {
			result += String.fromCharCode(46);
		} else if (str.charCodeAt(i) == 12289) {
			result += String.fromCharCode(44);
		} else if (str.charCodeAt(i) == 12288) {
			result += String.fromCharCode(32);
		} else if (str.charCodeAt(i) > 65280 && str.charCodeAt(i) < 65375) {
			result += String.fromCharCode(str.charCodeAt(i) - 65248);
		} else {
			result += String.fromCharCode(str.charCodeAt(i));
		}
	}
	GE(id).value = result;
}

function StringCtoH( p_source) 
{

	var str = p_source;
	var result = "";
	for (var i = 0; i < str.length; i++) {
		if (str.charCodeAt(i) == 8220 || str.charCodeAt(i) == 8221) {
			result += String.fromCharCode(34);
		} else if (str.charCodeAt(i) == 8216 || str.charCodeAt(i) == 8217) {
			result += String.fromCharCode(39);
		} else if (str.charCodeAt(i) == 12290) {
			result += String.fromCharCode(46);
		} else if (str.charCodeAt(i) == 12289) {
			result += String.fromCharCode(44);
		} else if (str.charCodeAt(i) == 12288) {
			result += String.fromCharCode(32);
		} else if (str.charCodeAt(i) > 65280 && str.charCodeAt(i) < 65375) {
			result += String.fromCharCode(str.charCodeAt(i) - 65248);
		} else {
			result += String.fromCharCode(str.charCodeAt(i));
		}
	}
	
	return result;
}

var firstErrorControl = "";

function SetError( id,state, text)
{
	if( text.trim().length > 0)
		GE( id + "Error" ).innerHTML = text;
		
	if( state.toLowerCase() == "block" && firstErrorControl != null && firstErrorControl == "")
	{
		firstErrorControl = id;
	}
	
	GE( id + "Error" ).style.display = state;
}

function SetErrorFocus()
{
	if( firstErrorControl != null && firstErrorControl != "")
	{
 		try
		{
			
			if( GE(firstErrorControl ).type != "hidden")
			{
				if( GE(firstErrorControl ) != null)
					GE(firstErrorControl ).focus();
				else
					GE(firstErrorControl + "Error" ).focus();
			}
			else
			{
				GE(firstErrorControl + "Error" ).focus();
			}
		}
		catch(e)
		{
			GE(firstErrorControl + "Error" ).focus();
		}
	}
}

function SetErrorControlFocus()
{
	if( firstErrorControl != null && firstErrorControl != "")
	{
 		try
		{
			
			if( GE(firstErrorControl ).type != "hidden")
			{
				if( GE(firstErrorControl ) != null)
					GE(firstErrorControl ).focus();
				else
					GE(firstErrorControl + "Error" ).focus();
			}
			else
			{
				GE(firstErrorControl + "Error" ).focus();
			}
		}
		catch(e)
		{
			if(GE(firstErrorControl + "Error" ) != null)
			{
				GE(firstErrorControl + "Error" ).style.display = "block";
			}
		}
	}
}

function isEmpty(str)
{
	return (str.trim()=="");
}

function isInteger(str)
{
    if (isEmpty(str)) return true;
    
    var reg = /^((\+|-)\d)?\d*$/;
    return reg.test(str);
}

function isDouble(str)
{
    if (isEmpty(str)) return true; 
    
	var reg = /^(?:\+|-)?\d+(?:\.\d+)?$/;
	return reg.test(str);	        
}

function isDate(str)
{
    if (isEmpty(str)) return true;
     
   var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/; 

	var r = str.match(reg); 
	if(r==null)return false; 
	var d= new Date(r[1], r[3]-1,r[4]); 
	var newStr=d.getFullYear()+r[2]+(d.getMonth()+1)+r[2]+d.getDate();
	return newStr==str;
}


function isEmail(str)
{
    if (isEmpty(str)) return true; 
     
   var reg=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/i;
   return reg.test(str);
}

function isEmailA(str)
{
   var reg=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/i;
   return reg.test(str);
}

function isBetween (val, lo, hi) {
    if ((val < lo) || (val > hi)) { return(false); }
    else { return(true); }
}

function isDateTime(str)

{
   if (str=="") return true;
   
   var strDate = str;
   if (strDate.length == 0) return false;

   var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})/;
   var r = strDate.match(reg);

   if (r != null)   //??strDate
      strDate = strDate + " 00:00:00";
 
    reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})/;
    r = strDate.match(reg);
    if (r == null)
    {
       return false;
    }

    var d = new Date(r[1], r[3]-1,r[4],r[5],r[6],r[7]);
    if (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]&&d.getHours()==r[5]&&d.getMinutes()==r[6]&&d.getSeconds()== r[7])
    {
       return d;
    }
    else
    {
       return false;
    }
}

function isChineseChar(str)
{
   if (isEmpty(str)) return true; 
  
   var reg=/^([\u4E00-\u9FA5]|[\uFE30-\uFFA0])*$/gi;
   return reg.test(str);  
}

function includeChineseChar(str)
{
   if (isEmpty(str)) return true;
  
   var reg=/^[\x00-\x7F]*$/;
   return reg.test(str);  
}

function isEngCharAll(str)
{ 
   if (isEmpty(str)) return true;	
   var reg=/^[a-zA-Z]{1,20}$/;
   return reg.test(str);
}
function isCountryCode(str)
{ 
   if (isEmpty(str)) return true;	
   var reg=/^([0-9\+]){1,10}$/;
   return reg.test(str);
}
function isDigitOrEngChar(str)
{ 
   if (isEmpty(str)) return true;	
   var reg=/^([a-zA-Z0-9]){1,100}$/;
   return reg.test(str);
}
function isEngCharUpper(str)
{ 
   if (isEmpty(str)) return true;
   var reg=/^[A-Z]{1,20}$/;
   return reg.test(str);
  
}
function isEngCharLower(str)
{ 
   if (isEmpty(str)) return true;
   var reg=/^[a-z]{1,20}$/;
   return reg.test(str);
}
function isPostCode(str)
{
   if (isEmpty(str)) return true;
   var reg=/^[A-Za-z0-9]{1,12}$/;
   return reg.test(str);
  
}
function isYear(str)
{
   if (isEmpty(str)) return true;
   var reg=/^[0-9]{4}$/;
   return reg.test(str);
  
}
function isUrl(str)
{
   if (isEmpty(str) || str.toLowerCase() == "http://" ) return true;
   var reg=/^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/;
   return reg.test(str);
}
function isDigit(str)
{
    if (isEmpty(str)) return true;
    var reg=/^[0-9]{1,20}$/;
    return reg.test(str);  
}
function isDigitDot(str)
{
    if (isEmpty(str)) return true;
    var reg=/^[0-9.]{1,20}$/;
    return reg.test(str);
}
function isRegisterUserName(str)
{
    if (isEmpty(str)) return true;
    var reg=/^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){4,19}$/;
    return reg.test(str);
    
}
function isTrueName(str)
{
    if (isEmpty(str)) return true;
    var reg=/^[a-zA-Z]{1,30}$/;
    return reg.test(str);
    
}
function isEngDigitEmpty(str)
{
    if (isEmpty(str)) return true;
    var reg=/^([a-zA-Z0-9]|[ ]){1,100}$/;
    return reg.test(str);
    
}
function isEngEmpty(str)
{
    if (isEmpty(str)) return true;
    var reg=/^([a-zA-Z]|[ ]){1,100}$/;
    return reg.test(str);
    
}
function isPasswd(str)
{
    if (isEmpty(str)) return true;
    var reg=/^(\w){6,20}$/;
    return reg.test(str);
    
}
function isTel(str)
{
    if (isEmpty(str)) return true;
    var reg=/^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
    return reg.test(str);
    
}
function isMobil(str)
{
    if (isEmpty(str)) return true;
	var reg=/^[0-9]{1,16}$/;
	return reg.test(str);
}
function isIP(str)  
{
   if (isEmpty(str)) return true; 
   var reg = /^((1??\d{1,2}|2[0-4]\d|25[0-5])\.){3}(1??\d{1,2}|2[0-4]\d|25[0-5])$/;
   return reg.test(str);
}
function isReal (theStr, decLen) {
    var dot1st = theStr.indexOf('.');
    var dot2nd = theStr.lastIndexOf('.');
    var OK = true;
    
    if (isEmpty(theStr)) return false;

    if (dot1st == -1) {
        if (!isInt(theStr)) return(false);
        else return(true);
    }
    
    else if (dot1st != dot2nd) return (false);
    else if (dot1st==0) return (false);
    else {
        var intPart = theStr.substring(0, dot1st);
        var decPart = theStr.substring(dot2nd+1);

        if (decPart.length > decLen) return(false);
        else if (!isInt(intPart) || !isInt(decPart)) return (false);
        else if (isEmpty(decPart)) return (false);
        else return(true);
    }
}
function isComdateMsg (lessDate1 ,startstr, moreDate1,endstr){
	var re =/\./g;
	var lessDate = lessDate1.replace(re,"-");
	var moreDate = moreDate1.replace(re,"-");
	var re1 =/\\/g;
	var lessDate = lessDate.replace(re1,"-");
	var moreDate = moreDate.replace(re1,"-");
	if(!isComdate(lessDate,moreDate)){
		var err = endstr + "";
		err = err + startstr;
		alert(err);
	}
	return isComdate(lessDate,moreDate);
}
function need_input(sForm)
{ 
    for(i=0;i<sForm.length;i++)
    {  
		if(sForm[i].tagName.toUpperCase()=="TEXTAREA")
		{
			if(sForm[i].value.realLength()>sForm[i].title)
			{
				sWarn= sForm[i].title + "'";
				alert(sWarn);
				sForm[i].focus();
				return false;				
			}
		}
		if(sForm[i].tagName.toUpperCase()=="INPUT" &&sForm[i].type.toUpperCase()=="TEXT")
		{  
			sForm[i].value = sForm[i].value.trim();
			if(sForm[i].value.indexOf('\'')>=0)
			{
				sWarn= "!";
				alert(sWarn);
				sForm[i].focus();
				return false;				
			}
			if(sForm[i].value.realLength()>sForm[i].maxLength)
			{
				sWarn= sForm[i].maxLength + "'";
				alert(sWarn);
				sForm[i].focus();
				return false;				
			}
		} 
		if(sForm[i].tagName.toUpperCase()=="INPUT" &&sForm[i].type.toUpperCase()=="TEXT" && (sForm[i].title!=""))
			if(sForm[i].value=="")
			{
				sWarn=sForm[i].title+"";
				alert(sWarn);
				sForm[i].focus();
				return false;
			}
		}
	return true;
} 
function Check_IsEmail(name,error,message)
{
	   if( includeChineseChar( GE(name).value) == false){
			SetError( error,"block","Please enter English.");	return false;
		}
	   else if( isEmailA(GE(name).value.trim()) == false){
			SetError( error,"block",message);	return false;
		}
		else{
			SetError( error,"none","");	return true;
		}
}
function Check_NotNull(name,error,errorM)
{
		CtoH( name);
	    
		if( isEmpty( GE(name).value.trim()) == true){
			SetError( error,"block",errorM);	return false;
		}
		else{
			SetError( error,"none","");	return true;
		}
}
function Check_NotNull_EnName(name,error,errorM){


		CtoH( name);
		
	    
		if( isEmpty( GE(name).value.trim()) == true){
			SetError( error,"block",errorM);	return false;
		}
		if( isEngEmpty( GE(name).value) == false){
			SetError( error,"block","Please enter English.");	return false;
		}
		else{
			SetError( error,"none","");	return true;
		}
}
function Check_NotCN(name,error){


		CtoH( name);

		if( includeChineseChar( GE(name).value.trim()) == false){
			SetError( error,"block","Please enter English.");	return false;
		}
		else{
			SetError( error,"none","");	return true;
		}
}
function Check_NotNull_NotCN(name,error,errorM){


		CtoH( name);

		if( isEmpty( GE(name).value.trim()) == true){
			SetError( error,"block",errorM);	return false;
		}
		else if( includeChineseChar( GE(name).value) == false){
			SetError( error,"block","Please enter English.");	return false;
		}
		else{
			SetError( error,"none","");	return true;
		}
}
function Check_NotNull_IsInteger(name,error,errorM)
{
		CtoH( name);

		if( isEmpty( GE(name).value.trim()) == true){
			SetError( error,"block",errorM);	return false;
		}
		else if( isInteger( GE(name).value) == false){
			SetError( error,"block","Please enter Number.");	return false;
		}
		else{
			SetError( error,"none","");	return true;
		}
}
function Check_IsInteger(name,error,errorM){

		CtoH( name);

		if( isInteger( GE(name).value.trim()) == false){
			SetError( error,"block","Please enter Number.");	return false;
		}
		else{
			SetError( error,"none","");	return true;
		}
}
function Check_NotNull_Length(name,error,maxLength,errorEM,errorLM){

		CtoH( name); 

		if( isEmpty( GE(name).value.trim()) == true){
			SetError( error,"block",errorEM);	return false;
		}
		else if(GE(name).value.length > maxLength){
			SetError( error,"block",errorLM);	return false;
		}
		else{
			SetError( error,"none","");	return true;
		}
}

function Check_NotNull_NotCN_Length(name,error,maxLength,errorEM,errorLM){

		CtoH( name); 

		if( isEmpty( GE(name).value.trim()) == true){
			SetError( error,"block",errorEM);	return false;
		}
		else if( includeChineseChar( GE(name).value) == false){
			SetError( error,"block","Please enter English.");	return false;
		}
		else if(GE(name).value.length > maxLength){
			SetError( error,"block",errorLM);	return false;
		}
		else{
			SetError( error,"none","");	return true;
		}
}
function Check_SelectNotFirstItem(name,error,errorMessage){

		if( GE(name).selectedIndex == 0){
			SetError( error,"block",errorMessage);	return false;
		}
		else{
			SetError( error,"none","");	return true;
		}
}
function UrlEncode(p_source)
{
	
	return ( ( p_source.split("&").join("ll_ll") ).split(",").join("dd_dd") ).split(" ").join("_");
}
function UrlDEcode(p_source)
{
	return( ( temp.split("ll_ll").join("&") ).split("dd_dd").join(",") ).split("_").join(" ");
}
function ClearUrlString(p_source)
{
	return p_source.replace(/[^0-9A-Za-z _-]/ig,"")
}
String.prototype.trim = function()
{
    return this.replace(/(^[\s]*)|([\s]*$)/g, "");
}
String.prototype.lTrim = function()
{
    return this.replace(/(^[\s]*)/g, "");
}
String.prototype.rTrim = function()
{
    return this.replace(/([\s]*$)/g, "");
}
String.prototype.realLength = function()
{
  return this.replace(/[^\x00-\xff]/g,"**").length;
}
function addToFavorite(title){
  window.external.AddFavorite(document.location,title)
}

var lastClickedTabItem;
function ClickTabItem( clickedObj )
{
	if(lastClickedTabItem != null )
	{
		lastClickedTabItem.className = lastClickedTabItem.className.replace( "active" , "" );
	}
	else
	{
		lastClickedTabItem = document.getElementById("firsttab");
		lastClickedTabItem.className = lastClickedTabItem.className.replace( "active" , "" );
	}
	clickedObj.className = "active";
	lastClickedTabItem = clickedObj;
}

function toueme()
{
	try
	{
		document.getElementById("poptips").style.display = "none";
	}
	catch(e)
	{
	}
}

function touemeon()
{
	document.getElementById("poptips").style.display = "";
}

//生成n位数的一个随机数
 function RndNum(n)
{
	var rnd="";
	for(var i=0;i<n;i++)
	rnd+=Math.floor(Math.random()*10);
	return rnd;
}

//锁屏对象
var ElementPosition =
{
    viewportHeight: function()
    {
        if (window.innerHeight != window.undefined)
            return window.innerHeight;
        if (document.compatMode == 'CSS1Compat') return document.documentElement.clientHeight;
        if (document.body) return document.body.clientHeight;
        return window.undefined;
    },

    viewportWidth: function()
    {
        if (window.innerWidth != window.undefined)
            return window.innerWidth;
        if (document.compatMode == 'CSS1Compat') return document.documentElement.clientWidth;
        if (document.body) return document.body.clientWidth;
        return window.undefined;
    },

    center: function()
    {
        var element = document.getElementById(arguments[0]);
        if (!element) return;

        var scTop = parseInt(document.documentElement.scrollTop, 10);
        var scLeft = parseInt(document.documentElement.scrollLeft, 10);
        
        
        var scwidth = parseInt(element.style.width, 10);
        var scHeight = parseInt(element.style.height, 10);

        element.style.top = ( scTop + ( ( this.viewportHeight() - element.offsetHeight ) / 2 ) -scHeight/2) + "px";
        element.style.left = ( scLeft + ( ( this.viewportWidth() - element.offsetWidth ) / 2 ) -scwidth/2) + "px";
    },

    move: function(element, x, y)
    {
        if (!element) return;

        var scTop = parseInt(document.documentElement.scrollTop, 10);
        var scLeft = parseInt(document.documentElement.scrollLeft, 10);

        element.style.top = scTop + y + "px";
        element.style.left = scLeft + x + "px";
    }
}
//当前页面锁屏并产生滚动条(10秒后自动解锁)
function createProgressBar()
{
    var oMask = document.createElement('iframe');
    oMask.id = "maskFrame";
    oMask.style.position = 'absolute';
    oMask.style.top = '0px';
    oMask.style.left = '0px';
    oMask.style.width = '100%';
    var height = ElementPosition.viewportHeight();
    oMask.style.height = height + 'px';
    oMask.style.zIndex = '300';
    oMask.style.backgroundColor = '#dbe4ee';
    oMask.style.filter = 'alpha( opacity = 0.0 )';
    oMask.style.opacity = '.0';
    oMask.scrolling = 'no';
    oMask.marginHeight = '0px';
    oMask.marginWidth = '0px';
    oMask.style.display = 'none';
    oMask.style.cursor="wait";
    document.body.appendChild(oMask);

    var oProgressBar = document.createElement("div");
    oProgressBar.id = "progressBar";
    oProgressBar.style.position = 'absolute';
    oProgressBar.style.zIndex = '310';
    var str = "<TABLE WIDTH=\"250px\" class=\"birtviewer_progressbar\" CELLSPACING=\"10px\">";
    str += "<TR>";
    str += "	<TD ALIGN=\"center\">";
    str += "<B style=\"background:#EDF4FC\">\u64cd\u4f5c\u8fdb\u884c\u4e2d,\u8bf7\u7a0d\u5019...</B>";
    str += "</TD>";
    str += "</TR>";
    str += "<TR>";
    str += "	<TD ALIGN=\"center\">";
    str += "		<IMG SRC=\"";
    //str += path;
    str += "./platform/theme/default/images/common/loading.gif\">";
    str += "	</TD>";
    str += "</TR>";
    str += "</TABLE>";
    oProgressBar.innerHTML = str;
    oProgressBar.style.display = 'none';
    oProgressBar.style.width = '250px';
    oProgressBar.style.height = '60px';
    oProgressBar.style.cursor="wait";
    //oProgressBar.style.background = "#EDF4FC";
    document.body.appendChild(oProgressBar);
    ElementPosition.center("progressBar");

    oMask.style.display = 'block';
    oProgressBar.style.display = 'block';
    
    //为防止死锁，20秒后自动解锁
    setTimeout("destroyProgressBar();" ,1000 * 20);
}
//当前页面锁屏并产生滚动条(不自动解锁)
function createProgressBar2()
{
    var oMask = document.createElement('iframe');
    oMask.id = "maskFrame";
    oMask.style.position = 'absolute';
    oMask.style.top = '0px';
    oMask.style.left = '0px';
    oMask.style.width = '100%';
    var height = ElementPosition.viewportHeight();
    oMask.style.height = height + 'px';
    oMask.style.zIndex = '300';
    oMask.style.backgroundColor = '#dbe4ee';
    oMask.style.filter = 'alpha( opacity = 0.0 )';
    oMask.style.opacity = '.0';
    oMask.scrolling = 'no';
    oMask.marginHeight = '0px';
    oMask.marginWidth = '0px';
    oMask.style.display = 'none';
    oMask.style.cursor="wait";
    document.body.appendChild(oMask);

    var oProgressBar = document.createElement("div");
    oProgressBar.id = "progressBar";
    oProgressBar.style.position = 'absolute';
    oProgressBar.style.zIndex = '310';
    var str = "<TABLE WIDTH=\"250px\" class=\"birtviewer_progressbar\" CELLSPACING=\"10px\">";
    str += "<TR>";
    str += "	<TD ALIGN=\"center\">";
    str += "<B style=\"background:#EDF4FC\">\u64cd\u4f5c\u8fdb\u884c\u4e2d,\u8bf7\u7a0d\u5019...</B>";
    str += "</TD>";
    str += "</TR>";
    str += "<TR>";
    str += "	<TD ALIGN=\"center\">";
    str += "		<IMG SRC=\"../images/common/loading.gif\">";
    str += "	</TD>";
    str += "</TR>";
    str += "</TABLE>";
    oProgressBar.innerHTML = str;
    oProgressBar.style.display = 'none';
    oProgressBar.style.width = '250px';
    oProgressBar.style.height = '60px';
    oProgressBar.style.cursor="wait";
    //oProgressBar.style.background = "#EDF4FC";
    document.body.appendChild(oProgressBar);
    ElementPosition.center("progressBar");

    oMask.style.display = 'block';
    oProgressBar.style.display = 'block';
}
//解锁
function destroyProgressBar(obj)
{
	var oMask;
	var oProgressBar;
	if(obj == null)
	{
		try
		{
				oMask = document.getElementById('maskFrame');
			    oProgressBar = document.getElementById('progressBar');
			    
			    document.body.removeChild(oMask);
			    document.body.removeChild(oProgressBar);
		}
		catch(e)
		{
		}
	}
	else
	{
		try
		{
				oMask = eval(obj).document.getElementById('maskFrame');
			    oProgressBar = eval(obj).document.getElementById('progressBar');
			    
			    eval(obj).document.body.removeChild(oMask);
			    eval(obj).document.body.removeChild(oProgressBar);
		}
		catch(e)
		{
		}
		
		try
		{
				oMask = eval(obj.parent).document.getElementById('maskFrame');
			    oProgressBar = eval(obj.parent).document.getElementById('progressBar');
			    
			    eval(obj.parent).document.body.removeChild(oMask);
			    eval(obj.parent).document.body.removeChild(oProgressBar);
		}
		catch(e)
		{
		}
	}
}

//禁用当前页面所有button
function disableButton()
{
	var btns = document.body.getElementsByTagName("button");
	for(var i = 0; i < btns.length; i++)
	{
		btns[i].disabled = true;
	}
}

function checkContent(id,len){
	var tag=document.getElementById(id);
	var leng=(len==undefined?50000:len);
	if(tag.value.length>leng){
		//window.alert('编辑器内容数量超过系统的最大支持字数'+leng);
		window.alert('对不起！编辑器内容已超过了系统支持的最大长度！');
		return false;
	}
	return true;
}