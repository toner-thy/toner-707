<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>首页-标题</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">

		<script src="${contextPath}/resources/js/environment/environment.js" type="text/javascript"></script>


		<script type="text/javascript">
		$ENV.loader.loadStyleSheet("${contextPath}/platform/template/borderlayout/resources/css/index.css");
		$ENV.loader.loadStyleSheet("${contextPath}/platform/template/borderlayout/skin/blue/css/index_blue.css");
		$ENV.loader.loadJavaScript("${contextPath}/resources/js/mootools/mootools-core-1.4.js");
		$ENV.loader.loadJavaScript("${contextPath}/resources/js/mootools/mootools-more-1.4.js");
		$ENV.loader.loadJavaScript("${contextPath}/resources/js/SimpleUI/SimpleUI.js");
		$ENV.loader.loadJavaScript("${contextPath}/platform/layout/borderlayout/js/TabUtils.js");
		$ENV.loader.loadJavaScript("${contextPath}/resources/js/AccordionMenu/AccordionMenu.js", function() {
			$ENV.onDomReady(function(){
				// TODO 暂未实现  根据内容判断是否需要显示遮罩层
				//openNewDiv('newDiv');

				//判断用户权限决定是否显示浮动层
				var jsonRequest = new Request.JSON({
	 					url: '${ctx}/bmp/yearPlanDomainValidate/domainValidate_checkDomain.action?_td=' + new Date().getTime(),
	 				    onComplete: function(result, text){
							if(result.flag == "true"){
                				showTutorial(6,'dayId');
							}
	 				    }
	 			}).get();
                //showTutorial(6,'dayId');

				$$('.head_tab_btn').each(function(item){
					var a = item.getElement('a');
					item.set('url', a.href);
					a.set('href', 'javascript:void(0)');
					item.addEvent('click', function(){
						$$('.head_tab_btn').removeClass('head_tab_btn_active');
						this.addClass('head_tab_btn_active');
						$('navigation_body').load(this.get('url') + "&_ts="+ new Date().getTime());
						<c:if test="${dynamicMenuTitle}">
						$('navigation_title').set('text', a.title);
						</c:if>
					});
				});

				$('btnSplit').addEvent('click',function() {
					if($('btnSplit').retrieve('oldLeft')){
						$('navigation').setStyle('visibility','visible').tween('width', $('navigation').retrieve('oldWidth'));
						if ($ENV.browser.isIE6 || $ENV.browser.isIE7) {
							$('divMain').tween('margin-left', $('divMain').retrieve('oldMarginLeft'));
						} else {
							$('divMain').tween('left', $('divMain').retrieve('oldLeft'));
						}
						$('btnSplit').removeClass("split_hidden").tween('left', $('btnSplit').retrieve('oldLeft'));
						$('btnSplit').store('oldLeft', null);
					}else{
						$('navigation').store('oldWidth', $('navigation').getStyle('width'));
						new Fx.Tween('navigation').start('width',0).chain(
						    function(){ $('navigation').setStyle('visibility','hidden'); }
						);
						if ($ENV.browser.isIE6 || $ENV.browser.isIE7) {
							$('divMain').store('oldMarginLeft', $('divMain').getStyle('margin-left'));
							$('divMain').tween('margin-left', 0);
						} else {
							$('divMain').store('oldLeft', $('divMain').getStyle('left'));
							$('divMain').tween('left', $('btnSplit').getStyle('width'));
						}
						$('btnSplit').store('oldLeft', $('btnSplit').getStyle('left'));
						new Fx.Tween('btnSplit').start('left',0).chain(
						    function(){ $('btnSplit').addClass("split_hidden"); }
						);
					}
				});

				if (window.activeDomain) $('btn_domain_'+activeDomain).fireEvent('click');
				else {
					var activeBtn = $(document).getElement('.head_tab_btn');
					if (activeBtn) activeBtn.fireEvent('click');
				}

				<c:if test="${useTab}">
				$ENV.registComponent('loader', {
					open : function(p) {
						if ($type(p) == 'string') {
							var url = p;
							p = {
								url : url,
								title : ""
							};
						}
						TabUtil.openAsTab({
							title : p.title ? p.title : "",
							url : p.url,
							onClose : p.onClose
						});
					},
					exit : function() {
						TabUtil.closeTab();
					}
				});
				</c:if>
				<c:if test="${!useTab}">
				$ENV.registComponent('loader', {
					open : function(p) {
						if ($type(p) == 'string') {
							var url = p;
							p = {
								url : url,
								title : ""
							};
						}
						if ($ENV.getContainer()) {
							$ENV.getContainer().location.href = p.url;
						} else {
							window.top.location.href = p.url;
						}
					},
					exit : function() {
						// TODO 未实现退出
						throw new Error('未实现');
					}
				});
				</c:if>

				<%-- 注销 --%>
				$('btn_exit_sys').addEvent('click', function(){
					if (window.confirm('确定退出系统')) {
						var href = "${contextPath}/platform/login/logouting.action";
						window.top.location.href = href;
					}
				});
				<%-- 返回首页 --%>
				$('btn_return_index').addEvent('click', function(){
					$ENV.getComponent('loader').open({
						title : "首页",
						url : "${index}"
					});
				});
				<%-- 修改密码 --%>
				$('btn_change_pwd').addEvent('click', function(){
					$ENV.getComponent('loader').open({
						title : "修改密码",
						url : "${contextPath}/platform/permission/user/passwordChange.action"
					});
				});
				<%-- 帮助 --%>
				$('btn_change_help').addEvent('click', function(){
					//open('${contextPath}/platform/help/help_clientInfo.do?help.helpId=index_borderlayout');
					open('${contextPath}/bmp/helptree/helpTree_treeIndex.action');
				});

				<%-- 导航 --%>
				$('btn_change_daoHang').addEvent('click', function(){
					$ENV.getComponent('loader').open({
						title : "功能导航",
						url : "${contextPath}/bmp/helptree/helpTree_loadDaoHangTree.action"
					});
				});

				<%-- 系统日志 --%>
				$('btn_change_releaseLog').addEvent('click', function(){
					$ENV.getComponent('loader').open({
						title : "系统日志",
						url : "${contextPath}/bmp/sysReleaseLog/sysReleaseLog_detail.action"
					});
				});

				$('loginUserNumber').addEvent('click', function(){
					loadOnlineNumber(this.getFirst());
					$ENV.getComponent('loader').open({
						title : "在线用户",
						url : "${contextPath}/platform/login/onlineInfo/list.action"
					});
				});
				var loadOnlineNumber = function(el) {
					new Request.JSON({
					    onComplete: function(result, text){
					    	el.set('text', result.total);
					    },
					    url: "${contextPath}/platform/login/onlineInfo/total.action"
					}).post();
				}
				loadOnlineNumber($('loginUserNumber').getFirst());
			});
		});

		</script>
