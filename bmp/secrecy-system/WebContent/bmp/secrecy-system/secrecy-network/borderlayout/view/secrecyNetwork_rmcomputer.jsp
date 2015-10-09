<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib prefix="secPersonlist" uri="http://www.cdthgk.com/tags/secrecyperson/list"%>
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<%@ taglib prefix="nestedlist" uri="http://www.cdthgk.com/bmp/tags/nestedlist"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>移除网络</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>

		<script type="text/javascript">
		window.onDialogReady = function() {
			if( "${needReload}" == "true" ){
				window.getOpener().refresh();
				$ENV.dialog.close();
			}
		};
		$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
		$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
		$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");
		$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/resources/css/component.css");
		$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/component_blue.css");

		$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
		$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
		$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
		$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
		$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
		$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
				$ENV.onDomReady(function(){
					var fc = new FormCheck('add_form',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});

					$("sbm_button").addEvent('click', function(){
						if (fc.isFormValid()) {
							if( confirm("确定提交吗？") ){
								$('sbm_button').style.display='none';
								$('sbm_button_hidden').style.display='';
								$('sub').click();
								window.setTimeout("$('sbm_button').style.display=''",8000);
								window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
							}
						}
					});
				});
			});
		</script>
	</head>

	<body>
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">

				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>
		<div id="body_content" class="body_content">
			<div class="panel tMargin bMargin" >
				<div class="panel_content panel_content_table">
					<form id="add_form" action="<s:url namespace='/bmp/secrecynetworkterminal' action='secrecyNetworkterminal_removeTerminals.action' includeParams='true'/>"
					      method="post" enctype="multipart/form-data">
					      <!-- 隐藏域 -->
					    <input type="hidden" name="secrecyNetworkterminal.secrecynetworkterminalId" id="secrecyNetworkterminal.secrecynetworkterminalId" value="${secrecyNetworkterminal.secrecynetworkterminalId }" />
						<table class="content_table st" cellspacing="0" cellpadding="0" width="100%;">
							<tr>
								<td class="tbLable fr">移除时间：</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyNetworkterminal.removeNetworkDate" name="secrecyNetworkterminal.removeNetworkDate"
										class="Wdate validate['required'] w130" value="<s:date name='#attr.secrecyNetworkterminal.removeNetworkDate' format='yyyy-MM-dd'/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" />
										&nbsp;<span style="color:red;">*</span>
								</td>
							</tr>

							<tr>
								<td class="tbLable fr">解除说明：</td>
								<td class="tbValue fl">
									<textarea rows="5" cols="80" id="secrecyNetworkterminal.removeNetworkReason" name="secrecyNetworkterminal.removeNetworkReason" class="validate['length[1000]']">${secrecyNetworkterminal.removeNetworkReason }</textarea>
								</td>
							</tr>
						</table>
						<div class="pop_button_bar" align="center">
							<a class="pop_button" href="###" id="sbm_button"><span>保 存</span></a>
							<a class="pop_button" href="javascript:void();" id="sbm_button_hidden" style="display:none;"><span>保存中...</span></a>
							<a class="pop_button" href="###" onclick="window.close();"><span>取消</span></a>
							<input id="sub" value="sub" type="submit" style="display: none;"/>
						</div>
					</form>

				</div>
			</div>
		</div>
	</body>
</html>