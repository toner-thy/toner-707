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

		$ENV.loader.loadStyleSheet("${ctx}/platform/theme/borderlayout/skin/blue/css/page.css");
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

		function showWin(){
			$ENV.dialog.open({
				url : "/bmp/bmp/demo/secrecyMessageSystemStatistics/statistics_detail.jsp?_ts="+new Date().getTime(),
				width : 0.8,
				height : 0.9,
				title : '详情'
			});
		}
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
					<div class="panel_title">涉密信息系统统计</div>
					<!-- <div class="panel_title">按行政级别统计</div>
					<div class="panel_title">按编制人数统计</div> -->
				</div>
			</div>
			<div class="tab_panel_content">
				<div class="panel_content" style="overflow: auto;">
					<div>
						<div class="panel_header">
							<div class="panel_title panel_titleListIco">
								按密级统计
							</div>
							<div class="panel_btn_bar pop_button_bar">
								<!-- 右侧按钮 -->
							</div>
						</div>

						<div class="eXtremeTable" style="margin: 0px;">
							<table class="tableRegion" cellspacing="0" cellpadding="0" width="100%;">
								<thead>
									<tr>
										<td class="tableHeader fc" width="15%">&nbsp;</td>
										<td class="tableHeader fc" width="12%">秘密(百分比)</td>
										<td class="tableHeader fc" width="12%">机密(百分比)</td>
										<td class="tableHeader fc" width="12%">绝密(百分比)</td>
										<td class="tableHeader fc" width="10%">合计</td>
									</tr>
								</thead>
							<tbody class="tableBody" >
								<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
									<td class="fc">
										涉密计算机
									</td>
									<td class="fc">
										30（30%）
									</td>
									<td class="fc">
										20（20%）
									</td>
									<td class="fc">
										50（50%）
									</td>
									<td class="fc">
										100
									</td>
								</tr>
								<tr class="even" onmouseover="this.className='highlight'" onmouseout="this.className='even'">
									<td class="fc">
										涉密移动存储介质
									</td>
									<td class="fc">
										3（30%）
									</td>
									<td class="fc">
										6（60%）
									</td>
									<td class="fc">
										1（10%）
									</td>
									<td class="fc">
										10
									</td>
								</tr>
								<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
									<td class="fc">
										其它涉密设备
									</td>
									<td class="fc">
										4（40%）
									</td>
									<td class="fc">
										6（60%）
									</td>
									<td class="fc">
										0（0%）
									</td>
									<td class="fc">
										10
									</td>
								</tr>
								<tr class="even" onmouseover="this.className='highlight'" onmouseout="this.className='even'">
									<td class="fc">
										合计
									</td>
									<td class="fc">
										37（30.8%）
									</td>
									<td class="fc">
										32（26.7%）
									</td>
									<td class="fc">
										51（42.5%）
									</td>
									<td class="fc">
										120
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>

<div class="split_line"></div>
				<div >
					<div class="panel_header">
						<div class="panel_title panel_titleListIco">
							按接入网络情况统计
						</div>
						<div class="panel_btn_bar pop_button_bar">
							<!-- 右侧按钮 -->
						</div>
					</div>
					<div class="eXtremeTable" style="margin: 0px;">
						<table class="tableRegion" cellspacing="0" cellpadding="0" width="100%;">
							<thead>
								<tr>
									<td class="tableHeader fc" width="5%">&nbsp;</td>
									<td class="tableHeader fc" width="15%">接入网络(百分比)</td>
									<td class="tableHeader fc" width="15%">未接入网络(百分比)</td>
									<td class="tableHeader fc" width="20%">合计</td>
								</tr>
							</thead>
							<tbody class="tableBody" >
									<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
										<td class="fc">
											涉密计算机
										</td>
										<td class="fc">
											10(20%)
										</td>
										<td class="fc">
											40(80%)
										</td>
										<td class="fc">
											50
										</td>
									</tr>
							</tbody>
						</table>
					</div>
				</div>
				<!-- <div >
					<div class="panel_header">
						<div class="panel_titleListIco">
							按密级及接入网络情况统计
						</div>
						<div class="panel_btn_bar pop_button_bar">
							右侧按钮
						</div>
					</div>
					<div class="eXtremeTable" style="margin: 0px;">
						<table class="tableRegion" cellspacing="0" cellpadding="0" width="100%;">
							<thead>
								<tr>
									<td class="tableHeader fc" width="15%" rowspan="2">&nbsp;</td>
									<td class="tableHeader fc" width="12%" colspan="2">秘密</td>
									<td class="tableHeader fc" width="12%" colspan="2">机密</td>
									<td class="tableHeader fc" width="12%" colspan="2">绝密</td>
									<td class="tableHeader fc" width="10%" rowspan="2">合计</td>
								</tr>
								<tr>
									<td class="tableHeader fc">
										已接入(百分比)
									</td>
									<td class="tableHeader fc">
										未接入(百分比)
									</td>
									<td class="tableHeader fc">
										已接入(百分比)
									</td>
									<td class="tableHeader fc">
										未接入(百分比)
									</td>
									<td class="tableHeader fc">
										已接入(百分比)
									</td>
									<td class="tableHeader fc">
										未接入(百分比)
									</td>
								</tr>
							</thead>
							<tbody class="tableBody" >
								<tr class="even" onmouseover="this.className='highlight'" onmouseout="this.className='even'">
									<td class="fc">
										涉密计算机
									</td>
									<td class="fc">
										10(10%)
									</td>
									<td class="fc">
										20(20%)
									</td>
									<td class="fc">
										30(30%)
									</td>
									<td class="fc">
										20(20%)
									</td>
									<td class="fc">
										15(15%)
									</td>
									<td class="fc">
										5(5%)
									</td>
									<td class="fc">
										100
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div> -->

