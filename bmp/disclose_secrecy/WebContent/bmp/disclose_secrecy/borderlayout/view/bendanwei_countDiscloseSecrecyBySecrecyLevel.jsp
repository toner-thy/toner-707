<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>保密要害部门统计(本单位)</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/pageNoButtonBar.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/resources/css/component.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/component_blue.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/resources/js/platform.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
				$ENV.onDomReady(function(){
				});
			});

			//导出
			function doExport(){
				var frm = $('excel_template_form');
				frm.action = "${ctx}/bmp/keySection/exportExcel_KeySectionCount.action";
				frm.submit();
			}

		</script>
		<style type="text/css">
			.pop_button_bar{}
		</style>
	</head>

	<body>

		<div class="body_content" style="height:600px;margin-top:-40px; "  >

			<div class="panel tMargin" style="overflow-y: auto;">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						泄密案件统计
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<a href="###" id="doExport"  onclick="doExport()" class="pop_button"><span>导出</span></a>
					</div>
				</div>
				<div class="panel_content panel_content_table" >
					<div class="eXtremeTable" >
						<s:if test="#request.countSectionList.size>0">
							<table id="countSectionList_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%">
								<tr>
									<td class="tableHeader" align="center">泄密案件密级</td>
									<td class="tableHeader" align="center">数量</td>
								</tr>
								<c:forEach items="${countSectionList}" var="countSection" varStatus="ifsStatus">
									<tr <c:if test="${ifsStatus.index % 2 ==0}">class="odd"</c:if>
									<c:if test="${ifsStatus.index % 2 != 0}">class="even"</c:if>>
										<td <c:if test="${fn:length(countSectionList)==ifsStatus.index+1}">style="font-weight:bold;"</c:if>>
											${countSection.option_text}
										</td>
										<td <c:if test="${fn:length(countSectionList)==ifsStatus.index+1}">style="font-weight:bold;"</c:if>>
											${countSection.fcount}
										</td>
									</tr>
								</c:forEach>
							</table>
						</s:if>
					</div>
				</div>
			</div>

			<br/>
			<div class="split_line"></div><!-- 分隔线 -->

			 <div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						严重违规案件统计
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table"><!-- 要害部位 -->
					<div class="eXtremeTable" >
						<s:if test="#request.countPartList.size>0">
							<table id="countPartList_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%">
								<tr>
									<td class="tableHeader" align="center">严重违规案件密级</td>
									<td class="tableHeader" align="center">数量</td>
								</tr>
								<c:forEach items="${countPartList}" var="countPart" varStatus="ifsStatus">
									<tr <c:if test="${ifsStatus.index % 2 ==0}">class="odd"</c:if><c:if test="${ifsStatus.index % 2 != 0}">class="even"</c:if>>
										<td <c:if test="${fn:length(countPartList)==ifsStatus.index+1}">style="font-weight:bold;"</c:if>>${countPart.option_text}</td>
										<td <c:if test="${fn:length(countPartList)==ifsStatus.index+1}">style="font-weight:bold;"</c:if>>${countPart.fcount}</td>
									</tr>
								</c:forEach>
							</table>
						</s:if>
					</div>
				</div>
			</div>

			<form action="" name="excel_template_form" id="excel_template_form" enctype="application/x-www-form-urlencoded">
			</form>
		</div>
	</body>
</html>