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
		<title>涉密移动存储介质密级解除</title>

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
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
				var fc = new FormCheck('secrecyOthers_clear_form',{
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
							window.setTimeout("$('sbm_button').style.display=''",8000);
							window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
						}
					}
				});
			});
		</script>
	</head>

	<body style="overflow: auto;overflow-x: hidden; ">
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

		<div class="panel tMargin" style="margin-top: -1px;">
			<div class="panel_content panel_content_table">
				<form id="secrecyOthers_clear_form" action="<s:url namespace='/secrecysystem/secrecyothers' action='others_declassifieding.action' includeParams='true'/>"
				      method="post" enctype="multipart/form-data">
				<!-- 隐藏域 -->
			    <input type="hidden" id="secrecyOthersClear.secrecyOthers.secrecyothersId" name="secrecyOthersClear.secrecyOthers.secrecyothersId" value="${secrecyOthers.secrecyothersId}" />

				<table class="content_table st" cellspacing="0" cellpadding="0" width="100%;">
					<tr>
						<td class="tbLable fr">原密级：</td>
						<td class="tbValue fl">
							<dictionary:text fieldCode="secrecy_level_thing" optionValue="${secrecyOthers.secrecyLevel}" tableCode="bmp" ></dictionary:text>
							<input type="hidden" id="secrecyOthers.secrecyLevel" name="secrecyOthers.secrecyLevel" value="${secrecyOthers.secrecyLevel}" />
						</td>
					</tr>
					<tr>
						<td class="tbLable fr">解除类型：</td>
						<td class="tbValue fl">
							<dictionary:select  id="secrecyOthersClear.clearType" name="secrecyOthersClear.clearType" tableCode="bmp" fieldCode="clear_secrecy_type" optionValue="" title="false" style="width:130px;"></dictionary:select>
						</td>
					</tr>
					<tr>
						<td class="tbLable fr">解除时间：</td>
						<td class="tbValue fl">
							<input type="text" id="secrecyOthersClear.clearTime" name="secrecyOthersClear.clearTime"
								class="Wdate validate['required','length[20]'] w130" value="" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" />&nbsp;<span style="color:red;">*</span>
						</td>
					</tr>
					<!--  暂时    不加入审批 的功能   请不要删除
					<tr>
						<td class="tbLable fr">
							审批人：
						</td>
						<td class="tbValue fl">
							<input id="sealingProduts.sealingProdutsFileno" name="sealingProduts.sealingProdutsFileno" type="text" class="validate['length[20]']" value=""/>
						</td>
					</tr>
					 -->
					<tr>
						<td class="tbLable fr">解除原因：</td>
						<td class="tbValue fl">
							<textarea rows="5" cols="70" id="secrecyOthersClear.cleanReason" name="secrecyOthersClear.cleanReason"></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="4" style="text-align: center;" >
							<a class="pop_button" href="###" id="sbm_button"><span>确定</span></a>
							<a class="pop_button" href="javascript:void();" id="sbm_button_hidden" style="display:none;"><span>提交中...</span></a>
							<a class="pop_button" href="###" onclick="window.close();"><span>取消</span></a>
							<input type="submit" id="sub" value="sub" style="display: none;" />
						</td>
					</tr>
				</table>
				</form>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		function save(){

			//验证时间
			var cdate = document.getElementById("keySectionSecrecyClear.clearTime").value;
			if(cdate=="") {
				alert("解除时间不能为空");
				return;
			}

			//提交

		}

	</script>
</html>