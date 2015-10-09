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
		<title>脱密</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>

		<script type="text/javascript">
		$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
		$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
		$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
		$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");
		$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/resources/css/component.css");
		$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/component_blue.css");

		$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
		$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
		$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
		$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
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
					window.onDialogReady = function() {
						if( "${reflag}" == "0" ){
							window.getOpener().parent.reloadMember();
							$ENV.dialog.close();
						}
					};
				});
			});
		</script>


		<script type="text/javascript">
			function doSave(){
				if (formcheck.isFormValid(true)) {
					$('sub').click();
				}
			}
		</script>
	</head>

	<body>
		<div class="panel tMargin" >
			<div class="panel_content panel_content_table">
				<form id="add_form" action="<s:url namespace='/secrecyorganization/secrecyofficemember' action='secrecyOfficeMember_decryptioning.action' includeParams='true'/>"
				      method="post" enctype="multipart/form-data">
					<input type="hidden" id="secrecyOfficeMemberDecryption.secrecyOfficeMember.secrecyOfficeMemberId" name="secrecyOfficeMemberDecryption.secrecyOfficeMember.secrecyOfficeMemberId" value="${secrecyOfficeMember.secrecyOfficeMemberId}" />
					<table class="content_table st" cellspacing="0" cellpadding="0" width="100%;">
							<tr>
								<td class="tbLable fr">
									脱密类型：
								</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="decryption_type" id="secrecyOfficeMemberDecryption.decryptionType" name="secrecyOfficeMemberDecryption.decryptionType" style="width:130px;"></dictionary:select>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr" colspan="2">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									脱密期限：
								</td>
								<td class="tbValue fl">
									<input type="text" name="secrecyOfficeMemberDecryption.decryptionLimit" id="secrecyOfficeMemberDecryption.decryptionLimit" class="validate['digit','length[10]']"/>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr">
									期限单位：
								</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="decryption_limit_type" name="secrecyOfficeMemberDecryption.limitMeasure" id="secrecyOfficeMemberDecryption.limitMeasure" style="width:130px;"></dictionary:select>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									脱密时间起：
								</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyOfficeMemberDecryption.decryptionStart" name="secrecyOfficeMemberDecryption.decryptionStart"  class="Wdate" value="" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr">
									脱密时间止：
								</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyOfficeMemberDecryption.decryptionEnd" name="secrecyOfficeMemberDecryption.decryptionEnd"  class="Wdate" value="" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>

							<tr>
								<td class="tbLable fr">
									脱密原因：
								</td>
								<td class="tbValue fl" colspan="4">
									<textarea rows="5" name="secrecyOfficeMemberDecryption.decryptionReason" id="secrecyOfficeMemberDecryption.decryptionReason" class="validate['length[1000]']" style="width:100%;"></textarea>
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