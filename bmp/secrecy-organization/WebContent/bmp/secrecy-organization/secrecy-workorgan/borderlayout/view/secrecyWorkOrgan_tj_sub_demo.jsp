<%@ page language="java" pageEncoding="utf-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">

		$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/resources/css/component.css");
		$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/component_blue.css");
		$ENV.loader.loadStyleSheet("${ctx}/platform/theme/borderlayout/skin/blue/css/ecTable.css");


		$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
		$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
		$ENV.loader.loadJavaScript("${ctx}/platform/template/borderlayout/resources/js/ectable/EcTable.js");

		$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
			$ENV.onDomReady(function(){
				$('main_tab').setStyle('height', window.getSize().y - 5);
				$('main_tab').setStyle('width', window.getSize().x - 10);
				$$('.tab_panel_content .panel_content').setStyle('height', window.getSize().y - $('main_tab_header').getSize().y - 5);
				new SimpleUI.SimpleTab({
					el : 'main_tab',
					onActive : function(item, content, index) {
					},
					onFirstActive : function(item, content, index) {
						new IFrame({
							src : content.get('url'),
							frameborder : 0,
							styles : {
								width : content.getSize().x,
								height: content.getSize().y
							},
							events : {
								load : function() {
								}
							}
						}).inject(content);
					}
				});

			});
		});

		var getSelectedItems = function() {
			return parent.getSelectedItems();
		};

		var selectItem = function(si, select) {
			parent.selectItem(si, select);
		};

		function daochu() {//导出
			alert("导出成功!");
			return;
		}
		function dayinyulan() {//打印预览
			$ENV.dialog.open({
				url : "${ctx}/bmp/key-section/borderlayout/view/demo/GuoJiaJiBenNian_detail.jsp",
				width : 0.8,
				height : 0.9,
				title : '打印预览'
			});
		}
		function dayin() {//打印
			alert("打印");
		}
		</script>
		<style type="text/css">
			html, body{
				margin: 0;
				padding: 0;
			}
		</style>
	</head>
	<body>
		<div class="stBtnBar" style="margin-top:5px;">
			<a class="pop_button" href="##" onclick="daochu();"><span>导出</span></a>
			<a class="pop_button" href="##" onclick="dayinyulan();"><span>打印预览</span></a>
			<a class="pop_button" href="##" onclick="dayin();"><span>打印</span></a>
		</div>

		<div id="main_tab" class="panel tab_panel">
			<div id="main_tab_header" class="panel_header">
				<div class="tab_bar">
					<div class="panel_title">概览</div>
					<div class="panel_title">按机构类别统计</div>
					<div class="panel_title">按行政级别统计</div>
					<div class="panel_title">按编制人数统计</div>
				</div>
			</div>
			<div class="tab_panel_content">
				<div class="panel_content" >
					<iframe name="xx" width="100%" height="100%" src="/bmp/bmp/secrecy-organization/secrecy-workorgan/borderlayout/view/secrecyWorkOrgan_tj_sub_sub1_demo.jsp"></iframe>
				</div>
				<div class="panel_content" >
					<iframe name="yy" width="100%" height="100%" src="/bmp/bmp/secrecy-organization/secrecy-workorgan/borderlayout/view/secrecyWorkOrgan_tj_sub_sub2_demo.jsp"></iframe>
				</div>
				<div class="panel_content" >
						<iframe name="ZZ" width="100%" height="100%" src="/bmp/bmp/secrecy-organization/secrecy-workorgan/borderlayout/view/secrecyWorkOrgan_tj_sub_sub3_demo.jsp"></iframe>
				</div>
				<div class="panel_content panel_content_table">
						<iframe name="ee" width="100%" height="100%" src="/bmp/bmp/secrecy-organization/secrecy-workorgan/borderlayout/view/secrecyWorkOrgan_tj_sub_sub4_demo.jsp"></iframe>
				</div>
			</div>
		</div>
	</body>
</html>


<form id="form_1372994911609" action="${ctx}/platform/organization/organ/multy_select_all_district_main.action" method="GET">

</form>
<script type="text/javascript">
<!--
window.refresh = function() {
	document.getElementById('form_1372994911609').submit();
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



