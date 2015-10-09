<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>机关涉密人员查询</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- css -->
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/page.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/miftree/css/mif-tree.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/explorer/message/css/ymPrompt.css" type="text/css" rel="stylesheet" />

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.2.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.2.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/miftree/1.1/mif.tree-v1.1.js", function(){
				$ENV.onDomReady(function(){
					tree = new Mif.Tree({
						container: $('district_tree'),
						forest: true,
						initialize: function(){
							new Mif.Tree.KeyNav(this);
						},
						types: {
							folder:{
								openIcon: 'mif-tree-open-icon',
								closeIcon: 'mif-tree-close-icon'
							},
							loader:{
								openIcon: 'mif-tree-loader-open-icon',
								closeIcon: 'mif-tree-loader-close-icon'
							},
							disabled:{
								openIcon: 'mif-tree-open-icon',
								closeIcon: 'mif-tree-close-icon',
								dragDisabled: true,
								cls: 'disabled'
							},
							book:{
								openIcon: 'mif-tree-book-icon',
								closeIcon: 'mif-tree-book-icon',
								loadable: true
							},
							bin:{
								openIcon: 'mif-tree-bin-open-icon',
								closeIcon: 'mif-tree-bin-close-icon'
							}
						},
						dfltType:'folder'
					});
					tree.load({
						url: '${ctx}/platform/district/district_tree.action?times='+new Date().getTime()});
					tree.addEvent('select',function(node){
						document.getElementById('idDistrictIframe').src = "secrecyPerson_districtQueryList.action?district.districtCode="+node.data.id;
					});
					$("district_tree").setStyle("height",window.getSize().y - $("district_tree_header").getSize().y - 10);
				});
			});
		</script>

		<s:actionmessage theme="messages" />

		<style type="text/css">
			html,body {padding: 2px}
		</style>
	</head>

	<body>
		<div id="body_content">
			<!-- 左侧树 -->
			<div class="tree">
				<!-- 树标题 -->
				<div id="district_tree_header" class="panel_header">
					<div class="panel_title">行政区划</div>
				</div>
				<!-- 树内容 -->
				<div id="district_tree" class="panel_content"></div>
			</div>

		    <!-- 右侧内容开始 -->
			<div class="tree_right_content" style="border: 1px solid #B5CBD9;">
				<iframe name="districtIframe" id="idDistrictIframe" scrolling="auto" src="${ctx}/bmp/secrecyperson/secrecyPerson_districtQueryList.action" height="100%" width="100%" frameborder="0"></iframe>
			</div>
			<!-- 右侧内容结束 -->
		</div>
	</body>
</html>