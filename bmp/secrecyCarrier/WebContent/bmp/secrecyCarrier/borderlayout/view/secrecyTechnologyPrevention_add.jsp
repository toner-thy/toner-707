<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/organ" prefix="organ"%>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib uri="http://www.cdthgk.com/tags/keyPart" prefix="part" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>新增装备保密技术防范设备情况</title>
		<link href="${ctx}/platform/css/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/FCKeditor/fckeditor.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js",function(){
				$ENV.onDomReady(function(){
							if (needReload) {
								if (!confirm('新增成功，是否继续新增？')){
									needReload2 = true;
									TabUtil.closeTab();
								}
							};
					formcheck = new FormCheck('form_secrecyTechnologyPrevention_save',{
								display:{
									showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
								},
								trimValue: true
							});
						});
					});
			var needReload = ${needReload};
			var needReload2 = false;
			//返回
			function doBack(){
				window.location.href="<s:url action="secrecyTechnologyPrevention_list"  includeParams="false"/>";
			}

			//保存
			function doSave(){
				if (formcheck.isFormValid(true)) {
				$('sub').click();
				$('sbm_button').style.display='none';
				$('sbm_button_hidden').style.display='';
				window.setTimeout("$('sbm_button').style.display=''",8000);
				window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
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
					<a class="pop_button" href="javascript:doSave();" id="sbm_button"><span>保存</span></a>
					<a class="pop_button" href="javascript:doBack();"><span>返回列表</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:doBack();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div class="body_content">
		<!-- 内容panel开始 -->
	 		<div class="panel">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						新增装备保密技术防范设备情况
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
				<form id="form_secrecyTechnologyPrevention_save" class="form"  action="<s:url namespace='/bmp/secrecyTechnologyPrevention' action='secrecyTechnologyPrevention_save' includeParams='true'/>" method="post" enctype="multipart/form-data">
					<table class="content_table" width="100%">
							<tr>
								<td class="tbLable fr">
									设备名称：
								</td>
								<td class="tbValue fl" colspan="3">
									<input type="text" size="50" class="validate['required','length[100]']" name="secrecyTechnologyPrevention.name" value="${secrecyTechnologyPrevention.name}"/><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									数量：
								</td>
								<td class="tbValue fl">
									<input type="text" size="50" class="validate['required','length[100]']" name="secrecyTechnologyPrevention.number" value="${secrecyTechnologyPrevention.number}"/><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr">
									时间：
								</td>
								<td class="tbValue fl">
									<input type="text" readonly="readonly" name="secrecyTechnologyPrevention.date" value="${secrecyTechnologyPrevention.date}" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd '})" />
								</td>

							</tr>
							<tr>

								<td class="tbLable fr">
									装备部门：
								</td>
								<td class="tbValue fl">
									 <dep:selectByOrgan textEl="secrecyTechnologyPrevention.equipDep.departmentName" valueEl="secrecyTechnologyPrevention.equipDep.departmentId" required="true"
								    text="${secrecyTechnologyPrevention.equipDep.departmentName}" value="${secrecyTechnologyPrevention.equipDep.departmentId}"
									onlyFromValue="true" styleClass="validate['length[32]']" buttonEl="butPerson3" />
								</td>
								<td class="tbLable fr">
									装备部位：
								</td>
								<td class="tbValue fl">
									<part:selectKeyPart valueEl="secrecyTechnologyPrevention.keyPart.partId" textEl="secrecyTechnologyPrevention.keyPart.partName"
									text="${secrecyTechnologyPrevention.keyPart.partName}" value="${secrecyTechnologyPrevention.keyPart.partId}"
										buttonEl="bb" required="true"></part:selectKeyPart>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									用途：
								</td>
								<td class="tbValue fl" colspan="3">
									<input type="text" class="validate['length[100]']"style="width: 98%" size="50" name="secrecyTechnologyPrevention.purpose" value="${secrecyTechnologyPrevention.purpose}"/>
								</td>
							</tr>
						</table>
						<input type="submit" id="sub" value="sub" class="btn_23" style="display: none;"/>
				</form>
			</div>
			</div>
		</div>
	</body>
</html>