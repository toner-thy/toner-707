<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>编辑装备配备类别</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>

		<s:actionmessage theme="messages"/>

		<script language="javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
				$ENV.onDomReady(function(){

				});
			});
			var needReload = ${needReload};
			var needReload2 = false;
			window.addEvent('domready', function(){
				if (needReload) {
					if (!confirm('是否继续编辑！')){
						needReload2 = true;
						TabUtil.closeTab();
					}
				};
				formcheck = new FormCheck('form_equipmentType_edit',{
					display:{
						showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
					},
					trimValue: true
				});
			});

			function doSave(){
				if (formcheck.isFormValid(true)) {
					$('sub').click();
					$('sbm_button').style.display='none';
					$('sbm_button_hidden').style.display='';
					window.setTimeout("$('sbm_button').style.display=''",8000);
					window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
				 }
			}
			function doBack(){
			 	TabUtil.closeTab();
			}
		</script>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="javascript:void();" id="sbm_button_hidden" style="display:none;"><span>保存中...</span></a>
					<a class="pop_button" href="javascript:doSave();" id="sbm_button"><span>保 存</span></a>
					<a class="pop_button" href="javascript:doBack();"><span>返回列表</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:TabUtil.refreshTab();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div class="body_content">

			<!-- 复合面板开始 -->
			<cp:start defaultTitle="装备配备类别简介" ctx="${ctx}" icoPath="${ctx}/equipment/equipmenttype/borderlayout/skin/blue/img/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','装备配备类别简介');">简 介</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于装备配备类别管理
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_equipment_info"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
				</cp:msg>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始-->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						编辑装备配备类别
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="form_equipmentType_edit" class="form" action="<s:url action='equipmentType_update' includeParams='true'/>" method="post">
						<!-- 隐藏域 -->
						<input name="equipmentType.equipmentTypeId" type="hidden" value="${equipmentType.equipmentTypeId }" />

						<table class="content_table">
							<tr>
								<td class="tbLable fr">装备配备类别名称：</td>
								<td class="tbValue fl" colspan="3">
									<input type="text" name="equipmentType.name" id="equipmentType.name" value="${equipmentType.name }" class="textInput validate['required','length[100]']" /> <font style="color:red;">*</font>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">装备配备类别说明：</td>
								<td class="tbValue fl" colspan="3">
									<textarea class="validate['length[300]']" name="equipmentType.description" id="equipmentType.description" style="width:95%;height:100px;">${equipmentType.description}</textarea>
								</td>
							</tr>
						</table>

						<!-- 隐藏button按钮 -->
						<div align="center">
							<input type="submit" id="sub" value="sub" style="display: none;" />
						</div>
					</form>
				</div>
			</div>
			<!-- 内容panel结束-->
		</div>
	</body>
</html>