<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="dep"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>涉密机算计统计(行政区划)</title>

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
				$ENV.onDomReady(function(){
				});
			});

			function doExportEstablish() {
				var districtCode;
				if('${param.districtCode}' == ''){
					districtCode = '${district.districtCode}';
				} else {
					districtCode = '${param.districtCode}';
				}
				window.location.href = "${ctx}/secrecysystem/statistics/export_secrecyComputerCount_District.action?districtCode=" + districtCode + "&_ts=" + $time();
			}

		</script>
	</head>

	<body>
		<div style="padding:5px;">
		<div class="panel">
			<div class="panel_header">
				<div class="panel_title panel_titleListIco">
					涉密计算机总数
				</div>
				<div class="panel_btn_bar pop_button_bar">
					<a class="pop_button" href="###" onclick="doExportEstablish()"><span>导 出</span></a>
				</div>
			</div>
			<div class="panel_content panel_content_table">
				<div class="eXtremeTable" >
					<table id="stat_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
						<tr>
							<td class="tableHeader" align="center" width="20%">类别</td>
							<td class="tableHeader" align="center" width="20%">绝密(百分比)</td>
							<td class="tableHeader" align="center" width="20%">机密(百分比)</td>
							<td class="tableHeader" align="center" width="20%">秘密(百分比)</td>
							<td class="tableHeader" align="center" width="20%">合计</td>
						</tr>
						<c:forEach items="${computerMap}" var="map" varStatus="ifsStatus">
							<tr <c:if test="${ifsStatus.index % 2 ==0}">class="odd"</c:if><c:if test="${ifsStatus.index % 2 != 0}">class="even"</c:if>>
								<td>${map.name}</td>
								<td>${map.level1} / <c:if test="${map.count == 0 }">0.00%</td></c:if><c:if test="${map.count != 0 }"><fmt:formatNumber value="${map.level1 / map.count}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
								<td>${map.level2} / <c:if test="${map.count == 0 }">0.00%</td></c:if><c:if test="${map.count != 0 }"><fmt:formatNumber value="${map.level2 / map.count}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
								<td>${map.level3} / <c:if test="${map.count == 0 }">0.00%</td></c:if><c:if test="${map.count != 0 }"><fmt:formatNumber value="${map.level3 / map.count}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
								<td>${map.count}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
		<div class="panel tMargin">
			<div class="panel_header">
				<div class="panel_title panel_titleListIco">
					是否安装保密技术防护专用系统（三合一）
				</div>
				<div class="panel_btn_bar pop_button_bar">

				</div>
			</div>
			<div class="panel_content panel_content_table">
				<div class="eXtremeTable" >
					<table id="stat_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
						<tr>
							<td class="tableHeader" align="center" width="20%">类别</td>
							<td class="tableHeader" align="center" width="20%">绝密(百分比)</td>
							<td class="tableHeader" align="center" width="20%">机密(百分比)</td>
							<td class="tableHeader" align="center" width="20%">秘密(百分比)</td>
							<td class="tableHeader" align="center" width="20%">合计</td>
						</tr>
						<c:forEach items="${threeMap}" var="map" varStatus="ifsStatus">
							<tr <c:if test="${ifsStatus.index % 2 ==0}">class="odd"</c:if><c:if test="${ifsStatus.index % 2 != 0}">class="even"</c:if>>
								<td>${map.name}</td>
								<td>${map.level1} / <c:if test="${map.count == 0 }">0.00%</td></c:if><c:if test="${map.count != 0 }"><fmt:formatNumber value="${map.level1 / map.count}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
								<td>${map.level2} / <c:if test="${map.count == 0 }">0.00%</td></c:if><c:if test="${map.count != 0 }"><fmt:formatNumber value="${map.level2 / map.count}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
								<td>${map.level3} / <c:if test="${map.count == 0 }">0.00%</td></c:if><c:if test="${map.count != 0 }"><fmt:formatNumber value="${map.level3 / map.count}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
								<td>${map.count}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
		<div class="panel tMargin">
			<div class="panel_header">
				<div class="panel_title panel_titleListIco">
					是否纳入违规外联监控系统
				</div>
				<div class="panel_btn_bar pop_button_bar">

				</div>
			</div>
			<div class="panel_content panel_content_table">
				<div class="eXtremeTable" >
					<table id="stat_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
						<tr>
							<td class="tableHeader" align="center" width="20%">类别</td>
							<td class="tableHeader" align="center" width="20%">绝密(百分比)</td>
							<td class="tableHeader" align="center" width="20%">机密(百分比)</td>
							<td class="tableHeader" align="center" width="20%">秘密(百分比)</td>
							<td class="tableHeader" align="center" width="20%">合计</td>
						</tr>
						<c:forEach items="${wailianMap}" var="map" varStatus="ifsStatus">
							<tr <c:if test="${ifsStatus.index % 2 ==0}">class="odd"</c:if><c:if test="${ifsStatus.index % 2 != 0}">class="even"</c:if>>
								<td>${map.name}</td>
								<td>${map.level1} / <c:if test="${map.count == 0 }">0.00%</td></c:if><c:if test="${map.count != 0 }"><fmt:formatNumber value="${map.level1 / map.count}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
								<td>${map.level2} / <c:if test="${map.count == 0 }">0.00%</td></c:if><c:if test="${map.count != 0 }"><fmt:formatNumber value="${map.level2 / map.count}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
								<td>${map.level3} / <c:if test="${map.count == 0 }">0.00%</td></c:if><c:if test="${map.count != 0 }"><fmt:formatNumber value="${map.level3 / map.count}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
								<td>${map.count}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
		</div>
	</body>
</html>