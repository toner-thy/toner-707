<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="configuration" uri="http://www.cdthgk.com/tags/configuration"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="help" uri="http://www.cdthgk.com/tags/help"%>


<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
	<head>
		<title>
			<configuration:value name="name" type="com.cdthgk.platform.configuration.ApplicationConfiguration"/>
		</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">

		<style type="text/css">

			#header {
				position: absolute;
				top: 0;
				left: 0;
				width: 100%;
				height: 52px;
				background: url("${pageContext.request.contextPath}/bmp/borderlayout/skin/blue/img/login/header_back.gif") 0px 0px repeat-x;
			}
			.headerLeft{
					float: left;
			}

			.faq{
				padding: 60px 90px 20px;
				min-height: 600px;
			}
			.faq h2{
				clear: both;
				height: 39px;
				line-height: 39px;
				padding: 0px;
				font-size: 14px;
				color: #246fab;
				cursor: pointer;
			}
			.faq p{
				padding: 0 30px 23px;
				font-size: 12px;
				color: #707070;
				line-height: 26px;
				margin-top: -2px;
			}
			.line{
				clear: both;
				overflow: hidden;
				background: #e0e0e0;
				height: 1px;
			}
		</style>

		<!--[if IE]>
			<style type="text/css">
				body
				{
				overflow-y: hidden;
				}
				div#wrapper
				{
				height: 100%;
				overflow: auto;
				}
			</style>
		<![endif]-->
		<script type="text/javascript">
			function toggle( pId ){
				var obj =document.getElementById(pId);
				if( obj.style.display == "block" ){
					obj.style.display = "none";
				}else{
					obj.style.display = "block";
				}
			};
			function loadFunction(){
				var pArray = document.getElementsByTagName("p");
				if( pArray!=null && pArray.length>0 ){
					pArray[0].style.display = "block";
				}
			}
		</script>
		<s:actionmessage theme="messages"/>
		<s:actionerror theme="messages"/>
	</head>
	<body onload="loadFunction()">
		<div id="header" style="width:100%;">
			<div class="headerLeft">
					<!-- 头部左侧图片 -->
					<img src="${pageContext.request.contextPath}/bmp/borderlayout/skin/blue/img/login/header_left_problem.gif"/>
			</div>
		</div>
		<div style="width:100%;padding-top:30px; ">
			<!-- 一个问题开始 -->
			<div class="faq" style="width:1024px; padding: 10px;">
				<h2 id="q1" onclick="toggle('a1')">我使用IE6浏览器登录系统，浏览器经常崩溃怎么办？</h2>
				<p id="a1" style="display: none;">
					答：首先查看您的操作系统的版本：
						<br/>如果是windows xp sp3 版本，请<a href="${ctx }/testAtt/testAtt/bmp-4.1.5-20130408-alpha.zip">点击这里下载IE6升级补丁包</a>，下载后解压并安装后重启操作系统即可。
						<br/>如果是windows xp sp2版本，请<a href="${ctx }/testAtt/bmp-4.1.5-20130408-alpha.zip">点击这里下载sp3升级补丁包和IE6升级补丁包</a>，下载后解压并安装后重启操作系统即可。
				</p>
			</div>
			<div class="line"></div>
			<!-- 一个问题结束 -->
		</div>
	</body>
</html>
