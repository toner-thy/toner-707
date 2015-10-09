<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>新增保密委员会</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<s:actionmessage theme="messages"/>

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
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js",function(){

			});
			// 保存
			function doSave(){
				var questionLength = document.getElementById("question").value.length;
				var adviseLength = document.getElementById("advise").value.length;
				if( questionLength>2000 ){
					alert("小提示，[存在问题]的内容不能超过2000字符,请适当削减字符。");
				}else if( adviseLength>2000 ){
					alert("小提示，[对策和建议]的内容不能超过2000字符,请适当削减字符。");
				}else{
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
		<!-- 涉密人员panel开始 -->
		<div class="panel tMargin bMargin">
			<div class="panel_header">
				<div class="panel_title panel_titleListIco">
					新增区、县（市）网络保密管理情况意见建议汇总表
				</div>
			</div>

			<div class="panel_content panel_content_table">
				<form id="form_secrecyAdvice_add" action="<s:url namespace='/bmp/secrecyadvice' action='secrecyAdvice_adding.action' includeParams='true'/>" method="post">
					<table class="content_table st" width="100%">
						<tr>
							<td align="center" colspan="6">
								<b><font style="font-size: 20px;">区、县（市）网络保密管理情况意见建议汇总表</font></b>
							</td>
						</tr>
						<tr>
							<td align="right" width="20%;">
								单位：（盖章）
							</td>
							<td  width="15%;">
								<input type="hidden" id="id" name="secrecyAdvice.id" value="${secrecyAdvice.id }">
								<input type="text" id="organName" style="width: 255px;" name="secrecyAdvice.organ.organName" value="${secrecyAdvice.organ.name }" readonly="readonly">
								<input type="hidden" id="organId" name="secrecyAdvice.organ.organId" value="${secrecyAdvice.organ.id }">
							</td>
							<td class="tbLable fr" width="20%;">
								填表人：
							</td>
							<td width="15%">
								<input type="text" id="userName" style="width: 155px;" value="${secrecyAdvice.reportUser.userInfo.name}" readonly="readonly">
								<input type="hidden" id="userId" name="secrecyAdvice.reportUser.userId" value="${secrecyAdvice.reportUser.userId }">
							</td>
							<td class="tbLable fr" width="20%">
								填报日期：
							</td>
							<td>
								<input type="text" id="reportTime" name="secrecyAdvice.reportTime"
								value="<fmt:formatDate value="${secrecyAdvice.reportTime}" pattern="yyyy-MM-dd"/>" readonly="readonly">
							</td>
						</tr>
						<tr>
							<td align="center">
								网络保密管理工作中存<br>
									在的其他问题
							</td>
							<td class="tbValue fl" colspan="5">
								<textarea id="question" name="secrecyAdvice.question" style="width: 100%;height: 130px;">${secrecyAdvice.question }</textarea>
							</td>
						</tr>
						<tr>
							<td align="center">
								建立"自上而下、覆盖全<br>
								国网络保密监管体系"的<br>
									对策和建议
							</td>
							<td class="tbValue fl" colspan="5">
								<textarea id="advise" name="secrecyAdvice.advise" style="width: 100%;height: 130px;">${secrecyAdvice.advise }</textarea>
								<input type="hidden"  name="secrecyAdvice.createOrgan.organId" value="${secrecyAdvice.createOrgan.organId }">
								<input type="hidden"  name="secrecyAdvice.createDepartment.departmentId" value="${secrecyAdvice.createDepartment.departmentId }">
								<input type="hidden"  name="secrecyAdvice.createPerson.userId" value="${secrecyAdvice.createPerson.userId}">
								<input type="hidden"  name="secrecyAdvice.modifyPerson.userId" value="${secrecyAdvice.modifyPerson.userId}">
								<input type="hidden"  name="secrecyAdvice.createTime" value="${secrecyAdvice.createTime}">
								<input type="hidden"  name="secrecyAdvice.year" value="${secrecyAdvice.year}">
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
	</body>
</html>