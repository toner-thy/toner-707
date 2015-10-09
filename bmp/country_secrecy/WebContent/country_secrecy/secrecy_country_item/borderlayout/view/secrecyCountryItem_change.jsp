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
		<title>国家秘密事项密级变更</title>

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
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function() {
				window.onDialogReady = function() {
					if( "${needReload}" == "true" ){
						alert("国家秘密事项密级变更成功!");
						window.getOpener().refresh();
						$ENV.dialog.close();
					}
				};
			});
		</script>
	</head>

	<body style="overflow:auto;overflow-x: hidden;">
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
				<form id="secrecyCountryItemChange_form" action="<s:url namespace='/bmp/secrecycountryitem' action='secrecyCountryItem_changeing.action' includeParams='true'/>"
				      method="post" enctype="multipart/form-data">
					<table class="content_table st" cellspacing="0" cellpadding="0" width="100%;">
						<tr>
							<td class="tbLable fr">原密级：</td>
							<td class="tbValue fl">
							    <dictionary:text fieldCode="secrecy_level_thing" optionValue="${secrecyCountryItemChange.beforeLevel}" tableCode="bmp" ></dictionary:text>
							    <input type="hidden" id="secrecyCountryItemChange.beforeLevel" name="secrecyCountryItemChange.beforeLevel" value="${secrecyCountryItemChange.beforeLevel}" />
								<input type="hidden" id="secrecyCountryItemChange.secrecyCountryItem.secrecyCountryItemId" name="secrecyCountryItemChange.secrecyCountryItem.secrecyCountryItemId" \
								value="${secrecyCountryItemChange.secrecyCountryItem.secrecyCountryItemId}" />
							</td>
						</tr>

						<tr>
							<td class="tbLable fr">现密级：</td>
							<td class="tbValue fl">
								<dictionary:select id="secrecyCountryItemChange.afterLevel" name="secrecyCountryItemChange.afterLevel" tableCode="bmp" fieldCode="secrecy_level_thing"
								optionValue="${secrecyCountryItemChange.afterLevel}" title="false" style="width:130px;"></dictionary:select>
							</td>
						</tr>

						<tr>
							<td class="tbLable fr" style="white-space:nowrap;">保密期限变更：</td>
							<td class="tbValue fl">
								<dictionary:select  id="secrecyCountryItemChange.changeTimeState" name="secrecyCountryItemChange.changeTimeState" tableCode="bmp" fieldCode="secrecy_limit"
								optionValue="" title="false" style="width:130px;"></dictionary:select>
							</td>
						</tr>

						<tr>
							<td class="tbLable fr">变更时间：</td>
							<td class="tbValue fl">
								<input type="text" id="secrecyCountryItemChange.changeDate" name="secrecyCountryItemChange.changeDate" class="Wdate validate['length[20]'] w130"
								 onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="readonly" />&nbsp;<span style="color:red;">*</span>
							</td>
						</tr>

						<tr>
							<td class="tbLable fr">变更原因：</td>
							<td class="tbValue fl">
								<textarea class="validate['length[500]']" rows="5" cols="50" id="secrecyCountryItemChange.changeReason" name="secrecyCountryItemChange.changeReason" ></textarea>
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
	//保存方法
	function save(){
		//判断  现密级的填写情况 ，不能和原密级一样
		var yuan_mi_ji = document.getElementById("secrecyCountryItemChange.beforeLevel").value;
		var xian_mi_ji = document.getElementById("secrecyCountryItemChange.afterLevel").value;
		if(yuan_mi_ji==xian_mi_ji){
			alert("现密级不能和原密级相同！请重新选择现密级。");
			return;
		}

		//验证时间
		var cdate = document.getElementById("secrecyCountryItemChange.changeDate").value;
		if(cdate=="") {
			alert("变更时间不能为空");
			return;
		}

		//变更原因
		var changeReason = $("secrecyCountryItemChange.changeReason").value;
		if(changeReason!="" && changeReason.length>500) {
			alert("变更原因长度不能超过500");
			return;
		}

		//提交
		var frm = document.getElementById("secrecyCountryItemChange_form");
		frm.submit();
	}

	</script>
</html>