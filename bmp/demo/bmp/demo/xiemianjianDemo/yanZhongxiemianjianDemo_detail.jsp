<%@ page language="java" pageEncoding="utf-8"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>严重违规案件详情</title>

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

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
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
						严重违规案件详情
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<table class="content_table st" cellspacing="0" cellpadding="0" width="100%;">
						   <tr>
								<td class="tbLable fr">
									案件名称：
								</td>
								<td class="tbValue fl">
									XXXX泄露案件
								</td>
								<td class="tbLable fr">
									查处结果：
								</td>
								<td class="tbValue fl">
									已查结
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									密级：
								</td>
								<td class="tbValue fl">
									秘密
								</td>
								<td class="tbLable fr">
									发案形式：
								</td>
								<td class="tbValue fl">
									非法获取、持有国家秘密载体泄密
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									责任单位性质：
								</td>
								<td class="tbValue fl">
									非武器装备科研生产企业

								<td class="tbLable fr">
									部门名称：
								</td>
								<td class="tbValue fl">
									信息中心
								</td>
							</tr>
					</table>
					<!--endprint1-->
					<!-- 打印标签 -->
				</div>
			</div>

			<div class="panel">
				<script type="text/javascript">
					$ENV.loader.loadStyleSheet("${ctx}/platform/theme/borderlayout/skin/blue/css/page.css");
					$ENV.loader.loadStyleSheet("${ctx}/platform/css/public/print/printPage.css");
					$ENV.loader.loadStyleSheet("${ctx}/platform/theme/borderlayout/skin/blue/css/ecTable.css");
					$ENV.loader.loadStyleSheet("/bmp/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
					$ENV.loader.loadStyleSheet("${ctx}/platform/css/public/table/complexTbSustain.css");

					$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
					$ENV.loader.loadJavaScript("${ctx}/platform/template/borderlayout/resources/js/ectable/EcTable.js");
					$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
					$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
					$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js",function(){
						$ENV.onDomReady(function(){
							//$('panel_content_keyPart').load('/bmp/bmp/part/nestedpart_list.action?t_date='+ $time() + '&departmentId=402881a23f3c1dfa013f3c20bacf000f');
						});
					});

					// 详 情
					function doDetail(id){
						$ENV.dialog.open({
							url : '/bmp/bmp/part/part_detail.action?part.partId='+id+'&t_date=' + new Date().getTime(),
							width : window.top.getSize().x * 0.8,
							height : window.top.getSize().y * 0.9,
							title : '要害部位详情'
						});
					}

				</script>
				<div class="panel tMargin">
					<div class="panel_header no_print" style="position:fixed;">
						<div class="panel_title panel_titleListIco no_print">
							严重违规案件详情处理责任人
						</div>
						<div class="pop_button_bar" style="float:right;">
						</div>
					</div>
					<div id="panel_content_keyPart" class="panel_content">
							<div class="eXtremeTable" >
								<table id="keySectionlist_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
									<thead>
									<tr>
										<td class="tableHeader"  width="5%" ><input type="checkbox"  name="keySectionId_checkbox"  id="keySectionId_checkbox"  class="class_keySectionId_checkbox"  onclick="EcTable.checkAll('keySectionId_checkbox')" /></td>
										<td class="tableHeader"  width="10%" >姓名</td>
										<td class="tableHeader"  width="10%" >行政级别</td>
										<td class="tableHeader"  width="10%" >处理形式</td>
										<td class="tableHeader"  width="10%" >政治面貌</td>
										<td class="tableHeader"  width="10%" >部门名称</td>
									</tr>
									</thead>
									<tbody class="tableBody" >
									<tr class="odd"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'" >
										<td width="5%" ><input type="checkbox"  name="keySectionId"  value="8d3c8470d2284779a4424e3d29ec9409"  class="row_checkbox " /></td>
										<td width="10%" >李某</td>
										<td width="10%" >正乡科级</td>
										<td width="10%" >行政、党纪处理</td>
										<td width="10%" >中共党员</td>
										<td width="10%" >信息中心</td>
									</tr>
									</tbody>
								</table>
							</div>
					</div>
				</div>

			</div>
			<div class="panel">
				<script type="text/javascript">
					$ENV.loader.loadStyleSheet("${ctx}/platform/theme/borderlayout/skin/blue/css/page.css");
					$ENV.loader.loadStyleSheet("${ctx}/platform/css/public/print/printPage.css");
					$ENV.loader.loadStyleSheet("${ctx}/platform/theme/borderlayout/skin/blue/css/ecTable.css");
					$ENV.loader.loadStyleSheet("/bmp/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
					$ENV.loader.loadStyleSheet("${ctx}/platform/css/public/table/complexTbSustain.css");

					$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
					$ENV.loader.loadJavaScript("${ctx}/platform/template/borderlayout/resources/js/ectable/EcTable.js");
					$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
					$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
					$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js",function(){
						$ENV.onDomReady(function(){

							$('panel_content_secperson').load('/bmp/bmp/secrecyperson/nested/secrecyPersonList_list.action?t_date='+ $time() + '&departmentId=402881a23f3c1dfa013f3c20bacf000f');
						});
					});

					// 详 情
					function doDetailSecPerson(id){
						$ENV.dialog.open({
							url : '/bmp/bmp/secrecyperson/keySectionSecrecyPerson_detail.action?secrecyPerson.secrecyPersonId='+id+'&t_date=' + new Date().getTime(),
							width : window.top.getSize().x * 0.8,
							height : window.top.getSize().y * 0.9,
							title : '涉密人员详情'
						});
					}

				</script>
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



