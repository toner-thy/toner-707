<%@ page language="java" pageEncoding="utf-8"%>














<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>保密要害部门详情</title>

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
										<td class="tableHeader"  width="10%" >名称</td>
										<td class="tableHeader"  width="10%" >涉密等级</td>
										<td class="tableHeader"  width="10%" >负责人</td>
									</tr>
									</thead>
									<tbody class="tableBody" >
									<tr class="odd"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'" >
										<td width="5%" ><input type="checkbox"  name="keySectionId"  value="8d3c8470d2284779a4424e3d29ec9409"  class="row_checkbox " /></td>
										<td width="10%" >要害XX</td>
										<td width="10%" >一般</td>
										<td width="10%" >王某</td>
									</tr>
									<tr class="even"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'" >
										<td width="5%" ><input type="checkbox"  name="keySectionId"  value="8d3c8470d2284779a4424e3d29ec9409"  class="row_checkbox " /></td>
										<td width="10%" >要害XX1</td>
										<td width="10%" >一般</td>
										<td width="10%" >李某</td>
									</tr>
									<tr class="odd"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'" >
										<td width="5%" ><input type="checkbox"  name="keySectionId"  value="8d3c8470d2284779a4424e3d29ec9409"  class="row_checkbox " /></td>
										<td width="10%" >要害XX2</td>
										<td width="10%" >一般</td>
										<td width="10%" >张某</td>
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



