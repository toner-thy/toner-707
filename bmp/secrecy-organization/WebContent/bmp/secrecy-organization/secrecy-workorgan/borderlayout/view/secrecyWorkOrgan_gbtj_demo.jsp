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
		<div class="stBtnBar" style="margin-top:15px;">
			<a class="pop_button" href="##" onclick="daochu();"><span>导出</span></a>
			<a class="pop_button" href="##" onclick="dayinyulan();"><span>打印预览</span></a>
			<a class="pop_button" href="##" onclick="dayin();"><span>打印</span></a>
		</div>
		<div id="main_tab" class="panel tab_panel">
			<div id="main_tab_header" class="panel_header">
				<div class="tab_bar">
					<div class="panel_title">保密干部统计</div>
					<!-- <div class="panel_title">按专业统计</div>
					<div class="panel_title">按行政级别统计</div>
					<div class="panel_title">按技术职称统计</div>
					<div class="panel_title">按是否专职统计</div> -->
				</div>
			</div>
			<div class="tab_panel_content">
				<div class="panel_content panel_content_table">
					<div class="panel">
						<!-- 头部 -->
						<div class="panel_header">
							<!-- 标题 -->
							<div class="panel_title panel_titleListIco">按学历统计</div>
							<!-- 右侧按钮 -->
							<div class="panel_btn_bar pop_button_bar">
							</div>
						</div>
						<div class="eXtremeTable" style="margin: 0px;">
								<table class="tableRegion" cellspacing="0" cellpadding="0" width="100%;">
									<thead>
										<tr>
											<td class="tableHeader fc" width="15%">单位名称</td>
											<td class="tableHeader fc" width="15%">博士研究生<br/>(百分比)</td>
											<td class="tableHeader fc" width="15%">硕士研究生<br/>(百分比)</td>
											<td class="tableHeader fc" width="15%">大学<br/>(百分比)</td>
											<td class="tableHeader fc" width="15%">大专以下<br/>(百分比)</td>
											<td class="tableHeader fc" width="10%">合计</td>
										</tr>
									</thead>
									<tbody class="tableBody" >
											<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='even'">
												<td class="fc">
													本单位名称
												</td>
												<td class="fc">
													10(10%)
												</td>
												<td class="fc">
													40(40%)
												</td>
												<td class="fc">
													20(20%)
												</td>
												<td class="fc">
													30(30%)
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
				<div class="">
					<div class="panel_header">
						<div class="panel_titleListIco">
							按专业统计
						</div>
						<div class="panel_btn_bar pop_button_bar">
							<!-- 右侧按钮 -->
						</div>
					</div>
					<div class="eXtremeTable" style="margin: 0px;">
						<table class="tableRegion" cellspacing="0" cellpadding="0" width="100%;">
							<thead>
								<tr>
									<td class="tableHeader fc" width="10%">单位名称</td>
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
										本单位名称
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
				<div class="" >
					<div class="panel_header">
						<div class="panel_titleListIco">
							按行政级别统计
						</div>
						<div class="panel_btn_bar pop_button_bar">
							<!-- 右侧按钮 -->
						</div>
					</div>
					<div class="eXtremeTable" style="margin: 0px;">
						<table class="tableRegion" cellspacing="0" cellpadding="0" width="100%;">
							<thead>
								<tr>
									<td class="tableHeader fc" width="10%">单位名称</td>
									<td class="tableHeader fc" width="8%">国级<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">副国级<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">正省部级<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">副省部级<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">正厅局级<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">副厅局级<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">正县处级<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">副县处级<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">正乡科级<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">副乡科级<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">科员<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">其他<br/>(百分比)</td>
									<td class="tableHeader fc" width="8%">合计</td>
								</tr>
							</thead>
							<tbody class="tableBody" >
								<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='even'">
									<td class="fc">
										本单位名称
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
										15(15%)
									</td>
									<td class="fc">
										16(16%)
									</td>
									<td class="fc">
										9(9%)
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
										11(11%)
									</td>
									<td class="fc">
										12(12%)
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
				<div class="">
					<div class="panel_header">
						<div class="panel_titleListIco">
							按技术职称统计
						</div>
						<div class="panel_btn_bar pop_button_bar">
							<!-- 右侧按钮 -->
						</div>
					</div>
					<div class="eXtremeTable" style="margin: 0px;">
						<table class="tableRegion" cellspacing="0" cellpadding="0" width="100%;">
							<thead>
								<tr>
									<td class="tableHeader fc" width="25%">单位名称</td>
									<td class="tableHeader fc" width="15%">高级<br/>(百分比)</td>
									<td class="tableHeader fc" width="15%">中级<br/>(百分比)</td>
									<td class="tableHeader fc" width="15%">初级<br/>(百分比)</td>
									<td class="tableHeader fc" width="15%">其他<br/>(百分比)</td>
									<td class="tableHeader fc" width="15%">合计</td>
								</tr>
							</thead>
							<tbody class="tableBody" >
								<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='even'">
									<td class="fc">
										本单位名称
									</td>
									<td class="fc">
										25(25%)
									</td>
									<td class="fc">
										15(15%)
									</td>
									<td class="fc">
										25(25%)
									</td>
									<td class="fc">
										35(35%)
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
				<div class="">
					<div class="panel_header">
						<div class="panel_titleListIco">
							按是否专职统计
						</div>
						<div class="panel_btn_bar pop_button_bar">
							<!-- 右侧按钮 -->
						</div>
					</div>
					<div class="eXtremeTable" style="margin: 0px;">
						<table class="tableRegion" cellspacing="0" cellpadding="0" width="100%;">
							<thead>
								<tr>
									<td class="tableHeader fc" width="25%">单位名称</td>
									<td class="tableHeader fc" width="15%">专职<br/>(百分比)</td>
									<td class="tableHeader fc" width="15%">非专职<br/>(百分比)</td>
									<td class="tableHeader fc" width="15%">合计</td>
								</tr>
							</thead>
							<tbody class="tableBody" >
								<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='even'">
									<td class="fc">
										本单位名称
									</td>
									<td class="fc">
										15(30%)
									</td>
									<td class="fc">
										35(70%)
									</td>
									<td class="fc">
										50
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
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



