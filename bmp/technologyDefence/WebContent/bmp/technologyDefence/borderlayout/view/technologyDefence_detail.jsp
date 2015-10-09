











<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>常见问题问答</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="/bmp/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<script src="/bmp/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("/bmp/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("/bmp/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadStyleSheet("/bmp/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("/bmp/platform/template/borderlayout/skin/blue/css/ecTable.css");

			$ENV.loader.loadJavaScript("/bmp/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("/bmp/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("/bmp/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("/bmp/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("/bmp/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("/bmp/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("/bmp/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("/bmp/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("/bmp/resources/FCKeditor/fckeditor.js");
			$ENV.loader.loadJavaScript("/bmp/resources/js/formcheck/1.4/formcheck.js",function(){
				$ENV.onDomReady(function(){
					formcheck = new FormCheck('add_form',{
						display:{
							showErrors:1,errorsLocation: 3
						},
						trimValue: true
					});
				});
			});

		</script>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="javascript:void();" id="sbm_button_hidden" style="display:none;"><span>保存中...</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:window.close();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div id="body_content" class="body_content">

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						技术防范详情
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="add_form" action="/bmp/bmp/sysQuestion/sysQuestion_editing.action" method="post" enctype="multipart/form-data">
						<table class="content_table" width="100%">
							<tr>
								<td class="tbLable fr">
									问题类型：
								</td>
								<td class="tbValue fl">
									物理安全
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									创建人：
								</td>
								<td class="tbValue fl">
									总系统管理员
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									问题内容：
								</td>
								<td class="tbValue fl">
									<pre><p>是否安装门、窗、柜、锁</p></pre>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									答案内容：
								</td>
								<td class="tbValue fl">
									<pre><p>是</p></pre>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>

	</body>
</html>


<form id="form_1403489516328" action="/bmp/bmp/sysQuestion/sysQuestion_detail.action" method="GET">

<input type="hidden" name="sysQuestion.questionId" value="ca82cae646b6ea110146b71db9d40000"/>

</form>
<script type="text/javascript">
<!--
window.refresh = function() {
	document.getElementById('form_1403489516328').submit();
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



