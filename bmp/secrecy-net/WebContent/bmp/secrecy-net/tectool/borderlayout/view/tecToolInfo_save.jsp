<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="department" %>
<%@ taglib uri="http://www.cdthgk.com/tags/theme" prefix="theme" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">

		<title>填报技术手段情况统计表</title>

		<script src="${pageContext.request.contextPath }/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
		$ENV.loader.loadStyleSheet("${pageContext.request.contextPath}/platform/theme/<theme:theme module="department"/>/skin/<theme:skin module="department"/>/css/page.css");
		$ENV.loader.loadStyleSheet("${pageContext.request.contextPath}/resources/theme/<theme:theme module="department"/>/skin/<theme:skin module="department"/>/formcheck/css/formcheck.css");

		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/mootools/mootools-core-1.4.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/mootools/mootools-more-1.4.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/My97DatePicker/WdatePicker.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/SimpleUI/SimpleUI.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/formcheck/1.4/formcheck.js",function(){
			$ENV.onDomReady(function(){
				var formcheck = new FormCheck('form_save',{
					display:{
						showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
					},
					trimValue: true
				});

				$('btnSave').addEvent('click', function(){
					if (formcheck.isFormValid()) {
						if (window.confirm("确定保存填写内容")) {
							$('sub').click();
						}
					}
				});
			});
		});
		</script>
		<style type="text/css">
		html,body {padding: 0}
		body {overflow: auto;}

		.input_line {
			border: 0;border-bottom: 1px solid #000;
			width: 40px;
		}
		.input_line_other {
			border: 0;border-bottom: 1px solid #000;
			width: 80%;
		}
		.content_table td {
			padding-left: 10px;
			padding-right: 10px;
		}
		</style>

	</head>
	<body>
		<div class="panel">
			<!-- 头部 -->
			<div class="panel_header">
				<!-- 标题 -->
				<div class="panel_title panel_titleListIco">
				技术手段情况统计表
				</div>
			</div>
			<!-- 内容 -->
			<div class="panel_content panel_content_table">
				<form id="form_save" class="form"
					action="<s:url action="saveing" namespace="/bmp/pucha/tecToolInfo" includeParams="true"/>" method="post">
				<input type="hidden" name="tecToolInfo.tecToolInfoId" value="${tecToolInfo.tecToolInfoId }"/>
				<input type="submit" id="sub" value="sub" class="btn_23" style="display: none;"/>
				<table border="0" style="width: 100%;">
					<tr>
						<td align="left" style="padding-left: 20px;">单位：（盖章）&nbsp;&nbsp; ${tecToolInfo.reportOrgan.name}</td>
						<td align="center">填表人：&nbsp;&nbsp; ${tecToolInfo.reportUser.userInfo.name}</td>
						<td align="right" style="padding-right: 20px;">填报日期：&nbsp;&nbsp;<fmt:formatDate value="${tecToolInfo.reportDate}" pattern="yyyy-MM-dd"/></td>
					</tr>
				</table>
				<table class="content_table">
					<tbody>
						<tr>
							<td width="5%" align="center" rowspan="3">
								技术<br/>手段
							</td>
							<td width="15%" align="center">
								涉密计算机违规外联监控平台（个）
							</td>
							<td width="15%" align="center">
								重要单位接入口保密检查平台纳入监管单位数量（个）
							</td>
							<td width="15%" align="center">
								机关单位网站保密检查平台纳入监管门户网站数量（个）
							</td>
							<td width="50%" align="center">
								保密技术检查工具
							</td>
						</tr>
						<tr>
							<td align="center" rowspan="2">
								<input type="text" name="tecToolInfo.internetConnectPlatNum" value="${tecToolInfo.internetConnectPlatNum}" class="input_line validate['required','digit']">
							</td>
							<td align="center" rowspan="2">
								<input type="text" name="tecToolInfo.impOrgCheckNum" value="${tecToolInfo.impOrgCheckNum}" class="input_line validate['required','digit']">
							</td>
							<td align="center" rowspan="2">
								<input type="text" name="tecToolInfo.orgWebsiteCheckNum" value="${tecToolInfo.orgWebsiteCheckNum}" class="input_line validate['required','digit']">
							</td>
							<td align="center">
								<table style="width: 100%;" class="content_table">
									<tr>
										<td align="left" style="border: 0;text-align: left:">
											<input type="checkbox" id="toolComputer" name="tecToolInfo.toolComputer" value="true" <c:if test="${tecToolInfo.toolComputer}">checked="checked"</c:if>>
											<label for="toolComputer">计算机检查工具</label>
										</td>
										<td align="left" style="border: 0;text-align: left:">
											<input type="checkbox" id="toolNet" name="tecToolInfo.toolNet" value="true" <c:if test="${tecToolInfo.toolNet}">checked="checked"</c:if>>
											<label for="toolNet">网络检查工具</label>
										</td>
										<td align="left" style="border: 0;text-align: left:">
											<input type="checkbox" id="toolCockhorse" name="tecToolInfo.toolCockhorse" value="true" <c:if test="${tecToolInfo.toolCockhorse}">checked="checked"</c:if>>
											<label for="toolCockhorse">木马检查工具</label>
										</td>
									</tr>
									<tr>
										<td align="left" style="border: 0;text-align: left:">
											<input type="checkbox" id="toolNetTest" name="tecToolInfo.toolNetTest" value="true" <c:if test="${tecToolInfo.toolNetTest}">checked="checked"</c:if>>
											<label for="toolNetTest">网络评测工具</label>
										</td>
										<td align="left" style="border: 0;text-align: left:">
											<input type="checkbox" id="toolSignal" name="tecToolInfo.toolSignal" value="true" <c:if test="${tecToolInfo.toolSignal}">checked="checked"</c:if>>
											<label for="toolSignal">电磁信号检查工具</label>
										</td>
										<td align="left" style="border: 0;text-align: left:">
											<input type="checkbox" id="toolOther" name="tecToolInfo.toolOther" value="true" <c:if test="${tecToolInfo.toolOther}">checked="checked"</c:if>>
											<label for="toolOther">其他工具：</label><input type="text" name="tecToolInfo.toolOtherDesc" value="${tecToolInfo.toolOtherDesc}" class="validate['length[1000]'] input_line_other">
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td align="left">
								配备计算机检查工具<input type="text" name="tecToolInfo.toolComputerNum" value="${tecToolInfo.toolComputerNum}" class="input_line validate['required','digit']">套，
								网络检查工具<input type="text" name="tecToolInfo.toolNetNum" value="${tecToolInfo.toolNetNum}" class="input_line validate['required','digit']">套，
								木马检查工具<input type="text" name="tecToolInfo.toolCockhorseNum" value="${tecToolInfo.toolCockhorseNum}" class="input_line validate['required','digit']">套，
								网络评测工具<input type="text" name="tecToolInfo.toolNetTestNum" value="${tecToolInfo.toolNetTestNum}" class="input_line validate['required','digit']">套，
								电磁信号检查工具<input type="text" name="tecToolInfo.toolSignalNum" value="${tecToolInfo.toolSignalNum}" class="input_line validate['required','digit']">套
							</td>
						</tr>
					</tbody>
				</table>
				<!-- 右侧按钮 -->
				<div class="panel_btn_bar pop_button_bar" style="text-align: center;">
					<a id="btnSave" href="###" class="pop_button"><span>保存</span></a>
				</div>
				</form>
			</div>
		</div>
	</body>
</html>