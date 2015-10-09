<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<title>${name}</title>

		<script src="${contextPath}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.onDomReady(function(){
				$ENV.setContext('${contextPath}');
			});

			$ENV.loader.loadStyleSheet("${contextPath}/platform/jsp/public/desktopMap/css/main.css");
			$ENV.loader.loadJavaScript("${contextPath}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${contextPath}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${contextPath}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/platform/resources/js/platform.js");
			$ENV.loader.loadJavaScript("${contextPath}/resources/js/notimoo/notimoo-1.2.1.js",function(){
// 				new Request.JSON({
// 				    url: "${contextPath}/bmp/desktopMap/desktopMap_mailUnRead.action",
// 				    onSuccess: function(messages, text){
// 				    	var obj = eval(text);
// 				    	var ul = $('menu_ul_id');
// 				    	$('menu_ul_id').innerHTML = "";
// 				    	for(var i=0;i<obj.length;i++){
// 							var li = new Element('li');
// 							var titleDiv = new Element('div',{'class':'menu_title'});
// 							var title_ = obj[i].title;
// 							if(title_.length > 13){
// 								title_ = title_.substring(0,13,title_);
// 							}
// 							var aa = new Element('a',{'title':obj[i].title,'href':"javascript:detailMail('"+obj[i].mailId+"')",'text':title_,'onclick':"detailMail('"+obj[i].mailId+"')"});
// 							aa.inject(titleDiv);
// 							titleDiv.inject(li);
// 							var timeDiv = new Element('div',{'class':'menu_time','text':obj[i].time});
// 							timeDiv.inject(li);
// 							li.inject(ul);
// 				    	}
// 				    }
// 				}).post();
// 				doShowTasks(1);

			});
		</script>
		<script type="text/javascript">
			function doOpenWindow(url){
				$ENV.dialog.open({
					url : url,
					width : 0.9,
					height :  0.99,
					title : ''
				});
			}

			function doWork(){
				window.open("http://74.2.74.75:8888/ServiceAction/j_acegi_security_check?userName=${username}&encodedCode=${currentUser.password}");
			}
			function doShowTask(){
				if($('tes').style.display == 'none'){
					$('tes').style.display='';
				} else {
					$('tes').style.display='none';
				}
			}

			function detailMail(mailId){
				var date = new Date();
				var action = "${contextPath}/mail/person/mail_detail.action?baseMail.mailId="+mailId + "&t_date="+date.getTime();
				$ENV.dialog.open({
					url : action,
					width : 0.8,
					height : 0.9,
					title : '邮件查看'
				});

			}
			function doYearShow(){
				$('tes').style.display='none';
				$('task_menu_ul_id').innerHTML = "";
				doShowTasks(1);
			}
			function doMonthShow(){
				$('tes').style.display='none';
				$('task_menu_ul_id').innerHTML = "";
				doShowTasks(4);
			}
			function doDayShow(){
				$('tes').style.display='none';
				$('task_menu_ul_id').innerHTML = "";
				doShowTasks(6);
			}
			function doShowTasks(type){
				new Request.JSON({
				    url: "${contextPath}/bmp/desktopMap/desktopMap_isCheckTask.action?type="+type,
				    onSuccess: function(messages, text){
				    	var obj = eval(text);
				    	var ul = $('task_menu_ul_id');
				    	if(obj.length==0){
				    		var li = new Element('li',{'id':'task_li_id'});
							var titleDiv = new Element('div',{'class':'task_menu_title'});
				    		var aa = new Element('a',{'href':'javascript:showDetailPage()','title':'没有任务计划','text':'没有任务计划','onclick':'showDetailPage()','width':'200px;'});
				    		aa.inject(titleDiv);
							titleDiv.inject(li);
							li.inject(ul);
				    	}
				    	for(var i=0;i<obj.length;i++){
							var li = new Element('li',{'id':'task_li_id'});
							var titleDiv = new Element('div',{'class':'task_menu_title'});
							var title_ = obj[i].title;
							if(title_.length > 17){
								title_ = title_.substring(0,17,title_)+'...';
							}
							var aa = new Element('a',{'href':'javascript:showDetailPage()','title':obj[i].title,'text':title_,'onclick':'showDetailPage()','width':'200px;'});
							aa.inject(titleDiv);
							titleDiv.inject(li);

							li.inject(ul);
				    	}
				    }
				}).post();
			}
			function sysLog(){
				var date = new Date();
				var action = "${contextPath}/bmp/sysReleaseLog/sysReleaseLog_detail.action?&t_date="+date.getTime();
				$ENV.dialog.open({
					url : action,
					width : 0.8,
					height : 0.9,
					title : '升级日志'
				});

			}
			function webMap(){
				var date = new Date();
				var action = "${contextPath}/bmp/helptree/helpTree_loadDaoHangTree.action?&t_date="+date.getTime();
				$ENV.dialog.open({
					url : action,
					width : 0.8,
					height : 0.9,
					title : '功能导航'
				});

			}
			function editPwd(){
				var date = new Date();
				var action = "${contextPath}/platform/permission/user/passwordChange.action?&t_date="+date.getTime();
				$ENV.dialog.open({
					url : action,
					width : 0.8,
					height : 0.9,
					title : '修改密码'
				});

			}
			function useHelp(){
				var date = new Date();
				var action = "${contextPath}/bmp/helptree/helpTree_treeIndex.action?&t_date="+date.getTime();
				$ENV.dialog.open({
					url : action,
					width : 0.8,
					height : 0.9,
					title : '使用帮助'
				});

			}
			function showDetailPage(){
				var date = new Date();
				var action = "${contextPath}/bmp/pPlanTaskResponse/pPlanTaskResponse_list.action?year="+new Date().getFullYear()+"&&_tm="+date.getTime();
				$ENV.dialog.open({
					url : action,
					width : 0.8,
					height : 0.9,
					title : '计划完成进度查询'
				});
			}

			function doWriteMail(){
				var action = "${contextPath}/mail/person/mail_sendMail.action?t_date="+new Date().getTime();
				$ENV.dialog.open({
					url : action,
					width : 0.8,
					height : 0.9,
					title : '写邮件'
				});
			}
			function time(){
			    //获得显示时间的div
			    t_div = document.getElementById('showtime');
			   var now=new Date()
			    //替换div内容
			   t_div.innerHTML =now.getFullYear()
			    +"年"+(now.getMonth()+1)+"月"+now.getDate()
			    +"日"+now.getHours()+"时"+now.getMinutes()
			    +"分"+now.getSeconds()+"秒";
			    //等待一秒钟后调用time方法，由于settimeout在time方法内，所以可以无限调用
			   setTimeout(time,1000);
			  }

		</script>
	</head>
	<body onload="time()">
		<div class="w_top">
			<div class="w_logo" <c:if test="${_.login.actor.user.organ.organType == 'ORGAN_MANAGER'}">style="background: url('${contextPath}/platform/jsp/public/desktopMap/img/logo_bms.png');"</c:if>
			<c:if test="${_.login.actor.user.organ.organType != 'ORGAN_MANAGER'}">style="background: url('${contextPath}/platform/jsp/public/desktopMap/img/logo_bmp.png');"</c:if>
			></div>
			<div class="w_version">
				<span class="w_version_word">${_.config.application.versionName}&nbsp;${version}</span>

			</div>
		</div>
		<div class="w_content">
			<div class="w_content_top">
				<div class="top_left">
					<span id="showtime"></span>&nbsp;&nbsp;
					用户：${currentUser.userInfo.name}&nbsp;&nbsp;
					部门：${currentUser.userInfo.department.departmentName }&nbsp;&nbsp;
					单位：${currentUser.organ.organName }&nbsp;&nbsp;
				</div>
				<div class="top_right">
					<a href="###" onclick="webMap()">功能导航</a>
					<a href="###" onclick="sysLog()">升级日志</a>
					<a href="###" onclick="editPwd()">修改密码</a>
					<a href="###" onclick="useHelp()">使用帮助</a>
					<a href="###" onclick="javascript:window.location.href='${contextPath}/platform/login/logouting.action'">安全退出</a>
					<a href="###" onclick="javascript:window.open('${contextPath}/bmp/desktopMap/desktopMap_homeOld.action')">原版界面</a>
				</div>
			</div>
			<div class="w_content_main">
				<div class="content_left">
					<c:forEach var="domainNode" items="${newList}" varStatus="status">
							<a title="${domainNode.nodeObject.domainName}" href="###" style="text-decoration:none" onclick="doOpenWindow('${contextPath}/bmp/desktopMap/desktopMap_homeNow.action?domainId=${domainNode.nodeObject.domainId}')">
								<img class="content_img" onmouseover="this.style.border='3px solid #ffffff';this.src='${contextPath}/platform/jsp/public/desktopMap/img/logo/${domainNode.nodeObject.domainId}.png'"
									onmouseout="this.style.border='0px';this.src='${contextPath}/platform/jsp/public/desktopMap/img/logo/${domainNode.nodeObject.domainId}.png'"
									style="border: 0px" src="${contextPath}/platform/jsp/public/desktopMap/img/logo/${domainNode.nodeObject.domainId}.png" width="105px" height="100px" />
							</a>
					</c:forEach>



				</div>
				<div class="content_right">
