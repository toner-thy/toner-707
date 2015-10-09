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

		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css" />

		<script src="${pageContext.request.contextPath}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
		$ENV.loader.loadJavaScript("${contextPath}/resources/js/mootools/mootools-core-1.4.js");
		$ENV.loader.loadJavaScript("${contextPath}/resources/js/mootools/mootools-more-1.4.js", function() {
			$ENV.onDomReady(function(){
				$('noticeDiv').load("${ctx}/bmp/notice/notice_indexList.action?_ts=" + new Date().getTime());
				$('attachmentDiv').load("${ctx}/bmp/attachment/attachment_indexList.action?_ts=" + new Date().getTime());
			});
		});
		</script>
		<style type="text/css">
			body {
				margin: 0;
				padding-bottom: 20px;
				padding-top: 52px;
/* 				background: url('${pageContext.request.contextPath}/bmp/borderlayout/skin/blue/img/login/content_back.gif') 0px 0px repeat-x; */
			}

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
				.headerRight{
					float: right;
					margin-top: 10px;
					margin-right: 10px;
					color: #fff;
				}
					.headerBtnFg{
						float: left;
						width: 14px;
						height: 22px;
						background: url("${pageContext.request.contextPath}/bmp/borderlayout/skin/blue/img/login/header_right_btn_fg.gif") 0px 0px repeat;
					}
					.headerHelpBtn{
						float: left;
						width: 52px;
						height: 22px;
						cursor: hand;
						background: url("${pageContext.request.contextPath}/bmp/borderlayout/skin/blue/img/login/header_help_btn.gif") 0px 0px repeat;
					}
					.headerAboutBtn{
						float: left;
						width: 52px;
						height: 22px;
						cursor: hand;
						background: url("${pageContext.request.contextPath}/bmp/borderlayout/skin/blue/img/login/header_about_btn.gif") 0px 0px repeat;
					}
					.headerQuestionBtn{
						float: left;
						width: 80px;
						height: 22px;
						cursor: hand;
						background: url("${pageContext.request.contextPath}/bmp/borderlayout/skin/blue/img/login/header_question_btn.gif") 0px 0px repeat;
					}



			#content{
				position: absolute;
				width: 100%;

			}

			.loginDiv{
				background: url('${pageContext.request.contextPath}/bmp/borderlayout/skin/blue/img/login/login_back.jpg') 0px 0px repeat-x;
				height: 257px;
				width: 448px;
			}

				.icoDiv{
					float: left;
					width: 144px;
					height: 156px;
					margin-top: 56px;
					margin-left: 12px;
				}

				.fromDiv{
					float: left;
					width: 265px;
					height: 160px;
					margin-top: 70px;
					overflow: hidden;
				}

				.loginLableDiv{
					float: left;
					margin-left: 10px;
					margin-bottom: 10px;
					height: 28px;
					line-height: 28px;
					text-align: right;
					color: #4E6E7B;
					font-size: 12px;
					font-weight: bold;
				}
				.loginInputDiv{
					float: left;
					width: 180px;
					margin-left: 5px;
					margin-bottom: 10px;
					height: 28px;
					text-align: left;
				}
				.textInput{
					width: 150px;
					height: 19px;
					float: left;
					color: #000;
					border: 1px solid #999;
					background-color: #fff;
				}
				.textInput_ov{
					width: 150px;
					height: 19px;
					float: left;
					color: #454545;
					border: 1px solid #BC6F00;
					background-color: #FFFBCC;
				}

				.ckInput{
					width: 89px;
					height: 19px;
					float: left;
					color: #000;
					margin-right: 10px;
					border: 1px solid #999;
					background-color: #fff;
				}
				.ckInput_ov{
					width: 89px;
					height: 19px;
					float: left;
					color: #454545;
					margin-right: 10px;
					border: 1px solid #BC6F00;
					background-color: #FFFBCC;
				}

				.subBtn{
					background: url('${pageContext.request.contextPath }/bmp/borderlayout/skin/blue/img/login/btn_login.gif') 0px 0px repeat-x;
					height: 25px;
					width: 81px;
					float: left;
					cursor: hand;
					margin-right: 20px;
				}

				.subBtn_mov{
					background: url('${pageContext.request.contextPath }/bmp/borderlayout/skin/blue/img/login/btn_login_mov.gif') 0px 0px repeat-x;
					height: 25px;
					width: 81px;
					float: left;
					cursor: hand;
					margin-right: 20px;
				}

				.resetBtn{
					background: url('${pageContext.request.contextPath }/bmp/borderlayout/skin/blue/img/login/btn_reset.gif') 0px 0px repeat-x;
					height: 25px;
					width: 81px;
					float: left;
					cursor: hand;
				}

				.resetBtn_mov{
					background: url('${pageContext.request.contextPath }/bmp/borderlayout/skin/blue/img/login/btn_reset_mov.gif') 0px 0px repeat-x;
					height: 25px;
					width: 81px;
					float: left;
					cursor: hand;
				}

			#footer {
				position: absolute;
				bottom: 0;
				left: 0;
				width: 100%;
				text-align: center;
				font-size: 12px;
				font-family: Arial, 'Times New Roman' !important;
				color: #678490;
				height: 20px;
			}
				.footerLeft{
					float: left;
					background: url('${pageContext.request.contextPath}/bmp/borderlayout/skin/blue/img/login/footer_back_left.gif') 0px 0px no-repeat;
					height: 18px;
					width: 170px;
				}
				.footerRight{
					float: left;
					background: url('${pageContext.request.contextPath}/bmp/borderlayout/skin/blue/img/login/footer_back_right.gif') 0px 0px no-repeat;
					height: 18px;
					width: 358px;
				}
				.footerIeBtn{
					float: left;
					background: url('${pageContext.request.contextPath}/bmp/borderlayout/skin/blue/img/login/footer_ie_btn.gif') 0px 0px no-repeat;
					height: 18px;
					width: 80px;
					cursor: hand;
				}


			html>body #footer {
				position: fixed;
			}

			.title_list{
				margin:0;
				width:300px;
				line-height: 1.5em;
				font-size: 12px;
			}
			.title_list ul{
				list-style:none;
				padding:0;
				border:0;
				margin:10px 0 5px 5px;
			}
			.title_list li{
				float:right;
				padding:0 5px 0 5px;
				height:1.5em;
				overflow: hidden;
				text-overflow:ellipsis;
			}
			.title_list li a{
				float: left;
				text-align:left;
				width:200px;
			}
		</style>
		<style type="text/css">
			.notice_div{
				background-color: #66B9D7;
				width: 100%;
				height: 30px;
				display: block;
				vertical-align: middle;
				padding-top:10px;
				font-size:16px ;
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

		<script language="JavaScript" type="text/JavaScript">
			var _Cookie = {
			  setCookie : function(name, value){
			    var exp  = new Date();    //new Date("December 31, 9998");
			    exp.setTime(exp.getTime() + 10*365*24*60*60*1000);
			    document.cookie = name +  "=" + escape(value) + ";" + "expires=" + exp.toGMTString();
			  },

			  getCookie : function(name)  {
				  	var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
					if(arr != null)   {
			  			return unescape(arr[2]);
			  		}
			  		return null;
			  },

			  deleteCookie : function(name){
			  	document.cookie = name + "= ;expires=Fri, 02-Jan-1970 00:00:00 GMT";
			  }
			};

			function btnShow(id,className) {
				document.getElementById(id).className = className;
			}

			// 显示帮助信息
			function showHelp(id){
				document.location.href = "${pageContext.request.contextPath}/help/help/help_clientInfo.do?help.helpId=" + id + "";
			}
		</script>
<%-- 		<script language="VBScript"> --%>
			<!--
			Sub Procedure2()
			Dim IDfromToken
			result=SMKeyCSP111.ReadIDfromToken (IDfromToken)
			if(result = 0) then
				document.getElementById("idFromToken").value=IDfromToken
			else
			end if
			end sub
			 -->
<%-- 		</script> --%>

		<s:actionmessage theme="messages"/>
		<s:actionerror theme="messages"/>
	</head>

	<!-- UK登陆注释开始，注意选择ukey登录方式 -->
<!-- 	<body onload="Procedure2()"> -->
<!-- 		<OBJECT ID="SMKeyCSP111" WIDTH=0 HEIGHT=0 -->
<!-- 	     CLASSID="CLSID:44955BCB-8054-4551-8E6A-0AE4498D4487"> -->
<!-- 	        <PARAM NAME="_Version" VALUE="65536"> -->
<!-- 	        <PARAM NAME="_ExtentX" VALUE="0"> -->
<!-- 	        <PARAM NAME="_ExtentY" VALUE="0"> -->
<!-- 	        <PARAM NAME="_StockProps" VALUE="0"> -->
<!-- 	    </OBJECT> -->

	    <!--ukey登录方式1： 最新ukey登录form（多张表且过滤admin账户ukey登录）建议使用
		<form action="${pageContext.request.contextPath }/ukeymanage/ukeyInfo/ukeyInfo_login.do" method="post">
		-->
		<!--ukey登录方式2： 原ukey登录form（1张表）
		<form action="${pageContext.request.contextPath }/bmp/userKey/userKey_login.do" method="post">
			<input type="hidden" id="idFromToken" name="idFromToken">
		-->

	<!--  UK登陆注释结束 -->

	<!--  普通登陆注释开始-->
	<body >

		<form action="${pageContext.request.contextPath }/platform/login/logining.action" method="post">
	<!--  普通登陆注释结束 -->
			<!-- 隐藏域 使用UKey登录系统 UKey的id -->
			<input type="hidden" id="idFromToken" name="idFromToken">
			<!-- 隐藏域 使用UKey登录系统 UKey的id -->
			<div id="header">
				<div class="headerLeft">
					<%
						session.setAttribute("loginType", "bms");
					%>
 					<!-- 头部左侧图片 -->
					<img src="${pageContext.request.contextPath}/bmp/borderlayout/skin/blue/img/login/header_left_orgbms.gif"/>
				</div>
				<div class="headerRight">
				    <a href='${pageContext.request.contextPath}/platform/help/help_clientInfo.do?help.helpId=login_help' class='pop_button' target='_back'><div class="headerHelpBtn" title="点击获取最新的帮助信息"></div></a>
					<div class="headerBtnFg"></div>
				    <a href='${pageContext.request.contextPath}/platform/help/help_clientInfo.action?help.helpId=login_common_problem' class='pop_button' target='_back'><div class="headerQuestionBtn" title="点击获取最新的系统问题解决方案"></div></a>
<%-- 				    <a href='${pageContext.request.contextPath}/bmp/borderlayout/view/login_common_question.jsp' class='pop_button' target='_back'><div class="headerQuestionBtn" title="点击获取最新的系统问题解决方案"></div></a> --%>
					<div class="headerBtnFg"></div>
					<a href='${pageContext.request.contextPath}/platform/help/help_clientInfo.do?help.helpId=platform_help' class='pop_button' target='_back'><div class="headerAboutBtn" title="点击查看系统关于信息"></div></a>
				</div>

			</div>
			<div style="width:100%;" align="center">
			<div style="width:1024px; padding: 10px;">
				<table>
					<tr>
						<td>
							<div name="noticeDiv" id="noticeDiv" style="width:448px;height:257px;overflow:hidden; min-height: 200px;max-height:400px;" >
							</div>
						</td>
						<td>&nbsp;</td>
						<td>
							<div id="content" align="center" style="position:relative; width:auto;margin-top: 0px;">
								<div class="loginDiv" align="right">
									<!-- ico -->
									<div class="icoDiv">
										<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="144" height="156">
											<param name="movie" value="${pageContext.request.contextPath}/bmp/borderlayout/skin/blue/flash/login/login_ico.swf" />
											<param name="quality" value="high" />
											<param name="wmode" value="transparent">
											<embed src="${pageContext.request.contextPath}/bmp/borderlayout/skin/blue/flash/login/login_ico.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="144" height="156"></embed>
										</object>
									</div>

									<!-- 登录表单 -->
									<div class="fromDiv" style="position:relative;">
										<div class="loginLableDiv">
											用户名：
										</div>
										<div class="loginInputDiv">
											<input id="user.username" name="user.username" type="text" class="textInput" value="${username}" onfocus="this.className='textInput_ov';" onblur="this.className='textInput';" onmousemove="this.className='textInput_ov'" onmouseout="this.className='textInput'" title="请填写您的账户名称"/>
											<script type="text/javascript">
								                var userName = _Cookie.getCookie('username');
								                if(userName){
								           			document.getElementById("user.username").value = userName;
								                }
								        	</script>
										</div>
										<div class="loginLableDiv">
											密　码：
										</div>
										<div class="loginInputDiv">
											<input id="pwInput" name="user.password" type="password" class="textInput" onfocus="this.className='textInput_ov';" onblur="this.className='textInput';" onmousemove="this.className='textInput_ov'" onmouseout="this.className='textInput'" title="请填写您的账户密码"/>
										</div>
										<div class="loginLableDiv">
											验证码：
										</div>
										<div class="loginInputDiv">
											<input id="ckInput" name="validateCode" type="text" class="ckInput" value="" onfocus="this.className='ckInput_ov';" onblur="this.className='ckInput';" onmousemove="this.className='ckInput_ov'" onmouseout="this.className='ckInput'" maxlength="6" title="请填写后面图片显示的数字"/><img src="${pageContext.request.contextPath }/platform/login/validateCode.action" style="float: left;border: 1px solid #638B9C;" title="请在前面的数据框内填写验证码，刷新页面可获取新的验证码。"/>
										</div>

										<!-- 提交和重置按钮 -->
								        <div style="width:100%; margin-left: 10px;">
								        	<!-- 提交按钮 -->
								        	<div id="subBtn" class="subBtn" onclick="login();" onMouseOut="btnShow('subBtn','subBtn')" onMouseOver="btnShow('subBtn','subBtn_mov');" title="请点击登录系统"></div>
								        	<!-- 重置按钮 -->
								        	<div id="resetBtn" class="resetBtn" onclick="reset();" onMouseOut="btnShow('resetBtn','resetBtn')" onMouseOver="btnShow('resetBtn','resetBtn_mov');" title="点击清空用户名、密码和验证码"></div>
								        </div>
									</div>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>
							<div name="attachmentDiv" id="attachmentDiv" style="overflow-y:hidden;width:448px;height:227px;">
							</div>
						</td>
						<td>&nbsp;</td>
						<td>
							<help:viewer helpId="login_index_remark" style="_margin-top:-200px;width:448px;height:227px; background-color:#D1E5EC;overflow: auto;"></help:viewer>
						</td>
					</tr>
				</table>
			</div>
			</div>
			<div id="footer" align="center">
<!-- 				<table width="100%"> -->
<!-- 					<tr> -->
<!-- 						<td width="*"></td> -->
<!-- 						<td width="620"> -->
<!-- 							<div class="footerLeft"></div> -->
<%-- 							<a href="${pageContext.request.contextPath}/soft/IE8_SETUP.exe" title="推荐使用IE8浏览器进行浏览，请点击下载安装程序"><div class="footerIeBtn"></div></a> --%>
<!-- 							<div class="footerRight"></div> -->
<!-- 						</td> -->
<!-- 						<td width="*"></td> -->
<!-- 					</tr> -->
<!-- 				</table> -->
			</div>
		</form>
	</body>
</html>
<script language="JavaScript">
	function login(){
		form = document.forms[0];
		if(form['user.username'].value == null || form['user.username'].value == "")
		{
			alert("请填写您的用户名");
			form['user.username'].focus();
		}
		else if(form['user.password'].value == null || form['user.password'].value == "")
		{
			alert("请填写您的登录密码");
			form['user.password'].focus();
		}
		else if(form.validateCode.value == null || form.validateCode.value == "")
		{
			alert("请填写登录验证码");
			form.validateCode.focus();
		}else{
			_Cookie.setCookie('username', form['user.username'].value);
			form.submit();
		}
	}

	function reset(){
		form = document.forms[0];
		form['user.username'].value = "";
		form['user.password'].value = "";
		form.validateCode.value = "";

		form['user.username'].focus();
	}

	document.forms[0]['user.username'].focus();

	document.onkeydown=function(event){
		if(window.event){
			event = window.event;
		}
		if(event.keyCode==13)
		login();
	}
</script>