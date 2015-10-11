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
		<title>编辑保密涉密载体复印情况</title>

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
								if (!confirm('编辑成功，是否继续编辑？')){
									needReload2 = true;
									TabUtil.closeTab();
								}
							};
					formcheck = new FormCheck('add_form',{
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
				window.location.href="<s:url action="secrecyCopy_list"  includeParams="false"/>";
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
						新增保密涉密载体复印情况
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
				<form id="add_form" class="form"  action="<s:url namespace='/bmp/secrecyCopy' action='secrecyCopy_update' includeParams='true'/>" method="post" enctype="multipart/form-data">
					<table class="content_table">
							<tr>
								<td class="tbLable fr">
									文件名称：
								</td>
								<td class="tbValue fl" colspan="3">
									<input type="text" size="50" class="validate['required','length[100]']" name="secrecyCopy.name" value="${secrecyCopy.name}"/><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									文号：
								</td>
								<td class="tbValue fl">
									<input type="text" size="50" class="validate['required','length[100]']" name="secrecyCopy.docNumber" value="${secrecyCopy.docNumber}"/><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr">
									复制日期：
								</td>
								<td class="tbValue fl">
									<input type="text" readonly="readonly"
									name="secrecyCopy.date" value="${secrecyCopy.date}"
									class="Wdate validate['required']" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>

							</tr>
							<tr>

								<td class="tbLable fr">
									密级：
								</td>
								<td class="tbValue fl">
									<dictionary:select fieldCode="secrecy_level_thing" tableCode="bmp" id="secrecyCopy.secrecyLevel"
								                           name="secrecyCopy.secrecyLevel" style="width: 132px;"
								                           title="true" titleText="请选择" styleClass="validate['required']"
								                           optionValue="${secrecyCopy.secrecyLevel}"/>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr">
									承办人：
								</td>
								<td class="tbValue fl">
									<ui:selectByOrgan valueEl="secrecyCopy.undertaker.userInfoId" textEl="secrecyCopy.undertaker.name"
									text="${secrecyCopy.undertaker.name }" value="${secrecyCopy.undertaker.userInfoId }" required="true"
									onlyFromValue="true" styleClass="validate['length[32]']" buttonEl="butPerson"   />
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									每份页数：
								</td>
								<td class="tbValue fl">
									<input type="text" name="secrecyCopy.pageNo" class="validate['required','length[20]']" value="${secrecyCopy.pageNo}" size="50"/><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr">
									申请人：
								</td>
								<td class="tbValue fl">
									<ui:selectByOrgan valueEl="secrecyCopy.applicant.userInfoId" textEl="secrecyCopy.applicant.name"
									text="${secrecyCopy.applicant.name }" value="${secrecyCopy.applicant.userInfoId }" required="true"
									onlyFromValue="true" styleClass="validate['length[32]']" buttonEl="butPerson2"   />
								</td>

							</tr>
							<tr>
							    <td class="tbLable fr">
									份数：
								</td>
								<td class="tbValue fl">
									<input type="text" size="50" class="validate['required','length[11]']" name="secrecyCopy.number" value="${secrecyCopy.number}"/><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>

								<td class="tbLable fr">
									文件制发单位：
								</td>
								<td class="tbValue fl">
								    <organ:select valueEl="secrecyCopy.didOrgan.organId" textEl="secrecyCopy.didOrgan.organName"
									 value="${secrecyCopy.didOrgan.organId}" required="true"
									onlyFromValue="true" styleClass="validate['length[32]']" buttonEl="butPerson3" />
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									申请部门：
								</td>
								<td class="tbValue fl">
									 <dep:selectByOrgan textEl="secrecyCopy.draftingDep.departmentName" valueEl="secrecyCopy.draftingDep.departmentId"required="true"
								    text="${secrecyCopy.draftingDep.departmentName}" value="${secrecyCopy.draftingDep.departmentId}"
									onlyFromValue="true" styleClass="validate['required']" buttonEl="butPerson4"   />
								</td>
								<td class="tbLable fr">
									批准人：
								</td>
								<td class="tbValue fl">
									<ui:selectByOrgan valueEl="secrecyCopy.approver.userInfoId" textEl="secrecyCopy.approver.name"
									 value="${secrecyCopy.approver.userInfoId }" required="true"
									onlyFromValue="true" styleClass="validate['length[32]']" buttonEl="butPerson11" />
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									用途：
								</td>
								<td class="tbValue fl" colspan="3">
									<input type="text" name="secrecyCopy.usePlace" value="${secrecyCopy.usePlace}" size="50"/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									备注：
								</td>
								<td class="tbValue fl" colspan="3">
								 <textarea rows="6" name="secrecyCopy.description" cols="100">${secrecyCopy.description}</textarea>
								</td>
							</tr>
					</table>
						<input type="hidden" id="secrecyCopy.id" name='secrecyCopy.id' value="${secrecyCopy.id}" class="btn_23" style="display: none;"/>
						<input type="submit" id="sub" value="sub" class="btn_23" style="display: none;"/>
				</form>
				<div>
					<attach:upload uploadBehavior="bmpUploadBehavior" applyForm="add_form" applyName="secAttach" attachments="${attachmentList }" showTitle="false"/>
				 </div>
			</div>
			</div>
		</div>
	</body>
</html>