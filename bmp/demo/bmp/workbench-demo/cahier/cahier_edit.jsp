<%@ page language="java" pageEncoding="utf-8" import="java.lang.String,java.util.Collection,java.util.List,java.util.ArrayList,com.cdthgk.platform.organization.userinfo.domain.UserInfo"%>

<%@page isELIgnored="false" %>

<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>保密委员会</title>

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
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js",function(){
				$ENV.onDomReady(function(){
					new FormCheck('add_form',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
				});
			});

			function doSave(){
				window.location.href = "${ctx}/bmp/workbench-demo/cahier/cahier_list.jsp";
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
			<cp:start defaultTitle="会议记录简介" ctx="${ctx}" icoPath="#">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','会议记录简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于会议记录
					</div>
					<div class="cpMsgContext">
						会议记录 会议记录 会议记录
					</div>
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
				</cp:msg>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						会议记录
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="add_form" action="<s:url namespace='/secrecyorganization/secrecyoffice' action='secrecyOffice_adding' includeParams='true'/>" method="post">
						<table class="content_table" width="100%">
							<tr>
								<td class="tbLable fr">
									会议议题：
								</td>
								<td class="tbValue fl">
									<input type="text" size="50" value="关于XXX的讨论会议"/><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									开会时间：
								</td>
								<td class="tbValue fl">
									<input type="text" readonly="readonly" class="Wdate" value="2014-03-03" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									主持人：
								</td>
								<td class="tbValue fl">
									<ui:selectByOrgan valueEl="keySection.principal.userInfoId" textEl="keySection.principal.name"
									text="段智兴" value="xxx" required="true"
									onlyFromValue="false" styleClass="validate['length[32]']" buttonEl="butPerson"   />
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									记录人：
								</td>
								<td class="tbValue fl">
									<ui:selectByOrgan valueEl="keySection.principal.userInfoId" textEl="keySection.principal.name"
									text="霍峻" value="yyy" required="true"
									onlyFromValue="false" styleClass="validate['length[32]']" buttonEl="butPerson1"   />
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									出席人员：
								</td>
								<td class="tbValue fl">
									<%
										Collection<Object> uiList = new ArrayList();
										UserInfo ui = new UserInfo();
										ui.setUserInfoId("yyy");
										ui.setName("庞德");
										uiList.add(ui);
										request.setAttribute("uiList", uiList);
									%>
									<ui:multySelect valueEl="userInfoId" textEl="name"
									required="true" value="${uiList }" textProperty="name" valueProperty="userInfoId"
									onlyFromValue="false" styleClass="validate['length[32]']" buttonEl="butPerson2" />
								</td>
							</tr>
							<tr>
								<td class="tbLable fr" style="vertical-align: top;">
									备注：
								</td>
								<td class="tbValue fl">
									<textarea rows="10" cols="120">会议讨论了xXXX，会议的结论是。。。。</textarea>
								</td>
							</tr>
						</table>

						<!-- 隐藏提交按钮 -->
						<div align="center">
							<input id="sub" value="sub" type="submit" style="display: none;"/>
						</div>
					</form>
				</div>
			</div>
		</div>

	</body>
</html>