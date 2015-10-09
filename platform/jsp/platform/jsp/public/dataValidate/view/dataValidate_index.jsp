<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
    	<title></title>


		<!-- 复杂表格CSS支持 -->
		<link href="${ctx }/platform/jsp/public/dataValidate/resource/css/jquery-ui.css" type="text/css" rel="stylesheet"/>

		<!-- 本页CSS/JS -->
		<script src="${ctx }/platform/jsp/public/dataValidate/resource/js/jquery-1.6.2.min.js"></script>
		<script src="${ctx }/platform/jsp/public/dataValidate/resource/js/jquery-ui.js"></script>


  		<script type="text/javascript">
	  		var array = new Array();
	  		var array2 = new Array();
	  		<c:forEach items="${list}" var="module" varStatus="moduleIndex">
		  		array.push('${module.validate_url}');
			</c:forEach>

	  		function validateData(){
	  			$('#messageId').css("display","none");
	  			$('#tijianId').css("display","none");
	  			$("#chkBtn").attr("disabled",true);
	  			if( array2.length > 0){
		  			array = array2;
		  			array2 = new Array();
	  			}
	  			var pro = $("#progressbar");   //进度条div
	  		    var proLabel = $(".progress-label"); //进度条里面文字
	  		    pro.progressbar({
	  		      value: 0,   //初始化的值为0
	  		      //create: function(event, ui){
	  		      //	addValue(array);
	  		      // },
	  		      change: function() {
	  		      	//当value值改变时，同时修改label的字
	  		        proLabel.text(pro.progressbar("value") + "%");
	  		      },
	  		      complete: function() {
	  		      	//当进度条完成时，显示complate
	  		        proLabel.text("体检完成!");
	  		      	$('#tijianImageId').attr("src","${ctx}/platform/jsp/public/dataValidate/resource/img/chongxintijian.bmp");
	  		      	$('#tijianId').css("display","");
	  		      	$('#returnImageId').css("display","");

	  		      	// 销毁
	  		      	//pro.progressbar("destroy");
	  		      }
	  		    });
	  		  	$.ajax({
	  			  url: "${ctx}/bmp/dataVailidate/dataValidate_createLog.action?_dt="+ new Date().getTime(),
	  			  cache: false
	  			});
	  		  	$("li[id$='_li']").removeClass("you");
	  		  	$("li[id$='_li']").removeClass("liang");
	  		  	$("li[id$='_li']").removeClass("cha");
	  		  	$("span[id$='_span']").text("");
				setTimeout("addValue(array)", 500);
	  		}

	  		    //动态修改value的函数
			function addValue(array){
	   		    if(array.length == 0){
	  		    	var pro = $("#progressbar");
					var newValue = pro.progressbar("value")+ 1;
						pro.progressbar("value",newValue); //设置新值
						if(newValue >= 100){
							return;
						}
						setTimeout("addValue(array)", 1);
					} else{
						$.ajax({
			  	             type: "post",
			  	             url: '${ctx}' + array[0],
			  	             data: {
			  	             },
			  	             dataType: "json",
			  	             success: function(data){
			  	            	$("#" + data.id + "_li").css('display', '');
			  	            	// TODO 根据类型判断显示图片
			  	            	$("#" + data.id + "_li").removeClass("you");
		  	            		$("#" + data.id + "_li").removeClass("liang");
		  	            		$("#" + data.id + "_li").removeClass("cha");

		  	            		var filterYou  = /已填写|不做检查/;
		  	            		var filterLiang  = /记录过少|数据变化/;
		  	            		var filterCha  = /未填写/;
			  	            	if( filterYou.test(data.msg) ){
				  	            	$("#" + data.id + "_li").addClass("you");
			  	            	}
			  	            	if( filterLiang.test(data.msg)){
			  	            		$("#" + data.id + "_li").addClass("liang");
			  	            	}
			  	            	if( filterCha.test(data.msg) ){
			  	            		$("#" + data.id + "_li").addClass("cha");
			  	            	}
			  	            	$("#" + data.id + "_span").html(data.msg);
			  	  		    	var pro = $("#progressbar");
			  					var newValue = pro.progressbar("value")+ 1;
								pro.progressbar("value",newValue); //设置新值
								if(newValue >= 100){
									return;
								}  //超过100时，返回
								setTimeout("addValue(array)", 500); //延迟500毫秒递归调用自己
			  	             }
			  	         });
						array2.push( array.shift());
					}
	  		    }
  		</script>
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			function showTab(url,moduleName){
				if(url==null || url=="" || url=="null"){
					alert("系统未设置跳转链接，请从【功能菜单】中查找模块或者使用【功能导航】查找并修复");
				}else{
				 	TabUtil.openAsTab({
						url : "${ctx}/"+url,
						title : moduleName
					});
				}
			}
			function returnToStart(){
				window.location.href="${ctx}/bmp/dataVailidate/dataValidate_index.action?_ts" + new Date().getTime();
			}
		</script>

		<style>
		  .ui-progressbar {
		    position: relative;
		  }
		  .progress-label {
		    position: absolute;
		    left: 50%;
		    top: 4px;
		    font-weight: bold;
		    text-shadow: 1px 1px 0 #fff;
		 }
		 .p_content{
		    margin: 40px;
		    margin-top:120px;
		    position: relative;
		    /* height: 600px;*/
		    overflow-y:auto;
		    z-index: -1;
		 }
		 .p_top{
		 	width: 90%;
		 	left: 20px;
		    top: 20px;
		    margin-left: 40px;
		    position:fixed;
			 /*height: 50px;*/
			filter:alpha(opacity=100);
			background-color: white;
			_position:absolute;
			_bottom:auto;
 			_top:expression(eval(document.documentElement.scrollTop));
		 }
		 .you{
		 	list-style: url("${ctx}/platform/jsp/public/dataValidate/resource/img/you.bmp");
		 }
		 .liang{
		 	list-style: url("${ctx}/platform/jsp/public/dataValidate/resource/img/liang.bmp");
		 }
		 .cha{
		 	list-style: url("${ctx}/platform/jsp/public/dataValidate/resource/img/cha.bmp");
		 }
		  </style>

	</head>

	<body>
		<div class="p_main" style="width: 90%;overflow: hidden;">
			<div style="background: white;height: 20px;top:0px;width:80%;position:fixed;z-index:100;">&nbsp;</div>
			<div class="p_top" style="z-index:100;">
				<div id="progressbar" style="height: 40px;">
					<div class="progress-label"></div>
				</div>
				<div id="messageId" style="height: 140px;">
					<div style="width:30%;float: left">
						<img style="padding-left:55%" alt="数据校验" src="${ctx}/platform/jsp/public/dataValidate/resource/img/computer_dangerous.bmp"/>
					</div>
					<div style="margin:23px;font-size:22px;font-family: 微软雅黑;width:60%;float: left">
						<span>
							<c:choose>
								<c:when test="${not empty lastCheckDate }">
									<font color="green">您上次校验时间：${lastCheckDate}</font>
								</c:when>
								<c:otherwise>
									<font color="orange">您从未对数据进行校验过</font>
								</c:otherwise>
							</c:choose>
						</span><br/>
						<span>业务数据可能填写不完善，请立即体检并修复！</span>
					</div>
				</div>
				<div id="tijianId" style="padding-top: 5px;text-align: center; background-color: white;">
					<img id="tijianImageId" alt="数据校验" src="${ctx}/platform/jsp/public/dataValidate/resource/img/tijian.bmp" style="cursor: pointer;" onclick="validateData()" />
					<img id="returnImageId" alt="确定" src="${ctx}/platform/jsp/public/dataValidate/resource/img/queding.bmp" style="cursor: pointer;display: none;" onclick="returnToStart()" />
				</div>
			</div>
			<div class="p_content" style="z-index:50;">
				<ul>
					<c:forEach items="${list}" var="module" varStatus="moduleIndex">
						<li id="${module.id}_li" style="display: none;line-height: 30px;">${module.business_module}：<span id="${module.id}_span"></span></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</body>
</html>
