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
<%@ taglib prefix="nestedlist" uri="http://www.cdthgk.com/bmp/tags/nestedlist"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>人员变动</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<style type="text/css" media="print">
			.no_print{display:none;}
		</style>

		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			//$ENV.loader.loadStyleSheet("${ctx}/platform/css/public/print/printPage.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js");

			window.onDialogReady = function() {
				if( "${needReload}" == "true" ){
					window.getOpener().refresh();
					$ENV.dialog.close();
				}
			};

			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
				$ENV.onDomReady(function(){
					//alert("${needReload}"+"111");
					var fc = new FormCheck('form_secrecy_work_organ_relation_member_change',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});

					$("sbm_button").addEvent('click', function(){
						if( confirm("确定提交吗？") ){
							if (fc.isFormValid()) {
			 					$('sbm_button').style.display='none';
			 					$('sbm_button_hidden').style.display='';
			  					$('sub').click();
							}
						}
					});
				});
			});
		</script>
	</head>

	<body style="overflow: auto;overflow-x: hidden; ">
		<!-- 公共头部 -->
		<div class="button_bar no_print">
			<div class="left no_print">
				<div class="pop_button_bar no_print">
				</div>
			</div>
			<div class="right no_print">
				<div class="pop_button_bar no_print">
					<a class="pop_button pop_button_close" href="###" onclick="javascript:environment.dialog.close();"><span>退出本页</span></a>
				</div>
			</div>
		</div>


		<!-- 保密要害部门panel 及 打印标签开始 -->
		<!--startprint1-->
			<!-- 这个CSS在IE6下影响了样式，导致滚动条没有出来 -->
<!-- 		<div class="printCss"> -->
		<div>
			<div class="panel tMargin" style="margin-top: -1px;">
				<div class="panel_header no_print">
					<div class="panel_title panel_titleListIco no_print">
						<%-- 保密要害部门【${keySection.department.departmentName }】详情 --%>
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="form_secrecy_work_organ_relation_member_change" class="form" action="<s:url action='secrecyWorkOrganRelationMember_personnelChanging' namespace='/secrecyorganization/secrecyWorkOrganRelationMember' includeParams='true'/>" enctype="multipart/form-data" method="post">
						<!-- 隐藏域 -->
						<input type="hidden" id="personGroupRelationChange.bmPersonGroupRelationId.secrecyWorkOrganRelationId" name="personGroupRelationChange.bmPersonGroupRelationId.secrecyWorkOrganRelationId" value="${secrecyWorkOrganRelationMember.secrecyWorkOrganRelationId }" />
						<table class="content_table st" cellspacing="0" cellpadding="0" width="100%;">
							<tr>
								<td class="tbLable fr">
									变动事由：
								</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="personnel_changes" id="personGroupRelationChange.changeType" name="personGroupRelationChange.changeType" styleClass="w135"></dictionary:select>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									变动时间：
								</td>
								<td class="tbValue fl">
									<input type="text" id="personGroupRelationChange.changeDate" name="personGroupRelationChange.changeDate" class="validate['required','length[19]'] Wdate w135" value="" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									变动说明：
								</td>
								<td class="tbValue fl">
									<textarea rows="5" cols="50" id="personGroupRelationChange.changeReason" name="personGroupRelationChange.changeReason" class="validate['length[2000]']"></textarea>
								</td>
							</tr>
							<tr>
								<td colspan="4" style="text-align: center;" >
									<a class="pop_button" href="javascript:void();" id="sbm_button_hidden" style="display:none;"><span>保存中...</span></a>
									<a class="pop_button" href="###" id="sbm_button"><span>保 存</span></a>
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