<div class="split_line"></div>
				<div >
					<div class="panel_header">
						<div class="panel_title panel_titleListIco">
							按接入网络情况及密级统计
						</div>
						<div class="panel_btn_bar pop_button_bar">
							<!-- 右侧按钮 -->
						</div>
					</div>
					<div class="eXtremeTable" style="margin: 0px;">
						<table class="tableRegion" cellspacing="0" cellpadding="0" width="100%;">
							<thead>
								<tr>
									<td class="tableHeader fc" width="5%" >&nbsp;</td>
									<td class="tableHeader fc" width="15%" >接入网路(百分比)</td>
									<td class="tableHeader fc" width="15%" >未接入网路(百分比)</td>
									<td class="tableHeader fc" width="20%"  >合计</td>
								</tr>
							</thead>
							<tbody class="tableBody" >
								<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
									<td class="tableHeader fc">
										秘密
									</td>
									<td class="fc">
										20(40%)
									</td>
									<td class="fc">
										30(60%)
									</td>
									<td class="fc">
										50
									</td>
								</tr>
								<tr class="even" onmouseover="this.className='highlight'" onmouseout="this.className='even'">
									<td class="tableHeader fc">
										机密
									</td>
									<td class="fc">
										10(20%)
									</td>
									<td class="fc">
										40(80%)
									</td>
									<td class="fc">
										50
									</td>
								</tr>
								<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
									<td class="tableHeader fc">
										绝密
									</td>
									<td class="fc">
										40(80%)
									</td>
									<td class="fc">
										10(20%)
									</td>
									<td class="fc">
										50
									</td>
								</tr>
								<tr class="even" onmouseover="this.className='highlight'" onmouseout="this.className='even'">
									<td class="tableHeader fc">
										合计
									</td>
									<td class="fc">
										70(46.7%)
									</td>
									<td class="fc">
										80(53.3%)
									</td>
									<td class="fc">
										150
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>

<div class="split_line"></div>
				<div>
						<div class="panel_header">
							<div class="panel_title panel_titleListIco">
								按要害部门、要害部位统计
							</div>
							<div class="panel_btn_bar pop_button_bar">
								<!-- 右侧按钮 -->
							</div>
						</div>

						<div class="eXtremeTable" style="margin: 0px;">
							<table class="tableRegion" cellspacing="0" cellpadding="0" width="100%;">
								<thead>
									<tr>
										<td class="tableHeader fc" width="15%">&nbsp;</td>
										<td class="tableHeader fc" width="12%">要害部门(百分比)</td>
										<td class="tableHeader fc" width="12%">要害部位(百分比)</td>
										<td class="tableHeader fc" width="12%">其他(百分比)</td>
										<td class="tableHeader fc" width="10%">合计</td>
									</tr>
								</thead>
							<tbody class="tableBody" >
								<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
									<td class="fc">
										涉密计算机
									</td>
									<td class="fc">
										30（30%）
									</td>
									<td class="fc">
										20（20%）
									</td>
									<td class="fc">
										50（50%）
									</td>
									<td class="fc">
										100
									</td>
								</tr>
								<tr class="even" onmouseover="this.className='highlight'" onmouseout="this.className='even'">
									<td class="fc">
										涉密移动存储介质
									</td>
									<td class="fc">
										3（30%）
									</td>
									<td class="fc">
										6（60%）
									</td>
									<td class="fc">
										1（10%）
									</td>
									<td class="fc">
										10
									</td>
								</tr>
								<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
									<td class="fc">
										其它涉密设备
									</td>
									<td class="fc">
										4（40%）
									</td>
									<td class="fc">
										6（60%）
									</td>
									<td class="fc">
										0（0%）
									</td>
									<td class="fc">
										10
									</td>
								</tr>
								<tr class="even" onmouseover="this.className='highlight'" onmouseout="this.className='even'">
									<td class="fc">
										合计
									</td>
									<td class="fc">
										37（30.8%）
									</td>
									<td class="fc">
										32（26.7%）
									</td>
									<td class="fc">
										51（42.5%）
									</td>
									<td class="fc">
										120
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>

