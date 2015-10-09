<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>编辑保密设备</title>
		<!-- FCK支持 -->
		<script src="${ctx}/resources/FCKeditor/fckeditor.js" type="text/javascript"></script>
		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js",function(){
				$ENV.onDomReady(function(){
					if (needReload) {
						if (!confirm('编辑成功，是否继续编辑？')){
							needReload2 = true;
							TabUtil.closeTab();
						}
					};
					formcheck=new FormCheck('form1',{
						display:{
							showErrors:1
						},
						trimValue: true
					});
				});
			});
			var needReload = ${needReload};
			var needReload2 = false;

			// 返回
			function doBack_edit(){
				window.location.href="${ctx}/bmp/devicemgr/deviceMgrAction_list.action?secrecyEquipmentCategory.secrecyEquipmentCategoryId=${secrecyEquipmentCategory.secrecyEquipmentCategoryId}";
			}

			//保存
			function doSave(){
				if (formcheck.isFormValid(true)) {
					$('sub').click();
					$('sbm_button').style.display='none';
					$('sbm_button_hidden').style.display='';
					window.setTimeout("$('sbm_button').style.display=''",3000);
					window.setTimeout("$('sbm_button_hidden').style.display='none'",3000);
				}
		   	}

		</script>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="javascript:void();" id="sbm_button_hidden" style="display:none;"><span>保存中...</span></a>
					<a class="pop_button" href="javascript:doSave();" id="sbm_button"><span>保 存</span></a>
					<a class="pop_button" href="javascript:doBack_edit();"><span>返回列表</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:doBack_edit();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div class="body_content">
		 	<!-- 内容panel开始 -->
	 		<div class="panel">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						编辑保密设备
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="form1" class="form" action="<s:url action="deviceMgrAction_update"  includeParams="true"/>" method="post">
						<input type="hidden" name="secrecyEquipment.secrecyEquipmentCategory.secrecyEquipmentCategoryId" value="${secrecyEquipment.secrecyEquipmentCategory.secrecyEquipmentCategoryId}">
						<input type="hidden" name="secrecyEquipment.secrecyEquipmentId" value="${secrecyEquipment.secrecyEquipmentId}">
						<table class="content_table">
							<tr height="30px;">
								<td align="right">
									设备分类：
								</td>
								<td>
									&nbsp;&nbsp;${secrecyEquipment.secrecyEquipmentCategory.name}
								</td>
							</tr>
							<tr height="30px;">
								<td align="right">
									设备名称：
								</td>
								<td>
									<input name="secrecyEquipment.name" type="text" value="${secrecyEquipment.name}"  class="validate['required','length[100]']" ><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td align="right">
									型号/序号：
								</td>
								<td>
									<input name="secrecyEquipment.typeCode" id="betraynum" type="text" value="${secrecyEquipment.typeCode}"  class="validate['length[100]']" >
								</td>
							</tr>
							<tr height="30px;">
								<td align="right">
									设备数量：
								</td>
								<td>
									<input type="text" name="secrecyEquipment.number" class="validate['required','digit[1,999999999]','length[11]']" value="${secrecyEquipment.number}"/><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td align="right">
									设备单价(单位：元)：
								</td>
								<td>
									<input type="text" name="secrecyEquipment.unitPrice" class="validate['required','arithmeticfloatnum','length[10]']" value="${secrecyEquipment.unitPrice}"/><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>

							<tr height="30px;">
								<td align="right">
									价格(单位：元)：
								</td>
								<td>
									<input name="secrecyEquipment.price" id="betraynum" type="text" value="${secrecyEquipment.price}"  class="validate['required','arithmeticfloatnum','length[10]']"><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td align="right">
									购买时间：
								</td>
								<td>
									<input name="secrecyEquipment.buyTime" value="<s:date name="secrecyEquipment.buyTime" format="yyyy-MM-dd"/>" type="text" class="Wdate validate['required']"  onFocus="WdatePicker({isShowClear:false,readOnly:true});"/><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>

							<tr height="30px;">
								<td align="right">
									存放地点：
								</td>
								<td>
									<input name="secrecyEquipment.atPlace" id="betraynum" type="text" value="${secrecyEquipment.atPlace}" class="validate['required','length[100]']"><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td align="right">
									责任人：
								</td>
								<td>
									<ui:selectByOrgan valueEl="secrecyEquipment.dutyPerson.userInfoId" textEl="secrecyEquipment.dutyPerson.name" text="${secrecyEquipment.dutyPerson.name}"
									onlyFromValue="true" value="${secrecyEquipment.dutyPerson.userInfoId}" required="true" buttonEl="readPerson" />

								</td>
							</tr>
						</table>

						<!-- 隐藏提交按钮 -->
						<div align="center">
							<input type="submit" id="sub" value="sub" style="display: none;" />
						</div>
					</form>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>