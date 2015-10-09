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
		<title>泄密案件解除</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>

		<script type="text/javascript">
		window.onDialogReady = function() {
			if( "${needReload}" == "true" ){
				alert("解除成功。");
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
					formcheck = new FormCheck('discloseSecrecy_Clear_form',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
				});

			});
			function save(){
				$('sub').click();
			};
		</script>
	</head>

	<body style="overflow:auto;overflow-x: hidden;">
	<div class="panel tMargin" >
			<div class="panel_content panel_content_table">
				<form id="discloseSecrecy_Clear_form" action="<s:url namespace='/bmp/discloseSecrecy' action='discloseSecrecy_clearing.action' includeParams='true'/>"
				      method="post" enctype="multipart/form-data">
				<table class="content_table st" cellspacing="0" cellpadding="0" width="100%;">
					<tr>
						<td class="tbLable fr">原密级：</td>
						<td class="tbValue fl">
							<dictionary:text tableCode="bmp"  fieldCode="secrecy_level_thing" optionValue="${discloseSecrecyClear.disclosesecrecycaseId.secrecyLevel}" ></dictionary:text>
							<input type="hidden" id="discloseSecrecyClear.disclosesecrecycaseId.secrecyLevel" name="discloseSecrecyClear.disclosesecrecycaseId.secrecyLevel" value="${discloseSecrecyClear.disclosesecrecycaseId.secrecyLevel}"/>
						    <input type="hidden" id="discloseSecrecyClear.disclosesecrecycaseId.disclosesecrecycaseId" name="discloseSecrecyClear.disclosesecrecycaseId.disclosesecrecycaseId" value="${discloseSecrecyClear.disclosesecrecycaseId.disclosesecrecycaseId}"/>
						</td>
					</tr>
					<tr>
						<td class="tbLable fr">解除类型：</td>
						<td class="tbValue fl">
							<dictionary:select  id="discloseSecrecyClear.clearType" name="discloseSecrecyClear.clearType" tableCode="bmp" fieldCode="clear_secrecy_type"
												optionValue="" title="false" style="width:130px;"></dictionary:select>
						</td>
					</tr>
					<tr>
						<td class="tbLable fr">解除时间：</td>
						<td class="tbValue fl">
							<input type="text" id="discloseSecrecyClear.clearTime" name="discloseSecrecyClear.clearTime"
								class="Wdate validate['required','length[20]'] w130"   value="" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" />
								&nbsp;<span style="color:red;">*</span>
						</td>
					</tr>

					<tr>
						<td class="tbLable fr">解除原因：</td>
						<td class="tbValue fl">
							<textarea  id="discloseSecrecyClear.cleanReason" class="validate['length[0,2000]']" name="discloseSecrecyClear.cleanReason" style="width: 80%;height: 100px;"></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="4" style="text-align: center;" >
							<a class="pop_button" href="##" onclick="save();"><span>确定</span></a>
							<a class="pop_button" href="##" onclick="window.close();"><span>取消</span></a>
							<input type="submit" id="sub" name="sub" value="sub" style="display: none;"/>
						</td>
					</tr>
				</table>
				</form>

			</div>
		</div>

	</body>
</html>