<script type="text/javascript">
var docEle = function()
{
    return document.getElementById(arguments[0]) || false;
}

function openNewDiv(_id)
{
    var m = "mask";
    if (docEle(_id)) document.body.removeChild(docEle(_id));
    if (docEle(m)) document.body.removeChild(docEle(m));

    //mask遮罩层

    var newMask = document.createElement("div");
    newMask.id = m;
    newMask.style.position = "absolute";
    newMask.style.zIndex = "99";
    _scrollWidth = Math.max(document.body.scrollWidth,document.documentElement.scrollWidth);
    _scrollHeight = Math.max(document.body.scrollHeight,document.documentElement.scrollHeight);
    newMask.style.width = _scrollWidth + "px";
    newMask.style.height = _scrollHeight + "px";
    newMask.style.top = "0px";
    newMask.style.left = "0px";
    newMask.style.background = "#33393C";
    newMask.style.filter = "alpha(opacity=40)";
    newMask.style.opacity = "0.40";
    document.body.appendChild(newMask);

    //新弹出层

    var newDiv = document.createElement("div");
    newDiv.id = _id;
    newDiv.style.position = "absolute";
    newDiv.style.zIndex = "9999";
    newDivWidth = 500;
    newDivHeight = 350;
    newDiv.style.width = newDivWidth + "px";
    newDiv.style.height = newDivHeight + "px";
    newDiv.style.top = (document.body.scrollTop + document.body.clientHeight/2 - newDivHeight/2) + "px";
    newDiv.style.left = (document.body.scrollLeft + document.body.clientWidth/2 - newDivWidth/2) + "px";
    newDiv.style.background = "#EFEFEF";
    newDiv.style.border = "1px solid #860001";
    newDiv.style.padding = "5px";
    newDiv.innerHTML = '<span style="font-size:24px;font-weight: bolder;">计划提醒&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>';//弹出层内容
    document.body.appendChild(newDiv);

    //弹出层滚动居中

    function newDivCenter()
    {
        newDiv.style.top = (document.body.scrollTop + document.body.clientHeight/2 - newDivHeight/2) + "px";
        newDiv.style.left = (document.body.scrollLeft + document.body.clientWidth/2 - newDivWidth/2) + "px";
    }
    if(document.all)
    {
        window.attachEvent("onscroll",newDivCenter);
    }
    else
    {
        window.addEventListener('scroll',newDivCenter,false);
    }

    //关闭新图层和mask遮罩层
    var newA = document.createElement("a");
    newA.id = "closeATag";
    newA.href = "#";
    newA.innerHTML = '关闭';
    newA.setAttribute("style","float:right;line-height: 30px;padding: 3px 8px;font-weight: bolder;");
    newA.style.color="#067bc8";


    newA.onclick = function()
    {
        if(document.all)
        {
            window.detachEvent("onscroll",newDivCenter);
        }
        else
        {
            window.removeEventListener('scroll',newDivCenter,false);
        }
        document.body.removeChild(docEle(_id));
        document.body.removeChild(docEle(m));
        return false;
    }
    newDiv.appendChild(newA);
}

