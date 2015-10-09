<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<script type="text/javascript">
$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/mootools/mootools-core-1.4.js");
$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/mootools/mootools-more-1.4.js");
$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/AccordionMenu/AccordionMenu.js",function() {
	$ENV.onDomReady(function(){
		var createAccordionMenu = function(){
			var acd = $('navigation_body');
			acd.setStyle('height',$('navigation').getSize().y - $('navigation_head').getSize().y);
			new AccordionMenu('div.accordion_title', 'div.accordion_content', {
				opacity: true,
				onActive: function(toggler, element){
					toggler.addClass('navigation_menu_active');
				},
				onBackground: function(toggler, element){
					toggler.removeClass('navigation_menu_active');
				}
			},acd);
		};
		createAccordionMenu();
		window.addEvent('resize', createAccordionMenu);
		$$('.navigation_menu_title a').each(function(item){
			var href = item.get('href');
			item.removeProperty('href');
			item.set('url', href);
			item.setStyle('cursor', 'pointer');
			item.addEvent('click', function(){
				var url = this.get('url');
				<%--
				if (url.indexOf("?") != -1) {
					url += "&_ts";
				} else {
					url += "?_ts";
				}
				url += new Date().getTime();
				--%>
				var text = this.get('text');
				window["main_content"].load({
					url : url,
					title : text
				});
			});
		});
	});
});

</script>
<c:forEach var="menuNode" items="${domainMenu.childNodes }">
<%-- 一级菜单 --%>
<div class="navigation_menu accordion_title" title="${menuNode.nodeObject.domainName}">
	<div class="navigation_menu_ico"></div>
	<div class="navigation_menu_title">${fn:substring(menuNode.nodeObject.domainName, 0 , _.config.borderlayout.maxLetterNumber)}<c:if test="${fn:length(menuNode.nodeObject.domainName) > _.config.borderlayout.maxLetterNumber}">...</c:if></div>
</div>
<div class="navigation_menu_content accordion_content">
	<c:forEach var="secondeMenuNode" items="${menuNode.childNodes}">
	<%-- 二级菜单 --%>
	<div class="navigation_menu">
		<div class="navigation_menu_ico"></div>
		<div class="navigation_menu_title">
			<a href="${pageContext.request.contextPath}${secondeMenuNode.nodeObject.resource.url}" target="frm_main" title="${secondeMenuNode.nodeObject.domainName}">${fn:substring(secondeMenuNode.nodeObject.domainName, 0 , _.config.borderlayout.maxLetterNumber)}<c:if test="${fn:length(secondeMenuNode.nodeObject.domainName) > _.config.borderlayout.maxLetterNumber}">...</c:if></a>
		</div>
	</div>
	</c:forEach>
</div>
</c:forEach>