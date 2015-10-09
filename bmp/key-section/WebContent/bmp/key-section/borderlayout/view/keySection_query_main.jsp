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
		<title>保密要害部门查询</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<link rel="stylesheet" type="text/css" href="${ctx}/platform/template/borderlayout/skin/blue/css/pageNoButtonBar.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/theme/borderlayout/skin/blue/miftree/css/mif-tree.css" />

		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/mootools/mootools-core-1.2.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/mootools/mootools-more-1.2.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/miftree/1.1/mif.tree-v1.1.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/notimoo/notimoo-1.2.1.js" type="text/javascript"></script>
		<script src="${ctx}/platform/layout/borderlayout/js/TabUtils.js" type="text/javascript"></script>

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
						<input id="chkIncludeChild" name="chkIncludeChild" type="checkbox" checked="checked" value="1" style="margin: 0 5px;" onclick="doChangeValue()">
						<input id="districtCode" name="districtCode" type="hidden" value="${districtCode}">
					</div>
				</div>
				<div id="district_tree" class="panel_content"></div><!-- 行政区划树 -->
			</div>
			<div class="tree_right_content"><!-- 右边表的内容  src=初始化内容 -->
				<iframe name="contentIframe" id="contentIframe" scrolling="auto" src="${ctx}/bmp/keySection/keySection_query_list.action?&ywFlag=1&isChildren=1" height="100%" width="100%" frameborder="0"></iframe>
			</div>
		</div>
	</body>
</html>

<script type="text/javascript">
	//勾选  是否包含下级
	function doChangeValue(){
		var inc = $("chkIncludeChild");
		var isChildren = "0";

		if(inc.checked==true) {//被选中
			isChildren = "1";
			inc.value = "1";
		}else {
			inc.value = "0";
		}

		var districtCode = $("districtCode").value;
		var url = "${ctx}/bmp/keySection/keySection_query_list.action?&ywFlag=1&districtCode="+districtCode+"&isChildren="+isChildren;
		contentLoad(url);//重新加载
	}

	function contentLoad(url) {
		document.getElementById("contentIframe").src = url;
	}

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
		$('districtCode').value = node.data.id;
		var isChildren = $("chkIncludeChild").value;//是否包含下级
		contentLoad("${ctx}/bmp/keySection/keySection_query_list.action?&ywFlag=1&districtCode="+node.data.id+"&isChildren="+isChildren+"&$(&editable=0&_ts"+$time());
	});
	// 最后的10是上下边距和
	$("district_tree").setStyle("height", $('body_content').getSize().y - $('district_tree_header').getSize().y - 10);
</script>
</html>