function showDetailPage(){
	$("closeATag").click();
	TabUtil.openAsTab({
		url : "${ctx}/bmp/pPlanTaskResponse/pPlanTaskResponse_list.action?year="+new Date().getFullYear()+"&&_tm="+new Date().getTime(),
		title : '计划完成进度查询'
	});
}

function showYearPlanPage(){
	$("closeATag").click();
	TabUtil.openAsTab({
		url : "${ctx}/bmp/pYearPlan/pPlan_edit.action",
		title : '制定计划任务'
	});
}

function showTutorial(type,idNew){
	openNewDiv('newDiv');
	var contentObj = $("newDiv");
	var htmlObject;
	var jsonRequest = new Request.JSON({
			url: '${ctx}/bmp/pPlanTask/pPlanTask_isCheckTask.action?type='+type+'&&_tm='+new Date().getTime(),
		    onComplete: function(result, text){
			    	if (result.flag != null && result.flag == "1") {
				    		var contentArray = result.pYearPlanTasks;
				    		htmlObject = new Element('div', {id: 'contentDiv', style:'height:88%;width:100%;overflow:auto;'});
				    		var hrObj = new Element("hr",{style:'margin-top:2px;border:1px solid #1669AA;background: -moz-linear-gradient(left, #1669AA,#fff );'});
				    		hrObj.inject(htmlObject);
				    		var pObjMsg = new Element('p', {id: 'pTagMsg'});
				    		var divObj1 = new Element('div', {align:'center'});
				    		var fontObj1 = new Element('a', {id:'dayId',text:'日',style : 'color:black;font-size: 22px;font-weight: bolder;'});
				    		var fontObj11 = new Element('font', {text:'    '});
				    		fontObj1.href = "###";
				    		fontObj1.onclick=function(){showTutorial(6,"dayId");}

				    		var fontObj2 = new Element('a', {id:'weekId',text:'周',style : 'color:black;font-size: 22px;font-weight: bolder;'});
				    		var fontObj22 = new Element('font', {text:'    '});
				    		fontObj2.href = "###";
				    		fontObj2.onclick=function(){showTutorial(5,"weekId");}

				    		var fontObj3 = new Element('a', {id:'monthId', text:'月',style : 'color:black;font-size: 22px;font-weight: bolder;'});
				    		var fontObj33 = new Element('font', {text:'    '});
				    		fontObj3.href = "###";
				    		fontObj3.onclick=function(){showTutorial(4,"monthId");}

				    		var fontObj4 = new Element('a', {id:'springId',text:'季度',style : 'color:black;font-size: 22px;font-weight: bolder;'});
				    		var fontObj44 = new Element('font', {text:'    '});
				    		fontObj4.href = "###";
				    		fontObj4.onclick=function(){showTutorial(3,"springId");}

				    		var fontObj5 = new Element('a', {id:'banId', text:'半年',style : 'color:black;font-size: 22px;font-weight: bolder;'});
				    		var fontObj55 = new Element('font', {text:'    '});
				    		fontObj5.href = "###";
				    		fontObj5.onclick=function(){showTutorial(2,"banId");}

				    		var fontObj6 = new Element('a', {id:'yearId', text:'年',style : 'color:black;font-size: 22px;font-weight: bolder;'});
				    		var fontObj = new Element('font', {text:'当日未完成计划任务：',style : 'color:red;'});
				    		fontObj6.href = "###";
				    		fontObj6.onclick=function(){showTutorial(1,"yearId");}

				    		if(type=='1')
				    		{
				    			fontObj = new Element('font', {text:'当年未完成计划任务：',style : 'color:red;'});
				    		}
				    		if(type=='2')
				    		{
				    			fontObj = new Element('font', {text:'半年未完成计划任务：',style : 'color:red;'});
				    		}
				    		if(type=='3')
				    		{
				    			fontObj = new Element('font', {text:'本季度未完成计划任务：',style : 'color:red;'});
				    		}
				    		if(type=='4')
				    		{
				    			fontObj = new Element('font', {text:'本月未完成计划任务：',style : 'color:red;'});
				    		}
				    		if(type=='5')
				    		{
				    			fontObj = new Element('font', {text:'本周未完成计划任务：',style : 'color:red;'});
				    		}
				    		if(type=='6')
				    		{
				    			fontObj = new Element('font', {text:'当日未完成计划任务：',style : 'color:red;'});
				    		}
				    		fontObj1.inject(divObj1);
				    		fontObj11.inject(divObj1);
				    		fontObj2.inject(divObj1);
				    		fontObj22.inject(divObj1);
				    		fontObj3.inject(divObj1);
				    		fontObj33.inject(divObj1);
				    		fontObj4.inject(divObj1);
				    		fontObj44.inject(divObj1);
				    		fontObj5.inject(divObj1);
				    		fontObj55.inject(divObj1);
				    		fontObj6.inject(divObj1);
				    		divObj1.inject(htmlObject);
				    		fontObj.inject(pObjMsg);
				    		pObjMsg.inject(htmlObject);
				    		for( var i=0; i<contentArray.length; i++ ){
				    			var pObj = new Element('p', {id: 'pTag'+i, style : 'margin-left:40px;'});
				    			var aObj = new Element('a', {id: 'aTag'+i,text:contentArray[i], title:'点击查看进度情况',style:"cursor: pointer;"});
				    			aObj.href = "###";
				    			aObj.onclick=function(){showDetailPage();}

				    			aObj.inject(pObj);
				    			pObj.inject(htmlObject);
				    		}

			    	}else{
			    		var contentArray = new Array();
			    		contentArray.push("当年未设置年度工作计划，请点击设置");
			    		htmlObject = new Element('div', {id: 'contentDiv'});
			    		var hrObj = new Element("hr",{style:'margin-top:2px;border:1px solid #1669AA;background: -moz-linear-gradient(left, #1669AA,#fff );'});
			    		hrObj.inject(htmlObject);
			    		for( var i=0; i<contentArray.length; i++ ){
			    			var pObj = new Element('p',
			    					{
			    						id: 'pTag'+i,
			    						style: 'margin-top:50px;margin-left:100px;'
			    					});
			    			var aObj = new Element('a',
			    					{
			    						id: 'aTag'+i,
			    						text:contentArray[i]
			    					});
			    			aObj.href = "###";
			    			aObj.onclick=function(){showYearPlanPage();}


			    			aObj.inject(pObj);
			    			pObj.inject(htmlObject);
			    		}
			    	}
			    	htmlObject.inject(contentObj);
			    	$(idNew+"").style.color="green";
				    }
				}).get();
}

