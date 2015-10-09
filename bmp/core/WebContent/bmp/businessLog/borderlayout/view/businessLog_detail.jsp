<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<title>操作日志详细内容</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- css -->
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/pageNoButtonBar.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />
		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<style type="text/css">
			.content_Table {
				margin-top:1px;
				color: #446586;
				width: 98%;
				background-color: #fff;
				border-spacing:0;
				border-collapse:collapse;
			}
		</style>

		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
				$ENV.onDomReady(function(){

				});
			});

		</script>

		<style type="text/css">
			.span_location{
				margin-left: 20px;
			}
		</style>
	</head>

	<body>
		<!-- 内容panel开始 -->
		<div class="panel">
			<div class="panel_header">
				<div class="panel_title panel_titleListIco">
					<span class="span_location">操作单位：${businessLog.operateOrgan.organName }</span>
					<span class="span_location">操作人员：${businessLog.operateUser.userInfo.name }</span>
					<span class="span_location">操作业务名称：${businessLog.businessName }</span>
					<span class="span_location">操作时间：<s:date name="#attr.businessLog.operateTime" format="yyyy-MM-dd HH:mm:ss"/></span>
				</div>
				<div class="panel_btn_bar pop_button_bar">
					<!-- 右侧按钮 -->
				</div>
			</div>
			<div class="panel_content panel_content_table">
				<s:if test="#request.businessLog.logContentSet.size>0">
					<table class="content_table" cellspacing="0" cellpadding="0" width="100%;">
						<tr height="36px;" style="font-weight: bolder;">
							<td align="center" width="5%">序号</td>
							<td align="center" width="10%">字段属性</td>
							<td align="center" width="10%">字段名称</td>
							<td align="center" width="15%">字段描述</td>
							<td align="center" width="30%">旧值</td>
							<td align="center" width="30%">新值</td>
						</tr>
						<c:forEach items="${businessLog.logContentSet }" var="logContent" varStatus="logIndex">
							<tr height="32px;">
								<td align="center">${logIndex.index +1}</td>
								<td>${logContent.tbkey }</td>
								<td>${logContent.filedName }</td>
								<td>${logContent.tbkeyDesc }</td>
								<td title="${logContent.tbvalue }">
									<c:if test="${fn:length(logContent.tbvalue) <= 30 }">${logContent.tbvalue }</c:if>
									<c:if test="${fn:length(logContent.tbvalue) > 30 }">${fn:substring(logContent.tbvalue,0,25)}...</c:if>
								</td>
								<td title="${logContent.currentTbvalue }">
									<c:if test="${fn:length(logContent.currentTbvalue) <= 30 }">${logContent.currentTbvalue }</c:if>
									<c:if test="${fn:length(logContent.currentTbvalue) > 30 }">${fn:substring(logContent.currentTbvalue,0,25)}...</c:if>
								</td>
							</tr>
						</c:forEach>
					</table>
				</s:if>
				<s:else>
					<u:noData text="当前暂无变化数据。"/>
				</s:else>
			</div>
		</div>
		<!-- 内容panel结束 -->
	</body>
</html>