<!-- 					<div class="r_up_title"> -->
<%-- 						<a href="###"  onclick="doShowTask()"><img src="${contextPath}/platform/jsp/public/desktopMap/img/jihuaTask.png" width="282px" height="40px" border="0"></a> --%>
<!-- 						<div id="tes" style="display: none"> -->
<!-- 							<div class="tes_div"> -->
<%-- 								<img  src="${contextPath}/platform/jsp/public/desktopMap/img/yearTask.png" --%>
<%-- 								onmouseover="this.src='${contextPath}/platform/jsp/public/desktopMap/img/yearTask1.png'" --%>
<%-- 								onmouseout="this.src='${contextPath}/platform/jsp/public/desktopMap/img/yearTask.png'" --%>
<!-- 								width="99px" onclick="doYearShow()" height="38px"> -->
<!-- 							</div> -->
<!-- 							<div class="tes_div"> -->
<%-- 								<img src="${contextPath}/platform/jsp/public/desktopMap/img/monthTask.png" --%>
<%-- 							 	onmouseover="this.src='${contextPath}/platform/jsp/public/desktopMap/img/monthTask1.png'" --%>
<%-- 								onmouseout="this.src='${contextPath}/platform/jsp/public/desktopMap/img/monthTask.png'" --%>
<!-- 							 	width="99px" onclick="doMonthShow()" height="38px"> -->
<!-- 							</div> -->
<!-- 							<div class="tes_div"> -->
<%-- 								<img src="${contextPath}/platform/jsp/public/desktopMap/img/dayTask.png" --%>
<%-- 							 	onmouseover="this.src='${contextPath}/platform/jsp/public/desktopMap/img/dayTask1.png'" --%>
<%-- 								onmouseout="this.src='${contextPath}/platform/jsp/public/desktopMap/img/dayTask.png'" --%>
<!-- 							 	width="99px" onclick="doDayShow()" height="38px"> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="r_up_img"> -->
<!-- 						<div class="menu_up"> -->
<!-- 					        <ul id="task_menu_ul_id"></ul> -->
<!-- 					    </div> -->
<!-- 					</div> -->
<!-- 					<div class="r_up_title"> -->
<%-- 						<a href="###" onclick="doWriteMail()"><img src="${contextPath}/platform/jsp/public/desktopMap/img/mailnotice.png" width="282px" height="40px" border="0"></a> --%>
<!-- 					</div> -->
<!-- 					<div id="brother" class="r_up_img"> -->
<!-- 						<div class="menu"> -->
<!-- 					        <ul id="menu_ul_id"> -->
<!-- 								桌面地图显示未读邮件5封 -->
<%-- 					            <c:forEach items="${inBoxMailList}" var="inboxMail" begin="0" end="4"> --%>
<!-- 						            <li class="menu_li"> -->
<!-- 						            	<div class="menu_title"> -->
<%-- 							            	<c:if test="${fn:length(inboxMail.title) > 13}"><a href="javascript:detailMail('${inboxMail.mailId }')" title="${inboxMail.title }">${fn:substring(inboxMail.title,0,13)} ...</a></c:if> --%>
<%-- 							            	<c:if test="${fn:length(inboxMail.title) <= 13}"><a href="javascript:detailMail('${inboxMail.mailId }')" title="${inboxMail.title }">${inboxMail.title} </a></c:if> --%>
<!-- 						            	</div> -->
<!-- 						            	<div class="menu_time"> -->
<%-- 						            		<fmt:formatDate value="${inboxMail.receiveMailDate }" pattern="yyyy-MM-dd"/> --%>
<!-- 						            	</div> -->
<!-- 						            </li> -->
<%-- 					            </c:forEach> --%>
<!-- 					        </ul> -->
<!-- 					    </div> -->
<!-- 					</div> -->
				</div>
			</div>
		</div>
		<div class="w_bottom">
		</div>
	</body>
</html>