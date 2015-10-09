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
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<%@ taglib prefix="secPersonlist" uri="http://www.cdthgk.com/tags/secrecyperson/list"%>
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

		<style type="text/css" media="print">
			.no_print{display:none;}
		</style>

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

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
				$ENV.onDomReady(function(){
					var fc = new FormCheck('secrecyOthers_secrecy_level_change_form',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
					$('btnSave').addEvent('click', function(){
						var oldSecrecyLevel = "${secrecyOthers.secrecyLevel }";
						var nowSecrecyLevel = $('secrecyOthersChange.afterLevel').value;
						if( oldSecrecyLevel == nowSecrecyLevel ){
							alert("现密级不能和原密级相同,请重新选择现密级。");
							return;
						}
						if (fc.isFormValid(false)) {
							$('sub').click();
							this.style.display='none';
							$('sbm_button_hidden').style.display='';
						}
					});
				});
			});

			//保存
			function doSave(){
				if (fc.isFormValid(true)) {
					$('sub').click();
					$('sbm_button').style.display='none';
					$('sbm_button_hidden').style.display='';
					window.setTimeout("$('sbm_button').style.display=''",8000);
					window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
				}
			}

		</script>
	</head>

	<body style="overflow: auto;overflow-x: hidden; ">
<!-- 公共头部 -->

		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_close" href="###" onclick="javascript:environment.dialog.close();"><span>退出本页</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
				</div>
			</div>
		</div>


		<div>
			<div class="panel tMargin" style="margin-top: -1px;">

				<div class="panel_content panel_content_table">
					<form id="secrecyOthers_secrecy_level_change_form" class="form" enctype="multipart/form-data" action="<s:url action="others_secrecyLevelChanging" namespace="/secrecysystem/secrecyothers"  includeParams="true"/>" method="post" >
						<!-- 隐藏域 -->
						<input type="hidden" name="secrecyOthers.secrecyothersId" id="secrecyOthers.secrecyothersId" value="${secrecyOthers.secrecyothersId }"/>
						<table class="content_table st" cellspacing="0" cellpadding="0" width="100%;">
							<tr>
								<td class="tbLable fr">
									原密级：
								</td>
								<td class="tbValue fl">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyOthers.secrecyLevel }"></dictionary:text>
									<input type="hidden" name="secrecyOthersChange.beforeLevel" id="secrecyOthersChange.beforeLevel" value="${secrecyOthers.secrecyLevel }" />
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									现密级：
								</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="secrecy_level_thing" id="secrecyOthersChange.afterLevel" name="secrecyOthersChange.afterLevel" style="width:130px;"></dictionary:select>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									保密期限变更：
								</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="secrecy_limit" name="secrecyOthersChange.changeTimeState" id="secrecyOthersChange.changeTimeState" style="width:130px;"></dictionary:select>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									变更时间：
								</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyOthersChange.changeDate" name="secrecyOthersChange.changeDate" class="Wdate validate['required','length[20]'] w135" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									变更原因：
								</td>
								<td class="tbValue fl">
									<textarea id="secrecyOthersChange.changeReason" name="secrecyOthersChange.changeReason" class="validate['length[1000]']" rows="5" cols="50"></textarea>
								</td>
							</tr>
							<tr>
								<td colspan="4" style="text-align: center;" >
									<a class="pop_button" href="javascript:void();" id="sbm_button_hidden" style="display:none;"><span>保存中...</span></a>
									<a class="pop_button" href="###" id="btnSave"><span>保 存</span></a>
								</td>
							</tr>
						</table>
						<div align="center">
							<input type="submit" id="sub" value="sub" style="display: none;" />
						</div>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>