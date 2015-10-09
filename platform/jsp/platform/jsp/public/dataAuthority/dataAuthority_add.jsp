<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>数据权限维护新增</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/FCKeditor/fckeditor.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js",function(){
				$ENV.onDomReady(function(){
					if (needReload) {
						if (!confirm('新增成功，是否继续添加?')){
							needReload2 = true;
							TabUtil.closeTab();
						}
					};
					formcheck = new FormCheck('add_form',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
				});
			});


			var needReload = ${needReload};
			var needReload2 = false;

			function doSave(){
				if (formcheck.isFormValid(true)) {
					$('sub').click();
					$('sbm_button').style.display='none';
					$('sbm_button_hidden').style.display='';
					window.setTimeout("$('sbm_button').style.display=''",8000);
					window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);

				}
			 }

			function doBack2(){
				action='${ctx}/bmp/dataAuthority/dataAuthority_query_list_list.action';
				window.location.href=action+"?dataAuthority.dataId=${dataAuthority.dataId}";
			}

		</script>
</head>

<body>
	<!-- 公共头部 -->
	<div class="button_bar">
		<div class="left">
			<div class="pop_button_bar">
				<a class="pop_button" href="javascript:void();"
					id="sbm_button_hidden" style="display: none;"><span>保存中...</span></a>
				<a class="pop_button" href="javascript:doSave();" id="sbm_button"><span>保
						存</span></a> <a class="pop_button" href="javascript:doBack2();"><span>返回列表</span></a>
			</div>
		</div>
		<div class="right">
			<div class="pop_button_bar">
				<a class="pop_button pop_button_refresh" href="#"
					onclick="javascript:window.refresh();"><span>刷新本页</span></a> <a
					class="pop_button pop_button_close" href="#"
					onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
			</div>
		</div>
	</div>

	<div id="body_content" class="body_content" style="width: 99%;">

	        <!-- 复合面板开始 -->
			<cp:start defaultTitle="数据权限维护简介" ctx="${ctx}" icoPath="">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','数据权限维护简介');">关 于</div>
			</cp:start>
			<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于数据权限维护
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_discloseSecrecy"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>

			</cp:msg>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->
		<!-- 内容panel开始 -->
		<div class="panel tMargin" style="line-height: 500px">
			<div class="panel_header">
				<div class="panel_title panel_titleListIco">${dataAuthority.name}数据维护新增人员</div>
				<div class="panel_btn_bar pop_button_bar">
					<!-- 右侧按钮 -->
				</div>
			</div>
			<div class="panel_content panel_content_table" >

				<form id="add_form"
					action="<s:url namespace='/bmp/dataAuthority' action='dataAuthority_adding.action' includeParams='true'/>"
					method="post" enctype="multipart/form-data">
					<table class="content_table">
						<tr>
							<td class="tbLable fr"><div style="padding-bottom:25px">人员名称：</div></td>
							<td class="tbValue fl" >
							 <div style="padding-bottom:25px">
							 <ui:multySelect textEl="readPersonNames" required="true"   valueEl="readPersonIds"  buttonEl="readPerson" ></ui:multySelect>
							 <input type="hidden" name="dataAuthority.dataId" value="${dataAuthority.dataId}"/>
							 </div>
							</td>
						</tr>
					</table>

					<!-- 隐藏提交按钮 -->
					<div align="center">
						<input id="sub" value="sub" type="submit" style="display: none;" />
					</div>
				</form>
			</div>
		</div>
		<!-- 内容panel结束 -->
	</div>

</body>
</html>