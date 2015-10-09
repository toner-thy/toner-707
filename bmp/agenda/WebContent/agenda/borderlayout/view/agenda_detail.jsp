<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>日程详情</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

        <script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
				$ENV.onDomReady(function(){

				});
			});

			function doBack(){
				window.close();
			}
		</script>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar"></div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_close" href="###" onclick="javascript:environment.dialog.close();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div class="body_content">
			<!-- 内容panel开始 -->
		  	<div class="panel">
				<!-- 头部 -->
				<div class="panel_header">
					<!-- 标题 -->
					<div class="panel_title panel_titleListIco">
						日程安排
					</div>
					<!-- 右侧按钮 -->
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
			    	<table class="content_table" style="font-family: Arial, 'Times New Roman' !important;width: 99%">
						<tr>
							<td class="tbValue fc" colspan="4" style="font-size: 16px;">
								<c:out value="${agenda.agendaTitle}" escapeXml="true" />
							</td>
						</tr>
						<tr>
							<td class="tbValue fc" colspan="4">
								日程时间：<s:date name="#attr.agenda.startTime" format="MM/dd HH:mm"/> ~ <s:date name="#attr.agenda.endTime" format="MM/dd HH:mm"/>
							</td>
						</tr>
						<tr>
							<td class="tbValue fl" colspan="4">
								<s:if test="#request.agenda.agendaContent==null || #request.agenda.agendaContent==''">
									<textarea rows="" cols="" style="width: 100%;height: 338px;border: 0px;color: #ddd;" readonly="readonly">日程中暂未安排事项</textarea>
								</s:if>
								<s:else>
									<textarea rows="" cols="" style="width: 100%;height: 338px;border: 0px;" readonly="readonly"><c:out value="${agenda.agendaContent}" escapeXml="true"/></textarea>
								</s:else>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>