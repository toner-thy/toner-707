<%@ page language="java" pageEncoding="utf-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>组织机构划分</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<link rel="stylesheet" type="text/css" href="${ctx}/platform/theme/borderlayout/skin/blue/css/page.css" />
		<link rel="stylesheet" type="text/css" href="/bmp/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css" />
		<link rel="stylesheet" type="text/css" href="/bmp/resources/theme/borderlayout/skin/blue/miftree/css/mif-tree.css" />

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
				<div class="pop_button_bar">
				</div>
			</div>
		</div>
		<div id="body_content" class="body_content" style="top:3px;">
			<div class="tree">
				<!-- 头部 -->
				<div id="district_tree_header" class="panel_header">
					<!-- 标题 -->
					<div class="panel_title panel_titleSearchIco">行政区划</div>

				</div>
				<!-- 内容 -->
				<div id="district_tree" class="panel_content"></div>
			</div>
			<div class="tree_right_content">
				<!-- <iframe name="contentIframe" id="contentIframe" scrolling="auto" src="/bmp/secrecyorganization/secrecycommittee/secrecyCommittee_list.action" height="100%" width="100%" frameborder="0"></iframe> -->
				<iframe name="contentIframe" id="contentIframe" scrolling="auto" src="/bmp/bmp/secrecy-organization/secrecy-workorgan/borderlayout/view/secrecyWorkOrgan_gbtj_sub_demo.jsp" height="100%" width="100%" frameborder="0">





				</iframe>
			</div>
		</div>
	</body>
</html>
<script type="text/javascript">
	function contentLoad(url) {
		document.getElementById("contentIframe").src = url;
	}

	tree = new Mif.Tree({
		container: $('district_tree'),
		initialize: function(){
			//this.initCheckbox('deps');
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
		contentLoad("/bmp/bmp/secrecy-organization/secrecy-workorgan/borderlayout/view/secrecyWorkOrgan_gbtj_sub_demo.jsp");
	});
	// 最后的10是上下边距和
	$("district_tree").setStyle("height", $('body_content').getSize().y - $('district_tree_header').getSize().y - 10);
</script>
</html>


<form id="form_1373009724000" action="/bmp/secrecyorganization/secrecycommittee/secrecyCommittee_main.action" method="GET">

</form>
<script type="text/javascript">
<!--
window.refresh = function() {
	document.getElementById('form_1373009724000').submit();
}
if(window.$ENV) {
	$ENV.page.refresh = window.refresh;
	//$ENV.getRegion();
}
//-->
</script>


<script type="text/javascript">
<!--
if(window.$ENV) {
	if (window.getOpener) {
		$ENV.page.exit = window.close;
	} else if (window.dialogArguments != "undefined") {
		$ENV.page.exit = window.close;
	} else if (window.opener){
		$ENV.page.exit = window.close;
	} else if (window.parent) {
		alert("未实现");
	}
}
//-->
</script>



<script type="text/javascript">
<!--
var messages = [];

if (window.$ENV && $ENV.getComponent('alert.messager')) {
	$ENV.getComponent('alert.messager').show(messages);
}
//-->
</script>







