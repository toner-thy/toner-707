<%@ page language="java" pageEncoding="utf-8"%>














<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>详情</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<style type="text/css" media="print">
			.no_print{display:none;}
		</style>

		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/css/public/print/printPage.css");

			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/resources/css/component.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/component_blue.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/theme/borderlayout/skin/blue/css/ecTable.css");


			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/template/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
				$ENV.onDomReady(function(){

				});
			});

			function preview(oper){
				if (oper < 10)
					{
					bdhtml=window.document.body.innerHTML;//获取当前页的html代码
					sprnstr="<!--startprint"+oper+"-->";//设置打印开始区域
					eprnstr="<!--endprint"+oper+"-->";//设置打印结束区域

					prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18); //从开始代码向后取html

					prnhtmlprnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));//从结束代码向前取html

					window.document.body.innerHTML=prnhtmlprnhtml;
					window.print();
					window.document.body.innerHTML=bdhtml;
				} else {
					window.print();
				}
			}
		</script>

	</head>

	<body style="overflow: auto;overflow-x: hidden; ">
		<!-- 公共头部 -->
		<div class="button_bar no_print">
			<div class="left no_print">
				<div class="pop_button_bar no_print">
					<a class="pop_button" href="###" onclick="javascript:preview(1);"><span>打 印</span></a>
				</div>
			</div>
			<div class="right no_print">
				<div class="pop_button_bar no_print">
					<a class="pop_button pop_button_close" href="###" onclick="javascript:environment.dialog.close();"><span>退出本页</span></a>
				</div>
			</div>
		</div>


		<!-- 保密要害部门panel 及 打印标签开始 -->
		<!--startprint1-->
			<!-- 这个CSS在IE6下影响了样式，导致滚动条没有出来 -->
<!-- 		<div class="printCss"> -->
		<div>
			<div class="panel tMargin" style="margin-top: -1px;">
				<div class="panel_header no_print">
					<div class="panel_title panel_titleListIco no_print">
						详情
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<div>
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
								<tr class="even" onmouseover="this.className='highlight'" onmouseout="this.className='even'">
									<td class="fc">
									秘密
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
										机密
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
										绝密
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

								<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='even'">
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
				</div>
			</div>


	</body>
</html>


<form id="form_1373332451890" action="/bmp/bmp/keySection/keySection_detail.action" method="GET">

<input type="hidden" name="keySection.keySectionId" value="402881a23f3c1dfa013f3c20badf0011"/>

</form>
<script type="text/javascript">
<!--
window.refresh = function() {
	document.getElementById('form_1373332451890').submit();
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



