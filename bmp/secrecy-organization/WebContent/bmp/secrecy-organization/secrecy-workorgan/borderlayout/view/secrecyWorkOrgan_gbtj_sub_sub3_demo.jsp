<%@ page language="java" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
		$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
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

		function doDetail(){
			$ENV.dialog.open({
				url : '/bmp/bmp/secrecy-organization/secrecy-workorgan/borderlayout/view/secrecyWorkOrgan_tj_sub_sub1_detail_demo.jsp?t_date=' + new Date().getTime(),
				width : 0.8,
				height : 0.9,
				title : '详情'
			});
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
		<div id="main_tab" class="panel tab_panel">
			<div id="main_tab_header" class="panel_header">
				<div class="tab_bar">
					<div class="panel_title">四川省</div>
					<div class="panel_title">省级机关</div>
				</div>
			</div>
			<div class="tab_panel_content">
				<div class="panel_content" >
				<div class="panel">
						<div class="panel_header">
							<div class="panel_titleListIco">
								全省
							</div>
							<div class="panel_btn_bar pop_button_bar">
								<!-- 右侧按钮 -->
							</div>
						</div>
						<div class="eXtremeTable" style="margin: 0px;">
						<table class="tableRegion" cellspacing="0" cellpadding="0" width="100%;">
							<thead>
								<tr>
									<td class="tableHeader fc" width="8%">名称</td>
									<td class="tableHeader fc" width="8%">哲学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">经济学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">法学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">教育学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">文学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">历史学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">理学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">工学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">农学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">医学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">军事学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">管理学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">其他<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">合计</td>
								</tr>
							</thead>
							<tbody class="tableBody" >
								<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='even'">
									<td class="fc">
										四川省
									</td>
									<td class="fc">
										1(1%)
									</td>
									<td class="fc">
										2(2%)
									</td>
									<td class="fc">
										3(3%)
									</td>
									<td class="fc">
										4(4%)
									</td>
									<td class="fc">
										5(5%)
									</td>
									<td class="fc">
										6(6%)
									</td>
									<td class="fc">
										7(7%)
									</td>
									<td class="fc">
										8(8%)
									</td>
									<td class="fc">
										9(9%)
									</td>
									<td class="fc">
										10(10%)
									</td>
									<td class="fc">
										20(20%)
									</td>
									<td class="fc">
										12(12%)
									</td>
									<td class="fc">
										13(13%)
									</td>
									<td class="fc">
										100
									</td>
								</tr>
							</tbody>
						</table>
						</div>
					</div>

<div class="split_line"></div>
					<div class="panel">
						<div class="panel_header">
							<div class="panel_titleListIco">
								下级行政区划
							</div>
							<div class="panel_btn_bar pop_button_bar">
								<!-- 右侧按钮 -->
							</div>
						</div>
						<div class="eXtremeTable" style="margin: 0px;">
							<table class="tableRegion" cellspacing="0" cellpadding="0" width="100%;">
							<thead>
								<tr>
									<td class="tableHeader fc" width="8%">行政区划名称</td>
									<td class="tableHeader fc" width="8%">哲学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">经济学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">法学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">教育学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">文学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">历史学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">理学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">工学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">农学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">医学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">军事学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">管理学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">其他<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">合计</td>
								</tr>
							</thead>
							<tbody class="tableBody" >
								<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='even'">
									<td class="fc">
										成都市
									</td>
									<td class="fc">
										1(1%)
									</td>
									<td class="fc">
										2(2%)
									</td>
									<td class="fc">
										3(3%)
									</td>
									<td class="fc">
										4(4%)
									</td>
									<td class="fc">
										5(5%)
									</td>
									<td class="fc">
										6(6%)
									</td>
									<td class="fc">
										7(7%)
									</td>
									<td class="fc">
										8(8%)
									</td>
									<td class="fc">
										9(9%)
									</td>
									<td class="fc">
										10(10%)
									</td>
									<td class="fc">
										20(20%)
									</td>
									<td class="fc">
										12(12%)
									</td>
									<td class="fc">
										13(13%)
									</td>
									<td class="fc">
										100
									</td>
								</tr><tr class="even" onmouseover="this.className='highlight'" onmouseout="this.className='even'">
									<td class="fc">
										绵阳市
									</td>
									<td class="fc">
										1(1%)
									</td>
									<td class="fc">
										2(2%)
									</td>
									<td class="fc">
										3(3%)
									</td>
									<td class="fc">
										4(4%)
									</td>
									<td class="fc">
										5(5%)
									</td>
									<td class="fc">
										6(6%)
									</td>
									<td class="fc">
										7(7%)
									</td>
									<td class="fc">
										8(8%)
									</td>
									<td class="fc">
										9(9%)
									</td>
									<td class="fc">
										10(10%)
									</td>
									<td class="fc">
										20(20%)
									</td>
									<td class="fc">
										12(12%)
									</td>
									<td class="fc">
										13(13%)
									</td>
									<td class="fc">
										100
									</td>
								</tr><tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='even'">
									<td class="fc">
										德阳市
									</td>
									<td class="fc">
										1(1%)
									</td>
									<td class="fc">
										2(2%)
									</td>
									<td class="fc">
										3(3%)
									</td>
									<td class="fc">
										4(4%)
									</td>
									<td class="fc">
										5(5%)
									</td>
									<td class="fc">
										6(6%)
									</td>
									<td class="fc">
										7(7%)
									</td>
									<td class="fc">
										8(8%)
									</td>
									<td class="fc">
										9(9%)
									</td>
									<td class="fc">
										10(10%)
									</td>
									<td class="fc">
										20(20%)
									</td>
									<td class="fc">
										12(12%)
									</td>
									<td class="fc">
										13(13%)
									</td>
									<td class="fc">
										100
									</td>
								</tr><tr class="even" onmouseover="this.className='highlight'" onmouseout="this.className='even'">
									<td class="fc">
										泸州市
									</td>
									<td class="fc">
										1(1%)
									</td>
									<td class="fc">
										2(2%)
									</td>
									<td class="fc">
										3(3%)
									</td>
									<td class="fc">
										4(4%)
									</td>
									<td class="fc">
										5(5%)
									</td>
									<td class="fc">
										6(6%)
									</td>
									<td class="fc">
										7(7%)
									</td>
									<td class="fc">
										8(8%)
									</td>
									<td class="fc">
										9(9%)
									</td>
									<td class="fc">
										10(10%)
									</td>
									<td class="fc">
										20(20%)
									</td>
									<td class="fc">
										12(12%)
									</td>
									<td class="fc">
										13(13%)
									</td>
									<td class="fc">
										100
									</td>
								</tr>
								<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='even'">
									<td class="fc">
										攀枝花市
									</td>
									<td class="fc">
										1(1%)
									</td>
									<td class="fc">
										2(2%)
									</td>
									<td class="fc">
										3(3%)
									</td>
									<td class="fc">
										4(4%)
									</td>
									<td class="fc">
										5(5%)
									</td>
									<td class="fc">
										6(6%)
									</td>
									<td class="fc">
										7(7%)
									</td>
									<td class="fc">
										8(8%)
									</td>
									<td class="fc">
										9(9%)
									</td>
									<td class="fc">
										10(10%)
									</td>
									<td class="fc">
										20(20%)
									</td>
									<td class="fc">
										12(12%)
									</td>
									<td class="fc">
										13(13%)
									</td>
									<td class="fc">
										100
									</td>
								</tr>
								<tr class="even" onmouseover="this.className='highlight'" onmouseout="this.className='even'">
									<td class="fc">
										合计
									</td>
									<td class="fc">
										5(1%)
									</td>
									<td class="fc">
										10(2%)
									</td>
									<td class="fc">
										15(3%)
									</td>
									<td class="fc">
										20(4%)
									</td>
									<td class="fc">
										25(5%)
									</td>
									<td class="fc">
										30(6%)
									</td>
									<td class="fc">
										35(7%)
									</td>
									<td class="fc">
										40(8%)
									</td>
									<td class="fc">
										45(9%)
									</td>
									<td class="fc">
										50(10%)
									</td>
									<td class="fc">
										100(20%)
									</td>
									<td class="fc">
										60(12%)
									</td>
									<td class="fc">
										65(13%)
									</td>
									<td class="fc">
										500
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					</div>
				</div>
			<div class="panel_content" >
				<div class="eXtremeTable" style="margin: 0px;">
						<table class="tableRegion" cellspacing="0" cellpadding="0" width="100%;">
							<thead>
								<tr>
									<td class="tableHeader fc" width="8%">单位名称</td>
									<td class="tableHeader fc" width="8%">哲学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">经济学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">法学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">教育学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">文学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">历史学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">理学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">工学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">农学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">医学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">军事学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">管理学<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">其他<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">合计</td>
								</tr>
							</thead>
							<tbody class="tableBody" >
								<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='even'">
									<td class="fc">
										四川省农业厅
									</td>
									<td class="fc">
										1(1%)
									</td>
									<td class="fc">
										2(2%)
									</td>
									<td class="fc">
										3(3%)
									</td>
									<td class="fc">
										4(4%)
									</td>
									<td class="fc">
										5(5%)
									</td>
									<td class="fc">
										6(6%)
									</td>
									<td class="fc">
										7(7%)
									</td>
									<td class="fc">
										8(8%)
									</td>
									<td class="fc">
										9(9%)
									</td>
									<td class="fc">
										10(10%)
									</td>
									<td class="fc">
										20(20%)
									</td>
									<td class="fc">
										12(12%)
									</td>
									<td class="fc">
										13(13%)
									</td>
									<td class="fc">
										100
									</td>
								</tr><tr class="even" onmouseover="this.className='highlight'" onmouseout="this.className='even'">
									<td class="fc">
										四川省林业厅
									</td>
									<td class="fc">
										1(1%)
									</td>
									<td class="fc">
										2(2%)
									</td>
									<td class="fc">
										3(3%)
									</td>
									<td class="fc">
										4(4%)
									</td>
									<td class="fc">
										5(5%)
									</td>
									<td class="fc">
										6(6%)
									</td>
									<td class="fc">
										7(7%)
									</td>
									<td class="fc">
										8(8%)
									</td>
									<td class="fc">
										9(9%)
									</td>
									<td class="fc">
										10(10%)
									</td>
									<td class="fc">
										20(20%)
									</td>
									<td class="fc">
										12(12%)
									</td>
									<td class="fc">
										13(13%)
									</td>
									<td class="fc">
										100
									</td>
								</tr><tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='even'">
									<td class="fc">
										四川省财政厅
									</td>
									<td class="fc">
										1(1%)
									</td>
									<td class="fc">
										2(2%)
									</td>
									<td class="fc">
										3(3%)
									</td>
									<td class="fc">
										4(4%)
									</td>
									<td class="fc">
										5(5%)
									</td>
									<td class="fc">
										6(6%)
									</td>
									<td class="fc">
										7(7%)
									</td>
									<td class="fc">
										8(8%)
									</td>
									<td class="fc">
										9(9%)
									</td>
									<td class="fc">
										10(10%)
									</td>
									<td class="fc">
										20(20%)
									</td>
									<td class="fc">
										12(12%)
									</td>
									<td class="fc">
										13(13%)
									</td>
									<td class="fc">
										100
									</td>
								</tr>
								<tr class="even" onmouseover="this.className='highlight'" onmouseout="this.className='even'">
									<td class="fc">
										四川省教育厅
									</td>
									<td class="fc">
										1(1%)
									</td>
									<td class="fc">
										2(2%)
									</td>
									<td class="fc">
										3(3%)
									</td>
									<td class="fc">
										4(4%)
									</td>
									<td class="fc">
										5(5%)
									</td>
									<td class="fc">
										6(6%)
									</td>
									<td class="fc">
										7(7%)
									</td>
									<td class="fc">
										8(8%)
									</td>
									<td class="fc">
										9(9%)
									</td>
									<td class="fc">
										10(10%)
									</td>
									<td class="fc">
										20(20%)
									</td>
									<td class="fc">
										12(12%)
									</td>
									<td class="fc">
										13(13%)
									</td>
									<td class="fc">
										100
									</td>
								</tr>
								<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='even'">
									<td class="fc">
										合计
									</td>
									<td class="fc">
										4(1%)
									</td>
									<td class="fc">
										8(2%)
									</td>
									<td class="fc">
										12(3%)
									</td>
									<td class="fc">
										16(4%)
									</td>
									<td class="fc">
										20(5%)
									</td>
									<td class="fc">
										24(6%)
									</td>
									<td class="fc">
										28(7%)
									</td>
									<td class="fc">
										32(8%)
									</td>
									<td class="fc">
										36(9%)
									</td>
									<td class="fc">
										40(10%)
									</td>
									<td class="fc">
										80(20%)
									</td>
									<td class="fc">
										48(12%)
									</td>
									<td class="fc">
										52(13%)
									</td>
									<td class="fc">
										400
									</td>
								</tr>
							</tbody>
						</table>
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



