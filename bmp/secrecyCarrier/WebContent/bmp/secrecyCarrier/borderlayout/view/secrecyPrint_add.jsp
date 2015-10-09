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
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>新增保密涉密载体打印情况</title>
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
					formcheck = new FormCheck('form_secrecyPrint_save',{
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
				window.location.href="<s:url action="secrecyPrint_list"  includeParams="false"/>";
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
						新增保密涉密载体打印情况
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
				<form id="form_secrecyPrint_save" class="form"  action="<s:url namespace='/bmp/secrecyPrint' action='secrecyPrint_save' includeParams='true'/>" method="post" enctype="multipart/form-data">
					<table class="content_table">
						<tr>
								<td class="tbLable fr">
									打印时间：
								</td>
								<td class="tbValue fl">
								    <input class="Wdate validate['required']" readonly="readonly" onfocus="WdatePicker()" value="${secrecyPrint.date}" name="secrecyPrint.date">
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									文件（资料）名称：
								</td>
								<td class="tbValue fl">
									<input type="text"  name="secrecyPrint.name" class="validate['required','length[100]']" value="${secrecyPrint.name}"/>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									份数：
								</td>
								<td class="tbValue fl">
									<input type="text" name="secrecyPrint.number" value="${secrecyPrint.number}" class="validate['required','digit','length[10]']"/>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									每份页数：
								</td>
								<td class="tbValue fl">
									<input type="text" name="secrecyPrint.pageNo" value="${secrecyPrint.pageNo}" class="validate['digit','length[10]']"/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									密级：
								</td>
								<td class="tbValue fl">
								<dictionary:select fieldCode="secrecy_level_thing" tableCode="bmp" id="secrecyPrint.secrecyLevel"
								                           name="secrecyPrint.secrecyLevel" style="width: 132px;"
								                           title="true" titleText="请选择" styleClass="validate['required']"
								                           optionValue="${secrecyPrint.secrecyLevel}" />
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									文号：
								</td>
								<td class="tbValue fl">
									<input type="text" name="secrecyPrint.docNumber" value="${secrecyPrint.docNumber}" class="validate['length[100]']"/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									起草部门：
								</td>
								<td class="tbValue fl">
								    <dep:selectByOrgan textEl="secrecyPrint.draftingDep.departmentName" valueEl="secrecyPrint.draftingDep.departmentId"required="true"
								    text="${secrecyPrint.draftingDep.departmentName}" value="${secrecyPrint.draftingDep.departmentId}"
									onlyFromValue="true" buttonEl="butPerson3"   />
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									承办人：
								</td>
								<td class="tbValue fl">
									<ui:multySelect  dialogWidth="0.9" dialogHeight="0.8"  valueEl="secrecyPrint.undertaker" textEl="receivePersonNames2"
									onlyFromValue="true" required="true"  buttonEl="readPerson"/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									批准人：
								</td>
								<td class="tbValue fl">
								<ui:multySelect  dialogWidth="0.9" dialogHeight="0.8"  valueEl="secrecyPrint.approver" textEl="receivePersonNames3"
									onlyFromValue="true" required="true"  buttonEl="readPerson2"/>
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