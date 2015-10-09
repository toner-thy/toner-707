<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib prefix="organ" uri="http://www.cdthgk.com/tags/organization/organ"%>
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>保密检查事件理</title>
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
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
		<s:actionmessage theme="messages" />
	</head>


<body>
		<div id="body_content">
			<div class="tree">
				<!-- 头部 -->
				<div id="district_tree_header" class="panel_header">
					<!-- 标题 -->
				<div class="panel_title panel_titleSearchIco">行政区划</div>
				<div class="panel_title" style="float: right;">
						<label for="chkIncludeChild">包含下级</label>
						<input id="chkIncludeChild" name="chkIncludeChild" type="checkbox" checked="checked"
						 value="0" style="margin: 0 5px;" onclick="doChangeValue(this.value)">
						<input id="district_code" name="district_code" type="hidden" value="${district.districtCode}">
						<input id="district_name" name="district_name" type="hidden" value="${district.districtName}">
				</div>
				</div>
				<div id="district_tree" class="panel_content"></div><!-- 行政区划树 -->
			</div>

			<!-- 右侧内容开始 -->
			<div class="tree_right_content" style="border: 1px solid #B5CBD9;">
				<iframe name="checkEventIframe" id="checkEventIframe" scrolling="auto" src="${ctx}/bmp/secrecyFullPartTime/secrecyFullPartTime_allList.action" height="100%" width="100%" frameborder="0"></iframe>
			</div>
			<!-- 右侧内容结束 -->
		</div>
	</body>
</html>

<script type="text/javascript">
	function contentLoad(url) {
		document.getElementById("checkEventIframe").src = url;
	}
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
		url: '${ctx}/platform/district/district_tree.action?times='+new Date().getTime()
	});
	tree.addEvent('select',function(node){
		$('district_code').value = node.data.id;
		var chkIncludeChild = $('chkIncludeChild').value;
		contentLoad("${ctx}/bmp/secrecyFullPartTime/secrecyFullPartTime_allList.action?district.districtCode="+node.data.id+"&district.districtName="+node.name+"&showType="+chkIncludeChild+"&_ts"+$time());
	});

	$("district_tree").setStyle("height", $('body_content').getSize().y - $('district_tree_header').getSize().y - 10);

</script>

<script type="text/javascript">
			function doChangeValue(value){
				if(value == 0){
					//不包含
					$('chkIncludeChild').value = 1;
					contentLoad("${ctx}/bmp/secrecyFullPartTime/secrecyFullPartTime_allList.action?district.districtCode="+$('district_code').value+"&district.districtName="+$('district_name').value+"&showType=1&_ts"+$time());
				} else {
					//包含
					$('chkIncludeChild').value = 0;
					contentLoad("${ctx}/bmp/secrecyFullPartTime/secrecyFullPartTime_allList.action?district.districtCode="+$('district_code').value+"&district.districtName="+$('district_name').value+"&showType=0&_ts"+$time());
				}
			}
</script>