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
		<title>严重违规案件密级变更</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>

		<script type="text/javascript">

		window.onDialogReady = function() {
			if( "${needReload}" == "true" ){
				window.getOpener().refresh();
				alert("密级变更成功。");
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
					formcheck = new FormCheck('caseCriticalviolation_change_form',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
				});
			});
			function save(){
				var yuan_mi_ji = document.getElementById("caseCriticalviolationChange.beforeLevel").value;
				var xian_mi_ji = document.getElementById("caseCriticalviolationChange.afterLevel").value;
				if(yuan_mi_ji==xian_mi_ji){
					alert("现密级不能和原密级相同！请重新选择现密级。");
					return;
				}
				$('sub').click();
			};
		</script>
	</head>

	<body>


		<div class="panel tMargin" >
			<div class="panel_content panel_content_table">
				<form id="caseCriticalviolation_change_form" action="<s:url namespace='/bmp/caseCriticalviolation' action='caseCriticalviolation_changeing.action' includeParams='true'/>"
				      method="post" enctype="multipart/form-data">
					<table class="content_table st" cellspacing="0" cellpadding="0" width="100%;">
						<tr>
							<td class="tbLable fr">原密级：</td>
							<td class="tbValue fl">
							    <dictionary:text fieldCode="secrecy_level_thing" optionValue="${caseCriticalviolationChange.beforeLevel}" tableCode="bmp" ></dictionary:text>
							    <input type="hidden" id="caseCriticalviolationChange.beforeLevel" name="caseCriticalviolationChange.beforeLevel" value="${caseCriticalviolationChange.beforeLevel}" />
								<input type="hidden" id="caseCriticalviolationChange.caseCriticalviolation.caseCriticalviolationId" name="caseCriticalviolationChange.caseCriticalviolation.caseCriticalviolationId" value="${caseCriticalviolationChange.caseCriticalviolation.caseCriticalviolationId}" />
							</td>
						</tr>

						<tr>
							<td class="tbLable fr">现密级：</td>
							<td class="tbValue fl">
								<dictionary:select id="caseCriticalviolationChange.afterLevel" name="caseCriticalviolationChange.afterLevel" tableCode="bmp" fieldCode="secrecy_level_thing"
								 title="false" style="width:130px;"></dictionary:select>
							</td>
						</tr>

						<tr>
							<td class="tbLable fr" >保密期限变更：</td>
							<td class="tbValue fl">
								<dictionary:select  id="caseCriticalviolationChange.changeTimeState" name="caseCriticalviolationChange.changeTimeState" tableCode="bmp" fieldCode="secrecy_limit"
								optionValue="" title="false" style="width:130px;"></dictionary:select>
							</td>
						</tr>

						<tr>
							<td class="tbLable fr">变更时间：</td>
							<td class="tbValue fl">
								<input type="text" id="caseCriticalviolationChange.changeDate" name="caseCriticalviolationChange.changeDate" class="Wdate validate['required','length[20]'] w130"
								 onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="readonly" />&nbsp;<span style="color:red;">*</span>
							</td>
						</tr>

						<tr>
							<td class="tbLable fr">变更原因：</td>
							<td class="tbValue fl">
								<textarea   class="validate['length[0,2000]']" id="caseCriticalviolationChange.changeReason" name="caseCriticalviolationChange.changeReason" style="width: 80%;height: 100px;"></textarea>
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