<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>操作日志详情</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- css -->
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/page.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/css/public/print/printPage.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js",function(){
				$ENV.onDomReady(function(){

				});
			});
		</script>
		<s:actionmessage theme="messages"/>
	</head>

	<body style="overflow-y:auto; ">
		<div class="panel tMargin" style="margin-top: -1px;">
			<!-- 保密要害部位panel开始 -->
			<div class="panel tMargin" style="margin-top: -1px;">
				<div class="panel_content panel_content_table">
					<s:if test="#request.infoLogList.size>0">
					<table class="content_table" cellspacing="0" cellpadding="0" width="100%;">
						<tr>
							<td class="tableHeader" align="center" width="20%">操作人单位</td>
							<td class="tableHeader" align="center" width="20%">操作人</td>
							<td class="tableHeader" align="center" width="20%">动作</td>
							<td class="tableHeader" align="center" width="20%">备注</td>
							<td class="tableHeader" align="center" width="20%">操作时间</td>
						</tr>
						<c:forEach items="${infoLogList }" var="infoLog">
							<tr>
								<td align="center" width="20%">${infoLog.operateOrgan.organName }</td>
								<td align="center" width="20%">${infoLog.operateUser.userName }</td>
								<td align="center" width="20%"><dictionary:text tableCode="info_report" fieldCode="info_operate_status" optionValue="${infoLog.operateStatus}"/></td>
								<td align="center" width="20%">${infoLog.operateDes }</td>
								<td align="center" width="20%"><fmt:formatDate value="${infoLog.operateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							</tr>
						</c:forEach>
					</table>
					</s:if>
					<s:else>
						暂无数据
					</s:else>
				</div>
			</div>
			<!-- 保密要害部位panel结束 -->
		</div>
	</body>
</html>