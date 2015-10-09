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
		<title>密级变更</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>

		<script type="text/javascript">
		window.onDialogReady = function() {
			if( "${reflag}" == "0" ){
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
					formcheck = new FormCheck('add_form',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
				});
			});
		</script>


		<script type="text/javascript">
			function doSave(){
				if (formcheck.isFormValid(true)) {
				//判断  现密级的填写情况 ，不能和原密级一样
					var yuan_mi_ji = $('secrecyNetworkChange.beforeLevel').value;
					var xian_mi_ji = $('secrecyNetworkChange.afterLevel').value;
					if(yuan_mi_ji == xian_mi_ji){
						alert("现密级不能和原密级相同！请重新选择现密级。");
						return;
					}
					$('sub').click();
				}
			}
		</script>
	</head>

	<body>
		<div class="panel tMargin" >
			<div class="panel_content panel_content_table">
				<form id="add_form" action="<s:url namespace='/bmp/secrecynetwork' action='secrecyNetwork_secrecyChanging.action' includeParams='true'/>"
				      method="post" enctype="multipart/form-data">
					<table class="content_table st" cellspacing="0" cellpadding="0" width="100%;">
						<tr>
							<td class="tbLable fr">原密级：</td>
							<td class="tbValue fl">
							    <dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyNetwork.secrecyLevel}"></dictionary:text>
							    <input type="hidden" id="secrecyNetworkChange.beforeLevel" name="secrecyNetworkChange.beforeLevel" value="${secrecyNetwork.secrecyLevel}" />
								<input type="hidden" id="secrecyNetworkChange.secrecyNetwork.secrecyNetworkId" name="secrecyNetworkChange.secrecyNetwork.secrecyNetworkId" value="${secrecyNetwork.secrecyNetworkId}" />
							</td>
						</tr>

						<tr>
							<td class="tbLable fr">现密级：</td>
							<td class="tbValue fl">
								<dictionary:select tableCode="bmp" fieldCode="secrecy_level_thing" id="secrecyNetworkChange.afterLevel" name="secrecyNetworkChange.afterLevel"
									 title="false" style="width:130px;"></dictionary:select>
							</td>
						</tr>

						<tr>
							<td class="tbLable fr" >保密期限变更：</td>
							<td class="tbValue fl">
								<dictionary:select tableCode="bmp" fieldCode="secrecy_limit" id="secrecyNetworkChange.changeTimeState" name="secrecyNetworkChange.changeTimeState"
									 title="false" style="width:130px;"></dictionary:select>
							</td>
						</tr>

						<tr>
							<td class="tbLable fr">变更时间：</td>
							<td class="tbValue fl">
								<input type="text" id="secrecyNetworkChange.changeDate" name="secrecyNetworkChange.changeDate" class="Wdate validate['required'] w130"
								 onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="readonly" />
								 <span style="color:red;">&nbsp;&nbsp;*</span>
							</td>
						</tr>

						<tr>
							<td class="tbLable fr">变更原因：</td>
							<td class="tbValue fl">
								<textarea rows="5" cols="50" id="secrecyNetworkChange.changeReason" name="secrecyNetworkChange.changeReason" ></textarea>
							</td>
						</tr>

					</table>
					<div class="pop_button_bar" align="center">
						<a class="pop_button" href="javascript:doSave();" id="sbm_button"><span>保 存</span></a>
						<a class="pop_button" href="###" onclick="window.close();"><span>取消</span></a>
						<input id="sub" value="sub" type="submit" style="display: none;"/>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>