</script>
	</head>
	<body>
		<%-- 头部 --%>
		<div id="div_head" class="head">
			<div class="logo_wrapper">
				<div class="logo">
					<img src="${logoPicPath}" height="56"/>
					<div class="version">${_.config.application.versionName}&nbsp;${version}</div>
				</div>
			</div>

			<div class="head_button_bar">
			</div>
			<div class="button btn_change_daoHang" id="btn_change_daoHang">&nbsp;</div>
			<div class="button btn_change_releaseLog" id="btn_change_releaseLog">&nbsp;</div>

			<div class="button btn_return_index" id="btn_return_index">&nbsp;</div>
			<div class="button btn_change_pwd" id="btn_change_pwd">&nbsp;</div>
			<div class="button btn_change_help" id="btn_change_help">&nbsp;</div>
			<div class="button btn_exit_sys" id="btn_exit_sys">&nbsp;</div>

			<div class="head_bottom_background">
				<div class="text"><fmt:formatDate value="${today }" pattern="yyyy年MM月dd日 " />&nbsp;&nbsp;
				用户：${currentUser.userInfo.name}&nbsp;&nbsp;
				单位：${currentUser.organ.organName }&nbsp;&nbsp;
				<%--
				部门：${currentUser.userInfo.department.departmentName }&nbsp;&nbsp;
				--%>
				<span id="loginUserNumber"  style="cursor: pointer;color: #FFFC00;">
				在线用户：<span style="font-weight:bold; font-size:14px; color:#FFFC00;cursor: pointer;">${loginUserNumber}</span>人
				</span>
				</div>
			</div>
			<div class="head_tab_bar">
				<c:forEach var="domainNode" items="${domainTree.rootNode.childNodes }" varStatus="status">
				<c:if test="${domain.domainId == subsys}">
				<script type="text/javascript">
				var activeDomain = '${domain.domainId }'
				</script>
				</c:if>
				<div class="head_tab_btn" id="btn_domain_${domain.domainId}">
						<div class="menu_btn_left"></div>
						<div class="menu_btn_center"><a title="${domainNode.nodeObject.domainName}" href="${contextPath}/platform/layout/borderlayout/menu.action?domainId=${domainNode.nodeObject.domainId}">${domainNode.nodeObject.domainName }</a></div>
						<div class="menu_btn_right"></div>
				</div>

				</c:forEach>
			</div>
		</div>
		<!-- 主体 -->
		<div id="div_body" class="body">
			<div id="navigation" class="navigation">
				<div id="navigation_head" class="navigation_head">
					<div class="navigation_title" id="navigation_title">功能菜单</div>
<!-- 					<div class="navigation_head_background"></div> -->
		  		</div>
				<div id="navigation_body" class="navigation_body">
				</div>
			</div>
			<div id="divMain" class="main" style="overflow: hidden;">
				<iframe frameborder="0" name="main_content" id="main_content" scrolling="no" src="${contextPath}/platform/layout/borderlayout/content.action" style="overflow: hidden;"></iframe>
			</div>
			<div id="btnSplit" class="split">&nbsp;</div>
		</div>
		<!-- 腿部 -->
		<div class="foot"></div>
	</body>
</html>