<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<%@ taglib prefix="dept" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>机关网络建设、运行保密管理情况</title>

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
				window.location.href = "${ctx}/bmp/workbench-demo/secretNetwork/secretNetwork_list.jsp";
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
			<cp:start defaultTitle="机关网络建设、运行保密管理情况简介" ctx="${ctx}" icoPath="#">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','机关网络建设、运行保密管理情况简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于机关网络建设、运行保密管理情况
					</div>
					<div class="cpMsgContext">
						机关网络建设、运行保密管理情况 机关网络建设、运行保密管理情况 机关网络建设、运行保密管理情况
					</div>
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
				</cp:msg>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						机关网络建设、运行保密管理情况
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
									建成时间：
								</td>
								<td class="tbValue fl" colspan="2">
									<input type="text" readonly="readonly" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr">
									承建单位：
								</td>
								<td class="tbValue fl" colspan="2">
									<input type="text" />
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									审批部门：
								</td>
								<td class="tbValue fl" colspan="2">
									<dept:selectByOrgan textEl="departmentName" valueEl="departmentId" buttonEl="buttonSelect"></dept:selectByOrgan>
								</td>
								<td class="tbLable fr">
									审批时间：
								</td>
								<td class="tbValue fl" colspan="2">
									<input type="text" readonly="readonly" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									文号：
								</td>
								<td class="tbValue fl" colspan="5">
									<input type="text" />
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr style="text-align: center">
								<td>三员类型</td>
								<td>姓名</td>
								<td>职务</td>
								<td>学历</td>
								<td>毕业学校</td>
								<td>是否参加过计算机保密业务培训</td>
							</tr>
							<tr style="text-align: center">
								<td>网络管理员</td>
								<td><input type="text" /><span style="color:red;">&nbsp;&nbsp;*</span></td>
								<td><input type="text" /><span style="color:red;">&nbsp;&nbsp;*</span></td>
								<td nowrap="nowrap">
									<dictionary:select tableCode="person" fieldCode="learning_level" id="secrecyPerson.userInfo.learningLevel" name="secrecyPerson.userInfo.learningLevel" title="false" titleText="请选择" style="width: 130px;" optionValue="${secrecyPerson.userInfo.learningLevel}"></dictionary:select>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td><input type="text" /><span style="color:red;">&nbsp;&nbsp;*</span></td>
								<td>
									<s:select list="#{0:'请选择',1:'是',2:'否' }" theme="simple"></s:select>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr style="text-align: center">
								<td>安全管理员</td>
								<td><input type="text" /><span style="color:red;">&nbsp;&nbsp;*</span></td>
								<td><input type="text" /><span style="color:red;">&nbsp;&nbsp;*</span></td>
								<td nowrap="nowrap">
									<dictionary:select tableCode="person" fieldCode="learning_level" id="secrecyPerson.userInfo.learningLevel" name="secrecyPerson.userInfo.learningLevel" title="false" titleText="请选择" style="width: 130px;" optionValue="${secrecyPerson.userInfo.learningLevel}"></dictionary:select>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td><input type="text" /><span style="color:red;">&nbsp;&nbsp;*</span></td>
								<td>
									<s:select list="#{0:'请选择',1:'是',2:'否' }" theme="simple"></s:select>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr style="text-align: center">
								<td>安全审计员</td>
								<td><input type="text" /><span style="color:red;">&nbsp;&nbsp;*</span></td>
								<td><input type="text" /><span style="color:red;">&nbsp;&nbsp;*</span></td>
								<td nowrap="nowrap">
									<dictionary:select tableCode="person" fieldCode="learning_level" id="secrecyPerson.userInfo.learningLevel" name="secrecyPerson.userInfo.learningLevel" title="false" titleText="请选择" style="width: 130px;" optionValue="${secrecyPerson.userInfo.learningLevel}"></dictionary:select>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td><input type="text" /><span style="color:red;">&nbsp;&nbsp;*</span></td>
								<td>
									<s:select list="#{0:'请选择',1:'是',2:'否' }" theme="simple"></s:select>
									<span style="color:red;">&nbsp;&nbsp;*</span>
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