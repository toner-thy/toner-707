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
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>商业秘密事项密级解除</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>

		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js",function(){
				window.onDialogReady = function() {
					if( "${needReload}" == "true" ){
						alert("商业秘密事项密级解除成功!");
						window.getOpener().refresh();
						$ENV.dialog.close();
					}
				};
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



		<div class="panel tMargin" >
			<div class="panel_content panel_content_table">
				<form id="Clear_form" action="<s:url namespace='/bmp/secrecycountryitem' action='secrecyCountryItem_clearing.action' includeParams='true'/>"
				      method="post" enctype="multipart/form-data">
				<table class="content_table st" cellspacing="0" cellpadding="0" width="100%;">
					<tr>
						<td class="tbLable fr">原密级：</td>
						<td class="tbValue fl">
							<dictionary:text tableCode="bmp"  fieldCode="secrecy_level_thing" optionValue="${secrecyCountryItemClear.secrecyCountryItem.secrecyLevel}" ></dictionary:text>
							<input type="hidden" id="secrecyCountryItemClear.secrecyCountryItem.secrecyLevel" name="secrecyCountryItemClear.secrecyCountryItem.secrecyLevel"
							value="${secrecyCountryItemClear.secrecyCountryItem.secrecyLevel}" />
						    <input type="hidden" id="secrecyCountryItemClear.secrecyCountryItem.secrecyCountryItemId" name="secrecyCountryItemClear.secrecyCountryItem.secrecyCountryItemId"
						    value="${secrecyCountryItemClear.secrecyCountryItem.secrecyCountryItemId}" />
						</td>
					</tr>
					<tr>
						<td class="tbLable fr">解除类型：</td>
						<td class="tbValue fl">
							<dictionary:select  id="secrecyCountryItemClear.clearType" name="secrecyCountryItemClear.clearType" tableCode="bmp" fieldCode="clear_secrecy_type"
												optionValue="" title="false" style="width:130px;"></dictionary:select>
						</td>
					</tr>
					<tr>
						<td class="tbLable fr">解除时间：</td>
						<td class="tbValue fl">
							<input type="text" id="secrecyCountryItemClear.clearTime" name="secrecyCountryItemClear.clearTime"
								class="Wdate validate['length[20]'] w130" value="" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" />
								&nbsp;<span style="color:red;">*</span>
						</td>
					</tr>

					<tr>
						<td class="tbLable fr" style="white-space:nowrap;">解除原因：</td>
						<td class="tbValue fl">
							<textarea class="validate['length[500]']" rows="5" cols="50" id="secrecyCountryItemClear.cleanReason" name="secrecyCountryItemClear.cleanReason"></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="4" style="text-align: center;" >
							<a class="pop_button" href="##" onclick="save();"><span>确定</span></a>
							<a class="pop_button" href="##" onclick="window.close();"><span>取消</span></a>
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
			var cdate = document.getElementById("secrecyCountryItemClear.clearTime").value;
			if(cdate=="") {
				alert("解除时间不能为空");
				return;
			}

			//解除原因
			var cleanReason = $("secrecyCountryItemClear.cleanReason").value;
			if(cleanReason!="" && cleanReason.length>500) {
				alert("解除原因长度不能超过500");
				return;
			}

			var frm = document.getElementById("Clear_form");
			frm.submit();
		}

	</script>
</html>