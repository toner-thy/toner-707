<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib prefix="organ" uri="http://www.cdthgk.com/tags/organization/organ"%>
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib prefix="sp" uri="http://www.cdthgk.com/tags/secrecyPerson/search"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>承担课题情况</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/FCKeditor/fckeditor.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js",function(){
				$ENV.onDomReady(function(){
					formcheck = new FormCheck('add_form',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
				});
			});

			function doSave(){
				if (formcheck.isFormValid(true)) {
						$('sub').click();
						$('sbm_button').style.display='none';
						$('sbm_button_hidden').style.display='';
						window.setTimeout("$('sbm_button').style.display=''",8000);
						window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
				 }
			}

			function doBackList(){
				if( confirm("确定退出吗？") ){
					window.location.href = "${ctx}/bmp/undertaketask/undertaketask_list.action?_ts="+ new Date().getTime();
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
					<a class="pop_button" href="javascript:doBackList();" id="back_button"><span>返回</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div id="body_content" class="body_content">
			<!-- 复合面板开始 -->
			<cp:start defaultTitle="承担课题情况简介" ctx="${ctx}" icoPath="#">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','承担课题情况简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于承担课题情况
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="annual_plan_description"> </cpc:tc>
					</div>
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
				</cp:msg>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						承担课题情况
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="add_form" action="<s:url namespace='/bmp/undertaketask' action='undertaketask_editing' includeParams='true'/>" method="post">
						<input type="hidden" name="undertaketask.undertaketaskId" value="${undertaketask.undertaketaskId }"/>
						<table class="content_table" width="100%">
							<tr>
								<td class="tbLable fr">
									课题（试点）名称：
								</td>
								<td class="tbValue fl">
									<input type="text" name="undertaketask.taskName" size="100" value="${undertaketask.taskName }" class="validate['required','length[100]']"/><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									承办单位：
								</td>
								<td class="tbValue fl">
									<organ:select textEl="undertaketask.undertakeOrgan.organName" valueEl="undertaketask.undertakeOrgan.organId" onlyFromValue="true"
										required="true" buttonEl="selectundertakeOrganName"
										text="${undertaketask.undertakeOrgan.organName}"
										value="${undertaketask.undertakeOrgan.organId}" />
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									下达单位：
								</td>
								<td class="tbValue fl">
									<organ:select textEl="undertaketask.releaseUnit.organName" valueEl="undertaketask.releaseUnit.organId" onlyFromValue="true"
										required="true" buttonEl="selectcheckOrganName"
										text="${undertaketask.releaseUnit.organName}"
										value="${undertaketask.releaseUnit.organId}" />
								</td>
							</tr>

							<tr>
								<td class="tbLable fr">课题负责人：</td>
								<td class="tbValue fl">
									<sp:selectSecrecyPerson required="true" onlyFromValue="false" styleClass="validate['length[32]']" buttonEl="secrecyPersonName"
									 valueEl="${undertaketask.projectLeader.userInfoId}" value="${undertaketask.projectLeader.userInfoId}"
									 textEl="${undertaketask.projectLeader.name}"  text="${undertaketask.projectLeader.name}">
									 </sp:selectSecrecyPerson>

								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									承办人：
								</td>
								<td class="tbValue fl">
									<ui:multySelectByOrgan textEl="undertaker_name"  valueEl="undertaketask.undertaker"
									textProperty="name" valueProperty="userInfoId" value="${undertaketask.undertakerList }"
									onlyFromValue="true" required="true" buttonEl="mulitySelectUI"></ui:multySelectByOrgan>
								</td>
							</tr>

							<tr>
								<td class="tbLable fr">
									完成时间：
								</td>
								<td class="tbValue fl">
									<input type="text" name="undertaketask.completeTime" readonly="readonly" class="Wdate validate['required']" value="${undertaketask.completeTime }" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									具体成效：
								</td>
								<td >
									<textarea rows="10" cols="150" style="width: 90%;" name="undertaketask.specificResults" class="validate['required','length[1000]']">${undertaketask.specificResults }</textarea><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									获奖情况：
								</td>
								<td >
									<textarea rows="10" cols="150" style="width: 90%;" name="undertaketask.awards" class="validate['required','length[2000]']">${undertaketask.awards}</textarea><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
						</table>
						<!-- 隐藏提交按钮 -->
						<div align="center">
							<input id="sub" value="sub" type="submit" style="display: none;"/>
						</div>
					</form>
					<div>
					<attach:upload uploadBehavior="bmpUploadBehavior" applyForm="add_form" applyName="secAttach" attachments="${attachmentList }" showTitle="false"/>
				 	</div>
				</div>
			</div>
		</div>

	</body>
</html>