<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib prefix="organ" uri="http://www.cdthgk.com/tags/organization/organ"%>
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<title>编辑涉密涉外活动记录</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

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
					formcheck=new FormCheck('form_externalPidgin_update',{
						display:{
							showErrors:1
						},
						trimValue: true
					});
				});
			});
			var needReload = ${needReload};
			var needReload2 = false;
			function backList(){
				window.location.href="<s:url action="externalPidgin_list" includeParams="false"/>";
		 	}
			function check(){
				if($('externalPidgin.startDate').value > $('externalPidgin.endDate').value){
					alert("有效起始日期不能大于有效结束日期。");
					return false;
				}else{
					return true;
				}
			}

			function doSave123(){
				if (formcheck.isFormValid(true)) {
					if($('externalPidgin.startDate').value > $('externalPidgin.endDate').value){
						alert("有效起始日期不能大于有效结束日期。");
						return false;
					}else{
						$('sub').click();
						$('sbm_button').style.display='none';
						$('sbm_button_hidden').style.display='';
						window.setTimeout("$('sbm_button').style.display=''",8000);
						window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
					}
				}
			}

			function doBack(){
			 	TabUtil.closeTab();
			}
		</script>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="javascript:void();" id="sbm_button_hidden" style="display:none;"><span>保存中...</span></a>
					<a class="pop_button" href="javascript:doSave123();" id="sbm_button"><span>保 存</span></a>
					<a class="pop_button" href="javascript:doBack();"><span>返回列表</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>
		<div class="body_content">
			<!-- 内容panel开始 -->
			<div class="panel">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						编辑涉密涉外活动记录
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="form_externalPidgin_update" class="form" action="<s:url action="externalPidgin_update" includeParams="true"/>" method="post" enctype="multipart/form-data" >
						<!-- 隐藏域 -->
						<input type="hidden" value="${externalPidgin.externalPidginId }" name="externalPidgin.externalPidginId" id="externalPidgin.externalPidginId" />
						<table class="content_table">
							<tr height="36px;">
								<td align="right" width="18%">
									活动种类：
								</td>
								<td>
									<input name="externalPidgin.externalPidginType" id="externalPidgin.externalPidginType" type="text" value="${externalPidgin.externalPidginType}" class="validate['required','length[20]']" /><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td align="right" width="20%">
									承办人员：
								</td>
								<td>
									<input name="externalPidgin.undertaker" id="externalPidgin.undertaker" type="text" value="${externalPidgin.undertaker}" class="validate['length[50]']" />
								</td>
							</tr>
							<tr height="36px;">
								<td align="right">
									开始时间：
								</td>
								<td>
									<input type="text" name="externalPidgin.startDate" value="<s:date format="yyyy-MM-dd" name="externalPidgin.startDate"/>"class="Wdate validate['required']" id="externalPidgin.startDate" onFocus="WdatePicker();" /><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td align="right">
									结束时间：
								</td>
								<td>
									<input type="text" name="externalPidgin.endDate" value="<s:date format="yyyy-MM-dd" name="externalPidgin.endDate"/>"class="Wdate validate['required']" id="externalPidgin.endDate" onFocus="WdatePicker()" /><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr height="36px;">
								<td align="right">
									 保密责任人：
								</td>
								<td>
									<input name="externalPidgin.secrecyDutier" id="externalPidgin.secrecyDutier" type="text" value="${externalPidgin.secrecyDutier}" class="validate['length[50]']" />
								</td>
								<td align="right">
									责任人职务：
								</td>
								<td colspan="3">
									<input name="externalPidgin.dutierHeadship" id="externalPidgin.dutierHeadship" type="text" value="${externalPidgin.dutierHeadship}" class="validate['length[50]']" />
								</td>
							</tr>
							<tr height="36px;">
								<td align="right">
									活动名称：
								</td>
								<td>
									<input name="externalPidgin.eternalPidginName" size="50" id="externalPidgin.eternalPidginName" type="text" value="${externalPidgin.eternalPidginName}" class="validate['required','length[60]']" /><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td align="right">
									密 级：
								</td>
								<td>
									<dictionary:select tableCode="bmp"  fieldCode="secrecy_level_externalpidgin" optionValue="${externalPidgin.secrecyLevel}" name="externalPidgin.secrecyLevel" style="width:42%"/>
								</td>
							</tr>
							<tr height="36px;">
								<td align="right">
									地 点：
								</td>
								<td>
									<input name="externalPidgin.place" size="50" id="externalPidgin.place" type="text"
										value="${externalPidgin.place}" class="validate['length[100]']" >
								</td>
								<td align="right">
									关键词：
								</td>
								<td>
									<input name="externalPidgin.keyWord" style="width: 90%" id="externalPidgin.keyWord" type="text"
										value="${externalPidgin.keyWord}" class="validate['required','length[50]']" ><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr height="36px;">
								<td align="right">
									主办单位：
								</td>
								<td colspan="3">
									<input name="externalPidgin.mainOrgan" size="50" id="externalPidgin.mainOrgan" type="text"
										value="${externalPidgin.mainOrgan}" class="validate['required','length[100]']" ><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td align="right">
									协办单位：
								</td>
								<td colspan="3" height="80px;" valign="top">
									<textarea name="externalPidgin.aidanceOrgan" style="width:95%;height:80px;">${externalPidgin.aidanceOrgan}</textarea>
								</td>
							</tr>
							<tr >
								<td align="right" valign="middle">
									内 容：
								</td>
								<td colspan="3" height="100px;" valign="top">
									<textarea name="externalPidgin.content" style="width:95%;height:100px;">${externalPidgin.content}</textarea>
								</td>
							</tr>
							<tr >
								<td align="right">
									活动保密预案：
								</td>
								<td colspan="3" height="100px;" valign="top">
									<textarea name="externalPidgin.plan" style="width:95%;height:100px;" class="textarea validate['length[2000]']">${externalPidgin.plan}</textarea>
								</td>
							</tr>
						</table>
						<!-- 隐藏按钮 -->
						<div align="center">
							<input name="sub" id="sub" value="sub" type="submit" style="display: none;" />
						</div>
					</form>
					<!-- 附件上传开始 -->
					<table class="content_table st" id="table_externalPidgin_edit" width="100%">
						<tr>
							<td class="tbLable fr">
								附件上传：
							</td>
							<td class="fl" colspan="3">
								<attach:upload uploadBehavior="bmpUploadBehavior" applyForm="form_externalPidgin_update" hostId="${externalPidgin.externalPidginId }" applyName="attachments"  titleText="附件上传" limit="1"/>
							</td>
						</tr>
					</table>
					<!-- 附件上传结束 -->
				</div>
			</div>
			<!-- 内容panel开始 -->
		</div>
	</body>
</html>