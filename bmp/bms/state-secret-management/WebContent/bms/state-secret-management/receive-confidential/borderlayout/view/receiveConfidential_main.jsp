<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>收到密件情况查询</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<link rel="stylesheet" type="text/css" href="${ctx}/platform/template/borderlayout/skin/blue/css/pageNoButtonBar.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/theme/borderlayout/skin/blue/miftree/css/mif-tree.css" />

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/miftree/1.2/mif.tree.js",function(){
				$ENV.onDomReady(function(){
					tree = new Mif.Tree({
						container: $('district_tree'),
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
						url: '${ctx}/platform/district/district_tree.action?_ts='+new Date().getTime()});
					tree.addEvent('select',function(node){
						var chkIncludeChild = $('chkIncludeChild').value;
						$('district_code').value = node.data.id;
						contentLoad("${ctx}/bmp/receiveConfidential/receiveConfidential_listQuery.action?district.districtCode="+node.data.id+"&fromQuery=1&checkLower="+chkIncludeChild+"&_ts"+$time());
					});
					// 最后的10是上下边距和
					$("district_tree").setStyle("height", $('body_content').getSize().y - $('district_tree_header').getSize().y - 10);

				});
			});

			function contentLoad(url) {
				document.getElementById("contentIframe").src = url;
			}
		</script>

		<script type="text/javascript">
			function doChangeValue(value){
				if(value == 1){
					$('chkIncludeChild').value = 0;
					contentLoad("${ctx}/bmp/receiveConfidential/receiveConfidential_listQuery.action?district.districtCode="+$('district_code').value+"&fromQuery=1&checkLower=0&_ts"+$time());
				} else {
					$('chkIncludeChild').value = 1;
					contentLoad("${ctx}/bmp/receiveConfidential/receiveConfidential_listQuery.action?district.districtCode="+$('district_code').value+"&fromQuery=1&checkLower=1&_ts"+$time());
				}
			}
		</script>

	</head>
	<body>
		<div class="button_bar">
			<div class="left">
			</div>
			<div class="right">
			</div>
		</div>
		<div id="body_content" class="body_content">
			<div class="tree">
				<!-- 头部 -->
				<div id="district_tree_header" class="panel_header">
					<!-- 标题 -->
					<div class="panel_title panel_titleSearchIco">行政区划</div>
					<div class="panel_title" style="float: right;">
						<label for="chkIncludeChild">包含下级</label>
						<input id="chkIncludeChild" name="chkIncludeChild" type="checkbox" checked="checked" value="1" style="margin: 0 5px;" onclick="doChangeValue(this.value)">
						<input id="district_code" name="district_code" type="hidden" value="${district.districtCode }">
					</div>
				</div>
				<div id="district_tree" class="panel_content"></div><!-- 行政区划树 -->
			</div>
			<div class="tree_right_content"><!-- 右边表的内容  src=初始化内容 -->
				<iframe name="contentIframe" id="contentIframe" scrolling="auto" src="${ctx}/bmp/receiveConfidential/receiveConfidential_listQuery.action?fromQuery=1&checkLower=1" height="100%" width="100%" frameborder="0"></iframe>
			</div>
		</div>
	</body>
</html>