<div class="split_line"></div>
				<div>
						<div class="panel_header">
							<div class="panel_title panel_titleListIco">
								按设备类型统计接入网络
							</div>
							<div class="panel_btn_bar pop_button_bar">
								<!-- 右侧按钮 -->
							</div>
						</div>

						<div class="eXtremeTable" style="margin: 0px;">
							<table class="tableRegion" cellspacing="0" cellpadding="0" width="100%;">
								<thead>
									<tr>
										<td class="tableHeader fc" width="15%">&nbsp;</td>
										<td class="tableHeader fc" width="12%">设备A(百分比)</td>
										<td class="tableHeader fc" width="12%">设备B(百分比)</td>
										<td class="tableHeader fc" width="10%">合计</td>
									</tr>
								</thead>
							<tbody class="tableBody" >
								<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
									<td class="fc">
										<a href="###" onclick="showWin();">涉密网络A</a>
									</td>
									<td class="fc">
										30（60%）
									</td>
									<td class="fc">
										20（40%）
									</td>
									<td class="fc">
										50
									</td>
								</tr>
								<tr class="even" onmouseover="this.className='highlight'" onmouseout="this.className='even'">
									<td class="fc">
										<a href="###" onclick="showWin();">涉密网络B</a>
									</td>
									<td class="fc">
										30（60%）
									</td>
									<td class="fc">
										20（40%）
									</td>
									<td class="fc">
										50
									</td>
								</tr>
								<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
									<td class="fc">
										<a href="###" onclick="showWin();">涉密网络C</a>
									</td>
									<td class="fc">
										30（60%）
									</td>
									<td class="fc">
										20（40%）
									</td>
									<td class="fc">
										50
									</td>
								</tr>

								<tr class="even" onmouseover="this.className='highlight'" onmouseout="this.className='even'">
									<td class="fc">
										合计
									</td>
									<td class="fc">
										90（30%）
									</td>
									<td class="fc">
										60（20%）
									</td>
									<td class="fc">
										150
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>


<div class="split_line"></div>
				<div>
						<div class="panel_header">
							<div class="panel_title panel_titleListIco">
								按密级统计接入网络
							</div>
							<div class="panel_btn_bar pop_button_bar">
								<!-- 右侧按钮 -->
							</div>
						</div>

						<div class="eXtremeTable" style="margin: 0px;">
							<table class="tableRegion" cellspacing="0" cellpadding="0" width="100%;">
								<thead>
									<tr>
										<td class="tableHeader fc" width="15%">&nbsp;</td>
										<td class="tableHeader fc" width="12%">秘密(百分比)</td>
										<td class="tableHeader fc" width="12%">机密(百分比)</td>
										<td class="tableHeader fc" width="12%">绝密(百分比)</td>
										<td class="tableHeader fc" width="10%">合计</td>
									</tr>
								</thead>
							<tbody class="tableBody" >
								<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
									<td class="fc">
										涉密网络A
									</td>
									<td class="fc">
										30（30%）
									</td>
									<td class="fc">
										20（20%）
									</td>
									<td class="fc">
										50（50%）
									</td>
									<td class="fc">
										100
									</td>
								</tr>
								<tr class="even" onmouseover="this.className='highlight'" onmouseout="this.className='even'">
									<td class="fc">
										涉密网络B
									</td>
									<td class="fc">
										3（30%）
									</td>
									<td class="fc">
										6（60%）
									</td>
									<td class="fc">
										1（10%）
									</td>
									<td class="fc">
										10
									</td>
								</tr>
								<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
									<td class="fc">
										涉密网络C
									</td>
									<td class="fc">
										4（40%）
									</td>
									<td class="fc">
										6（60%）
									</td>
									<td class="fc">
										0（0%）
									</td>
									<td class="fc">
										10
									</td>
								</tr>
								<tr class="even" onmouseover="this.className='highlight'" onmouseout="this.className='even'">
									<td class="fc">
										合计
									</td>
									<td class="fc">
										37（30.8%）
									</td>
									<td class="fc">
										32（26.7%）
									</td>
									<td class="fc">
										51（42.5%）
									</td>
									<td class="fc">
										120
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



