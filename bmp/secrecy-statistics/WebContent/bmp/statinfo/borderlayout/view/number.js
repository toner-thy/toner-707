function transfer(input){
	  var danwei=Array("","十","百","千","万","十","百","千","亿");
	  var inputvalue=parseInt(input);
	  var l=input.length;
	  var a=new Array(l);
	  var b=new Array(l);
	  var result="";
	        for(var i=0;i<l;i++)
	  {
	     a[i]=input.substr(i,1);
	     b[i]=getchinese(a[i]);
	     result+=b[i]+danwei[l-i-1];
	  }
	  return result;
 }
function getchinese(p)
{
    var input=p;
 if(input=="0")
     return "零";
    else if(input=="1")
     return "一";
 else if(input=="2")
     return "二";
 else if(input=="3")
     return "三";
 else if(input=="4")
     return "四";
 else if(input=="5")
     return "五";
 else if(input=="6")
  return "六";
 else if(input=="7")
     return "七";
 else if(input=="8")
     return "八";
 else if(input=="9")
     return "九";
 else if(input=="10")
	 return "十";
 else if(input=="11")
	 return "十一";
 else if(input=="12")
	 return "十二";
 else if(input=="13")
	 return "十三";
 else if(input=="14")
	 return "十四";
 else if(input=="15")
	 return "十五";
 else if(input=="16")
	 return "十六";
 else if(input=="17")
	 return "十七";
 else if(input=="18")
	 return "十八";
 else if(input=="19")
	 return "十九";
 else if(input=="20")
	 return "二十";
 else if(input=="21")
	 return "二十一";
 else if(input=="22")
	 return "二十二";
 else if(input=="23")
	 return "二十三";
 else if(input=="24")
	 return "二十四";
 else if(input=="25")
	 return "二十五";
 else if(input=="26")
	 return "二十六";
 else if(input=="27")
	 return "二十七";
 else if(input=="28")
	 return "二十八";
 else if(input=="29")
	 return "二十九";
 else if(input=="30")
	 return "三十";
 else if(input=="31")
	 return "三十一";
 else if(input=="32")
	 return "三十二";
 else if(input=="33")
	 return "三十三";
 else if(input=="34")
	 return "三十四";
 else if(input=="35")
	 return "三十五";
 else if(input=="36")
	 return "三十六";
 else if(input=="37")
	 return "三十七";
 else if(input=="38")
	 return "三十八";
 else if(input=="39")
	 return "三十九";
 else if(input=="40")
	 return "四十";
}

