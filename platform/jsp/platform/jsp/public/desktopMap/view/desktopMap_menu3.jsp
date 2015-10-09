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
		$ENV.loader.loadStyleSheet("${contextPath}/platform/jsp/public/desktopMap/css/index.css");
		$ENV.loader.loadStyleSheet("${contextPath}/platform/template/borderlayout/skin/blue/css/index_blue.css");
		$ENV.loader.loadJavaScript("${contextPath}/resources/js/mootools/mootools-core-1.4.js");
		$ENV.loader.loadJavaScript("${contextPath}/resources/js/mootools/mootools-more-1.4.js");
		$ENV.loader.loadJavaScript("${contextPath}/resources/js/SimpleUI/SimpleUI.js");
		$ENV.loader.loadJavaScript("${contextPath}/platform/layout/borderlayout/js/TabUtils.js");
		$ENV.loader.loadJavaScript("${contextPath}/resources/js/AccordionMenu/AccordionMenu.js", function() {
			$ENV.onDomReady(function(){
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
			});
		});

		</script>
	</head>
	<body>
		<%-- 头部 --%>
		<div id="div_head" class="head">
			<div class="head_bottom_background" >
				<div class="text"><fmt:formatDate value="${today }" pattern="yyyy年MM月dd日 " />&nbsp;&nbsp;
				用户：${currentUser.userInfo.name}&nbsp;&nbsp;
				单位：${currentUser.organ.organName }&nbsp;&nbsp;
				<%--
				部门：${currentUser.userInfo.department.departmentName }&nbsp;&nbsp;
				<span id="loginUserNumber"  style="cursor: pointer;color: #FFFC00;">
				在线用户：<span style="font-weight:bold; font-size:14px; color:#FFFC00;cursor: pointer;">${loginUserNumber}</span>人
				</span>
				--%>
				</div>
			</div>
			<div class="head_tab_bar">
				<!-- 根据杜周川的建议：把以上三个子系统合并为一个图标，点进去还是显示三个子系统 -->
				<c:if test="${domainId == ''}">
					<c:forEach var="domainNode" items="${newList}" varStatus="status">
						<div class="head_tab_btn" id="btn_domain_${domainNode.nodeObject.domainId}">
							<div class="menu_btn_left"></div>
							<div class="menu_btn_center"><a title="${domainNode.nodeObject.domainName}" href="${contextPath}/platform/layout/borderlayout/menu.action?domainId=${domainNode.nodeObject.domainId}">${domainNode.nodeObject.domainName }</a></div>
							<div class="menu_btn_right"></div>
						</div>
					</c:forEach>
				</c:if>
				<c:if test="${domainId != ''}">
					<div class="head_tab_btn" id="btn_domain_${domain.domainId}">
						<div class="menu_btn_left"></div>
						<div class="menu_btn_center"><a title="${parentDomain.domainName}" href="${contextPath}/platform/layout/borderlayout/menu.action?domainId=${parentDomain.domainId}">${parentDomain.domainName }</a></div>
						<div class="menu_btn_right"></div>
					</div>
				</c:if>
			</div>
		</div>
		<!-- 主体 -->
		<div id="div_body" class="body" style="top:0px;margin-top:0px;">
			<div id="navigation" class="navigation" style="">
				<div id="navigation_head" class="navigation_head">
					<div class="navigation_title" id="navigation_title">功能菜单</div>
		  		</div>
				<div id="navigation_body" class="navigation_body">
				</div>
			</div>
			<div id="divMain" class="main" style="overflow: hidden;top:25px;*top:0px;">
				<iframe frameborder="0" name="main_content" id="main_content" scrolling="no" src="${contextPath}/bmp/desktopMap/desktopMap_menu4.action?domainId=${domainId}" style="overflow: hidden;"></iframe>
			</div>
			<div id="btnSplit" class="split">&nbsp;</div>
		</div>
		<!-- 腿部 -->
		<div class="foot"></div>
	</body>
</html>